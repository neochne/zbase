package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.AppCompatButton;

public final class ButtonX extends AppCompatButton {

    public ButtonX(Context context) {
        super(context);
    }

    public ButtonX text(String text) {
        setText(text);
        return this;
    }

    public ButtonX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

}
