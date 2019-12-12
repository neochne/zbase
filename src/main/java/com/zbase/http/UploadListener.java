/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

public interface UploadListener {

    void onStart();

    void onProgress(int progress);

    void onFinish();

    void onCancel();

}
