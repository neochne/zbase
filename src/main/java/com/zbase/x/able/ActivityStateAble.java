package com.zbase.x.able;

import android.view.ViewTreeObserver;

public interface ActivityStateAble extends ContextAble{

    default void addViewTreeGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener listener) {
        getActivityX()
                .getWindow()
                .getDecorView()
                .getViewTreeObserver()
                .addOnGlobalLayoutListener(listener);
    }

    default void removeViewTreeGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener listener) {
        getActivityX()
                .getWindow()
                .getDecorView()
                .getViewTreeObserver()
                .removeOnGlobalLayoutListener(listener);
    }

}
