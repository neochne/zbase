package com.zbase.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * author:       sharp
 * created at:   2018/10/16 6:25 PM
 * company:      JingYouGroup
 */
public final class DensityUtils {

    private DensityUtils(){}

    /**
     * dp > px
     */
    public static float dip2px(Context context, float dpValue) {
        return dpValue * getDensity(context);
    }
    /**
     * px > dp
     */
    public static float px2dip(Context context, float pxValue) {
        return pxValue / getDensity(context);
    }

    /**
     * sp > px
     */
    public static float sp2px(Context context, float spValue) {
        return spValue * getScaledDensity(context);
    }

    /**
     * px > sp
     */
    public static float px2sp(Context context, float pxValue) {
        return pxValue / getScaledDensity(context);
    }

    private static float getDensity(Context context){
        return (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics().density;
    }

    private static float getScaledDensity(Context context){
        return (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics().scaledDensity;
    }

}
