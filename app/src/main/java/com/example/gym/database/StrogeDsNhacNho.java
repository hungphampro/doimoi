package com.example.gym.database;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.gym.activity.MyAlarmService;
import com.example.gym.model.nhacnho;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 11/11/2016.
 */

public class StrogeDsNhacNho {
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    ArrayList<nhacnho> batnhacnho;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Gym";

    public static final String DsNhacNho="ListNhacNho";

    // User name (make variable public to access from outside)

    private Gson gson;

    public StrogeDsNhacNho(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        gson=new Gson();

    }

    public void createDsNhacNho(List<nhacnho> nhacnhoArrayList){
        // Storing name in pref
        String json = gson.toJson(nhacnhoArrayList);
        editor.putString(DsNhacNho, json);
        editor.commit();
        batnhacnho=new ArrayList<>();
        for(int i=0;i<nhacnhoArrayList.size();i++){
            if(nhacnhoArrayList.get(i).isNhacnho()){
                batnhacnho.add(nhacnhoArrayList.get(i));
            }
        }

    }
    public void logoutDsNhacnho() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
    public ArrayList<nhacnho> getlist(){
        Gson gson = new Gson();
        String json = pref.getString(DsNhacNho, null);
        Type type = new TypeToken<ArrayList<nhacnho>>() {}.getType();
        ArrayList<nhacnho> arrayList = gson.fromJson(json, type);
        return arrayList;
    }
    public void batbaothuc(){
        NotificationManager manager = (NotificationManager) _context.getSystemService(Context.NOTIFICATION_SERVICE);
        ArrayList<nhacnho> Alarm=new ArrayList<>();
        for(int i=0;i<batnhacnho.size();i++){
            if(batnhacnho.get(i).getmCalendar().getTime().getHours()> new Date().getHours()){
                Alarm.add(batnhacnho.get(i));
            }else if(batnhacnho.get(i).getmCalendar().getTime().getHours()== new Date().getHours()){
                if(batnhacnho.get(i).getmCalendar().getTime().getMinutes()>= new Date().getMinutes()){
                    Alarm.add(batnhacnho.get(i));
                }
            }
        }
        AlarmManager[] alarmManager=new AlarmManager[Alarm.size()];
        ArrayList<PendingIntent> pendingIntentArrayList=new ArrayList<>();
        for(int i=0;i<Alarm.size();i++){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,Alarm.get(i).getmCalendar().getTime().getHours());
            calendar.set(Calendar.MINUTE,Alarm.get(i).getmCalendar().getTime().getMinutes());
            calendar.set(Calendar.SECOND, Alarm.get(i).getmCalendar().getTime().getSeconds());
            Intent intent = new Intent(_context, MyAlarmService.class);
            PendingIntent pi= PendingIntent.getService(_context,i,intent, 0);

            alarmManager[i] = (AlarmManager) _context.getSystemService(_context.ALARM_SERVICE);
            alarmManager[i].set(AlarmManager.RTC_WAKEUP,Alarm.get(i).getmCalendar().getTimeInMillis(),pi);
            pendingIntentArrayList.add(pi);
        }
    }
}
