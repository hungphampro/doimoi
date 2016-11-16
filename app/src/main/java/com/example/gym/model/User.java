package com.example.gym.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by DefaultAccount on 10/5/2016.
 */
public class User extends RealmObject{
    private double weight;
    private int height;
    private Date date;
    private double indexIBM;
    private int categoryID;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getIndexIBM() {
        double h= Double.valueOf(height);
        double chia=h/100;
        return weight/Math.pow(chia,2);
    }

    public void setIndexIBM(double indexIBM) {
        this.indexIBM = indexIBM;
    }

    public int getCategoryID() {
       return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public User(double weight, int height, Date date, double indexIBM, int categoryID) {
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.indexIBM = indexIBM;
        this.categoryID = categoryID;
    }

    public User() {
    }
}
