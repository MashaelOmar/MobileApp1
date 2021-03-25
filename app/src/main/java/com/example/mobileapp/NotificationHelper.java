package com.example.mobileapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channeL1ID = "channel1ID" ;
    public static final String channeL1Name = "Channel 1";
    public static final String channeL2ID = "channel2ID" ;
    public static final String channeL2Name = "Channel 2";
    private NotificationManager mManager;
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        createChanel();
        }
    }
//int chanelType
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChanel() {

        NotificationChannel channel1 = new NotificationChannel(channeL1ID, channeL1Name, NotificationManager.IMPORTANCE_LOW);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.purple_200);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getmManager().createNotificationChannel(channel1);

            NotificationChannel channel2 = new NotificationChannel(channeL2ID, channeL2Name, NotificationManager.IMPORTANCE_HIGH);
            channel2.enableLights(true);
            channel2.enableVibration(true);
            channel2.setLightColor(R.color.black);
            channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            getmManager().createNotificationChannel(channel2);



    }
    public NotificationManager getmManager(){
        if(mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public NotificationCompat.Builder getChanelNotification(int chanelType, String title, String date, String time){
        Intent resultIntent = new Intent(this, MainActivity.class);
//        resultIntent.putExtra("title", title);
//        resultIntent.putExtra("time", time);
//        resultIntent.putExtra("imp", ""+chanelType);
//        resultIntent.putExtra("date", date);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        if (chanelType == 1){
        return new  NotificationCompat.Builder(getApplicationContext(),channeL1ID)
                .setContentTitle(title)
                .setContentText(time)
                .setSmallIcon(R.drawable.ic_baseline_notifications_none_24)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);
        }
        else return new  NotificationCompat.Builder(getApplicationContext(),channeL2ID)
                .setContentTitle(title)
                .setContentText(time)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);
    }


}
