package com.zbase.x.drawable;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public final class StateListDrawableX extends StateListDrawable {

    /**
     * 可以链式添加多个状态
     *
     * @param states android.R.attr.state_pressed,android.R.attr.state_checked 等
     * @param color 对应这些状态被激发时的颜色
     */
    public StateListDrawableX stateColors(int[] states,int color) {
        addState(states,new ColorDrawable(color));
        return this;
    }

    public StateListDrawableX exitFadeDuration(int ms) {
        setExitFadeDuration(ms);
        return this;
    }

    public StateListDrawableX alpha(int a) {
        setAlpha(a);
        return this;
    }

    public static Drawable createStateDrawable(int pressColor, int dftColor) {
        StateListDrawable stateDrawable = new StateListDrawable();
        stateDrawable.addState(new int[]{android.R.attr.state_pressed},new ColorDrawable(pressColor));
        stateDrawable.addState(new int[]{},new ColorDrawable(dftColor));
        return stateDrawable;
    }

}
