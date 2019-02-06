package com.example.week5daily1homeassignment.view.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.week5daily1homeassignment.R;
import com.example.week5daily1homeassignment.view.activities.mainactivity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class ChatService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationManager notificationManager;
        Notification.Builder notificationBuild;

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuild = new Notification.Builder(this, "456")
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent);
        }
        else {
            notificationBuild = new Notification.Builder(this)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent);

        }

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("456", "chatChannel", NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(456, notificationBuild.build());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel("456", "chatChannel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        notificationManager.notify(456, notificationBuild.build());

    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d("TAG", "onNewToken: " + token);

    }
}
