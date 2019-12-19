package com.zbase.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.zbase.R;

public final class Dialogs {

    private static AlertDialog sProgressDialog;

    private Dialogs() {
    }

    public static void showProgressDialog(Context context) {
        showProgressDialog(context, "加载中...");
    }

    public static void showProgressDialog(Context context, String msg) {
        View dialogView = View.inflate(context, R.layout.dialog_progress, null);
        ((TextView) dialogView.findViewById(R.id.tv_msg)).setText(msg);
        sProgressDialog = new AlertDialog.Builder(context, R.style.ProgressDialogTheme)
                .setView(dialogView)
                .create();
        sProgressDialog.setCanceledOnTouchOutside(false);
        sProgressDialog.show();
    }

    public static void cancelProgressDialog() {
        if (sProgressDialog != null) {
            sProgressDialog.cancel();
            sProgressDialog = null;
        }
    }

}
