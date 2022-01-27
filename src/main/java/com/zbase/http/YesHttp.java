package com.zbase.http;

public final class YesHttp {

    public static void initClient(YesHttpClient httpClient) {
        YesRequest.instance().setYesHttpClient(httpClient);
    }

    public static void addCommonHeader(String name,String value) {
        YesRequest.instance().getYesHttpClient().addCommonHeader(name,value);
    }

    public static YesRequest request(String url) {
        return YesRequest.instance().url(url);
    }

}
