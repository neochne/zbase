/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import com.zbase.x.json.JSONObject;

public class Response {

    private final int httpCode;

    private final JSONObject bodyJSONObject;

    public Response(int httpCode, JSONObject bodyJSONObject) {
        this.httpCode = httpCode;
        this.bodyJSONObject = bodyJSONObject;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public JSONObject getBodyJSONObject() {
        return bodyJSONObject;
    }

}
