/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

public interface YesCallback {

    default void onSuccess(YesResponse response) {}

    /**
     * when progress is 100,is done
     */
    default void onProgress(int progress,long totalLengthByte) {}

    default void onFail(YesResponse response) {}

    default void onException(Exception exception) {}

}
