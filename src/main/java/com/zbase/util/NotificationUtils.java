package com.zbase.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public final class NotificationUtils {

    private NotificationUtils() {
    }

    public static void showMsgNotification(Context context,
                                           int notificationId,
                                           String title,
                                           String content,
                                           int icon,
                                           PendingIntent pendingIntent) {
        String channelId = "msg";
        int mediaDuration = 3000;
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(icon)
                .setLights(Color.YELLOW, mediaDuration, mediaDuration)
                .setVibrate(new long[]{mediaDuration, mediaDuration})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH);
            NotificationManagerCompat.from(context).createNotificationChannel(channel);
        }
        NotificationManagerCompat.from(context).notify(notificationId, notification);
    }

    public static void startForegroundService(Service context, String title, String content, int icon) {
        String channelId = "service";
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(icon)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_MIN);
            NotificationManagerCompat.from(context).createNotificationChannel(channel);
        }
        context.startForeground(6, notification);
    }

    public static void clearAll(Context context) {
        NotificationManagerCompat.from(context).cancelAll();
    }

    public static PendingIntent createPendingIntent(Context context, int requestCode, Intent intent) {
        return PendingIntent.getActivity(
                context,
                requestCode,
                intent,
                // 12.0需要明确设置flag，否则会有报错：
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
