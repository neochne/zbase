package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public final class LinearLayoutX extends LinearLayout implements IViewGroupX<LinearLayoutX> {

    public LinearLayoutX(Context context) {
        super(context);
    }

    public LinearLayoutX gravity(int gravity) {
        setGravity(gravity);
        return this;
    }

    @Override
    public LinearLayoutX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public LinearLayoutX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public LinearLayoutX tag(int key, Object tag) {
        setTag(key,tag);
        return this;
    }

    @Override
    public LinearLayoutX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public LinearLayoutX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public LinearLayoutX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public LinearLayoutX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public LinearLayoutX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public LinearLayoutX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public LinearLayoutX padding(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    @Override
    public LinearLayoutX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public LinearLayoutX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }

    @Override
    public LinearLayoutX addChildView(View view) {
        addView(view);
        return this;
    }

    @Override
    public LinearLayoutX addChildView(View view,ViewGroup.LayoutParams lp) {
        addView(view,lp);
        return this;
    }

}
