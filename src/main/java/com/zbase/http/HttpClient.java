/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import android.content.Context;

public interface HttpClient {

    void init(Context context, HttpGlobalConfigure configure);

    Rep get(Req req);
    void getAsync(Req req, Callback callback);

    Rep post(Req req);
    void postAsync(Req req, Callback callback);

    Rep put(Req req);
    void putAsync(Req req, Callback callback);

    Rep delete(Req req);
    void deleteAsync(Req req, Callback callback);

    void uploadFileAsync(Req req, UploadListener listener);

    void downloadFileAsync(Req req, DownloadCallback callback);

    void release();

}
