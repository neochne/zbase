package com.zbase.view.x;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface IViewX<T extends View> {

    T id(int id);

    T tag(Object tag);

    T tag(int key,Object tag);

    T disable();

    T background(Drawable drawable);

    T backgroundColor(int color);

    T backgroundResource(int res);

    T minimumHeight(int height);

    T minimumWidth(int width);

    T padding(int l, int t, int r, int b);

    T clickListener(View.OnClickListener clickListener);

    T longClickListener(View.OnLongClickListener longClickListener);

}
