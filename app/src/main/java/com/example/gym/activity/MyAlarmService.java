package com.example.gym.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

/**
 * Created by DefaultAccount on 10/21/2016.
 */
public class MyAlarmService extends Service
{

    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);
        long time=System.currentTimeMillis();
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setAutoCancel(false);
        builder.setSound(alarmSound);
        builder.setTicker("Tập gym nào!!!");
        builder.setContentTitle("Tập gym bạn ");
        builder.setContentText("Tới giờ tập gym rồi ban ơi và hãy cho tôi thấy bạn xứng đáng đứng trên đỉnh thành công");
        builder.setSmallIcon(android.R.drawable.alert_dark_frame);
        builder.setContentIntent(pendingNotificationIntent);
        builder.setOngoing(true);
        builder.setWhen(time);
        builder.setSubText("You are ready! I'm millionaire and i have boy prefect but i never give up gym and dream. I hope you also like m");
        builder.setNumber(100);
        builder.build();
        Notification notification=builder.getNotification();
        mManager.notify(0,notification);
    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
