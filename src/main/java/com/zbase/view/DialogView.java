package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;

import androidx.annotation.NonNull;

import com.zbase.util.DensityUtils;
import com.zbase.x.lp.ConstraintLayoutParamsX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.ConstraintLayoutX;

public class DialogView extends ConstraintLayoutX {

    public DialogView(@NonNull Context context) {
        super(context);
        /*
         * Size configure
         */
        int titleVerPadding = DensityUtils.dp2px2int(context, 10);
        float titleSize = DensityUtils.sp2px(context, 17);

        /*
         * View
         */
        this.backgroundColor(Color.WHITE)
                .addChildView(new TextViewX(context)
                                .id(generateViewId())
                                .textColor(Color.BLACK)
                                .textSize(titleSize)
                                .typeFace(Typeface.DEFAULT_BOLD)
                                .padding(0, titleVerPadding, 0, titleVerPadding)
                        , new ConstraintLayoutParamsX()
                                .top2topParent()
                                .start2startParent()
                                .end2endParent());
    }

    public int getTitleId() {
        return getChildAt(0).getId();
    }

    public void contentView(View view, ConstraintLayoutParamsX lp) {
        this.addChildView(view, lp.top2bottom(getTitleId()));
    }

    public void enablePositiveAndNegativeButton(int aboveViewId) {

    }

}
