/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import com.zbase.x.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class YesHttpClient {

    /*
     * fields
     */
    private String url;

    String[] headerNamesAndValues;

    String[] queryNamesAndValues;

    String[] pathValues;

    Object[] bodyNamesAndValues;

    JSONObject bodyJsonObject;

    String bodyJsonString;

    String fileKey;

    File file;

    File[] files;

    Map<String, String> commonHeaderMap = new HashMap<>();

    /*
     * setter
     */
    public YesHttpClient url(String url) {
        this.url = url;
        return this;
    }

    String getUrl() {
        StringBuilder urlBuilder = new StringBuilder(url);

        // path parameters
        if (pathValues != null && pathValues.length > 0) {
            for (String pathValue : pathValues) {
                urlBuilder.append("/")
                        .append(pathValue);
            }
        }

        // query parameters
        if (queryNamesAndValues != null && queryNamesAndValues.length > 0) {
            for (int i = 0, len = queryNamesAndValues.length; i < len; i++) {
                if (i == 0) {
                    urlBuilder.append("?")
                            .append(queryNamesAndValues[i])
                            .append("=");
                    continue;
                }
                if (i % 2 == 1) {
                    urlBuilder.append(queryNamesAndValues[i]);
                    continue;
                }
                urlBuilder.append("&")
                        .append(queryNamesAndValues[i])
                        .append("=");
            }
        }
        return urlBuilder.toString();
    }

    public YesHttpClient addHeaderNamesAndValues(String... headerNameAndValues) {
        this.headerNamesAndValues = headerNameAndValues;
        return this;
    }

    public YesHttpClient addQueryNamesAndValues(String... queryNameAndValues) {
        this.queryNamesAndValues = queryNameAndValues;
        return this;
    }

    public YesHttpClient addPathValues(String... pathValues) {
        this.pathValues = pathValues;
        return this;
    }

    public YesHttpClient addBodyNamesAndValues(Object... bodyNameAndValues) {
        this.bodyNamesAndValues = bodyNameAndValues;
        return this;
    }

    public YesHttpClient addBodyJsonObject(JSONObject bodyJsonObject) {
        this.bodyJsonObject = bodyJsonObject;
        return this;
    }

    public YesHttpClient addBodyJsonString(String bodyJsonString) {
        this.bodyJsonString = bodyJsonString;
        return this;
    }

    public YesHttpClient addFileKey(String fileKey) {
        this.fileKey = fileKey;
        return this;
    }

    public YesHttpClient addFile(File file) {
        this.file = file;
        return this;
    }

    public YesHttpClient addFiles(File... files) {
        this.files = files;
        return this;
    }

    void addCommonHeader(String name, String value) {
        commonHeaderMap.put(name, value);
    }

    /*
     * abstract methods
     */
    public abstract Response get();

    public abstract void getAsync(Callback callback);

    public abstract void downloadFileAsync(Callback callback);

    public abstract Response post();

    public abstract void postAsync(Callback callback);

    public abstract void uploadFileAsync(Callback callback);

    public abstract Response put();

    public abstract void putAsync(Callback callback);

    public abstract Response patch();

    public abstract void patchAsync(Callback callback);

    public abstract Response delete();

    public abstract void deleteAsync(Callback callback);

}
