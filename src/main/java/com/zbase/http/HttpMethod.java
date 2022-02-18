package com.zbase.http;

public enum HttpMethod {

    GET("GET"),

    POST("POST"),

    PATCH("PATCH"),

    PUT("PUT"),

    DELETE("DELETE");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

}
