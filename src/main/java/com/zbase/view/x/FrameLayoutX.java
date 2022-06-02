package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class FrameLayoutX extends FrameLayout implements IViewGroupX<FrameLayoutX> {

    public FrameLayoutX(@NonNull Context context) {
        super(context);
    }

    public FrameLayoutX(@NonNull Context context,int width,int height) {
        super(context);
        setLayoutParams(new LayoutParams(width,height));
    }

    @Override
    public FrameLayoutX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public FrameLayoutX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public FrameLayoutX tag(int key, Object tag) {
        setTag(key,tag);
        return this;
    }

    @Override
    public FrameLayoutX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public FrameLayoutX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public FrameLayoutX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public FrameLayoutX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public FrameLayoutX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public FrameLayoutX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public FrameLayoutX padding(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    @Override
    public FrameLayoutX lp(ViewGroup.LayoutParams lp) {
        setLayoutParams(lp);
        return this;
    }

    @Override
    public FrameLayoutX focus() {
        requestFocus();
        return this;
    }

    @Override
    public FrameLayoutX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public FrameLayoutX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }

    @Override
    public FrameLayoutX addChildView(View view) {
        addView(view);
        return this;
    }

    @Override
    public FrameLayoutX addChildView(View view,ViewGroup.LayoutParams lp) {
        addView(view,lp);
        return this;
    }

}
