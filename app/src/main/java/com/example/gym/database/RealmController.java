package com.example.gym.database;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.gym.model.Exercise;
import com.example.gym.model.ExerciseFollowDay;
import com.example.gym.model.Nutrition;
import com.example.gym.model.User;
import com.example.gym.model.VDetailExerciseForDay;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DefaultAccount on 10/5/2016.
 */
public class RealmController {
    private static RealmController mInstance;
    private final Realm mRealm;

    public RealmController(Application application) {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (mInstance == null) {
            mInstance = new RealmController(fragment.getActivity().getApplication());
        }
        return mInstance;
    }
    public static RealmController with(Context context){
        if(mInstance==null){
            Activity activity = (Activity) context;
            mInstance=new RealmController(activity.getApplication());
        }
        return mInstance;
    }
    public static RealmController with(Activity activity) {
        if (mInstance == null) {
            mInstance = new RealmController(activity.getApplication());
        }
        return mInstance;
    }

    public static RealmController with(Application application) {
        if (mInstance == null) {
            mInstance = new RealmController(application);
        }
        return mInstance;
    }

    public Realm getRealm() {
        return mRealm;
    }

    public static RealmController getInstance() {
        return mInstance;
    }

    public void RefeshRealm() {
        mRealm.refresh();
    }

    public void clearAllDetailExercise() {
        mRealm.beginTransaction();
        mRealm.clear(VDetailExerciseForDay.class);
        mRealm.commitTransaction();
    }

    public void clearAllDinhDuong() {
        mRealm.beginTransaction();
        mRealm.clear(Nutrition.class);
        mRealm.commitTransaction();
    }

    public void clearAllExercise() {
        mRealm.beginTransaction();
        mRealm.clear(Exercise.class);
        mRealm.commitTransaction();
    }

    public void clearUser() {
        mRealm.beginTransaction();
        mRealm.clear(User.class);
        mRealm.commitTransaction();
    }

    public void clearAllListExerciseFollowDay() {
        mRealm.beginTransaction();
        mRealm.clear(ExerciseFollowDay.class);
        mRealm.commitTransaction();
    }

    public RealmResults<Exercise> getListFollowCategory(int CategoryId) {
        return mRealm.where(Exercise.class).equalTo("CategoryId", CategoryId).findAll();
    }

    public Nutrition getNutritionFollowCategory(int CategoryId) {
        return mRealm.where(Nutrition.class).equalTo("CategoryExerciseId", CategoryId).findFirst();
    }

    public RealmResults<VDetailExerciseForDay> getDetailExerciseFollowDay(int listExerciseFollowDayID) {
        return mRealm.where(VDetailExerciseForDay.class).equalTo("listExerciseFollowDayId", listExerciseFollowDayID).findAll();
    }

    public User getUser() {
        return mRealm.where(User.class).findFirst();
    }
}
