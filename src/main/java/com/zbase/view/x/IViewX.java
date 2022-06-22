package com.zbase.view.x;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

public interface IViewX<T extends View> {

    default T id(int id) {
        T t = (T) this;
        t.setId(id);
        return t;
    }

    default T tag(Object tag) {
        T t = (T) this;
        t.setTag(tag);
        return t;
    }

    default T tag(int key,Object tag) {
        T t = (T) this;
        t.setTag(key,tag);
        return t;
    }

    default T disable() {
        T t = (T) this;
        t.setEnabled(false);
        return t;
    }

    default T background(Drawable drawable) {
        T t = (T) this;
        t.setBackground(drawable);
        return t;
    }

    default T backgroundColor(int color) {
        T t = (T) this;
        t.setBackgroundColor(color);
        return t;
    }

    default T backgroundResource(int res) {
        T t = (T) this;
        t.setBackgroundResource(res);
        return t;
    }

    default T minimumHeight(int height) {
        T t = (T) this;
        t.setMinimumHeight(height);
        return t;
    }

    default T minimumWidth(int width) {
        T t = (T) this;
        t.setMinimumWidth(width);
        return t;
    }

    default T padding(int l, int t, int r, int b) {
        T tv = (T) this;
        tv.setPadding(l,t,r,b);
        return tv;
    }

    default T padding(int padding) {
        T tv = (T) this;
        tv.setPadding(padding,padding,padding,padding);
        return tv;
    }

    default T lp(ViewGroup.LayoutParams lp) {
        T t = (T) this;
        t.setLayoutParams(lp);
        return t;
    }

    default T focus() {
        T t = (T) this;
        t.requestFocus();
        return t;
    }

    default T visible() {
        T t = (T) this;
        t.setVisibility(View.VISIBLE);
        return t;
    }

    default T invisible() {
        T t = (T) this;
        t.setVisibility(View.INVISIBLE);
        return t;
    }

    default T gone() {
        T t = (T) this;
        t.setVisibility(View.GONE);
        return t;
    }

    default boolean isVisible() {
        return ((T)this).getVisibility() == View.VISIBLE;
    }

    default T toFront() {
        T t = (T) this;
        t.bringToFront();
        return t;
    }

    default T clickListener(View.OnClickListener clickListener) {
        T t = (T) this;
        t.setOnClickListener(clickListener);
        return t;
    }

    default T longClickListener(View.OnLongClickListener longClickListener) {
        T t = (T) this;
        t.setOnLongClickListener(longClickListener);
        return t;
    }

}
