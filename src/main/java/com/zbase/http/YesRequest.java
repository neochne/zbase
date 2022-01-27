package com.zbase.http;

import org.json.JSONObject;

import java.io.File;

public final class YesRequest {

    private static final YesRequest yesRequest = new YesRequest();

    private YesHttpClient yesHttpClient;

    private String url;

    private String[] headerNamesAndValues;

    private String[] queryNamesAndValues;

    private Object[] bodyNamesAndValues;

    private JSONObject bodyJsonObject;

    private String bodyJsonString;

    private String fileKey;

    private File file;

    private File[] files;

    private YesRequest() {
    }

    public static YesRequest instance() {
        return yesRequest;
    }

    void setYesHttpClient(YesHttpClient yesHttpClient) {
        this.yesHttpClient = yesHttpClient;
    }

    public YesHttpClient getYesHttpClient() {
        return yesHttpClient;
    }

    public YesRequest url(String url) {
        this.url = url;
        return this;
    }

    public YesRequest addHeaderNamesAndValues(String... headerNameAndValues) {
        this.headerNamesAndValues = headerNameAndValues;
        return this;
    }

    public YesRequest addQueryNamesAndValues(String... queryNameAndValues) {
        this.queryNamesAndValues = queryNameAndValues;
        return this;
    }

    public YesRequest addBodyNamesAndValues(Object... bodyNameAndValues) {
        this.bodyNamesAndValues = bodyNameAndValues;
        return this;
    }

    public YesRequest addBodyJsonObject(JSONObject bodyJsonObject) {
        this.bodyJsonObject = bodyJsonObject;
        return this;
    }

    public YesRequest addBodyJsonString(String bodyJsonString) {
        this.bodyJsonString = bodyJsonString;
        return this;
    }

    public YesRequest addFileKey(String fileKey) {
        this.fileKey = fileKey;
        return this;
    }

    public YesRequest addFile(File file) {
        this.file = file;
        return this;
    }

    public YesRequest addFiles(File... files) {
        this.files = files;
        return this;
    }

    String getUrl() {
        if (queryNamesAndValues == null || queryNamesAndValues.length < 1) {
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        for (int i = 0; i < queryNamesAndValues.length; i++) {
            if (i == 0) {
                urlBuilder.append("?");
                urlBuilder.append(queryNamesAndValues[i]);
                urlBuilder.append("=");
                continue;
            }
            if (i % 2 == 1) {
                urlBuilder.append(queryNamesAndValues[i]);
                continue;
            }
            urlBuilder.append("&");
            urlBuilder.append(queryNamesAndValues[i]);
            urlBuilder.append("=");
        }
        return urlBuilder.toString();
    }

    String[] getHeaderNamesAndValues() {
        return headerNamesAndValues;
    }

    String[] getQueryNamesAndValues() {
        return queryNamesAndValues;
    }

    Object[] getBodyNamesAndValues() {
        return bodyNamesAndValues;
    }

    JSONObject getBodyJsonObject() {
        return bodyJsonObject;
    }

    String getBodyJsonString() {
        return bodyJsonString;
    }

    File getFile() {
        return file;
    }

    public String getFileKey() {
        return fileKey;
    }

    public File[] getFiles() {
        return files;
    }

    public YesResponse get() {
        return yesHttpClient.get(this);
    }

    public void getAsync(YesCallback callback) {
        yesHttpClient.getAsync(this, callback);
    }

    public YesResponse post() {
        return yesHttpClient.post(this);
    }

    public void postAsync(YesCallback callback) {
        yesHttpClient.postAsync(this, callback);
    }

    public void downloadFileAsync(YesCallback callback) {
        yesHttpClient.downloadFileAsync(this,callback);
    }

    public void uploadFileAsync(YesCallback callback) {
        yesHttpClient.uploadFileAsync(this,callback);
    }

}
