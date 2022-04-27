package com.zbase.view.x;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

public final class LinearLayoutX extends LinearLayoutCompat {

    public LinearLayoutX(@NonNull Context context) {
        super(context);
    }

    public LinearLayoutX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    public LinearLayoutX horizontal() {
        setOrientation(HORIZONTAL);
        return this;
    }

    public LinearLayoutX vertical() {
        setOrientation(VERTICAL);
        return this;
    }

    public LinearLayoutX appendView(View view) {
        addView(view);
        return this;
    }

}
