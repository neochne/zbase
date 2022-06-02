package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.zbase.util.ViewUtils;

public final class CheckBoxX extends AppCompatCheckBox implements IViewX<CheckBoxX>{

    public CheckBoxX(Context context) {
        super(context, null, android.R.attr.checkboxStyle);
    }

    public CheckBoxX checkListener(CompoundButton.OnCheckedChangeListener listener) {
        setOnCheckedChangeListener(listener);
        return this;
    }

    public CheckBoxX color(int color) {
        ViewUtils.setCheckBoxColor(this, color);
        return this;
    }

    public CheckBoxX gravity(int gravity) {
        setGravity(gravity);
        return this;
    }

    @Override
    public CheckBoxX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public CheckBoxX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public CheckBoxX tag(int key, Object tag) {
        setTag(key,tag);
        return this;
    }

    @Override
    public CheckBoxX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public CheckBoxX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public CheckBoxX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public CheckBoxX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public CheckBoxX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public CheckBoxX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public CheckBoxX padding(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    @Override
    public CheckBoxX lp(ViewGroup.LayoutParams lp) {
        setLayoutParams(lp);
        return this;
    }

    @Override
    public CheckBoxX focus() {
        requestFocus();
        return this;
    }

    @Override
    public CheckBoxX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public CheckBoxX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }
    
}
