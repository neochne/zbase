/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import com.zbase.x.json.JSONObject;

public class Response {

    private final int code;

    private final JSONObject json;

    public Response(int code, JSONObject json) {
        this.code = code;
        this.json = json;
    }

    public int getCode() {
        return code;
    }

    public JSONObject getJson() {
        return json;
    }

}
