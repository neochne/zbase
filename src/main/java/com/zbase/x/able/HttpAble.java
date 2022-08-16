package com.zbase.x.able;

import com.zbase.http.JDataCallback;
import com.zbase.http.YesHttp;

public interface HttpAble {

    default void get(String url,
                    String[] queryParams,
                    JDataCallback callback) {
        YesHttp
                .request(url)
                .addQueryNamesAndValues(queryParams)
                .getAsync(callback);
    }

    default void postForm(String url, String[] formParams, JDataCallback callback) {
        YesHttp
                .request(url)
                .addQueryNamesAndValues(formParams)
                .postAsync(callback);
    }

    default void postBody(String url, Object[] bodyParams, JDataCallback callback) {
        YesHttp
                .request(url)
                .addBodyNamesAndValues(bodyParams)
                .postAsync(callback);
    }

}
