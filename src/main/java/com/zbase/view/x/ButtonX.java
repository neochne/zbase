package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class ButtonX extends AppCompatButton implements IViewX<ButtonX>{

    public ButtonX(@NonNull Context context) {
        super(context);
    }

    public ButtonX text(String text) {
        setText(text);
        return this;
    }

    public ButtonX text(int res) {
        setText(res);
        return this;
    }

    public ButtonX textSize(float sp) {
        setTextSize(sp);
        return this;
    }

    public ButtonX textColor(int color) {
        setTextColor(color);
        return this;
    }

    @Override
    public ButtonX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public ButtonX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public ButtonX tag(int key, Object tag) {
        setTag(key,tag);
        return this;
    }

    @Override
    public ButtonX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public ButtonX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public ButtonX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public ButtonX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public ButtonX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public ButtonX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public ButtonX padding(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    @Override
    public ButtonX lp(ViewGroup.LayoutParams lp) {
        setLayoutParams(lp);
        return this;
    }

    @Override
    public ButtonX focus() {
        requestFocus();
        return this;
    }

    @Override
    public ButtonX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public ButtonX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }
    
}
