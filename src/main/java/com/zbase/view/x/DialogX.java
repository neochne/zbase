package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.zbase.util.SystemBarUtils;

public final class DialogX extends AlertDialog {

    public DialogX(@NonNull Context context) {
        super(context);
    }

    public DialogX(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public DialogX view(View view) {
        setView(view);
        return this;
    }

    public DialogX display() {
        show();
        return this;
    }

    public DialogX backgroundDrawable(Drawable drawable) {
        getWindow().setBackgroundDrawable(new InsetDrawable(drawable,0));
        return this;
    }

    public DialogX lp(int x,int y,int width,int height,int gravity) {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.x = x;
        wlp.y = y;
        wlp.width = width;
        wlp.height = height;
        wlp.gravity = gravity;
        window.setAttributes(wlp);
        return this;
    }

    public DialogX hideNavBar() {
        SystemBarUtils.hideNavBar(getWindow());
        return this;
    }

    public DialogX canceledOnTouchOutside(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        return this;
    }

    public DialogX cancelable(boolean cancel) {
        setCancelable(cancel);
        return this;
    }

    public DialogX cancelListener(OnCancelListener cancelListener) {
        setOnCancelListener(cancelListener);
        return this;
    }

}
