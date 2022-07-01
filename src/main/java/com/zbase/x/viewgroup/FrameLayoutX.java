package com.zbase.x.viewgroup;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class FrameLayoutX extends FrameLayout implements IViewGroupX<FrameLayoutX> {

    public FrameLayoutX(@NonNull Context context) {
        super(context);
    }

    public FrameLayoutX(@NonNull Context context,int width,int height) {
        super(context);
        setLayoutParams(new LayoutParams(width,height));
    }

}
