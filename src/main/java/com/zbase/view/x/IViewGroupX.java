package com.zbase.view.x;

import android.view.View;
import android.view.ViewGroup;

public interface IViewGroupX<T extends View> extends IViewX<T>{

    T addChildView(View view);

    T addChildView(View view, ViewGroup.LayoutParams lp);

}
