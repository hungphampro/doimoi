package com.example.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gym.app.Prefs;


/**
 * Created by DefaultAccount on 10/5/2016.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Prefs.with(this).getPreLoad()) {
            Intent i = new Intent(MainActivity.this, BmiActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            /*AlertDialog.Builder alert=new AlertDialog.Builder(this);
            final Realm realm= RealmController.with(this).getRealm();
            alert.setMessage("Do you want to clear all datạ");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    realm.beginTransaction();
                    realm.clear(User.class);
                    realm.clear(Exercise.class);
                    realm.clear(Nutrition.class);
                    realm.clear(ExerciseFollowDay.class);
                    realm.clear(VDetailExerciseForDay.class);
                    realm.commitTransaction();*/
                   /* Intent i = new Intent(MainActivity.this, BmiActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(MainActivity.this,ExerciseActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            });
            alert.show();*/
            Intent i = new Intent(MainActivity.this, ExerciseActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
