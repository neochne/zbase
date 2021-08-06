/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class SpUtils {

    private SpUtils() {
    }

    public static SharedPreferences.Editor getEditor(Context context,String fileName) {
        return getSharedPreferences(context,fileName).edit();
    }

    public static SharedPreferences getSharedPreferences(Context context,String fileName) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

}
