package com.example.gym.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.activity.BmiActivity;
import com.example.gym.database.RealmController;
import com.example.gym.model.ExerciseFollowDay;
import com.example.gym.model.VDetailExerciseForDay;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by root on 09/11/2016.
 */

public class KeHoachFragment extends Fragment {
    Realm realm;
    ImageView[] done=new ImageView[30];
    TextView[] text=new TextView[30];
    int[] giatri=new int[]{R.id.ngay1,R.id.ngay2,R.id.ngay3,R.id.ngay4,R.id.ngay5,R.id.ngay6,R.id.ngay7,R.id.ngay8,
            R.id.ngay9,R.id.ngay10,R.id.ngay11,R.id.ngay12,R.id.ngay13,R.id.ngay14,R.id.ngay15,R.id.ngay16,R.id.ngay17,R.id.ngay18,R.id.ngay19,
            R.id.ngay20,R.id.ngay21,R.id.ngay22,R.id.ngay23,R.id.ngay24,R.id.ngay25,R.id.ngay26,R.id.ngay27,R.id.ngay28,R.id.ngay29,R.id.ngay30};
    int[] label=new int[]{R.id.textngay1,R.id.textngay2,R.id.textngay3,R.id.textngay4,R.id.textngay5,R.id.textngay6,R.id.textngay7,R.id.textngay8,R.id.textngay9,
            R.id.textngay10,R.id.textngay11,R.id.textngay12,R.id.textngay13,R.id.textngay14,R.id.textngay15,R.id.textngay16,R.id.textngay17,R.id.textngay18,R.id.textngay19,
            R.id.textngay20,R.id.textngay21,R.id.textngay22,R.id.textngay23,R.id.textngay24,R.id.textngay25,R.id.textngay26,R.id.textngay27,R.id.textngay28,R.id.textngay29,R.id.textngay30};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int t=0;
        View view = inflater.inflate(R.layout.kehoach_fragment, container, false);
        TextView conlai= (TextView) view.findViewById(R.id.conlai);
        TextView hoanthanh= (TextView) view.findViewById(R.id.hoanthanh);
        Button resetIbm= (Button) view.findViewById(R.id.resetIbm);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView back=(ImageView) toolbar.findViewById(R.id.back);
        back.setVisibility(View.GONE);
        TextView textView= (TextView) toolbar.findViewById(R.id.title);
        textView.setText("Kế Hoạch");
        final TextView action=(TextView) toolbar.findViewById(R.id.action);
        action.setVisibility(View.GONE);
        final TextView actionLeft=(TextView) toolbar.findViewById(R.id.actionleft);
        actionLeft.setVisibility(View.GONE);
        final  ImageView imageAction=(ImageView) toolbar.findViewById(R.id.imageaction);
        imageAction.setVisibility(View.GONE);
        for (int i = 0; i < done.length; i++) {
            done[i] = (ImageView) view.findViewById(giatri[i]);
        }
        for (int i = 0; i < label.length; i++) {
            text[i] = (TextView) view.findViewById(label[i]);
        }
        realm = RealmController.with(this).getRealm();
        ArrayList<ExerciseFollowDay> mExerciseFollowDays = new ArrayList<>(realm.where(ExerciseFollowDay.class).findAll());
        for (int i = 0; i < mExerciseFollowDays.size(); i++) {
            ArrayList<VDetailExerciseForDay> mVDetailExerciseForDays=new ArrayList<>(realm.where(VDetailExerciseForDay.class).equalTo("listExerciseFollowDayId",mExerciseFollowDays.get(i).getListExerciseFollowDayId()).findAll());
            int j = 0;
            for (; j < mVDetailExerciseForDays.size(); j++) {
                if (!mVDetailExerciseForDays.get(j).isDone()) break;
            }
            if (j == mVDetailExerciseForDays.size()) {
                t++;
                done[i].setImageResource(R.drawable.check);
            }
            else {
                done[i].setImageResource(R.drawable.oval);
                text[i].setVisibility(View.VISIBLE);
                text[i].setText(i + "");
            }

        }
        for(int i=mExerciseFollowDays.size();i<done.length;i++){
            done[i].setImageResource(R.drawable.oval);
            text[i].setVisibility(View.VISIBLE);
            text[i].setText((i+1) + "");
        }
        System.out.println("toi muon biet gia tri cua t:"+t);
        conlai.setText(conlai.getText()+":"+(30-t)+" ngày");
        hoanthanh.setText(hoanthanh.getText()+":"+t+" ngày");
        resetIbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), BmiActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}
