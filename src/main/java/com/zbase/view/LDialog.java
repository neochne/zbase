/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

public class LDialog {

    private static ProgressDialog mProgressDialog;

    private LDialog() {
    }

    public static void show(Activity activity) {
        show(activity, "加载中...");
    }

    public static void show(final Activity activity, final String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(activity, "", msg, true);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(true);
            Window w = mProgressDialog.getWindow();
        }else {
            mProgressDialog.show();
        }
    }

    public static void cancel() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
            mProgressDialog = null;
        }
    }

}
