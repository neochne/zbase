package com.zbase.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public final class ResourceUtils {

    private ResourceUtils() {
    }

    /**
     * 获取 <integer name="text_size">14</integer> 标签定义的值，不能加单位，只能是纯数字
     */
    public static int getInt(Context context, int id) {
        return context.getResources().getInteger(id);
    }

    /**
     * 获取 <dimen name="padding_end">10dp</dimen> 标签定义的值，无论定义的值单位是什么，都会自动转化为像素
     */
    public static int getPixel(Context context, int id) {
        return context.getResources().getDimensionPixelOffset(id);
    }

    public static int getColor(Context context,int id) {
        return ContextCompat.getColor(context,id);
    }

    public static Drawable getDrawable(Context context, int id) {
        return ContextCompat.getDrawable(context, id);
    }

    public static Bitmap getBitmap(Context context, int id) {
        return BitmapFactory.decodeResource(context.getResources(), id);
    }

    public static String getString(Context context,int id) {
        return context.getResources().getString(id);
    }

}
