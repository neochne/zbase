package com.zbase.x.able;

import com.zbase.util.LogUtils;

public interface LogAble {

    default void i(String tag,String method,Object ...data) {
        LogUtils.i(tag,method,data);
    }

    default void d(String tag,String method,Object ...data) {
        LogUtils.d(tag,method,data);
    }

    default void e(String tag,String method,Throwable tr) {
        LogUtils.e(tag,method,tr);
    }

}
