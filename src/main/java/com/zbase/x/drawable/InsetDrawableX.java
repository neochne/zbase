package com.zbase.x.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;

import androidx.annotation.Nullable;

public final class InsetDrawableX extends InsetDrawable {

    public InsetDrawableX(@Nullable Drawable drawable, int inset) {
        super(drawable, inset);
    }

    public InsetDrawableX(@Nullable Drawable drawable
            , int insetLeft
            , int insetTop
            , int insetRight
            , int insetBottom) {
        super(drawable, insetLeft, insetTop, insetRight, insetBottom);
    }

    public static InsetDrawableX createInsetRadiusDrawable(int solidColor, float radius, int inset) {
        return new InsetDrawableX(GradientDrawableX.createRadiusDrawable(solidColor,radius),inset);
    }

}
