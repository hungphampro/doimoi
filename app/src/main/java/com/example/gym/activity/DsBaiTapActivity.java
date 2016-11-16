package com.example.gym.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.adapter.DividerItemDecoration;
import com.example.gym.adapter.DsBaitap_Adapter;
import com.example.gym.database.RealmController;
import com.example.gym.model.Exercise;
import com.example.gym.model.ExerciseFollowDay;
import com.example.gym.model.VDetailExerciseForDay;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import io.realm.Realm;

/**
 * Created by root on 10/11/2016.
 */

public class DsBaiTapActivity extends AppCompatActivity {
    RecyclerView mRecycleView;
    ImageView mStart;
    int mNumber;
    ArrayList<VDetailExerciseForDay> mExerciseTodayList;
    Realm mRealm;
    int listExerciseFollowDayId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsach_baitap);
        mRecycleView= (RecyclerView) findViewById(R.id.recycleview);
        mStart= (ImageView) findViewById(R.id.start);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        final Intent i=getIntent();
        listExerciseFollowDayId=i.getIntExtra("listExerciseFollowDayId",0);
        mRealm = RealmController.with(this).getRealm();
        if (mRealm.where(ExerciseFollowDay.class).equalTo("listExerciseFollowDayId", listExerciseFollowDayId).findFirst() == null) {
            initializationDatabase();
        }else{
            mExerciseTodayList=new ArrayList<>(mRealm.where(VDetailExerciseForDay.class).equalTo("listExerciseFollowDayId",listExerciseFollowDayId).findAll());
        }
        DsBaitap_Adapter mDsBaitap_adapter=new DsBaitap_Adapter(mExerciseTodayList,this);
        mRecycleView.setAdapter(mDsBaitap_adapter);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(DsBaiTapActivity.this,DetailExerciseActivity.class);
                intent.putExtra("listExerciseFollowDayId",listExerciseFollowDayId);
                startActivity(intent);*/
                EventBus.getDefault().postSticky(mExerciseTodayList);
                Intent intent=new Intent(DsBaiTapActivity.this,DetailExerciseActivity.class);
                startActivity(intent);
            }
        });
        mRecycleView.addItemDecoration(new DividerItemDecoration(mRecycleView.getContext(),R.drawable.custom_item_nav));
    }
    public void initializationDatabase() {
        mNumber = mRealm.where(ExerciseFollowDay.class).findAll().size();
        ExerciseFollowDay exerciseForToday = new ExerciseFollowDay(mNumber + 1, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        listExerciseFollowDayId=mNumber+1;
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
