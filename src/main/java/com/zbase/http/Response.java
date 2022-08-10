/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.http;

import com.zbase.x.json.JSONObjectX;

public class Response {

    private final int code;

    private final JSONObjectX json;

    public Response(int code, JSONObjectX json) {
        this.code = code;
        this.json = json;
    }

    public int getCode() {
        return code;
    }

    public JSONObjectX getJson() {
        return json;
    }

}
