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
        try {
            return (object == null || key == null || "".equals(key.trim()) || !object.has(key) || object.isNull(key)) ? "" : String.valueOf(object.get(key));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getValueTrust(JSONObject object, String key) {
        try {
            return String.valueOf(object.get(key));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getIntValue(JSONObject object, String key) {
        try {
            return (object == null || key == null || "".equals(key.trim()) || !object.has(key)) ? 0 : object.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getIntValueTrust(JSONObject object, String key) {
        try {
            return object.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static boolean getBoolValue(JSONObject object, String key) {
        try {
            return object.getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static JSONObject getJSONObject(JSONObject object, String key) {
        try {
            return (object == null || key == null || "".equals(key.trim()) || !object.has(key) || object.isNull(key)) ? new JSONObject() : object.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static JSONObject getJSONObjectTrust(JSONObject object, String key) {
        try {
            return object.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static JSONArray getJSONArray(JSONObject object, String key) {
        try {
            return (object == null || key == null || "".equals(key.trim()) || !object.has(key) || object.isNull(key)) ? new JSONArray() : object.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    public static JSONArray getJSONArrayTrust(JSONObject object, String key) {
        try {
            return object.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    public static String getValue(JSONArray array, int index) {
        try {
            return (array == null || array.length() < 1 || index >= array.length() || array.isNull(index)) ? "" : String.valueOf(array.get(index));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getValueTrust(JSONArray array, int index) {
        try {
            return String.valueOf(array.get(index));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getIntValue(JSONArray array, int index) {
        try {
            return (array == null || array.length() < 1 || index >= array.length()) ? 0 : array.getInt(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getIntValueTrust(JSONArray array, int index) {
        try {
            return array.getInt(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static JSONObject getJSONObject(JSONArray array, int index) {
        try {
            return (array == null || array.length() < 1 || index >= array.length() || array.isNull(index)) ? new JSONObject() : array.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static JSONObject getJSONObjectTrust(JSONArray array, int index) {
        try {
            return array.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static JSONArray constructJSONArray(String jsonStr) {
        try {
            return new JSONArray(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    public static JSONObject constructJSONObject(String jsonStr) {
        try {
            return new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}
