/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import org.json.JSONObject;

public class Response {

    private final int httpCode;

    private final JSONObject bodyJsonObject;

    public Response(int httpCode, JSONObject bodyJsonObject) {
        this.httpCode = httpCode;
        this.bodyJsonObject = bodyJsonObject;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public JSONObject getBodyJsonObject() {
        return bodyJsonObject;
    }

}
