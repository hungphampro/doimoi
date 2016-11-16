package com.example.gym.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gym.R;
import com.example.gym.activity.DetailExerciseActivity;
import com.example.gym.adapter.ExerciseTodayAdapter;
import com.example.gym.adapter.RecyclerItemClickListener;
import com.example.gym.database.RealmController;
import com.example.gym.model.Exercise;
import com.example.gym.model.ExerciseFollowDay;
import com.example.gym.model.VDetailExerciseForDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import io.realm.Realm;

/**
 * Created by DefaultAccount on 10/9/2016.
 */
public class ExerciseTodayFragment extends Fragment {
    private int mNumber;
    private Realm mRealm;
    private ExerciseTodayAdapter mExerciseTodayAdapter;
    private ArrayList<VDetailExerciseForDay> mExerciseTodayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
       View view=LayoutInflater.from(container.getContext()).inflate(R.layout.baitap_fragment,container,false);
        mRealm = RealmController.with(this).getRealm();
        if (mRealm.where(ExerciseFollowDay.class).equalTo("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date())).findFirst() == null) {
            initializationDatabase();
        }else{
             ExerciseFollowDay exerciseFollowDay=mRealm.where(ExerciseFollowDay.class).equalTo("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date())).findFirst();
            mExerciseTodayList=new ArrayList<>(mRealm.where(VDetailExerciseForDay.class).equalTo("listExerciseFollowDayId",exerciseFollowDay.getListExerciseFollowDayId()).findAll());
        }
        RecyclerView rc = (RecyclerView) view.findViewById(R.id.recycleview);
        rc.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));

        mExerciseTodayAdapter = new ExerciseTodayAdapter(container.getContext(), mExerciseTodayList);
        rc.setAdapter(mExerciseTodayAdapter);
        rc.setItemAnimator(new DefaultItemAnimator());
        rc.addOnItemTouchListener(new RecyclerItemClickListener(container.getContext(), rc, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(container.getContext(), DetailExerciseActivity.class);
                Bundle bundle = new Bundle();

                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

        }));
        return view;
    }
    public void initializationDatabase() {
        mNumber = mRealm.where(ExerciseFollowDay.class).findAll().size();
        ExerciseFollowDay exerciseForToday = new ExerciseFollowDay(mNumber + 1, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        mExerciseTodayList = new ArrayList<>();
        mRealm.beginTransaction();
        mRealm.copyToRealm(exerciseForToday);
        mRealm.commitTransaction();
        Random r = new Random();
        if (mNumber >= 1) {
            ArrayList<VDetailExerciseForDay> detailExerciseListYesterday = new ArrayList<>(mRealm.where(VDetailExerciseForDay.class).equalTo("listExerciseFollowDayId", mNumber).findAll());
            ArrayList<Exercise> exerciseListChoiceToday = new ArrayList<>(mRealm.where(Exercise.class).findAll());
            for (int i = 0; i < detailExerciseListYesterday.size(); i++) {
                Exercise e=mRealm.where(Exercise.class).equalTo("exerciseId",detailExerciseListYesterday.get(i).getExerciseId()).findFirst();
                exerciseListChoiceToday.remove(e);
            }
            for (int i = 0; i < 6; i++) {
                int n = r.nextInt(exerciseListChoiceToday.size() - 1);
                VDetailExerciseForDay detailExerciseForToday = new VDetailExerciseForDay(exerciseListChoiceToday.get(n).getExerciseId(), mNumber + 1,false);
                mRealm.beginTransaction();
                mRealm.copyToRealm(detailExerciseForToday);
                mRealm.commitTransaction();
                mExerciseTodayList.add(detailExerciseForToday);
                exerciseListChoiceToday.remove(exerciseListChoiceToday.get(n));
            }
        } else {
            ArrayList<Exercise> allExerciseHaveDatabase = new ArrayList<>(mRealm.where(Exercise.class).findAll());
            for (int i = 0; i < 6; i++) {
                int n = r.nextInt(allExerciseHaveDatabase.size() - 1);
                VDetailExerciseForDay detailExerciseForToday = new VDetailExerciseForDay(allExerciseHaveDatabase.get(n).getExerciseId(), 1,false);
                mRealm.beginTransaction();
                mRealm.copyToRealm(detailExerciseForToday);
                mRealm.commitTransaction();
                mExerciseTodayList.add(detailExerciseForToday);
                allExerciseHaveDatabase.remove(allExerciseHaveDatabase.get(n));
            }
        }
    }
}
