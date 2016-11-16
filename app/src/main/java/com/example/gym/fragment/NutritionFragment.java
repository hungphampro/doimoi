package com.example.gym.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.database.RealmController;
import com.example.gym.model.Nutrition;

import io.realm.Realm;

/**
 * Created by DefaultAccount on 10/9/2016.
 */
public class NutritionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.nutrition_fragment,container,false);
        TextView buoisang= (TextView) view.findViewById(R.id.buoisang);
        TextView buoitrua=(TextView)view.findViewById(R.id.buoitrua);
        TextView buoichieu=(TextView)view.findViewById(R.id.buoitoi);
        TextView nhacnho=(TextView)view.findViewById(R.id.nhacnho);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView back=(ImageView) toolbar.findViewById(R.id.back);
        back.setVisibility(View.GONE);
        TextView textView= (TextView) toolbar.findViewById(R.id.title);
        textView.setText("Dinh Dưỡng");
        final TextView action=(TextView) toolbar.findViewById(R.id.action);
        action.setVisibility(View.GONE);
        final TextView actionLeft=(TextView) toolbar.findViewById(R.id.actionleft);
        actionLeft.setVisibility(View.GONE);
        final  ImageView imageAction=(ImageView) toolbar.findViewById(R.id.imageaction);
        imageAction.setVisibility(View.GONE);
        Realm realm= RealmController.with(this).getRealm();
        Nutrition n=realm.where(Nutrition.class).findFirst();
        buoisang.setText(n.getBuoisang());
        buoitrua.setText(n.getBuoitrua());
        buoichieu.setText(n.getBuoichieu());
        nhacnho.setText(n.getNhacnho());
        return view;
    }
}
