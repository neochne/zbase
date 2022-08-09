package com.zbase.util;

import android.content.Context;

/**
 * author:       sharp
 * created at:   2018/10/16 6:25 PM
 */
public final class DensityUtils {

    private DensityUtils(){}

    /**
     * @param dp: (dp or dip)(Density-independent Pixels)
     */
    public static int dp2px2int(Context context, float dp) {
        return (int) dp2px(context,dp);
    }

    public static float dp2px(Context context, float dp) {
        return dp * getDensity(context);
    }

    public static int px2dp2int(Context context, float px) {
        return (int) px2dp(context,px);
    }

    public static float px2dp(Context context, float px) {
        return px / getDensity(context);
    }

    /**
     * @param sp: Scale Pixels OR Scale-independent Pixels
     */
    public static float sp2px(Context context, float sp) {
        return sp * getScaledDensity(context);
    }

    public static float px2sp(Context context, float px) {
        return px / getScaledDensity(context);
    }

    public static float getDensity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    public static float getScaledDensity(Context context){
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

}
