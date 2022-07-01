package com.zbase.x.view;

import android.content.Context;

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
    
}
