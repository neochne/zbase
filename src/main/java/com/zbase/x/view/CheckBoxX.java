package com.zbase.x.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatCheckBox;

public final class CheckBoxX extends AppCompatCheckBox implements ICompoundButtonX<CheckBoxX> {

    public CheckBoxX(Context context) {
        super(context, null, android.R.attr.checkboxStyle);
    }

    public CheckBoxX checkListener(CompoundButton.OnCheckedChangeListener listener) {
        setOnCheckedChangeListener(listener);
        return this;
    }

    public CheckBoxX color(int color) {
        setButtonTintList(ColorStateList.valueOf(color));
        return this;
    }

    public CheckBoxX gravity(int gravity) {
        setGravity(gravity);
        return this;
    }

}
