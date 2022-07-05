package com.zbase.x.view;

import android.content.Context;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.zbase.util.ViewUtils;

public final class CheckBoxX extends AppCompatCheckBox implements ICompoundButtonX<CheckBoxX> {

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

}
