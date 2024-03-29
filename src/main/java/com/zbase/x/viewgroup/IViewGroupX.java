package com.zbase.x.viewgroup;

import android.view.View;
import android.view.ViewGroup;

import com.zbase.x.view.IViewX;

public interface IViewGroupX<T extends ViewGroup> extends IViewX<T> {

    default T addChildView(View view) {
        T t = (T) this;
        t.addView(view);
        return t;
    }

    default T addChildView(View view, ViewGroup.LayoutParams lp) {
        T t = (T) this;
        t.addView(view,lp);
        return t;
    }

}
