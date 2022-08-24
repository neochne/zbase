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

        // Path parameters
        if (pathValues != null && pathValues.length > 0) {
            for (String pathValue : pathValues) {
                urlBuilder
                        .append("/")
                        .append(pathValue);
            }
        }

        // Query parameters
        if (queryNamesAndValues != null && queryNamesAndValues.length > 0) {
            // Add first parameters
            urlBuilder
                    .append("?")
                    .append(queryNamesAndValues[0])
                    .append("=")
                    .append(queryNamesAndValues[1]);
            // Traverse the next parameters from the index 2
            for (int i = 2, len = queryNamesAndValues.length; i < len; i++) {
                if ((i & 1) == 0) {// even: echo $((0&1))
                    urlBuilder
                            .append("&")
                            .append(queryNamesAndValues[i])
                            .append("=");
                } else {// odd
                    urlBuilder.append(queryNamesAndValues[i]);
                }
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

    public abstract void getAsync(HttpCallback httpCallback);

    public abstract void downloadFileAsync(HttpCallback httpCallback);

    public abstract Response post();

    public abstract void postAsync(HttpCallback httpCallback);

    public abstract void uploadFileAsync(HttpCallback httpCallback);

    public abstract Response put();

    public abstract void putAsync(HttpCallback httpCallback);

    public abstract Response patch();

    public abstract void patchAsync(HttpCallback httpCallback);

    public abstract Response delete();

    public abstract void deleteAsync(HttpCallback httpCallback);

}
