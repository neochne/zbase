package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public final class RelativeLayoutX extends RelativeLayout implements IViewGroupX<RelativeLayoutX>{

    public RelativeLayoutX(Context context) {
        super(context);
    }

    @Override
    public RelativeLayoutX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public RelativeLayoutX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public RelativeLayoutX tag(int key, Object tag) {
        setTag(key,tag);
        return this;
    }

    @Override
    public RelativeLayoutX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public RelativeLayoutX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public RelativeLayoutX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public RelativeLayoutX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public RelativeLayoutX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public RelativeLayoutX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public RelativeLayoutX padding(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    @Override
    public RelativeLayoutX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public RelativeLayoutX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }

    @Override
    public RelativeLayoutX addChildView(View view) {
        addView(view);
        return this;
    }

    @Override
    public RelativeLayoutX addChildView(View view,ViewGroup.LayoutParams lp) {
        addView(view,lp);
        return this;
    }

}
