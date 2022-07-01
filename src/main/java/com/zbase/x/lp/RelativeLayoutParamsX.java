package com.zbase.x.lp;

import android.widget.RelativeLayout;

public final class RelativeLayoutParamsX extends RelativeLayout.LayoutParams implements IViewGroupLayoutParamsX<RelativeLayoutParamsX>{

    public RelativeLayoutParamsX() {
        super(WRAP_CONTENT,WRAP_CONTENT);
    }

    public RelativeLayoutParamsX(int width, int height) {
        super(width, height);
    }

    public RelativeLayoutParamsX rule(int rule) {
        addRule(rule);
        return this;
    }

}
