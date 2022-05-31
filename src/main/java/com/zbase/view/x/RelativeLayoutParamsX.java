package com.zbase.view.x;

import android.widget.RelativeLayout;

public final class RelativeLayoutParamsX extends RelativeLayout.LayoutParams {

    public RelativeLayoutParamsX() {
        super(WRAP_CONTENT,WRAP_CONTENT);
    }

    public RelativeLayoutParamsX(int width, int height) {
        super(width, height);
    }

    public RelativeLayoutParamsX width(int w) {
        width = w;
        return this;
    }

    public RelativeLayoutParamsX height(int h) {
        height = h;
        return this;
    }

    public RelativeLayoutParamsX rule(int rule) {
        addRule(rule);
        return this;
    }

}
