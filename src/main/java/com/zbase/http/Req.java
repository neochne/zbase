/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import java.io.File;

public class Req {

    private String mUrl;
    private String[] mQueries;
    private String[] mHeaders;
    private String[] mBodies;
    private String mBodyJson;
    private File mFile;
    private boolean mCallBackOnMainThread = true;

    /*
     * =========
     * setters
     * =========
     */
    public Req(String url) {
        this.mUrl = url;
    }

    public Req queries(String... queries) {
        this.mQueries = queries;
        return this;
    }

    public Req headers(String... headers) {
        this.mHeaders = headers;
        return this;
    }

    public Req bodies(String... bodies){
        this.mBodies = bodies;
        return this;
    }

    public Req bodyJson(String bodyJson){
        this.mBodyJson = bodyJson;
        return this;
    }

    public Req file(File file){
        this.mFile = file;
        return this;
    }

    public Req isCallBackOnMainThread(boolean isCallBackOnMainThread){
        this.mCallBackOnMainThread = isCallBackOnMainThread;
        return this;
    }

    /*
     * ----------------
     * getter
     * ----------------
     */
    public String[] getQueries() {
        return mQueries;
    }

    public String[] getHeaders() {
        return mHeaders;
    }

    public String[] getBodies() {
        return mBodies;
    }

    public String getBodyJson() {
        return mBodyJson;
    }

    public File getFile() {
        return mFile;
    }

    public boolean isCallBackOnMainThread() {
        return mCallBackOnMainThread;
    }

    String getUrl() {
        StringBuilder urlBuilder = new StringBuilder(mUrl);
        if (mQueries != null && mQueries.length > 0) {
            for (int i = 0; i < mQueries.length; i++) {
                if (i == 0) {
                    urlBuilder.append("?");
                    urlBuilder.append(mQueries[i]);
                    urlBuilder.append("=");
                    continue;
                }
                if (1 == i % 2) {
                    urlBuilder.append(mQueries[i]);
                    continue;
                }
                if (0 == i % 2) {
                    urlBuilder.append("&");
                    urlBuilder.append(mQueries[i]);
                    urlBuilder.append("=");
                }
            }
        }
        return urlBuilder.toString();
    }

    /*
     * ----------------
     * request method
     * ----------------
     */
    public Rep get() {
        return Http.getHttpClient().get(this);
    }
    public void getAsync(Callback callback) {
        Http.getHttpClient().getAsync(this, callback);
    }

    public Rep post() {
        return Http.getHttpClient().post(this);
    }
    public void postAsync(Callback callback) {
        Http.getHttpClient().postAsync(this, callback);
    }

    public Rep put() {
        return Http.getHttpClient().put(this);
    }
    public void putAsync(Callback callback) {
        Http.getHttpClient().putAsync(this, callback);
    }

    public Rep delete() {
        return Http.getHttpClient().delete(this);
    }
    public void deleteAsync(Callback callback) {
        Http.getHttpClient().deleteAsync(this, callback);
    }

    public void uploadFileAsync(UploadListener listener){Http.getHttpClient().uploadFileAsync(this,listener);}

    public void downloadFileAsync(DownloadCallback callback){Http.getHttpClient().downloadFileAsync(this,callback);}

}
