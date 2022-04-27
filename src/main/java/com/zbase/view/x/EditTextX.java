package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatEditText;

import com.zbase.R;

public final class EditTextX extends AppCompatEditText {

    public EditTextX(@NonNull Context context) {
        super(new ContextThemeWrapper(context, R.style.Et));
    }

    public EditTextX text(String text) {
        setText(text);
        return this;
    }

    public EditTextX hint(String hint) {
        setHint(hint);
        return this;
    }

    public EditTextX singleLine() {
        setSingleLine();
        return this;
    }

    public EditTextX numberDecimal() {
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        return this;
    }

    public EditTextX number() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        return this;
    }

    public EditTextX phone() {
        setInputType(InputType.TYPE_CLASS_PHONE);
        return this;
    }

    public EditTextX emptyBackground() {
        setBackground(null);
        return this;
    }

    public EditTextX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    public EditTextX gravity(int gravity) {
        setGravity(gravity);
        return this;
    }

    public EditTextX paddings(int l,int t,int r,int b) {
        setPadding(l,t,r,b);
        return this;
    }

    public EditTextX minHeight(int h) {
        setMinHeight(h);
        return this;
    }

    public EditTextX setClickListener(View.OnClickListener clickListener) {
        setKeyListener(null);
        setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                clickListener.onClick(view);
                performClick();
            }
            return true;
        });
        return this;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

}
