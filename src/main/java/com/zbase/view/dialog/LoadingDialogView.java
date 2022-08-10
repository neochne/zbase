package com.zbase.view.dialog;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.zbase.util.DensityUtils;
import com.zbase.x.ColorX;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.view.ProgressBarX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.ILinearLayoutX;

public final class LoadingDialogView extends LinearLayout implements ILinearLayoutX<LoadingDialogView> {

    public LoadingDialogView(Context context) {
        super(context);
        this
                .gravity(Gravity.CENTER)
                .orientation(VERTICAL)
                .padding(DensityUtils.dp2px2int(context, 20))
                .addChildView(new ProgressBarX(context)
                        .scaleX(.8f)
                        .scaleY(.8f)
                        .colorFilter(ColorX.WHITE))
                .addChildView(new TextViewX(context)
                                .textSize(14)
                                .textColor(ColorX.WHITE)
                        , new LinearLayoutParamsX()
                                .margins(0, DensityUtils.dp2px2int(context, 5), 0, 0));
    }

    public LoadingDialogView text(String text) {
        ((TextViewX) getChildAt(1)).text(text);
        return this;
    }

}
