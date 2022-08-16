package com.zbase.x.able;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;

public interface WindowAble extends ContextAble {

    @SuppressLint("SourceLockedOrientationActivity")
    default void forceScreenPortrait() {
        getActivityX().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
