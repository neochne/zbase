package com.zbase.x.viewgroup;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.zbase.x.lp.ConstraintLayoutParamsX;

public class ConstraintLayoutX extends ConstraintLayout implements IViewGroupX<ConstraintLayoutX>{

    public ConstraintLayoutX(@NonNull Context context) {
        super(context);
    }

    public ConstraintLayoutX(@NonNull Context context,int width,int height) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(width,height));
    }

    public ConstraintLayoutParamsX addViewX(View view) {
        return addViewX(view, View.generateViewId());
    }

    public ConstraintLayoutParamsX addViewX(View view, int id) {
        view.setId(id);
        ConstraintLayoutParamsX layoutParams = new ConstraintLayoutParamsX(ConstraintLayoutParamsX.WRAP_CONTENT, ConstraintLayoutParamsX.WRAP_CONTENT);
        addView(view,layoutParams);
        return layoutParams;
    }

}
