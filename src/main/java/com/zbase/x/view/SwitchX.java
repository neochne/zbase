package com.zbase.x.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

public class SwitchX extends SwitchCompat implements ICompoundButtonX<SwitchX> {

    public SwitchX(@NonNull Context context) {
        super(context);
    }

    public SwitchX check() {
        setChecked(true);
        return this;
    }

    public SwitchX switchMinWidth(int w) {
        setSwitchMinWidth(w);
        return this;
    }

}
