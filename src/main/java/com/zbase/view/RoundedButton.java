package com.zbase.view;

import android.content.Context;

import com.zbase.R;
import com.zbase.util.DrawableUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.x.view.ButtonX;

public final class RoundedButton extends ButtonX {

    public RoundedButton(Context context) {
        super(context);
        setTextSize(16);
    }

    public ButtonX solidColor(int color) {
        float r = ResourceUtils.getPixel(getContext(), R.dimen.btn_radius);
        setBackground(DrawableUtils.createRectDrawable(color,0,0,new float[] {r,r,r,r,r,r,r,r}));
        return this;
    }

}
