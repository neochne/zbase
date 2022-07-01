package com.zbase.x.viewgroup;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

public final class RelativeLayoutX extends RelativeLayout implements IViewGroupX<RelativeLayoutX>{

    public RelativeLayoutX(Context context) {
        super(context);
    }

    public RelativeLayoutX(@NonNull Context context, int width, int height) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(width,height));
    }

}
