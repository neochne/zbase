/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

public class HttpGlobalConfigure {

    private int connectTimeout;
    private int requestTimeout;
    private int retryCount;
    private String cachePath;
    private String cookiePath;
    private String[] headers;
    private static String[] sGlobalHeaders;

    public static void setGlobalHeaders(String ...globalHeaders){
        sGlobalHeaders = globalHeaders;
    }

    public static String[] getGlobalHeaders(){
        return sGlobalHeaders;
    }

    private HttpGlobalConfigure(Builder builder) {
        connectTimeout = builder.connectTimeout;
        requestTimeout = builder.requestTimeout;
        cachePath = builder.cachePath;
        cookiePath = builder.cookiePath;
        retryCount = builder.retryCount;
        headers = builder.headers;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public String getCachePath() {
        return cachePath;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public String[] getHeaders() {
        return headers;
    }

    public static class Builder {

        private int connectTimeout;
        private int requestTimeout;
        private int retryCount;
        private String cachePath;
        private String cookiePath;
        private String[] headers;

        public Builder connectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder requestTimeout(int requestTimeout) {
            this.requestTimeout = requestTimeout;
            return this;
        }

        public Builder cachePath(String cachePath) {
            this.cachePath = cachePath;
            return this;
        }

        public Builder cookiePath(String cookiePath) {
            this.cookiePath = cookiePath;
            return this;
        }

        public Builder retryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public Builder headers(String... headers) {
            this.headers = headers;
            return this;
        }

        public HttpGlobalConfigure build() {
            return new HttpGlobalConfigure(this);
        }

    }

}
