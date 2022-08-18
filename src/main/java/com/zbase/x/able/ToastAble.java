package com.zbase.x.able;

import com.zbase.util.ToastUtils;

public interface ToastAble extends ContextAble {

    default void toast(String text) {
        ToastUtils.show(getActivityX(), text);
    }

    default void toastOnWorkThread(String text) {
        ToastUtils.showOnWorkThread(getActivityX(), text);
    }

}
