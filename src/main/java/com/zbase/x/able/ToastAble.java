package com.zbase.x.able;

import com.zbase.util.ToastUtils;

public interface ToastAble extends ContextAble {

    default void toast(String text) {
        if (isActivityX()) {
            ToastUtils.show(getActivityX(), text);
        } else if (isFragmentX()) {
            ToastUtils.show(getFragmentX().requireContext(), text);
        }
    }

    default void toastOnWorkThread(String text) {
        if (isActivityX()) {
            ToastUtils.showOnWorkThread(getActivityX(), text);
        } else if (isFragmentX()) {
            ToastUtils.showOnWorkThread(getFragmentX().requireContext(), text);
        }
    }

}
