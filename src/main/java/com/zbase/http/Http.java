/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import android.content.Context;

public class Http {

    private static HttpClient sHttpClient;

    private Http(){}

    public static void init(Context context, HttpClient httpClient,HttpGlobalConfigure configure){
        Http.sHttpClient = httpClient;
        Http.sHttpClient.init(context,configure);
    }

    static HttpClient getHttpClient() {
        if (sHttpClient == null) throw new RuntimeException("you must init HttpClient first");
        return sHttpClient;
    }

    public static Req withUrl(String url){
        return new Req(url);
    }

}
