package com.example.mobileapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int imp = 2;
        NotificationHelper notificationHelper = new NotificationHelper(context);
        String title = intent.getStringExtra("title");
        String time = intent.getStringExtra("time");
        String impString = intent.getStringExtra("imp");
        String date = intent.getStringExtra("date");

        if(impString.startsWith("H")){
            imp = 2;
        } else {
            imp=1;
        }

        NotificationCompat.Builder nb = notificationHelper.getChanelNotification(imp,title,date,time);
        notificationHelper.getmManager().notify(imp, nb.build());
    }
}
