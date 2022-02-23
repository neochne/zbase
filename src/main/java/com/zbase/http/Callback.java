/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

public interface Callback {

    default void onSuccess(Response response) {}

    /**
     * @param progress download or upload progress,if the value is 100,it's done
     * @param fileName file name (use when upload multiple images once)
     * @param totalLength file total size in byte
     */
    default void onProgress(int progress,String fileName,long totalLength) {}

    default void onFail(Response response) {}

    default void onException(Exception exception) {}

}
