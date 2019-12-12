/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import android.content.Context;

public final class DensityUtils {

    private DensityUtils(){}

    /**
     * dp > px
     */
    public static int dip2px(Context context, float dpValue) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().density);
    }
    /**
     * px > dp
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) (pxValue / context.getResources().getDisplayMetrics().density);
    }

    /**
     * px > sp
     */
    public static int px2sp(Context context, float pxValue) {
        return (int) (pxValue / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * sp > px
     */
    public static int sp2px(Context context, float spValue) {
        return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity);
    }

}
