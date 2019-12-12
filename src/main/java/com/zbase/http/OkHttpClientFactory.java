package com.zbase.http;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by neo on Wed,Dec 11,2019,at 10:57
 */
public final class OkHttpClientFactory {

    private OkHttpClientFactory() {
    }

    public static OkHttpClient getOkHttpClient(final HttpGlobalConfigure configure) {
        return new OkHttpClient.Builder()
                .connectTimeout(configure.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(configure.getRequestTimeout(), TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request.Builder reqBuilder = chain.request().newBuilder();
                        String[] headers = configure.getHeaders();
                        if (headers != null && headers.length > 0) {
                            for (int i = 0; i < headers.length; i += 2) {
                                reqBuilder.addHeader(headers[i], headers[i + 1]);
                            }
                        }
                        return chain.proceed(reqBuilder.build());
                    }
                }).build();
    }

    public static OkHttpClient getSslOkHttpClient(final HttpGlobalConfigure configure) {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            TrustAllHttpsCerts trustAllHttpsCerts = new TrustAllHttpsCerts();
            sc.init(null,  new TrustManager[] { trustAllHttpsCerts }, new SecureRandom());
            SSLSocketFactory ssfFactory = sc.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            return builder
                    .connectTimeout(configure.getConnectTimeout(), TimeUnit.SECONDS)
                    .readTimeout(configure.getRequestTimeout(), TimeUnit.SECONDS)
                    .sslSocketFactory(ssfFactory,trustAllHttpsCerts)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request.Builder reqBuilder = chain.request().newBuilder();
                            String[] headers = configure.getHeaders();
                            if (headers != null && headers.length > 0) {
                                for (int i = 0; i < headers.length; i += 2) {
                                    reqBuilder.addHeader(headers[i], headers[i + 1]);
                                }
                            }
                            return chain.proceed(reqBuilder.build());
                        }
                    }).build();
        } catch (Exception e) {
            return getOkHttpClient(configure);
        }
    }

}
