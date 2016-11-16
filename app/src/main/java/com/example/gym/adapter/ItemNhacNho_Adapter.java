package com.example.gym.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.database.StrogeDsNhacNho;
import com.example.gym.model.nhacnho;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by root on 10/11/2016.
 */

public class ItemNhacNho_Adapter extends RecyclerView.Adapter<ItemNhacNho_Adapter.viewHolder> {
    public ArrayList<nhacnho> mNhacnhos;
    public Context mContext;
    public boolean mIsEdit;
    public StrogeDsNhacNho mStrogeDsNhacNho;
    public ItemNhacNho_Adapter(ArrayList<nhacnho> nhacNhos,Context context,boolean isEdit){
        mNhacnhos=nhacNhos;
        mContext=context;
        mIsEdit=isEdit;
        mStrogeDsNhacNho=new StrogeDsNhacNho(mContext);
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhacnho_fragment,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
           nhacnho nn=mNhacnhos.get(position);
           holder.time.setText(nn.getmCalendar().getTime().getHours()+":"+nn.getmCalendar().getTime().getMinutes());
           if(nn.isNhacnho()){
               holder.toogle.setImageResource(R.drawable.toogleon);
               holder.toogle.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       holder.toogle.setImageResource(R.drawable.toogleoff);
                       mNhacnhos.get(position).setNhacnho(false);
                       mStrogeDsNhacNho.createDsNhacNho(mNhacnhos);
                       notifyDataSetChanged();
                   }
               });
           }else{
               holder.toogle.setImageResource(R.drawable.toogleoff);
               holder.toogle.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       holder.toogle.setImageResource(R.drawable.toogleon);
                       mNhacnhos.get(position).setNhacnho(true);
                       mStrogeDsNhacNho.createDsNhacNho(mNhacnhos);
                       notifyDataSetChanged();
                   }
               });
           }
           if(mIsEdit){
               holder.minus.setVisibility(View.VISIBLE);
               holder.minus.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       mNhacnhos.remove(position);
                       notifyDataSetChanged();
                       mStrogeDsNhacNho.createDsNhacNho(mNhacnhos);
                   }
               });
           }else{
               holder.minus.setVisibility(View.GONE);
           }
    }

    @Override
    public int getItemCount() {
        return mNhacnhos.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView time;
        ImageView toogle;
        ImageView minus;
        public viewHolder(View itemView) {
            super(itemView);
            time= (TextView) itemView.findViewById(R.id.time);
            toogle= (ImageView) itemView.findViewById(R.id.toogle);
            minus=(ImageView)itemView.findViewById(R.id.eidt);
        }
    }
}
