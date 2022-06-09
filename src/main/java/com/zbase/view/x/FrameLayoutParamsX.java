package com.zbase.view.x;

import android.widget.FrameLayout;

public class FrameLayoutParamsX extends FrameLayout.LayoutParams implements IViewGroupLayoutParamsX<FrameLayoutParamsX>{

    public FrameLayoutParamsX() {
        super(WRAP_CONTENT,WRAP_CONTENT);
    }

    public FrameLayoutParamsX(int width, int height) {
        super(width, height);
    }

    public FrameLayoutParamsX gravity(int g) {
        gravity = g;
        return this;
    }

}
