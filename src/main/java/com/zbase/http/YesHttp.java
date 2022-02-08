package com.zbase.http;

public final class YesHttp {

    private static YesHttpClient yesHttpClient;

    public static void initClient(YesHttpClient httpClient) {
        yesHttpClient = httpClient;
    }

    public static void addCommonHeader(String name,String value) {
        yesHttpClient.addCommonHeader(name,value);
    }

    public static YesHttpClient request(String url) {
        return yesHttpClient.url(url);
    }

}
