package com.zbase.x.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.DrawableCompat;

public final class ProgressBarX extends ProgressBar implements IViewX<ProgressBarX> {

    public ProgressBarX(Context context) {
        super(context);
    }

    public ProgressBarX progress(int progress) {
        setProgress(progress);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ProgressBarX progressAnimate(int progress) {
        setProgress(progress, true);
        return this;
    }

    public ProgressBarX max(int max) {
        setMax(max);
        return this;
    }

    public ProgressBarX color(int color) {
        DrawableCompat.setTint(getIndeterminateDrawable(), color);
        return this;
    }

    public ProgressBarX colorFilter(int color) {
        getIndeterminateDrawable().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        return this;
    }

}
