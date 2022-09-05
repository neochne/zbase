/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.util.Log;

/**
 * getThread and class name code block
 * <p>
 * try {
 * Thread thread = Thread.currentThread();
 * String curThread = thread.getName();
 * String clsName = thread.getStackTrace()[3].getClassName();
 * Log.v(TAG, curThread + "-" + "【" + clsName + "】> " + msg);
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 */
public final class LogUtils {
    /*
     * Log level
     */
    public static final int ALL = 0;

    public static final int DEBUG = 1;

    public static final int INFO = 2;

    public static final int WARN = 3;

    public static final int ERR = 4;

    public static final int OFF = 5;

    private static int sCurLogLevel = ALL;

    public static void setLogLevel(int level) {
        sCurLogLevel = level;
    }

    private LogUtils() {
    }

    public static void d(String tag, String method, Object... data) {
        if (sCurLogLevel > DEBUG) {
            return;
        }
        Log.d(tag, toMethodMsg(method, data));
    }

    public static void i(String tag, String method, Object... data) {
        if (sCurLogLevel > INFO) {
            return;
        }
        Log.i(tag, toMethodMsg(method, data));
    }

    public static void w(String tag, String method, Object... data) {
        if (sCurLogLevel > WARN) {
            return;
        }
        Log.w(tag, toMethodMsg(method, data));
    }

    public static void e(String tag, String method, String err) {
        if (sCurLogLevel > ERR) {
            return;
        }
        Log.e(tag, toMethodMsg(method, err));
    }

    public static void e(String tag, String method, Throwable tr) {
        e(tag, method, "", tr);
    }

    public static void e(String tag, String method, String prefix, Throwable tr) {
        if (sCurLogLevel > ERR) {
            return;
        }
        Log.e(tag, toMethodMsg(method, prefix + (tr.getCause() == null ? tr.getMessage() : tr.getCause().getMessage())));
    }

    private static String toMethodMsg(String method, Object... data) {
        method = String.format("|%-28s|  ", method);
        if (data == null || data.length < 1) {
            return method;
        }
        if (data.length == 1) {
            return method + data[0];
        }
        StringBuilder dataBuilder = new StringBuilder();
        // Add first data
        dataBuilder
                .append(data[0])
                .append("=")
                .append(data[1]);
        // Traverse the next data from the index 2
        for (int i = 2, len = data.length; i < len; i++) {
            if ((i & 1) == 0) {// even: echo $((0&1))
                dataBuilder
                        .append("&")
                        .append(data[i])
                        .append("=");
            } else {// odd
                dataBuilder.append(data[i]);
            }
        }
        return method + dataBuilder;
    }

}
