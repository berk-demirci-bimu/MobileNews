package com.iucproject.mobilenews;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notifs extends Application {

    public static final String CHANNEL_ID ="channel";
    @Override
    public void onCreate() {
        super.onCreate();

    }

    private void createNotifChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Seni özledik. Yeni haberlerimizi kaçırma");
        }

    }
}

