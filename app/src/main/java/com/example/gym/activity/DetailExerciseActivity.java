package com.example.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.database.RealmController;
import com.example.gym.model.Exercise;
import com.example.gym.model.VDetailExerciseForDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.realm.Realm;


/**
 * Created by DefaultAccount on 10/6/2016.
 */
public class DetailExerciseActivity extends AppCompatActivity {
    private TextView mExerciseName, mExerciseContent;
    private Realm mRealm;
    private ImageView done;
    private ImageView mExerciseImage;
    ArrayList<VDetailExerciseForDay> vDetailExerciseForDays;
    int listExerciseFollowDayId;
    Exercise exercise = null;
    int i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_exercise);
        EventBus.getDefault().register(this);
        mExerciseImage = (ImageView) findViewById(R.id.exerciseImage);
        mExerciseName = (TextView) findViewById(R.id.exerciseName);
        mExerciseContent = (TextView) findViewById(R.id.exerciseContent);
        done = (ImageView) findViewById(R.id.check);
        getSupportActionBar().hide();
        mRealm = RealmController.with(this).getRealm();
        dequybaitap();
    }
    public void dequybaitap(){
            i = 0;
            for (; i < vDetailExerciseForDays.size(); i++) {
                if (!vDetailExerciseForDays.get(i).isDone()) {
                    exercise = mRealm.where(Exercise.class).equalTo("exerciseId", vDetailExerciseForDays.get(i).getExerciseId()).findFirst();
                    break;
                }
            }
        if (i == vDetailExerciseForDays.size()) {
            Intent gohome = new Intent(DetailExerciseActivity.this, ExerciseActivity.class);
            gohome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(gohome);
        } else {
            exercise = mRealm.where(Exercise.class).equalTo("exerciseId", exercise.getExerciseId()).findFirst();
            mExerciseImage.setImageResource(exercise.getImageName());
            mExerciseName.setText(exercise.getExerciseName());
            mExerciseContent.setText(Html.fromHtml(exercise.getExerciseDetail()));
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VDetailExerciseForDay vDetailExerciseForDay = vDetailExerciseForDays.get(i);
                    mRealm.beginTransaction();
                    vDetailExerciseForDay.setDone(true);
                    mRealm.commitTransaction();
                    dequybaitap();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        vDetailExerciseForDays=null;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(ArrayList<VDetailExerciseForDay> list){
        vDetailExerciseForDays = list;
        EventBus.getDefault().unregister(this);
    }
}
