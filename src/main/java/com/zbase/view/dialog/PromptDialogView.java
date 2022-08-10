package com.zbase.view.dialog;

import android.content.Context;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.zbase.util.DensityUtils;
import com.zbase.x.lp.ConstraintLayoutParamsX;
import com.zbase.x.view.TextViewX;

public final class PromptDialogView extends DialogView<PromptDialogView> {

    public PromptDialogView(@NonNull Context context) {
        super(context);
        int msgPadding = DensityUtils.dp2px2int(context, 10);
        this.addChildView(new TextViewX(context)
                        .id(getAboveBtnViewId())
                        .padding(msgPadding)
                        .gravity(Gravity.CENTER)
                        .textSize(14)
                , new ConstraintLayoutParamsX()
                        .top2bottom(getTitleId())
                        .start2startParent()
                        .end2endParent());
    }

    public PromptDialogView text(String text) {
        ((TextViewX) findViewById(getAboveBtnViewId())).text(text);
        return this;
    }

}
