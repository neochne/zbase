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

    public static String getString(JSONObject object, String key) {
        try {
            return String.valueOf(object.get(key));
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getInt(JSONObject object, String key) {
        try {
            return object.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getBoolean(JSONObject object, String key) {
        try {
            return object.getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject getJSONObject(JSONObject object, String key) {
        try {
            return object.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static JSONArray getJSONArray(JSONObject object, String key) {
        try {
            return object.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static String getString(JSONArray array, int index) {
        try {
            return String.valueOf(array.get(index));
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getInt(JSONArray array, int index) {
        try {
            return array.getInt(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getBoolean(JSONArray array, int index) {
        try {
            return array.getBoolean(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONArray getJSONArray(JSONArray array, int index) {
        try {
            return array.getJSONArray(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static JSONObject getJSONObject(JSONArray array, int index) {
        try {
            return array.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static JSONArray newJSONArray(String json) {
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static JSONObject newJSONObject(String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

}
