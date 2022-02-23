/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonUtils {

    private JsonUtils() {
    }

    public static String getString(JSONObject jsonObject, String key) {
        try {
            return String.valueOf(jsonObject.get(key));
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getInt(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getBoolean(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static JSONObject getJSONObject(JSONArray jsonArray, int index) {
        try {
            return jsonArray.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static JSONArray newJSONArray(String jsonString) {
        try {
            return new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static JSONObject newJSONObject(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

}
