package com.zbase.view.x;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public final class LinearLayoutParamsX extends LinearLayout.LayoutParams {

    public LinearLayoutParamsX() {
        super(WRAP_CONTENT,WRAP_CONTENT);
    }

    public LinearLayoutParamsX(Context c, AttributeSet attrs) {
        super(c, attrs);
    }

}
