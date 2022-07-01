package com.zbase.x.view;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public final class BottomNavigationViewX extends BottomNavigationView implements IViewX<BottomNavigationViewX> {

    public BottomNavigationViewX(@NonNull Context context) {
        super(context);
    }

    public BottomNavigationViewX menu(int menu) {
        inflateMenu(menu);
        return this;
    }

    public BottomNavigationViewX labelVisibilityMode(int mode) {
        setLabelVisibilityMode(mode);
        return this;
    }

}
