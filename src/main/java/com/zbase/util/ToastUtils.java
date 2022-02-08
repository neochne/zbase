/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public final class ToastUtils {

    private ToastUtils(){}

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showInWorkThread(final Context context, final String msg){
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, msg, Toast.LENGTH_LONG).show());
    }

}
