package com.zbase.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;

public final class DrawableUtils {

    public DrawableUtils() {
    }

    public static GradientDrawable createRectDrawable(int solidColor,
                                                      int strokeColor,
                                                      int strokeWidth,
                                                      float[] radii) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(solidColor);
        gradientDrawable.setCornerRadii(radii);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }

    public static Drawable createInsetDrawable(int solidColor,
                                               int strokeColor,
                                               int strokeWidth,
                                               float[] radii,
                                               int[] insets) {
        return new InsetDrawable(createRectDrawable(solidColor, strokeColor, strokeWidth, radii), insets[0], insets[1], insets[2], insets[3]);
    }

    /**
     * 设置 drawable 的 view 长宽必须一样，才能是圆形
     */
    public static Drawable createCircleDrawable(int solidColor,
                                                int strokeColor,
                                                int strokeWidth) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(solidColor);
        gradientDrawable.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }

}
