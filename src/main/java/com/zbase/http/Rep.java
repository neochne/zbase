/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

public class Rep {

    private int httpCode;

    private String result;

    public Rep(int httpCode, String result) {
        this.httpCode = httpCode;
        this.result = result;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Rep{" +
                "httpCode=" + httpCode +
                ", result='" + result + '\'' +
                '}';
    }

}
