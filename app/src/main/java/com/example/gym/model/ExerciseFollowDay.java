package com.example.gym.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DefaultAccount on 10/5/2016.
 */
public class ExerciseFollowDay extends RealmObject {
    @PrimaryKey
    private int listExerciseFollowDayId;
    private String date;

    public ExerciseFollowDay(int listExerciseFollowDayId, String date) {
        this.listExerciseFollowDayId = listExerciseFollowDayId;
        this.date = date;
    }

    public int getListExerciseFollowDayId() {
        return listExerciseFollowDayId;
    }

    public void setListExerciseFollowDayId(int listExerciseFollowDayId) {
        this.listExerciseFollowDayId = listExerciseFollowDayId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ExerciseFollowDay() {
    }
}
