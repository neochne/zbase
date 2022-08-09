package com.zbase.view;

import android.content.Context;

import com.zbase.util.DensityUtils;
import com.zbase.x.drawable.GradientDrawableX;
import com.zbase.x.view.ButtonX;

public final class RoundedButton extends ButtonX {

    public RoundedButton(Context context) {
        super(context);
        setTextSize(16);
    }

    public ButtonX solidColor(int color) {
        float r = DensityUtils.dp2px2int(getContext(), 5);
        setBackground(GradientDrawableX.createRadiusDrawable(color, r));
        return this;
    }

}
