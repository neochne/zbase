package com.zbase.http;

public enum HttpContentType {

    JSON("application/json"),

    FORM_URL_ENCODE("application/x-www-form-urlencoded"),

    FORM_MULTI_PART("multipart/form-data"),

    TEXT_PLAIN("text/plain");

    private final String contentType;

    HttpContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
    
}
