/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import androidx.annotation.NonNull;

import com.zbase.util.FileUtils;
import com.zbase.util.StringUtils;
import com.zbase.x.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public final class OkHttpClientImpl extends YesHttpClient {

    private final OkHttpClient okHttpClient;

    private static final int EXCEPTION_CODE = -1;

    public OkHttpClientImpl(int connectTimeout, int readTimeout) throws Exception {
        okHttpClient = OkHttpClientFactory.getSslOkHttpClient(connectTimeout, readTimeout, chain -> {
            Request.Builder builder = chain.request().newBuilder();
            for (Map.Entry<String, String> entry : commonHeaderMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                builder.header(key, value);
            }
            // chain.proceed fail will throw a IOException and invoke okhttp3.Callback.onFailure() method
            // if return a http code not 200，chain.proceed not throw a IOException
            return chain.proceed(builder.build());
        });
    }

    @Override
    public Response get() {
        return execute(makeJsonRequest(HttpMethod.GET));
    }

    @Override
    public void getAsync(HttpCallback httpCallback) {
        enqueue(makeJsonRequest(HttpMethod.GET), httpCallback);
    }

    @Override
    public void downloadFileAsync(HttpCallback httpCallback) {
        httpCallback.onStart();
        okHttpClient.newCall(makeJsonRequest(HttpMethod.GET)).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) {
                httpCallback.onFinish();
                int httpCode = response.code();
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    httpCallback.onSuccess(new Response(httpCode, JSONObject.create()));
                    long fileTotalLength = responseBody.contentLength();
                    FileUtils.saveFileWithProgress(
                            file,
                            responseBody.byteStream(),
                            fileTotalLength,
                            (progress) -> httpCallback.onProgress(progress, file.getName(), fileTotalLength));
                } else {
                    httpCallback.onFail(new Response(httpCode, JSONObject.create()));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                httpCallback.onFinish();
                httpCallback.onException(e);
            }

        });
    }

    @Override
    public Response post() {
        return execute(makeJsonRequest(HttpMethod.POST));
    }

    @Override
    public void postAsync(HttpCallback httpCallback) {
        enqueue(makeJsonRequest(HttpMethod.POST), httpCallback);
    }

    @Override
    public void uploadFileAsync(HttpCallback httpCallback) {
        enqueue(makeFileFormRequest(httpCallback), httpCallback);
    }

    @Override
    public Response put() {
        return execute(makeJsonRequest(HttpMethod.PUT));
    }

    @Override
    public void putAsync(HttpCallback httpCallback) {
        enqueue(makeJsonRequest(HttpMethod.PUT), httpCallback);
    }

    @Override
    public Response patch() {
        return execute(makeJsonRequest(HttpMethod.PATCH));
    }

    @Override
    public void patchAsync(HttpCallback httpCallback) {
        enqueue(makeJsonRequest(HttpMethod.PATCH), httpCallback);
    }

    @Override
    public Response delete() {
        return execute(makeJsonRequest(HttpMethod.DELETE));
    }

    @Override
    public void deleteAsync(HttpCallback httpCallback) {
        enqueue(makeJsonRequest(HttpMethod.DELETE), httpCallback);
    }

    private Response execute(Request request) {
        try {
            okhttp3.Response response = okHttpClient.newCall(request).execute();
            int httpCode = response.code();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                return new Response(httpCode, JSONObject.create(responseBody.string()));
            } else {
                throw new IOException("response is not successful，httpCode = " + httpCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(EXCEPTION_CODE, JSONObject.create());
        }
    }

    private void enqueue(Request request, HttpCallback httpCallback) {
        httpCallback.onStart();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {

            /**
             * onResponse and onFailure method called on work thread,not in main thread
             */
            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                httpCallback.onFinish();
                int httpCode = response.code();
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    httpCallback.onSuccess(new Response(httpCode, JSONObject.create(responseBody.string())));
                } else {
                    httpCallback.onFail(new Response(httpCode, JSONObject.create()));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                httpCallback.onFinish();
                httpCallback.onException(e);
            }

        });
    }

    /*
     * make request
     */

    private void addHeaders(Request.Builder builder, String[] headerNamesAndValues) {
        if (headerNamesAndValues != null && headerNamesAndValues.length > 0) {
            for (int i = 0; i < headerNamesAndValues.length; i += 2) {
                builder.addHeader(headerNamesAndValues[i], headerNamesAndValues[i + 1]);
            }
        }
    }

    private Request makeJsonRequest(HttpMethod method) {
        Request.Builder builder = new Request.Builder();
        builder.url(getUrl());

        /*
         * add header
         */
        addHeaders(builder, headerNamesAndValues);

        /*
         * name value body
         */
        String jsonStringBody = "";
        if (bodyNamesAndValues != null && bodyNamesAndValues.length > 0) {
            JSONObject bodyJson = JSONObject.create();
            for (int i = 0; i < bodyNamesAndValues.length; i += 2) {
                bodyJson.put(String.valueOf(bodyNamesAndValues[i]), bodyNamesAndValues[i + 1]);
            }
            jsonStringBody = bodyJson.toString();
        }

        /*
         * json string body
         */
        if (StringUtils.isEmpty(jsonStringBody)) {
            jsonStringBody = (bodyJsonString == null || bodyJsonString.trim().length() < 1) ? "" : bodyJsonString;
        }

        /*
         * json object body
         */
        if (StringUtils.isEmpty(jsonStringBody)) {
            jsonStringBody = bodyJsonObject == null ? "" : bodyJsonObject.toString();
        }

        /*
         * request method
         */
        switch (method) {
            case GET:
                builder.get();
                break;
            case POST:
                builder.post(RequestBody.create(jsonStringBody, MediaType.get(HttpContentType.JSON.getContentType())));
                break;
            case PUT:
                builder.put(RequestBody.create(jsonStringBody, MediaType.get(HttpContentType.JSON.getContentType())));
                break;
            case PATCH:
                builder.patch(RequestBody.create(jsonStringBody, MediaType.get(HttpContentType.JSON.getContentType())));
                break;
            case DELETE:
                builder.delete(RequestBody.create(jsonStringBody, MediaType.get(HttpContentType.JSON.getContentType())));
                break;
        }
        return builder.build();
    }

    private Request makeFileFormRequest(HttpCallback httpCallback) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(getUrl());

        /*
         * add header
         */
        addHeaders(requestBuilder, headerNamesAndValues);

        /*
         * add form data
         */
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        if (bodyNamesAndValues != null && bodyNamesAndValues.length > 0) {
            for (int i = 0; i < bodyNamesAndValues.length; i += 2) {
                multipartBuilder.addFormDataPart(String.valueOf(bodyNamesAndValues[i]), String.valueOf(bodyNamesAndValues[i + 1]));
            }
        }
        if (file != null) {
            RequestBody oriRequestBody = RequestBody.create(file, MediaType.get(HttpContentType.FORM_MULTI_PART.getContentType()));
            String fileName = file.getName();
            multipartBuilder.addFormDataPart(fileKey, fileName, new FileProgressRequestBody(oriRequestBody, fileName, httpCallback));
        }
        if (files != null && files.length > 0) {
            for (File uploadedFile : files) {
                if (uploadedFile == null) {
                    continue;
                }
                String fileName = uploadedFile.getName();
                RequestBody oriRequestBody = RequestBody.create(uploadedFile, MediaType.get(HttpContentType.FORM_MULTI_PART.getContentType()));
                multipartBuilder.addFormDataPart(fileKey, fileName, new FileProgressRequestBody(oriRequestBody, fileName, httpCallback));
            }
        }
        return requestBuilder.post(multipartBuilder.build()).build();
    }

}
