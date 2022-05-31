package com.zbase.view.x;

import android.widget.FrameLayout;

public class FrameLayoutParamsX extends FrameLayout.LayoutParams {

    public FrameLayoutParamsX() {
        super(WRAP_CONTENT,WRAP_CONTENT);
    }

    public FrameLayoutParamsX(int width, int height) {
        super(width, height);
    }

    public FrameLayoutParamsX width(int w) {
        width = w;
        return this;
    }

    public FrameLayoutParamsX height(int h) {
        height = h;
        return this;
    }

    public FrameLayoutParamsX gravity(int g) {
        gravity = g;
        return this;
    }

    public FrameLayoutParamsX margins(int l,int t,int r,int b) {
        setMargins(l,t,r,b);
        return this;
    }

}
