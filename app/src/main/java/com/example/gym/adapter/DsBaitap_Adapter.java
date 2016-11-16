package com.example.gym.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.database.RealmController;
import com.example.gym.model.Exercise;
import com.example.gym.model.VDetailExerciseForDay;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by root on 10/11/2016.
 */

public class DsBaitap_Adapter extends RecyclerView.Adapter<DsBaitap_Adapter.viewHolder> {
    Context mContext;
    ArrayList<VDetailExerciseForDay> mExerciseArrayList;

    public DsBaitap_Adapter(ArrayList<VDetailExerciseForDay> exerciseArrayList,Context context){
        mContext=context;
        mExerciseArrayList=exerciseArrayList;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dsach_baitap, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Realm realm= RealmController.with(mContext).getRealm();
           Exercise exercise=realm.where(Exercise.class).equalTo("exerciseId",mExerciseArrayList.get(position).getExerciseId()).findFirst();
            holder.mExerciseName.setText(exercise.getExerciseName());
            holder.mEngery.setText(exercise.getEnergy()+"");
            holder.mTime.setText(exercise.getTime()+"");
    }

    @Override
    public int getItemCount() {
        return mExerciseArrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView mExerciseName;
        TextView mEngery;
        TextView mTime;

        public viewHolder(View itemView) {
            super(itemView);
            mExerciseName= (TextView) itemView.findViewById(R.id.name);
            mEngery= (TextView) itemView.findViewById(R.id.calo);
            mTime= (TextView) itemView.findViewById(R.id.number);
        }
    }
}
