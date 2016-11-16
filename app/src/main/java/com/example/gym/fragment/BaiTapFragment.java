package com.example.gym.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.adapter.DividerItemDecoration;
import com.example.gym.adapter.Item_baitap_Adapter;
import com.example.gym.adapter.SimpleDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by root on 09/11/2016.
 */

public class BaiTapFragment extends Fragment {
    ArrayList<String> mNgayList;
    Item_baitap_Adapter mItem_baitap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.baitap_fragment,container,false);
        RecyclerView rc= (RecyclerView) view.findViewById(R.id.recycleview);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView back=(ImageView) toolbar.findViewById(R.id.back);
        back.setVisibility(View.GONE);
        TextView textView= (TextView) toolbar.findViewById(R.id.title);
        textView.setText("Bài tập");
        final TextView action=(TextView) toolbar.findViewById(R.id.action);
        action.setVisibility(View.GONE);
        final TextView actionLeft=(TextView) toolbar.findViewById(R.id.actionleft);
        actionLeft.setVisibility(View.GONE);
        final  ImageView imageAction=(ImageView) toolbar.findViewById(R.id.imageaction);
        imageAction.setVisibility(View.GONE);
        mNgayList=new ArrayList<>();
        SetUpArray();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rc.setLayoutManager(linearLayoutManager);
        rc.setItemAnimator(new DefaultItemAnimator());
        rc.addItemDecoration(new DividerItemDecoration(rc.getContext(),R.drawable.custom_item_nav));
        mItem_baitap=new Item_baitap_Adapter(mNgayList,inflater.getContext());
        rc.setAdapter(mItem_baitap);
        return view;
    }

    private void SetUpArray() {
        for(int i=1;i<=30;i++){
            mNgayList.add("Ngày "+i);
        }
    }

}
