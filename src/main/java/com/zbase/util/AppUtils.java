package com.zbase.util;

import android.content.Context;
import android.content.Intent;

public final class AppUtils {

    private static int sStateCounter = 0;

    private AppUtils() {
    }

    public static void startActivityInNewTask(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        packageContext.startActivity(intent);
    }

    public static void activityStarted() {
        sStateCounter++;
    }

    public static void activityStopped() {
        sStateCounter--;
    }

    public static boolean isApplicationOnBackground() {
        return sStateCounter == 0;
    }

}
