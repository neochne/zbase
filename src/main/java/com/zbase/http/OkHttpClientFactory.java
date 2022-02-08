package com.zbase.http;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by neo on Wed,Dec 11,2019,at 10:57
 */
public final class OkHttpClientFactory {

    private OkHttpClientFactory() {}

    public static OkHttpClient getOkHttpClient(int connectTimeout, int readTimeout, Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    public static OkHttpClient getSslOkHttpClient(int connectTimeout, int readTimeout, Interceptor interceptor) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLS");
        TrustAllHttpsCerts trustAllHttpsCerts = new TrustAllHttpsCerts();
        sc.init(null, new TrustManager[]{trustAllHttpsCerts}, new SecureRandom());
        SSLSocketFactory ssfFactory = sc.getSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .sslSocketFactory(ssfFactory, trustAllHttpsCerts)
                .hostnameVerifier((hostname, session) -> true)
                .addInterceptor(interceptor)
                .build();
    }

    private static class TrustAllHttpsCerts implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
