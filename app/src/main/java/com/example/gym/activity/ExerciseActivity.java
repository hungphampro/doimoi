package com.example.gym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.app.Prefs;
import com.example.gym.fragment.BaiTapFragment;
import com.example.gym.fragment.KeHoachFragment;
import com.example.gym.fragment.NhacNho_Fragment;
import com.example.gym.fragment.NutritionFragment;
import com.example.gym.fragment.ThemNhacNho_Fragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import static com.example.gym.R.id.tab_dinhduong;
import static com.example.gym.R.id.tab_kehoach;
import static com.example.gym.R.id.tab_nhacnho;

public class ExerciseActivity extends AppCompatActivity {
    private BottomBar mBottomBar;
    FragmentManager mFragmentManager;
    ImageView backToolbar;
    TextView titleBar;
    TextView actionBar;
    TextView actionLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            if(Prefs.with(this).getPreLoad()) {
                setContentView(R.layout.activity_exercise_today);
                mFragmentManager = getSupportFragmentManager();
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                backToolbar = (ImageView) toolbar.findViewById(R.id.back);
                titleBar = (TextView) toolbar.findViewById(R.id.title);
                actionBar = (TextView) toolbar.findViewById(R.id.action);
                actionLeft = (TextView) toolbar.findViewById(R.id.actionleft);
                mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
                mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
                    @Override
                    public void onTabSelected(@IdRes int tabId) {
                        Fragment fragment = null;
                        switch (tabId) {
                            case tab_kehoach:
                                fragment = new KeHoachFragment();
                                break;
                            case tab_dinhduong:
                                fragment = new NutritionFragment();
                                break;
                            case tab_nhacnho:
                                fragment = new NhacNho_Fragment();
                                break;
                            default:
                                fragment = new BaiTapFragment();
                        }
                        mFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
                    }
                });

                mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
                    @Override
                    public void onTabReSelected(@IdRes int tabId) {

                    }
                });
            }else{
                Intent i=new Intent(ExerciseActivity.this,BmiActivity.class);
                startActivity(i);
            }
        }



}
