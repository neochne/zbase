/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class DeviceUtils {

    private DeviceUtils() {
    }

    /**
     * @return eg: 28
     */
    public static int getApiVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * @return eg: 6.0
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * @return eg: HUAWEI PLK-AL10
     */
    public static String getOemNum() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    public static boolean isKeyboardShowing(Context context) {
        if (context == null) return false;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return false;
        return imm.isActive();
    }

    public static void showKeyboard(Context context, EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
    }

    public static void hideKeyboard(Activity activity) {
        View v = activity.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    /**
     * @return unit is px
     */
    public static int getDeviceWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null)
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * @return unit is px
     */
    public static int getDeviceHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null)
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @SuppressLint("HardwareIds")
    public static String getDeviceUUID(Context context) {
        String uuid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (uuid != null && !"".equals(uuid) && !"unknown".equals(uuid)) {
            return uuid;
        }
        return String.valueOf(
                (Build.BOARD.length() % 10) +
                        (Build.BRAND.length() % 10) +
                        (Build.CPU_ABI.length() % 10) +
                        (Build.DEVICE.length() % 10) +
                        (Build.MANUFACTURER.length() % 10) +
                        (Build.MODEL.length() % 10) +
                        (Build.PRODUCT.length() % 10) << 16);
    }

}






