package com.zbase.x.lp;

import android.widget.LinearLayout;

public final class LinearLayoutParamsX extends LinearLayout.LayoutParams implements IViewGroupLayoutParamsX<LinearLayoutParamsX>{

    public LinearLayoutParamsX() {
        super(WRAP_CONTENT,WRAP_CONTENT);
    }

    public LinearLayoutParamsX(int width,int height) {
        super(width,height);
    }

    public LinearLayoutParamsX weight(float weight) {
        this.weight = weight;
        return this;
    }

}
