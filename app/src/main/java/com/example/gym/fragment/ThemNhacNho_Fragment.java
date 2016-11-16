package com.example.gym.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.gym.R;
import com.example.gym.database.StrogeDsNhacNho;
import com.example.gym.model.nhacnho;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by root on 11/11/2016.
 */

public class ThemNhacNho_Fragment extends Fragment {

    private Context mContext;
    private ArrayList<nhacnho> listnotification;
    private nhacnho nhacnho;
    private StrogeDsNhacNho mStrogeDsNhacNho;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.notification_exercise,container,false);
        final TimePicker timePicker= (TimePicker) view.findViewById(R.id.timePicker);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView back=(ImageView) toolbar.findViewById(R.id.back);
        back.setVisibility(View.GONE);
        TextView textView= (TextView) toolbar.findViewById(R.id.title);
        textView.setText("Thêm Nhắc Nhỡ");
        final TextView action=(TextView) toolbar.findViewById(R.id.action);
        action.setVisibility(View.VISIBLE);
        action.setText("Hủy");
        final TextView actionLeft=(TextView) toolbar.findViewById(R.id.actionleft);
        actionLeft.setVisibility(View.VISIBLE);
        actionLeft.setText("Lưu");
        final  ImageView imageAction=(ImageView) toolbar.findViewById(R.id.imageaction);
        imageAction.setVisibility(View.GONE);
        timePicker.setIs24HourView(true);
        mContext=container.getContext();
        System.out.println("tau toi day");
        mStrogeDsNhacNho=new StrogeDsNhacNho(inflater.getContext());
        if(mStrogeDsNhacNho.getlist()==null){
            listnotification=new ArrayList<>();
            mStrogeDsNhacNho.createDsNhacNho(listnotification);
        }else{
            listnotification=mStrogeDsNhacNho.getlist();
        }
        actionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                calendar.set(Calendar.SECOND,00);
                nhacnho=new nhacnho(calendar,true);
                listnotification.add(nhacnho);
                mStrogeDsNhacNho.createDsNhacNho(listnotification);
                getFragmentManager().beginTransaction().replace(R.id.fragment,new NhacNho_Fragment()).commit();
            }
        });
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment,new NhacNho_Fragment()).commit();
            }
        });
        return view;
    }
}
