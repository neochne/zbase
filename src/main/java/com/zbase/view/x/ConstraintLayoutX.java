package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintLayoutX extends ConstraintLayout implements IViewGroupX<ConstraintLayoutX>{

    public ConstraintLayoutX(@NonNull Context context) {
        super(context);
    }

    public ConstraintLayoutParamsX addViewBackParams(View view) {
        return addViewBackParams(view, View.generateViewId());
    }

    public ConstraintLayoutParamsX addViewBackParams(View view, int id) {
        view.setId(id);
        ConstraintLayoutParamsX layoutParams = new ConstraintLayoutParamsX(ConstraintLayoutParamsX.WRAP_CONTENT, ConstraintLayoutParamsX.WRAP_CONTENT);
        addView(view,layoutParams);
        return layoutParams;
    }

    @Override
    public ConstraintLayoutX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public ConstraintLayoutX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public ConstraintLayoutX tag(int key, Object tag) {
        setTag(key,tag);
        return this;
    }

    @Override
    public ConstraintLayoutX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public ConstraintLayoutX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public ConstraintLayoutX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public ConstraintLayoutX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public ConstraintLayoutX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public ConstraintLayoutX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public ConstraintLayoutX padding(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    @Override
    public ConstraintLayoutX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public ConstraintLayoutX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }

    @Override
    public ConstraintLayoutX addChildView(View view) {
        addView(view);
        return this;
    }

    @Override
    public ConstraintLayoutX addChildView(View view,ViewGroup.LayoutParams lp) {
        addView(view,lp);
        return this;
    }

}
