package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

public final class TextViewX extends AppCompatTextView {

    public TextViewX(@NonNull Context context) {
        super(context);
    }

    public TextViewX text(String text) {
        setText(text);
        return this;
    }

    public TextViewX textColor(int color) {
        setTextColor(color);
        return this;
    }

    public TextViewX hint(String hint) {
        setHint(hint);
        return this;
    }

    public TextViewX singleLine() {
        setSingleLine();
        return this;
    }

    public TextViewX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    public TextViewX gravity(int gravity) {
        setGravity(gravity);
        return this;
    }

    public TextViewX paddings(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    public TextViewX minHeight(int h) {
        setMinHeight(h);
        return this;
    }

}
