package com.example.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.app.Prefs;
import com.example.gym.database.RealmController;
import com.example.gym.model.Exercise;
import com.example.gym.model.ExerciseFollowDay;
import com.example.gym.model.Nutrition;
import com.example.gym.model.User;
import com.example.gym.model.VDetailExerciseForDay;

import java.util.Date;

import io.realm.Realm;

/**
 * Created by DefaultAccount on 10/5/2016.
 */
public class BmiActivity extends AppCompatActivity {
    private EditText mEditHeight,mEditWeight;
    private TextView mIndex,mStatus;
    private Button mCacurlator;
    private ImageView mStart;
    private Realm mRealm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        mEditHeight= (EditText) findViewById(R.id.editheight);
        mEditWeight=(EditText)findViewById(R.id.edit_weight);
        mIndex=(TextView) findViewById(R.id.chisobmitinhduoc);
        mStatus=(TextView) findViewById(R.id.tinhtrangcothe);
        mCacurlator=(Button)findViewById(R.id.tinhbmi);
        mStart=(ImageView)findViewById(R.id.start);
        mRealm = RealmController.with(this).getRealm();
        mCacurlator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(!checkHeightEmpty()&&checkHeightWrong()&&!checkWeightEmpty()&&checkWeightWrong()){
                     xoatacca();
                     User u=new User();
                     u.setHeight(Integer.parseInt(mEditHeight.getText().toString()));
                     u.setWeight(Double.parseDouble(mEditWeight.getText().toString()));
                     double h= Double.valueOf(Integer.parseInt(mEditHeight.getText().toString()));
                     double chia=h/100;
                     double layso=Double.parseDouble(mEditWeight.getText().toString())/Math.pow(chia,2);
                     mIndex.setText(mIndex.getText()+":"+layso);
                    if(layso<18) {
                        mStatus.setText(mStatus.getText()+": Người gầy");
                        u.setCategoryID(1);
                    }
                     else if(18<=layso&&layso<24.9) {
                        mStatus.setText(mStatus.getText()+": Người bình thường");
                        u.setCategoryID(2);
                    }
                     else {
                        mStatus.setText(mStatus.getText()+": Người Mập");
                        u.setCategoryID(3);
                    }
                     mRealm.beginTransaction();
                     mRealm.copyToRealm(u);
                     mRealm.commitTransaction();
                     initializationDatabase();
                 }
            }
        });
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(Prefs.with(getApplicationContext()).getPreLoad()){
                     User user=mRealm.where(User.class).findFirst();
                     mRealm.beginTransaction();
                     user.setDate(new Date());
                     mRealm.commitTransaction();
                     Intent i=new Intent(BmiActivity.this,ExerciseActivity.class);
                     i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     startActivity(i);
                 }
            }
        });
    }
    public boolean checkHeightWrong(){
        try{
            Integer heightIndex=Integer.parseInt(mEditHeight.getText().toString());
            if(heightIndex<0){
                requestFocus(mEditHeight);
                return false;
            }
        }catch(Exception e){

        }
        return true;
    }
    public boolean checkWeightWrong(){
        try{
            double weightIndex= Double.parseDouble(mEditWeight.getText().toString());
            if(weightIndex<0){
                requestFocus(mEditWeight);
                return false;
            }
        }catch(Exception e){

        }
        return true;
    }
    public boolean checkHeightEmpty(){
        if(mEditHeight.getText().toString().equals("")){
            requestFocus(mEditHeight);
            return true;
        }
        return false;
    }
    public boolean checkWeightEmpty(){
        if(mEditWeight.getText().toString().equals("")){
            requestFocus(mEditWeight);
            return true;
        }
        return false;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    public void initializationDatabase(){
        if(mRealm.where(User.class).findFirst().getCategoryID()==1)
        {
            Exercise e;
            for(int i=1;i<=15;i++){
            switch(i) {
                case 1:
                    e= new Exercise(i, "Jumping Jacks", 1, 20, getResources().getString(R.string.JumpingJacks), R.drawable.jumping, getResources().getString(R.string.summary), 1000);
                    break;
                case 2: e=new Exercise(i, "Push Up", 1, 20, getResources().getString(R.string.PushUp), R.drawable.pushup, getResources().getString(R.string.summary), 1000);
                    break;
                case 3: e =new Exercise(i, "Abdominal Crunches", 1, 20, getResources().getString(R.string.AbdominalCrunches), R.drawable.abdominal, getResources().getString(R.string.summary), 1000);
                    break;
                case 4: e =new Exercise(i, "Sep Up Onto Chair", 1, 20, getResources().getString(R.string.sep_upOntoChair), R.drawable.stepup, getResources().getString(R.string.summary), 1000);
                    break;
                case 5: e =new Exercise(i, "Plank", 1, 20, getResources().getString(R.string.plank), R.drawable.plank, getResources().getString(R.string.summary), 1000);
                    break;
                case 6: e =new Exercise(i, "Squat", 1, 20, getResources().getString(R.string.squat), R.drawable.squad, getResources().getString(R.string.summary), 1000);
                    break;
                case 7: e =new Exercise(i, "Bird Dog", 1, 20, getResources().getString(R.string.birdDog), R.drawable.plank, getResources().getString(R.string.summary), 1000);
                    break;
                case 8: e =new Exercise(i, "Triceps Dip On Chair", 1, 20, getResources().getString(R.string.tricepsDipOnChair), R.drawable.triceps, getResources().getString(R.string.summary), 1000);
                    break;
                case 9: e =new Exercise(i, "High Knees Running In Place", 1, 20, getResources().getString(R.string.highKneesRunningInPlace), R.drawable.highknees, getResources().getString(R.string.summary), 1000);
                    break;
                case 10: e =new Exercise(i, "Plunge", 1, 20, getResources().getString(R.string.plunge), R.drawable.lunge, getResources().getString(R.string.summary), 1000);
                    break;
                case 11: e =new Exercise(i, "Push Up And Rotation", 1, 20, getResources().getString(R.string.push_upAndRotation), R.drawable.pushupandrotation, getResources().getString(R.string.summary), 1000);
                    break;
                case 12: e =new Exercise(i, "Side Plank", 1, 20, getResources().getString(R.string.sidePlank), R.drawable.sideplank, getResources().getString(R.string.summary), 1000);
                    break;
                case 13: e=new Exercise(i,"Wall Sit",1,20,getResources().getString(R.string.wallSit), R.drawable.wallsit, getResources().getString(R.string.summary), 1000);
                    break;
                case 14: e=new Exercise(i,"Break Dancer",1,20,getResources().getString(R.string.breakDancer), R.drawable.drinkswami, getResources().getString(R.string.summary), 1000);
                    break;
                default: e=new Exercise(i, "Bicycle Crunch", 1, 20, getResources().getString(R.string.bicycleCrunch), R.drawable.plank, getResources().getString(R.string.summary), 1000);
            }
            mRealm.beginTransaction();
            mRealm.copyToRealm(e);
            mRealm.commitTransaction();
        }
            Nutrition nutritionWeek=new Nutrition(1,1,getResources().getString(R.string.nutritionWeekMorning),getResources().getString(R.string.nutritionWeekNoon),getResources().getString(R.string.nutritionWeekLunch),getResources().getString(R.string.nutritionWeekNhacNho));
            mRealm.beginTransaction();
            mRealm.copyToRealm(nutritionWeek);
            mRealm.commitTransaction();
        }else if(mRealm.where(User.class).findFirst().getCategoryID()==2) {
            Exercise e;
            for (int i = 16; i <= 30; i++) {
                    switch(i) {
                        case 16:
                            e= new Exercise(i, "Jumping Jacks", 1, 20, getResources().getString(R.string.JumpingJacks), R.drawable.jumping, getResources().getString(R.string.summary), 1000);
                            break;
                        case 17: e=new Exercise(i, "Push Up", 1, 20, getResources().getString(R.string.PushUp), R.drawable.pushup, getResources().getString(R.string.summary), 1000);
                            break;
                        case 18: e =new Exercise(i, "Abdominal Crunches", 1, 20, getResources().getString(R.string.AbdominalCrunches), R.drawable.abdominal, getResources().getString(R.string.summary), 1000);
                            break;
                        case 19: e =new Exercise(i, "Sep Up Onto Chair", 1, 20, getResources().getString(R.string.sep_upOntoChair), R.drawable.stepup, getResources().getString(R.string.summary), 1000);
                            break;
                        case 20: e =new Exercise(i, "Plank", 1, 20, getResources().getString(R.string.plank), R.drawable.plank, getResources().getString(R.string.summary), 1000);
                            break;
                        case 21: e =new Exercise(i, "Squat", 1, 20, getResources().getString(R.string.squat), R.drawable.squad, getResources().getString(R.string.summary), 1000);
                            break;
                        case 22: e =new Exercise(i, "Bird Dog", 1, 20, getResources().getString(R.string.birdDog), R.drawable.plank, getResources().getString(R.string.summary), 1000);
                            break;
                        case 23: e =new Exercise(i, "Triceps Dip On Chair", 1, 20, getResources().getString(R.string.tricepsDipOnChair), R.drawable.triceps, getResources().getString(R.string.summary), 1000);
                            break;
                        case 24: e =new Exercise(i, "High Knees Running In Place", 1, 20, getResources().getString(R.string.highKneesRunningInPlace), R.drawable.highknees, getResources().getString(R.string.summary), 1000);
                            break;
                        case 25: e =new Exercise(i, "Plunge", 1, 20, getResources().getString(R.string.plunge), R.drawable.lunge, getResources().getString(R.string.summary), 1000);
                            break;
                        case 26: e =new Exercise(i, "Push Up And Rotation", 1, 20, getResources().getString(R.string.push_upAndRotation), R.drawable.pushupandrotation, getResources().getString(R.string.summary), 1000);
                            break;
                        case 27: e =new Exercise(i, "Side Plank", 1, 20, getResources().getString(R.string.sidePlank), R.drawable.sideplank, getResources().getString(R.string.summary), 1000);
                            break;
                        case 28: e=new Exercise(i,"Wall Sit",1,20,getResources().getString(R.string.wallSit), R.drawable.wallsit, getResources().getString(R.string.summary), 1000);
                            break;
                        case 29: e=new Exercise(i,"Break Dancer",1,20,getResources().getString(R.string.breakDancer), R.drawable.drinkswami, getResources().getString(R.string.summary), 1000);
                            break;
                        default: e=new Exercise(i, "Bicycle Crunch", 1, 20, getResources().getString(R.string.bicycleCrunch), R.drawable.plank, getResources().getString(R.string.summary), 1000);
                    }
                mRealm.beginTransaction();
                mRealm.copyToRealm(e);
                mRealm.commitTransaction();
            }
            Nutrition nutritionNormal=new Nutrition(2,2,getResources().getString(R.string.nutritionNormalMorning),getResources().getString(R.string.nutritionNormalNoon),getResources().getString(R.string.nutritionNormalLunch),getResources().getString(R.string.nutritionNormalNhacNho));
            mRealm.beginTransaction();
            mRealm.copyToRealm(nutritionNormal);
            mRealm.commitTransaction();
        }else {
            Exercise e;
            for (int i = 31; i <= 45; i++) {
                switch(i) {
                    case 31:
                        e= new Exercise(i, "Jumping Jacks", 1, 20, getResources().getString(R.string.JumpingJacks), R.drawable.jumping, getResources().getString(R.string.summary), 1000);
                        break;
                    case 32: e=new Exercise(i, "Push Up", 1, 20, getResources().getString(R.string.PushUp), R.drawable.pushup, getResources().getString(R.string.summary), 1000);
                        break;
                    case 33: e =new Exercise(i, "Abdominal Crunches", 1, 20, getResources().getString(R.string.AbdominalCrunches), R.drawable.abdominal, getResources().getString(R.string.summary), 1000);
                        break;
                    case 34: e =new Exercise(i, "Sep Up Onto Chair", 1, 20, getResources().getString(R.string.sep_upOntoChair), R.drawable.stepup, getResources().getString(R.string.summary), 1000);
                        break;
                    case 35: e =new Exercise(i, "Plank", 1, 20, getResources().getString(R.string.plank), R.drawable.plank, getResources().getString(R.string.summary), 1000);
                        break;
                    case 36: e =new Exercise(i, "Squat", 1, 20, getResources().getString(R.string.squat), R.drawable.squad, getResources().getString(R.string.summary), 1000);
                        break;
                    case 37: e =new Exercise(i, "Bird Dog", 1, 20, getResources().getString(R.string.birdDog), R.drawable.plank, getResources().getString(R.string.summary), 1000);
                        break;
                    case 38: e =new Exercise(i, "Triceps Dip On Chair", 1, 20, getResources().getString(R.string.tricepsDipOnChair), R.drawable.triceps, getResources().getString(R.string.summary), 1000);
                        break;
                    case 39: e =new Exercise(i, "High Knees Running In Place", 1, 20, getResources().getString(R.string.highKneesRunningInPlace), R.drawable.highknees, getResources().getString(R.string.summary), 1000);
                        break;
                    case 40: e =new Exercise(i, "Plunge", 1, 20, getResources().getString(R.string.plunge), R.drawable.lunge, getResources().getString(R.string.summary), 1000);
                        break;
                    case 41: e =new Exercise(i, "Push Up And Rotation", 1, 20, getResources().getString(R.string.push_upAndRotation), R.drawable.pushupandrotation, getResources().getString(R.string.summary), 1000);
                        break;
                    case 42: e =new Exercise(i, "Side Plank", 1, 20, getResources().getString(R.string.sidePlank), R.drawable.sideplank, getResources().getString(R.string.summary), 1000);
                        break;
                    case 43: e=new Exercise(i,"Wall Sit",1,20,getResources().getString(R.string.wallSit), R.drawable.wallsit, getResources().getString(R.string.summary), 1000);
                        break;
                    case 44: e=new Exercise(i,"Break Dancer",1,20,getResources().getString(R.string.breakDancer), R.drawable.drinkswami, getResources().getString(R.string.summary), 1000);
                        break;
                    default: e=new Exercise(i, "Bicycle Crunch", 1, 20, getResources().getString(R.string.bicycleCrunch), R.drawable.plank, getResources().getString(R.string.summary), 1000);
                }
                mRealm.beginTransaction();
                mRealm.copyToRealm(e);
                mRealm.commitTransaction();
            }
            Nutrition nutritionWeight = new Nutrition(3, 3, getResources().getString(R.string.nutritionStrongMorning),getResources().getString(R.string.nutritionStrongNoon),getResources().getString(R.string.nutritionStrongLunch),getResources().getString(R.string.nutritionStrongNhacNho));
            mRealm.beginTransaction();
            mRealm.copyToRealm(nutritionWeight);
            mRealm.commitTransaction();
        }
        Prefs.with(this).setPreLoad(true);
    }
    public void xoatacca(){
        mRealm.beginTransaction();
        mRealm.clear(ExerciseFollowDay.class);
        mRealm.clear(VDetailExerciseForDay.class);
        mRealm.clear(User.class);
        mRealm.clear(Nutrition.class);
        mRealm.clear(Exercise.class);
        mRealm.commitTransaction();
    }
}
