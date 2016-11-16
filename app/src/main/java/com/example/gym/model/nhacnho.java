package com.example.gym.model;

import java.util.Calendar;

import io.realm.RealmObject;

/**
 * Created by root on 11/11/2016.
 */

public class nhacnho {

    private Calendar mCalendar;
    private boolean nhacnho;

    public Calendar getmCalendar() {
        return mCalendar;
    }

    public void setmCalendar(Calendar mCalendar) {
        this.mCalendar = mCalendar;
    }

    public boolean isNhacnho() {
        return nhacnho;
    }

    public void setNhacnho(boolean nhacnho) {
        this.nhacnho = nhacnho;
    }

    public nhacnho(Calendar mCalendar, boolean nhacnho) {
        this.mCalendar = mCalendar;
        this.nhacnho = nhacnho;
    }

    public nhacnho() {
    }
}
