package com.zbase.x.able;

import android.content.Context;
import android.view.View;

import com.zbase.util.ThreadUtils;

public interface AsyncCreateFragmentViewAble<V extends View> {

    default boolean async() {
        return false;
    }

    default V onCreateViewSync(Context context) {
        return null;
    }

    default void onCreateViewAsync(Context context,V rootView) {
    }

    default void runOnMainThread(Runnable r) {
        ThreadUtils.runOnUiThread(r);
    }

}
