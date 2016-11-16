package com.example.gym.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DefaultAccount on 9/25/2016.
 */
public class Exercise extends RealmObject implements Serializable {
    @PrimaryKey
    private int exerciseId;
    private String exerciseName;
    private int categoryId;
    private int time;
    private String exerciseDetail;
    private int imageName;
    private String summary;
    private int energy;

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getExerciseDetail() {
        return exerciseDetail;
    }

    public void setExerciseDetail(String exerciseDetail) {
        this.exerciseDetail = exerciseDetail;
    }

    public int getImageName() {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Exercise(int exerciseId, String exerciseName, int categoryId, int time, String exerciseDetail, int imageName, String summary, int energy) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.categoryId = categoryId;
        this.time = time;
        this.exerciseDetail = exerciseDetail;
        this.imageName = imageName;
        this.summary = summary;
        this.energy = energy;
    }

    public Exercise() {
    }
}
