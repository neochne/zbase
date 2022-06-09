package com.zbase.view.x;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public interface ITextViewX<T extends TextView> extends IViewX<T> {

    default T text(String text) {
        T t = (T) this;
        t.setText(text);
        return t;
    }

    default T text(int res) {
        T t = (T) this;
        t.setText(res);
        return t;
    }

    default T textSize(float sp) {
        T t = (T) this;
        t.setTextSize(sp);
        return t;
    }

    default T hint(String hint) {
        T t = (T) this;
        t.setHint(hint);
        return t;
    }

    default T hint(int res) {
        T t = (T) this;
        t.setHint(res);
        return t;
    }

    default T textColor(int color) {
        T t = (T) this;
        t.setTextColor(color);
        return t;
    }

    default T hintTextColor(int color) {
        T t = (T) this;
        t.setHintTextColor(color);
        return t;
    }

    default T singleLine() {
        T t = (T) this;
        t.setSingleLine();
        return t;
    }

    default T maxLines(int lines) {
        T t = (T) this;
        t.setMaxLines(lines);
        return t;
    }

    default T gravity(int gravity) {
        T t = (T) this;
        t.setGravity(gravity);
        return t;
    }

    default T minHeight(int height) {
        T t = (T) this;
        t.setMinHeight(height);
        return t;
    }

    default T minWidth(int width) {
        T t = (T) this;
        t.setMinWidth(width);
        return t;
    }

    default T ellipsize(TextUtils.TruncateAt where) {
        T t = (T) this;
        t.setEllipsize(where);
        return t;
    }

    default T typeFace(Typeface typeface) {
        T t = (T) this;
        t.setTypeface(typeface);
        return t;
    }

    default T enableContentScroll() {
        T t = (T) this;
        t.setMovementMethod(new ScrollingMovementMethod());
        return t;
    }

}
