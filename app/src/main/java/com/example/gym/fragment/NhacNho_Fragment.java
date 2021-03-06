package com.example.gym.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.activity.ExerciseActivity;
import com.example.gym.adapter.DividerItemDecoration;
import com.example.gym.adapter.ItemNhacNho_Adapter;
import com.example.gym.database.StrogeDsNhacNho;
import com.example.gym.model.nhacnho;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by root on 10/11/2016.
 */

public class NhacNho_Fragment extends Fragment {
    RecyclerView mNhacNhoRecycleView;
    ArrayList<nhacnho> mCalendars;
    private StrogeDsNhacNho mStrogeDsNhacNho;
    FragmentManager mFragmentManager;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.nhacnho_fragment,container,false);
        mNhacNhoRecycleView= (RecyclerView) view.findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(inflater.getContext(),LinearLayoutManager.VERTICAL,false);
        mNhacNhoRecycleView.setLayoutManager(linearLayoutManager);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView back=(ImageView) toolbar.findViewById(R.id.back);
        back.setVisibility(View.GONE);
        TextView textView= (TextView) toolbar.findViewById(R.id.title);
        final TextView action=(TextView) toolbar.findViewById(R.id.action);
        final TextView actionLeft=(TextView) toolbar.findViewById(R.id.actionleft);
        final  ImageView imageAction=(ImageView) toolbar.findViewById(R.id.imageaction);
        textView.setText("Nhắc Nhở");
        action.setVisibility(View.GONE);
        actionLeft.setText("Sửa");
        actionLeft.setVisibility(View.VISIBLE);
        imageAction.setVisibility(View.VISIBLE);
        imageAction.setImageResource(R.drawable.add);
        mFragmentManager=getFragmentManager();
        actionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment,new suaNhacNho_Fragment()).commit();
            }
        });

        imageAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment,new ThemNhacNho_Fragment()).commit();
            }
        });

        textView.setTextSize(18);
        action.setTextSize(18);
        actionLeft.setTextSize(18);
        mStrogeDsNhacNho=new StrogeDsNhacNho(inflater.getContext());
        if(mStrogeDsNhacNho.getlist()==null){
            mCalendars=new ArrayList<>();
            mStrogeDsNhacNho.createDsNhacNho(mCalendars);
        }else{
            mCalendars=mStrogeDsNhacNho.getlist();
            mStrogeDsNhacNho.createDsNhacNho(mCalendars);
            mStrogeDsNhacNho.batbaothuc();
        }
        ItemNhacNho_Adapter itemNhacNho_adapter=new ItemNhacNho_Adapter(mCalendars,inflater.getContext(),false);
        mNhacNhoRecycleView.setAdapter(itemNhacNho_adapter);
        mNhacNhoRecycleView.addItemDecoration(new DividerItemDecoration(mNhacNhoRecycleView.getContext(),R.drawable.custom_item_nav));
        mNhacNhoRecycleView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }
}
