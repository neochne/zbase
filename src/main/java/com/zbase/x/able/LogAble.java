package com.zbase.x.able;

import com.zbase.util.LogUtils;

public interface LogAble {

    default void i(String tag,String msg) {
        LogUtils.i(tag,msg);
    }

    default void d(String tag,String msg) {
        LogUtils.d(tag,msg);
    }

    default void e(String tag,String msg) {
        LogUtils.e(tag,msg);
    }

}
