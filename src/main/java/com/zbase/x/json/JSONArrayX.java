package com.zbase.x.json;

import org.json.JSONArray;
import org.json.JSONException;

public final class JSONArrayX extends JSONArray {

    public JSONArrayX() {
    }

    public JSONArrayX(String json) throws JSONException {
        super(json);
    }

    public static JSONArrayX create(String json) {
        try {
            return new JSONArrayX(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Add
     */
    public JSONArrayX add(int value) {
        put(value);
        return this;
    }

    public JSONArrayX add(double value) {
        try {
            put(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONArrayX add(long value) {
        put(value);
        return this;
    }

    public JSONArrayX add(boolean value) {
        put(value);
        return this;
    }

    public JSONArrayX add(Object value) {
        put(value);
        return this;
    }

    public JSONArrayX add(int index, int value) {
        try {
            put(index, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONArrayX add(int index, double value) {
        try {
            put(index, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONArrayX add(int index, long value) {
        try {
            put(index, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONArrayX add(int index, boolean value) {
        try {
            put(index, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JSONArrayX add(int index, Object value) {
        try {
            put(index, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    /*
     * Get
     */
    public String get2string(int index) {
        try {
            return String.valueOf(get(index));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String get1string(int index) {
        try {
            return getString(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int get1int(int index) {
        try {
            return getInt(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public long get1long(int index) {
        try {
            return getLong(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double get1double(int index) {
        try {
            return getDouble(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean get1boolean(int index) {
        try {
            return getBoolean(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object get1object(int index) {
        try {
            return get(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArrayX get1JsonArray(int index) {
        try {
            return (JSONArrayX) getJSONArray(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArrayX();
    }

    public JSONObjectX get1JsonObject(int index) {
        try {
            return (JSONObjectX) getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObjectX();
    }

}
