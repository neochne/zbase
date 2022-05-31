package com.zbase.view.x;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;

public interface ITextViewX<T extends View> extends IViewX<T>{

    T text(String text);

    T text(int res);

    T textSize(float sp);

    T hint(String hint);

    T hint(int res);

    T textColor(int color);

    T hintTextColor(int color);

    T singleLine();

    T maxLines(int line);

    T gravity(int gravity);

    T minHeight(int height);

    T minWidth(int width);

    T ellipsize(TextUtils.TruncateAt where);

    T typeFace(Typeface typeface);

    T enableContentScroll();

}
