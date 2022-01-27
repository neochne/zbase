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
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class OkHttpClientImpl implements YesHttpClient {

    private final OkHttpClient okHttpClient;

    public OkHttpClientImpl(int connectTimeout, int readTimeout) throws Exception {
        SSLContext sc = SSLContext.getInstance("TLS");
        TrustAllHttpsCerts trustAllHttpsCerts = new TrustAllHttpsCerts();
        sc.init(null, new TrustManager[]{trustAllHttpsCerts}, new SecureRandom());
        SSLSocketFactory ssfFactory = sc.getSocketFactory();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .sslSocketFactory(ssfFactory, trustAllHttpsCerts)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        for (Map.Entry<String, String> entry : commonHeaderMap.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            builder.header(key, value);
                        }
                        // chain.proceed fail will throw a IOException
                        // if return a http code not 200，chain.proceed not throw a IOException
                        return chain.proceed(builder.build());
                    }
                })
                .build();
    }

    @Override
    public YesResponse get(YesRequest request) {
        int httpCode = 201;
        try {
            Response response = okHttpClient.newCall(makeJsonRequest(request, HttpMethod.GET)).execute();
            httpCode = response.code();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                return new YesResponse(httpCode, JsonUtils.constructJSONObject(responseBody.string()));
            } else {
                throw new IOException("response is not successful，httpCode = " + httpCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new YesResponse(httpCode, new JSONObject());
        }
    }

    @Override
    public void getAsync(YesRequest request, YesCallback callback) {
        okHttpClient.newCall(makeJsonRequest(request, HttpMethod.GET)).enqueue(new okhttp3.Callback() {

            /**
             * onResponse and onFailure method called on work thread,not in main thread
             */
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int httpCode = response.code();
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    callback.onSuccess(new YesResponse(httpCode, JsonUtils.constructJSONObject(responseBody.string())));
                } else {
                    callback.onFail(new YesResponse(httpCode, new JSONObject()));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onException(e);
            }

        });
    }

    @Override
    public YesResponse post(YesRequest request) {
        int httpCode = 201;
        try {
            Response response = okHttpClient.newCall(makeJsonRequest(request, HttpMethod.POST)).execute();
            httpCode = response.code();
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                return new YesResponse(httpCode, JsonUtils.constructJSONObject(responseBody.string()));
            } else {
                throw new IOException("response is not successful，httpCode = " + httpCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new YesResponse(httpCode, new JSONObject());
        }
    }

    @Override
    public void postAsync(YesRequest request, YesCallback callback) {
        okHttpClient.newCall(makeJsonRequest(request, HttpMethod.POST)).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int httpCode = response.code();
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    // okhttp3.ResponseBody.string() method can only call once
                    callback.onSuccess(new YesResponse(httpCode, JsonUtils.constructJSONObject(responseBody.string())));
                } else {
                    callback.onFail(new YesResponse(httpCode, new JSONObject()));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onException(e);
            }

        });
    }

    @Override
    public void downloadFileAsync(YesRequest request, YesCallback callback) {
        okHttpClient.newCall(makeJsonRequest(request, HttpMethod.GET)).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int httpCode = response.code();
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    callback.onSuccess(new YesResponse(httpCode, new JSONObject()));
                    long fileTotalByte = responseBody.contentLength();
                    FileUtils.writeFileWithProgress(
                            request.getFile(),
                            responseBody.byteStream(),
                            fileTotalByte,
                            (progress) -> callback.onProgress(progress, fileTotalByte));
                } else {
                    callback.onFail(new YesResponse(httpCode, new JSONObject()));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onException(e);
            }

        });
    }

    @Override
    public void uploadFileAsync(YesRequest request, YesCallback callback) {
        okHttpClient.newCall(makeMultipartRequest(request)).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int httpCode = response.code();
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    // okhttp3.ResponseBody.string() method can only call once
                    callback.onSuccess(new YesResponse(httpCode, JsonUtils.constructJSONObject(responseBody.string())));
                } else {
                    callback.onFail(new YesResponse(httpCode, new JSONObject()));
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onException(e);
            }

        });
    }

    private Request makeJsonRequest(YesRequest request, HttpMethod method) {
        Request.Builder builder = new Request.Builder();
        builder.url(request.getUrl());

        /*
         * add header
         */
        String[] headerNamesAndValues = request.getHeaderNamesAndValues();
        if (headerNamesAndValues != null && headerNamesAndValues.length > 0) {
            for (int i = 0; i < headerNamesAndValues.length; i += 2) {
                builder.addHeader(headerNamesAndValues[i], headerNamesAndValues[i + 1]);
            }
        }

        /*
         * name value body
         */
        String jsonStringBody = "";
        Object[] bodyNamesAndValues = request.getBodyNamesAndValues();
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
            String requestBodyJsonString = request.getBodyJsonString();
            jsonStringBody = (requestBodyJsonString == null || requestBodyJsonString.trim().length() < 1) ? "" : requestBodyJsonString;
        }

        /*
         * json object body
         */
        if (StringUtils.isEmpty(jsonStringBody)) {
            JSONObject requestJsonObject = request.getBodyJsonObject();
            jsonStringBody = requestJsonObject == null ? "" : requestJsonObject.toString();
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
        }
        return builder.build();
    }

    private Request makeMultipartRequest(YesRequest request) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(request.getUrl());


        /*
         * add header
         */
        String[] headerNamesAndValues = request.getHeaderNamesAndValues();
        if (headerNamesAndValues != null && headerNamesAndValues.length > 0) {
            for (int i = 0; i < headerNamesAndValues.length; i += 2) {
                requestBuilder.addHeader(headerNamesAndValues[i], headerNamesAndValues[i + 1]);
            }
        }


        /*
         * add form data
         */
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        Object[] bodyNamesAndValues = request.getBodyNamesAndValues();
        if (bodyNamesAndValues != null && bodyNamesAndValues.length > 0) {
            for (int i = 0; i < bodyNamesAndValues.length; i += 2) {
                multipartBuilder.addFormDataPart(String.valueOf(bodyNamesAndValues[i]), String.valueOf(bodyNamesAndValues[i + 1]));
            }
        }
        File uploadFile = request.getFile();
        if (uploadFile != null) {
            multipartBuilder.addFormDataPart(request.getFileKey(), uploadFile.getName(), RequestBody.create(uploadFile, MediaType.get(HttpContentType.FORM_MULTI_PART.getContentType())));
        }
        File[] uploadFiles = request.getFiles();
        if (uploadFiles != null && uploadFiles.length > 0) {
            for (File file : uploadFiles) {
                if (file == null) {
                    continue;
                }
                multipartBuilder.addFormDataPart(request.getFileKey(), file.getName(), RequestBody.create(file, MediaType.get(HttpContentType.FORM_MULTI_PART.getContentType())));
            }
        }
        return requestBuilder.post(multipartBuilder.build()).build();
    }

}
