package com.zbase.x.lp;

import android.view.ViewGroup;

public interface IViewGroupLayoutParamsX<T extends ViewGroup.MarginLayoutParams> {

    default T width(int width) {
        T t = (T) this;
        t.width = width;
        return t;
    }

    default T height(int height) {
        T t = (T) this;
        t.height = height;
        return t;
    }

    default T wMatchParent() {
        T t = (T) this;
        t.width = ViewGroup.LayoutParams.MATCH_PARENT;
        return t;
    }

    default T hMatchParent() {
        T t = (T) this;
        t.height = ViewGroup.LayoutParams.MATCH_PARENT;
        return t;
    }

    default T matchParent() {
        T t = (T) this;
        t.width = ViewGroup.LayoutParams.MATCH_PARENT;
        t.height = ViewGroup.LayoutParams.MATCH_PARENT;
        return t;
    }

    default T margins(int margin) {
        T t = (T) this;
        t.setMargins(margin,margin,margin,margin);
        return t;
    }

    default T margins(int l,int t,int r,int b) {
        T tlp = (T) this;
        tlp.setMargins(l,t,r,b);
        return tlp;
    }

}
