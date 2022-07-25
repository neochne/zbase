package com.zbase.util;

import android.content.Context;
import android.content.Intent;

public final class AppUtils {

    private AppUtils() {
    }

    public static void startActivityNewTask(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        packageContext.startActivity(intent);
    }

}
