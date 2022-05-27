package com.zbase.view.x.params;

import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ViewGroupParamsX extends ViewGroup.LayoutParams {

    public ViewGroupParamsX() {
        super(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    public ViewGroupParamsX(int width, int height) {
        super(width, height);
    }

    public ViewGroupParamsX width(int w) {
        this.width = w;
        return this;
    }

    public ViewGroupParamsX height(int h) {
        this.width = h;
        return this;
    }

}
