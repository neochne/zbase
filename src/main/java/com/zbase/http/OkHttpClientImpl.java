/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import androidx.annotation.NonNull;

import com.zbase.util.FileUtils;
import com.zbase.util.JsonUtils;
import com.zbase.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void getAsync(Callback callback) {
        enqueue(makeJsonRequest(HttpMethod.GET), callback);
    }

    @Override
    public void downloadFileAsync(Callback callback) {
        okHttpClient.newCall(makeJsonRequest(HttpMethod.GET)).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) {
                int httpCode = response.code();
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    callback.onSuccess(new Response(httpCode, new JSONObject()));
                    long fileTotalLength = responseBody.contentLength();
                    FileUtils.writeFileWithProgress(
                            file,
                            responseBody.byteStream(),
                            fileTotalLength,
                            (progress) -> callback.onProgress(progress, file.getName(), fileTotalLength));
                } else {
                    callback.onFail(new Response(httpCode, new JSONObject()));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onException(e);
            }

        });
    }

    @Override
    public Response post() {
        return execute(makeJsonRequest(HttpMethod.POST));
    }

    @Override
    public void postAsync(Callback callback) {
        enqueue(makeJsonRequest(HttpMethod.POST), callback);
    }

    @Override
    public void uploadFileAsync(Callback callback) {
        enqueue(makeFileFormRequest(callback), callback);
    }

    @Override
    public Response put() {
        return execute(makeJsonRequest(HttpMethod.PUT));
    }

    @Override
    public void putAsync(Callback callback) {
        enqueue(makeJsonRequest(HttpMethod.PUT), callback);
    }

    @Override
    public Response patch() {
        return execute(makeJsonRequest(HttpMethod.PATCH));
    }

    @Override
    public void patchAsync(Callback callback) {
        enqueue(makeJsonRequest(HttpMethod.PATCH), callback);
    }

    @Override
    public Response delete() {
        return execute(makeJsonRequest(HttpMethod.DELETE));
    }

    @Override
    public void deleteAsync(Callback callback) {
        enqueue(makeJsonRequest(HttpMethod.DELETE), callback);
    }

    private Response execute(Request request) {
        try {
            okhttp3.Response response = okHttpClient.newCall(request).execute();
            int httpCode = response.code();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                return new Response(httpCode, JsonUtils.newJSONObject(responseBody.string()));
            } else {
                throw new IOException("response is not successful，httpCode = " + httpCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(EXCEPTION_CODE, new JSONObject());
        }
    }

    private void enqueue(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {

            /**
             * onResponse and onFailure method called on work thread,not in main thread
             */
            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                int httpCode = response.code();
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    callback.onSuccess(new Response(httpCode, JsonUtils.newJSONObject(responseBody.string())));
                } else {
                    callback.onFail(new Response(httpCode, new JSONObject()));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onException(e);
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
            JSONObject bodyJson = new JSONObject();
            for (int i = 0; i < bodyNamesAndValues.length; i += 2) {
                try {
                    bodyJson.put(String.valueOf(bodyNamesAndValues[i]), bodyNamesAndValues[i + 1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private Request makeFileFormRequest(Callback callback) {
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
            multipartBuilder.addFormDataPart(fileKey, fileName, new FileProgressRequestBody(oriRequestBody, fileName, callback));
        }
        if (files != null && files.length > 0) {
            for (File uploadedFile : files) {
                if (uploadedFile == null) {
                    continue;
                }
                String fileName = uploadedFile.getName();
                RequestBody oriRequestBody = RequestBody.create(uploadedFile, MediaType.get(HttpContentType.FORM_MULTI_PART.getContentType()));
                multipartBuilder.addFormDataPart(fileKey, fileName, new FileProgressRequestBody(oriRequestBody, fileName, callback));
            }
        }
        return requestBuilder.post(multipartBuilder.build()).build();
    }

}
