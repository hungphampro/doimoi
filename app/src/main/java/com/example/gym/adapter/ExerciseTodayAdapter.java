package com.example.gym.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.database.RealmController;
import com.example.gym.model.Exercise;
import com.example.gym.model.VDetailExerciseForDay;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by DefaultAccount on 9/25/2016.
 */
public class ExerciseTodayAdapter extends RecyclerView.Adapter<ExerciseTodayAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<VDetailExerciseForDay> mExerciseListToday;

    public ExerciseTodayAdapter(Context context, ArrayList<VDetailExerciseForDay> exerciseListToday) {
        this.mContext = context;
        this.mExerciseListToday = exerciseListToday;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_baitap_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Realm realm= RealmController.with(mContext).getRealm();
        Exercise e=realm.where(Exercise.class).equalTo("exerciseId",mExerciseListToday.get(position).getExerciseId()).findFirst();
        holder.exerciseName.setText(e.getExerciseName());
        holder.img.setImageResource(e.getImageName());
        if(mExerciseListToday.get(position).isDone()){
            holder.done.setImageResource(R.drawable.checked);
        }else holder.done.setImageResource(R.drawable.ic_done);
    }

    @Override
    public int getItemCount() {
        return mExerciseListToday.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img,done;
        private TextView exerciseName;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.exerciseImage);
            exerciseName = (TextView) itemView.findViewById(R.id.exerciseName);
            done=(ImageView)itemView.findViewById(R.id.check);
        }
    }
}
