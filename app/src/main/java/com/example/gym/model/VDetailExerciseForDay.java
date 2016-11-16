package com.example.gym.model;

import io.realm.RealmObject;

/**
 * Created by DefaultAccount on 10/6/2016.
 */
public class VDetailExerciseForDay extends RealmObject {
    private int  exerciseId;
    private boolean done;
    private int  listExerciseFollowDayId;
    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getListExerciseFollowDayId() {
        return listExerciseFollowDayId;
    }

    public void setListExerciseFollowDayId(int listExerciseFollowDayId) {
        this.listExerciseFollowDayId = listExerciseFollowDayId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public VDetailExerciseForDay(int exerciseId, int listExerciseFollowDayId,boolean done) {
        this.exerciseId = exerciseId;
        this.listExerciseFollowDayId = listExerciseFollowDayId;
        this.done=done;
    }

    public VDetailExerciseForDay() {
    }
}
