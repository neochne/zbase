package com.zbase.x.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public final class GradientDrawableX extends GradientDrawable {

    /**
     * @param s GradientDrawable.RECTANGLE,GradientDrawable.OVAL 等
     */
    public GradientDrawableX shape(int s) {
        setShape(s);
        return this;
    }

    public GradientDrawableX solidColor(int color) {
        setColor(color);
        return this;
    }

    public GradientDrawableX cornerRadius(float radius) {
        setCornerRadius(radius);
        return this;
    }

    public GradientDrawableX cornerRadii(float[] radii) {
        setCornerRadii(radii);
        return this;
    }

    public GradientDrawableX stroke(int width,int color) {
        setStroke(width,color);
        return this;
    }

    /**
     * 设置 drawable 的 view 长宽必须一样，才能是圆形
     */
    public static Drawable createCircleDrawable(int solidColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(solidColor);
        gradientDrawable.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});
        gradientDrawable.setStroke(0, 0);
        return gradientDrawable;
    }

    public static GradientDrawableX createRadiusDrawable(int solidColor, float radius) {
        return new GradientDrawableX()
                .shape(RECTANGLE)
                .solidColor(solidColor)
                .stroke(0,0)
                .cornerRadius(radius);
    }

}
