/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

public final class SpUtils {

    private SpUtils() {
    }

    public static void save(Context context, String file, String key, String value) {
        if (isArgsEmpty(context, file, key)) return;
        context.getSharedPreferences(file, Context.MODE_PRIVATE).edit().putString(key, value == null || "null".equals(value.trim()) ? "" : value).apply();
    }

    public static void save(Context context, String file, String key, boolean value) {
        if (isArgsEmpty(context, file, key)) return;
        context.getSharedPreferences(file, Context.MODE_PRIVATE).edit().putBoolean(key, value).apply();
    }

    public static void save(Context context, String file, String key, int value) {
        if (isArgsEmpty(context, file, key)) return;
        context.getSharedPreferences(file, Context.MODE_PRIVATE).edit().putInt(key, value).apply();
    }

    public static void save(Context context, String file, String key, float value) {
        if (isArgsEmpty(context, file, key)) return;
        context.getSharedPreferences(file, Context.MODE_PRIVATE).edit().putFloat(key, value).apply();
    }

    public static void save(Context context, String file, String key, Set<String> values) {
        if (isArgsEmpty(context, file, key) || values == null || values.size() < 1) return;
        context.getSharedPreferences(file, Context.MODE_PRIVATE).edit().putStringSet(key, values).apply();
    }

    public static String get(Context context, String file, String key, String dftValue) {
        if (isArgsEmpty(context, file, key) || dftValue == null || "".equals(dftValue.trim())) return "";
        return context.getSharedPreferences(file, Context.MODE_PRIVATE).getString(key, dftValue);
    }

    public static boolean get(Context context, String file, String key, boolean dftValue) {
        if (isArgsEmpty(context, file, key)) return dftValue;
        return context.getSharedPreferences(file, Context.MODE_PRIVATE).getBoolean(key, dftValue);
    }

    public static int get(Context context, String file, String key, int dftVaule) {
        if (isArgsEmpty(context, file, key)) return dftVaule;
        return context.getSharedPreferences(file, Context.MODE_PRIVATE).getInt(key, dftVaule);
    }

    public static float get(Context context, String file, String key, float dftVaule) {
        if (isArgsEmpty(context, file, key)) return dftVaule;
        return context.getSharedPreferences(file, Context.MODE_PRIVATE).getFloat(key, dftVaule);
    }

    public static void remove(Context context, String file, String key) {
        if (isArgsEmpty(context, file, key)) return;
        context.getSharedPreferences(file, Context.MODE_PRIVATE).edit().remove(key).apply();
    }

    public static void clearFile(Context context, String file) {
        if (context == null || file == null || "".equals(file.trim())) return;
        context.getSharedPreferences(file, Context.MODE_PRIVATE).edit().clear().apply();
    }

    public static Map<String, ?> getAllKeyValues(Context context, String file) {
        if (context == null || file == null || "".equals(file.trim())) return null;
        return context.getSharedPreferences(file, Context.MODE_PRIVATE).getAll();
    }

    public static void save(SharedPreferences.Editor editor,String key, String value) {
        if (isArgsEmpty(editor,key)) return;
        editor.putString(key, value == null || "null".equals(value.trim()) ? "" : value).apply();
    }

    public static void save(SharedPreferences.Editor editor, String key, boolean value) {
        if (isArgsEmpty(editor,key)) return;
        editor.putBoolean(key, value).apply();
    }

    public static void save(SharedPreferences.Editor editor, String key, int value) {
        if (isArgsEmpty(editor,key)) return;
        editor.putInt(key, value).apply();
    }

    public static void save(SharedPreferences.Editor editor, String key, float value) {
        if (isArgsEmpty(editor,key)) return;
        editor.putFloat(key, value).apply();
    }

    public static void save(SharedPreferences.Editor editor, String key, Set<String> values) {
        if (isArgsEmpty(editor,key) || values == null || values.size() < 1) return;
        editor.putStringSet(key, values).apply();
    }

    public static String get(SharedPreferences preferences, String key, String dftValue) {
        if (isArgsEmpty(preferences, key) || dftValue == null || "".equals(dftValue.trim())) return "";
        return preferences.getString(key, dftValue);
    }

    public static boolean get(SharedPreferences preferences, String key, boolean dftValue) {
        if (isArgsEmpty(preferences, key)) return dftValue;
        return preferences.getBoolean(key, dftValue);
    }

    public static int get(SharedPreferences preferences, String key, int dftVaule) {
        if (isArgsEmpty(preferences, key)) return dftVaule;
        return preferences.getInt(key, dftVaule);
    }

    public static float get(SharedPreferences preferences, String key, float dftVaule) {
        if (isArgsEmpty(preferences, key)) return dftVaule;
        return preferences.getFloat(key, dftVaule);
    }

    public static void remove(SharedPreferences.Editor editor, String key) {
        if (isArgsEmpty(editor,key)) return;
        editor.remove(key).apply();
    }

    public static void clearFile(SharedPreferences.Editor editor, String file) {
        if (editor == null || file == null || "".equals(file.trim())) return;
        editor.clear().apply();
    }

    public static Map<String, ?> getAllKeyValues(SharedPreferences preferences, String file) {
        if (preferences == null || file == null || "".equals(file.trim())) return null;
        return preferences.getAll();
    }

    private static boolean isArgsEmpty(Context context, String file, String key) {
        return context == null ||
                file == null ||
                key == null ||
                "".equals(file.trim()) ||
                "".equals(key.trim());
    }

    private static boolean isArgsEmpty(SharedPreferences.Editor editor,String key) {
        return editor == null ||
                key == null ||
                "".equals(key.trim());
    }

    private static boolean isArgsEmpty(SharedPreferences preferences,String key) {
        return preferences == null ||
                key == null ||
                "".equals(key.trim());
    }

}
