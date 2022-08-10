package com.zbase.x.json;

import org.json.JSONException;
import org.json.JSONObject;

public final class JSONObjectX extends JSONObject {

    public JSONObjectX() {
    }

    public JSONObjectX(String json) throws JSONException {
        super(json);
    }

    public static JSONObjectX create(String json) {
        try {
            return new JSONObjectX(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Add
     */
    public JSONObjectX add(String key, int value) {
        try {
            put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONObjectX add(String key, double value) {
        try {
            put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONObjectX add(String key, long value) {
        try {
            put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONObjectX add(String key, boolean value) {
        try {
            put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONObjectX add(String key, Object value) {
        try {
            put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    /*
     * Get
     */
    public String get2string(String key) {
        try {
            return String.valueOf(get(key));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String get1string(String key) {
        try {
            return getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int get1int(String key) {
        try {
            return getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public long get1long(String key) {
        try {
            return getLong(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double get1double(String key) {
        try {
            return getDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean get1boolean(String key) {
        try {
            return getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object get1object(String key) {
        try {
            return get(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArrayX get1JsonArray(String key) {
        try {
            return (JSONArrayX) getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArrayX();
    }

    public JSONObjectX get1JsonObject(String key) {
        try {
            return (JSONObjectX) getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObjectX();
    }

}
