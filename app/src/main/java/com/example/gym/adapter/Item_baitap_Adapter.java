package com.example.gym.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.activity.DsBaiTapActivity;
import com.example.gym.database.RealmController;
import com.example.gym.model.Exercise;
import com.example.gym.model.ExerciseFollowDay;
import com.example.gym.model.User;
import com.example.gym.model.VDetailExerciseForDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import io.realm.Realm;

/**
 * Created by root on 10/11/2016.
 */

public class Item_baitap_Adapter extends RecyclerView.Adapter<Item_baitap_Adapter.viewHolder> {
     ArrayList<String> mNgayList;
     Context mContext;
     Realm mRealm;
     int mNumber;
    public Item_baitap_Adapter(ArrayList<String> ngayList, Context context){
        mNgayList=ngayList;
        mContext=context;
        mRealm= RealmController.with(mContext).getRealm();
        mNumber=0;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_baitap_fragment, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
              holder.ngay.setText(mNgayList.get(position));
              Date date=mRealm.where(User.class).findFirst().getDate();
              Date today=new Date();
              String chuyen=new SimpleDateFormat("MM-dd-yyyy").format(date);
              System.out.println("co mot chi tiet tau muon biet:"+chuyen);
              String chuyentiep=new SimpleDateFormat("MM-dd-yyyy").format(today);
                try {
                    Date ngaydangki=new SimpleDateFormat("MM-dd-yyyy").parse(chuyen);
                    date=ngaydangki;
                    System.out.println("toi muon biet ngay dang ki:"+date.getTime());
                    Date ngayhomnay=new SimpleDateFormat("MM-dd-yyyy").parse(chuyentiep);
                    today=ngayhomnay;
                    System.out.println("toi muon biet ngay hom nay:"+today.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
              int days = (int)( (today.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
              System.out.println("the thi dayS:"+days);
              Calendar c = Calendar.getInstance();
              for(int i=0;i<=days;i++){
                  c.setTime(date);
                  c.add(Calendar.DATE, i);
                  Date datemoi = c.getTime();
                  ExerciseFollowDay e=mRealm.where(ExerciseFollowDay.class).equalTo("date",new SimpleDateFormat("dd-MM-yyyy").format(datemoi)).findFirst();
                  if(e==null){
                        initializationDatabase(datemoi);
                  }
              }
              mNumber= mRealm.where(ExerciseFollowDay.class).findAll().size();
               ArrayList<ExerciseFollowDay> mExerciseFollowDaymoi=new ArrayList<>(mRealm.where(ExerciseFollowDay.class).findAll());
        for(int i=0;i<mExerciseFollowDaymoi.size();i++){
                 ArrayList<VDetailExerciseForDay> mVDetailExerciseForDays=new ArrayList<>(mRealm.where(VDetailExerciseForDay.class).equalTo("listExerciseFollowDayId",mExerciseFollowDaymoi.get(i).getListExerciseFollowDayId()).findAll());
                 int j=0;

                 for(;j<mVDetailExerciseForDays.size();j++){
                     if(!mVDetailExerciseForDays.get(j).isDone()) break;
                 }
                 if(j<mVDetailExerciseForDays.size()) holder.check.setVisibility(View.GONE);
                 else holder.check.setVisibility(View.VISIBLE);
             }
             if(position>=mNumber) holder.check.setVisibility(View.GONE);
             else
             if(position<mNumber){
                 holder.rowchinh.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent i=new Intent(mContext, DsBaiTapActivity.class);
                         i.putExtra("listExerciseFollowDayId",position+1);
                         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                         mContext.startActivity(i);

                     }
                 });
             }
    }

    @Override
    public int getItemCount() {
        return mNgayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public ImageView check;
        public TextView ngay;
        public ImageView row;
        public RelativeLayout rowchinh;
        public viewHolder(View itemView) {
            super(itemView);
            check= (ImageView) itemView.findViewById(R.id.check);
            ngay= (TextView) itemView.findViewById(R.id.ngay);
            row=(ImageView) itemView.findViewById(R.id.row);
            rowchinh=(RelativeLayout) itemView.findViewById(R.id.rowchinh);
        }
    }

    public void initializationDatabase(Date date) {
        mNumber = mRealm.where(ExerciseFollowDay.class).findAll().size();
        ExerciseFollowDay exerciseForToday = new ExerciseFollowDay(mNumber + 1, new SimpleDateFormat("dd-MM-yyyy").format(date));
        ArrayList<VDetailExerciseForDay>mExerciseTodayList = new ArrayList<>();
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
