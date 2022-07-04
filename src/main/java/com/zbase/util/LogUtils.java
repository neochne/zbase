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
public class LogUtils {

    private static final int LEVEL_VERBOSE = 0;

    private static final int LEVEL_DEBUG = 1;

    private static final int LEVEL_INFO = 2;

    private static final int LEVEL_WARNING = 3;

    private static final int LEVEL_ERROR = 4;

    private static final int LEVEL_FATAL = 5;

    // ---------- //

    private static boolean sIsDebug = true;

    private static int sLevel = LEVEL_VERBOSE;

    private LogUtils() {
    }

    public static void setIsDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    /**
     * 只打印大于等于设置的这个 level 的日志
     */
    public static void setLogLevel(int level) {
        sLevel = level;
    }

    public static void v(String tag, String msg) {
        if (!sIsDebug || sLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable throwable) {
        if (!sIsDebug || sLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v(tag, msg, throwable);
    }

    public static void v(String tag, String msg, Object... args) {
        if (!sIsDebug || sLevel > LEVEL_VERBOSE) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (!sIsDebug || sLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Object... args) {
        if (!sIsDebug || sLevel > LEVEL_DEBUG) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable throwable) {
        if (!sIsDebug || sLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d(tag, msg, throwable);
    }

    public static void i(String tag, String msg) {
        if (!sIsDebug || sLevel > LEVEL_INFO) {
            return;
        }
        Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Object... args) {
        if (!sIsDebug || sLevel > LEVEL_INFO) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable throwable) {
        if (!sIsDebug || sLevel > LEVEL_INFO) {
            return;
        }
        Log.i(tag, msg, throwable);
    }

    public static void w(String tag, String msg) {
        if (!sIsDebug || sLevel > LEVEL_WARNING) {
            return;
        }
        Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Object... args) {
        if (!sIsDebug || sLevel > LEVEL_WARNING) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable throwable) {
        if (!sIsDebug || sLevel > LEVEL_WARNING) {
            return;
        }
        Log.w(tag, msg, throwable);
    }

    public static void e(String tag, String msg) {
        if (!sIsDebug || sLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(tag, msg);
    }

    public static void e(String tag, Exception e) {
        if (!sIsDebug || sLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(tag, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
    }

    public static void e(String tag, String msg, Object... args) {
        if (!sIsDebug || sLevel > LEVEL_ERROR) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (!sIsDebug || sLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(tag, msg, throwable);
    }

    public static void f(String tag, String msg) {
        if (!sIsDebug || sLevel > LEVEL_FATAL) {
            return;
        }
        Log.wtf(tag, msg);
    }

    public static void f(String tag, String msg, Object... args) {
        if (!sIsDebug || sLevel > LEVEL_FATAL) {
            return;
        }
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        Log.wtf(tag, msg);
    }

    public static void f(String tag, String msg, Throwable throwable) {
        if (!sIsDebug || sLevel > LEVEL_FATAL) {
            return;
        }
        Log.wtf(tag, msg, throwable);
    }

}
