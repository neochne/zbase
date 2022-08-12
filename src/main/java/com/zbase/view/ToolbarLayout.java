package com.zbase.view;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.zbase.util.DensityUtils;
import com.zbase.x.ColorX;
import com.zbase.x.lp.ConstraintLayoutParamsX;
import com.zbase.x.view.ImageViewX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.IViewGroupX;

public final class ToolbarLayout extends ConstraintLayout implements IViewGroupX<ToolbarLayout> {

    public ToolbarLayout(@NonNull Context context) {
        super(context);
        this
                .backgroundColor(ColorX.WHITE)
                .lp(new ConstraintLayoutParamsX(MATCH_PARENT, WRAP_CONTENT))
                .tag(generateViewId());
    }

    public int getToolbarId() {
        return (int) getTag();
    }

    public ToolbarLayout addTitle(String title, int ColorX, int bgColor, int sp, Typeface typeface) {
        Context context = getContext();
        return addChildView(new TextViewX(context)
                        .id(getToolbarId())
                        .text(title)
                        .textColor(ColorX)
                        .textSize(sp)
                        .backgroundColor(bgColor)
                        .gravity(Gravity.CENTER)
                        .typeFace(typeface),
                new ConstraintLayoutParamsX()
                        .width(0)
                        .height(DensityUtils.dp2px2int(context, 56))
                        .start2start(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(ConstraintLayoutParamsX.PARENT_ID)
                        .end2end(ConstraintLayoutParamsX.PARENT_ID));
    }

    public ToolbarLayout title(String title) {
        ((TextViewX) findViewById(getToolbarId())).setText(title);
        return this;
    }

    public ToolbarLayout addBackIcon(int res, int leftMargin, View.OnClickListener listener) {
        return addChildView(new ImageViewX(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar)
                        .src(res)
                        .clickListener(listener),
                new ConstraintLayoutParamsX()
                        .start2start(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(getToolbarId())
                        .bottom2bottom(getToolbarId())
                        .margins(leftMargin, 0, 0, 0));
    }

    public ToolbarLayout addRightIcon(int res, int rightMargin, View.OnClickListener listener) {
        return addChildView(new ImageViewX(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar)
                        .src(res)
                        .clickListener(listener),
                new ConstraintLayoutParamsX()
                        .end2end(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(getToolbarId())
                        .bottom2bottom(getToolbarId())
                        .margins(0, 0, rightMargin, 0));
    }

    public ToolbarLayout addRightText(String text,
                                      int ColorX,
                                      int sp,
                                      int rightMargin,
                                      View.OnClickListener listener) {

        return addChildView(new TextViewX(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar)
                        .text(text)
                        .textColor(ColorX)
                        .textSize(sp)
                        .clickListener(listener),
                new ConstraintLayoutParamsX()
                        .end2end(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(getToolbarId())
                        .bottom2bottom(getToolbarId())
                        .margins(0, 0, rightMargin, 0));
    }

    public void setContentView(View view, ConstraintLayoutParamsX lp) {
        addChildViewBelowToolbar(view, lp.width(0)
                .start2start(LayoutParams.PARENT_ID)
                .end2end(LayoutParams.PARENT_ID));
    }

    public ToolbarLayout addChildViewBelowToolbar(View view, ConstraintLayoutParamsX lp) {
        return addChildView(view, lp.top2bottom(getToolbarId()));
    }

}
