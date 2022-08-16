package com.zbase.x.able;

import android.view.View;

import com.zbase.view.ToolbarLayout;
import com.zbase.x.ActivityX;
import com.zbase.x.lp.FrameLayoutParamsX;

public interface ToolbarAble {

    default boolean withToolbar() {
        return false;
    }

    default void setToolbarLayout(View view) {
        ((ActivityX)view.getContext()).setContentView(view, new FrameLayoutParamsX(FrameLayoutParamsX.MATCH_PARENT, FrameLayoutParamsX.MATCH_PARENT));
    }

    default ToolbarLayout createToolbar() {
        return null;
    }

}
