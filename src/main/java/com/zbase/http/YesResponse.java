/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import org.json.JSONObject;

public class YesResponse {

    private final int httpCode;

    private final JSONObject bodyJsonObject;

    public YesResponse(int httpCode, JSONObject bodyJsonObject) {
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
