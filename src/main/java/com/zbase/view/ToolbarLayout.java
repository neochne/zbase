package com.zbase.view;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.zbase.R;
import com.zbase.util.ResourceUtils;
import com.zbase.view.x.ConstraintLayoutParamsX;
import com.zbase.view.x.ConstraintLayoutX;
import com.zbase.view.x.ImageViewX;
import com.zbase.view.x.TextViewX;

public final class ToolbarLayout extends ConstraintLayoutX {

    private final int TOOLBAR_ID;

    public ToolbarLayout(@NonNull Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);
        setLayoutParams(new ConstraintLayoutParamsX(MATCH_PARENT, WRAP_CONTENT));
        TOOLBAR_ID = generateViewId();
    }

    public int getToolbarId() {
        return TOOLBAR_ID;
    }

    public ToolbarLayout setTitle(String title, int color, int bgColor, int sp, Typeface typeface) {
        Context context = getContext();
        return addChildView(new TextViewX(context)
                        .id(TOOLBAR_ID)
                        .text(title)
                        .textColor(color)
                        .textSize(sp)
                        .backgroundColor(bgColor)
                        .gravity(Gravity.CENTER)
                        .typeFace(typeface),
                new ConstraintLayoutParamsX()
                        .width(0)
                        .height(ResourceUtils.getPixel(context, R.dimen.toolbar_height))
                        .start2start(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(ConstraintLayoutParamsX.PARENT_ID)
                        .end2end(ConstraintLayoutParamsX.PARENT_ID));
    }

    public ToolbarLayout setBackIcon(int res, int leftMargin, View.OnClickListener listener) {
        return addChildView(new ImageViewX(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar)
                        .src(res)
                        .clickListener(listener),
                new ConstraintLayoutParamsX()
                        .start2start(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(TOOLBAR_ID)
                        .bottom2bottom(TOOLBAR_ID)
                        .leftMargin(leftMargin));
    }

    public ToolbarLayout setRightIcon(int res, int rightMargin, View.OnClickListener listener) {
        return addChildView(new ImageViewX(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar)
                        .src(res)
                        .clickListener(listener),
                new ConstraintLayoutParamsX()
                        .end2end(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(TOOLBAR_ID)
                        .bottom2bottom(TOOLBAR_ID)
                        .rightMargin(rightMargin));
    }

    public ToolbarLayout setRightText(String text,
                                      int color,
                                      int sp,
                                      int rightMargin,
                                      View.OnClickListener listener) {

        return addChildView(new TextViewX(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar)
                        .text(text)
                        .textColor(color)
                        .textSize(sp)
                        .clickListener(listener),
                new ConstraintLayoutParamsX()
                        .end2end(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(TOOLBAR_ID)
                        .bottom2bottom(TOOLBAR_ID)
                        .rightMargin(rightMargin));
    }

    public void setContentView(View view, ConstraintLayoutParamsX lp) {
        addChildViewBelowToolbar(view, lp.width(0)
                .start2start(LayoutParams.PARENT_ID)
                .end2end(LayoutParams.PARENT_ID));
    }

    public ToolbarLayout addChildViewBelowToolbar(View view, ConstraintLayoutParamsX lp) {
        return addChildView(view, lp.top2bottom(TOOLBAR_ID));
    }

    @Override
    public ToolbarLayout addChildView(View view) {
        super.addChildView(view);
        return this;
    }

    @Override
    public ToolbarLayout addChildView(View view, ViewGroup.LayoutParams lp) {
        super.addChildView(view,lp);
        return this;
    }

}
