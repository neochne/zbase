package com.zbase.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintLayoutX extends ConstraintLayout {

    public ConstraintLayoutX(@NonNull Context context) {
        super(context);
    }

    public ConstraintLayoutParamsX add1View(View view) {
        return add1View(view, View.generateViewId());
    }

    public ConstraintLayoutParamsX add1View(View view, int id) {
        ConstraintLayoutParamsX layoutParams = new ConstraintLayoutParamsX(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        view.setId(id);
        view.setLayoutParams(layoutParams);
        addView(view);
        return layoutParams;
    }

}
