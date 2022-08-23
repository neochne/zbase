package com.zbase.x;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public final class NotificationX extends NotificationCompat.Builder {

    private final Context CTX;

    private final String CHANNEL_ID;

    private String mChannelName;

    private int mImportance;

    public NotificationX(@NonNull Context context, @NonNull String channelId) {
        super(context, channelId);
        this.CTX = context;
        this.CHANNEL_ID = channelId;
    }

    public NotificationX contentTitle(String title) {
        setContentTitle(title);
        return this;
    }

    public NotificationX contentText(String text) {
        setContentText(text);
        return this;
    }

    public NotificationX contentIntent(PendingIntent pendingIntent) {
        setContentIntent(pendingIntent);
        return this;
    }

    public NotificationX subText(CharSequence text) {
        setSubText(text);
        return this;
    }

    public NotificationX color(int color) {
        setColor(color);
        return this;
    }

    public NotificationX smallIcon(int id) {
        setSmallIcon(id);
        return this;
    }

    public NotificationX lights(int color, int on, int off) {
        setLights(color, on, off);
        return this;
    }

    public NotificationX vibrate(long[] pattern) {
        setVibrate(pattern);
        return this;
    }

    public NotificationX sound(Uri soundUri) {
        setSound(soundUri);
        return this;
    }

    public NotificationX category(String category) {
        setCategory(category);
        return this;
    }

    public NotificationX priority(int pri) {
        setPriority(pri);
        return this;
    }

    public NotificationX action(NotificationCompat.Action action) {
        addAction(action);
        return this;
    }

    public NotificationX clearAllActions() {
        clearActions();
        return this;
    }

    public NotificationX extras(Bundle bundle) {
        setExtras(bundle);
        return this;
    }

    public NotificationX channelName(String name) {
        this.mChannelName = name;
        return this;
    }

    /**
     * @param importance NotificationManager.IMPORTANCE_HIGH ...
     */
    public NotificationX importance(int importance) {
        this.mImportance = importance;
        return this;
    }

    public void show() {
        createChannel();
        NotificationManagerCompat.from(CTX).notify((int) (System.currentTimeMillis() % Integer.MAX_VALUE), build());
    }

    public void startForegroundService(Service service, int id) {
        createChannel();
        service.startForeground(id, build());
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, mChannelName, mImportance);
            NotificationManagerCompat.from(CTX).createNotificationChannel(channel);
        }
    }

    public static void clearAllNotifications(Context context) {
        NotificationManagerCompat.from(context).cancelAll();
    }

    public static PendingIntent createPendingIntent(Context context, int requestCode, Intent intent) {
        return PendingIntent.getActivity(context
                , requestCode
                , intent
                // 12.0需要明确设置flag，否则会有报错
                , Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
