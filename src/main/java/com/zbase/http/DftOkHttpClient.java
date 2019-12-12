package com.zbase.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by neo on Thu,Aug 29,2019,at 10:09
 */
public class DftOkHttpClient implements HttpClient {

    private OkHttpClient mClient;

    @Override
    public void init(Context context, final HttpGlobalConfigure configure) {
        mClient = OkHttpClientFactory.getSslOkHttpClient(configure);
    }

    @Override
    public Rep get(Req req) {
        Rep rep = new Rep(0, "");
        try {
            Response response = mClient.newCall(getRequest(req, "GET")).execute();
            rep.setHttpCode(response.code());
            ResponseBody body = response.body();
            if (body != null) {
                rep.setResult(body.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rep;
    }

    @Override
    public void getAsync(final Req req, final Callback callback) {
        callback.onStart();
        mClient.newCall(getRequest(req, "GET")).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                if (req.isCallBackOnMainThread()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(null, e);
                            callback.onFinish();
                        }
                    });
                } else {
                    callback.onError(null, e);
                    callback.onFinish();
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (isExpired(response)) {
                    expired();
                    callback.onFinish();
                    return;
                }
                final Rep rep = new Rep(0, "");
                rep.setHttpCode(response.code());
                ResponseBody body = response.body();
                if (body != null) {
                    rep.setResult(body.string());
                }
                if (req.isCallBackOnMainThread()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(rep);
                            callback.onFinish();
                        }
                    });
                } else {
                    callback.onSuccess(rep);
                    callback.onFinish();
                }
            }
        });

    }

    @Override
    public Rep post(Req req) {
        Rep rep = new Rep(0, "");
        try {
            Response response = mClient.newCall(getRequest(req, "POST")).execute();
            rep.setHttpCode(response.code());
            ResponseBody body = response.body();
            if (body != null) {
                rep.setResult(body.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rep;
    }

    @Override
    public void postAsync(final Req req, final Callback callback) {
        callback.onStart();
        mClient.newCall(getRequest(req, "POST")).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                if (req.isCallBackOnMainThread()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(null, e);
                            callback.onFinish();
                        }
                    });
                } else {
                    callback.onError(null, e);
                    callback.onFinish();
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (isExpired(response)) {
                    expired();
                    callback.onFinish();
                    return;
                }
                final Rep rep = new Rep(0, "");
                rep.setHttpCode(response.code());
                ResponseBody body = response.body();
                if (body != null) {
                    rep.setResult(body.string());
                }
                if (req.isCallBackOnMainThread()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(rep);
                            callback.onFinish();
                        }
                    });
                } else {
                    callback.onSuccess(rep);
                    callback.onFinish();
                }
            }
        });

    }

    @Override
    public Rep put(Req req) {
        return null;
    }

    @Override
    public void putAsync(Req req, Callback callback) {

    }

    @Override
    public Rep delete(Req req) {
        return null;
    }

    @Override
    public void deleteAsync(Req req, Callback callback) {

    }

    @Override
    public void uploadFileAsync(Req req, UploadListener listener) {

    }

    @Override
    public void downloadFileAsync(Req req, DownloadCallback callback) {

    }

    public boolean isExpired(Response response) {
        return false;
    }

    public void expired() {

    }

    @Override
    public void release() {

    }

    private Request getRequest(Req req, String method) {
        Request.Builder builder = new Request.Builder();
        builder.url(req.getUrl());
        // add headers
        String globalHeaders[] = HttpGlobalConfigure.getGlobalHeaders();
        if (globalHeaders != null && globalHeaders.length > 0) {
            for (int i = 0; i < globalHeaders.length; i += 2) {
                builder.addHeader(globalHeaders[i], globalHeaders[i + 1]);
            }
        }
        String headers[] = req.getHeaders();
        if (headers != null && headers.length > 0) {
            for (int i = 0; i < headers.length; i += 2) {
                builder.addHeader(headers[i], headers[i + 1]);
            }
        }
        // add bodies
        if ("POST".equals(method)) {
            builder.post(RequestBody.create("", MediaType.get("application/json; charset=utf-8")));
        }
        String bodies[] = req.getBodies();
        if (bodies != null && bodies.length > 0) {
            JSONObject bodyJson = new JSONObject();
            for (int i = 0; i < bodies.length; i += 2) {
                try {
                    bodyJson.put(bodies[i], bodies[i + 1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            RequestBody body = RequestBody.create(bodyJson.toString(), MediaType.get("application/json; charset=utf-8"));
            builder.post(body);
        }
        String bodyJson = req.getBodyJson();
        if (bodyJson != null && !"".equals(bodyJson)) {
            builder.post(RequestBody.create(bodyJson, MediaType.get("application/json; charset=utf-8")));
        }
        return builder.build();
    }

}
