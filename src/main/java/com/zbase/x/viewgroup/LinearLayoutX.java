package com.zbase.x.viewgroup;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class LinearLayoutX extends LinearLayout implements IViewGroupX<LinearLayoutX> {

    public LinearLayoutX(Context context) {
        super(context);
    }

    public LinearLayoutX(@NonNull Context context, int width, int height) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(width, height));
    }

    public LinearLayoutX gravity(int gravity) {
        setGravity(gravity);
        return this;
    }

    public LinearLayoutX orientation(int orientation) {
        setOrientation(orientation);
        return this;
    }

}
