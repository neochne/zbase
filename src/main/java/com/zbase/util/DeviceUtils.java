/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;

import androidx.core.content.FileProvider;

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
    public static String getSysVersion() {
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

    public static File getCachedPath(Context context) {
        if (isSdCardAvailable()) {
            return Environment.getExternalStorageDirectory();
        } else {
            return context.getFilesDir();
        }
    }

    public static boolean isSdCardAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sd.canWrite();
        } else {
            return false;
        }
    }

    public static void installApk(Context context, String apkFilePath, String authorities) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri contentUri = FileProvider.getUriForFile(
                context
                , authorities
                , new File(apkFilePath));

        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

}






