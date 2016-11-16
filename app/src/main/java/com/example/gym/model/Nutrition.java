package com.example.gym.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DefaultAccount on 10/5/2016.
 */
public class Nutrition extends RealmObject {
    @PrimaryKey
    private int nutritionId;
    private int categoryExerciseId;
    private String buoisang;
    private String buoitrua;
    private String buoichieu;
    private String nhacnho;

    public int getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(int nutritionId) {
        this.nutritionId = nutritionId;
    }

    public int getCategoryExerciseId() {
        return categoryExerciseId;
    }

    public void setCategoryExerciseId(int categoryExerciseId) {
        this.categoryExerciseId = categoryExerciseId;
    }

    public String getBuoisang() {
        return buoisang;
    }

    public void setBuoisang(String buoisang) {
        this.buoisang = buoisang;
    }

    public String getBuoitrua() {
        return buoitrua;
    }

    public void setBuoitrua(String buoitrua) {
        this.buoitrua = buoitrua;
    }

    public String getBuoichieu() {
        return buoichieu;
    }

    public void setBuoichieu(String buoichieu) {
        this.buoichieu = buoichieu;
    }

    public String getNhacnho() {
        return nhacnho;
    }

    public void setNhacnho(String nhacnho) {
        this.nhacnho = nhacnho;
    }

    public Nutrition(int nutritionId, int categoryExerciseId, String buoisang, String buoitrua, String buoichieu, String nhacnho) {
        this.nutritionId = nutritionId;
        this.categoryExerciseId = categoryExerciseId;
        this.buoisang = buoisang;
        this.buoitrua = buoitrua;
        this.buoichieu = buoichieu;
        this.nhacnho = nhacnho;
    }

    public Nutrition() {
    }
}
