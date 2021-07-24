/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonUtils {

    private JsonUtils() {
    }

    public static String getValue(JSONObject object, String key) {
        if (object == null
                || key == null
                || "".equals(key.trim())) {
            return "";
        }
        String value = null;
        try {
            if (object.has(key)) {
                value = String.valueOf(object.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value == null || "null".equals(value) ? "" : value;
    }

    public static String getValue(JSONArray array, int index) {
        if (array == null || array.length() < 1 || index >= array.length()) {
            return "";
        }
        String value = null;
        try {
            value = array.getString(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value == null || "null".equals(value) ? "" : value;
    }

    public static JSONObject getJSONObject(JSONObject object, String key) {
        if (object == null || key == null || "".equals(key.trim())) {
            return new JSONObject();
        }
        if (!object.has(key)) {
            return new JSONObject();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = object.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject == null ? new JSONObject() : jsonObject;
    }

    public static JSONObject getJSONObject(JSONArray array, int index) {
        if (array == null || array.length() < 1 || index >= array.length()) {
            return new JSONObject();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = array.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject == null ? new JSONObject() : jsonObject;
    }

    public static JSONArray getJSONArray(JSONObject object, String key) {
        if (object == null || key == null || "".equals(key.trim())) {
            return new JSONArray();
        }
        if (!object.has(key)) {
            return new JSONArray();
        }
        JSONArray array = null;
        try {
            array = object.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array == null ? new JSONArray() : array;
    }
    
    public static JSONArray constructArray(String arrayStr) {
        try {
            return new JSONArray(arrayStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject constructObject(String arrayStr) {
        try {
            return new JSONObject(arrayStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
