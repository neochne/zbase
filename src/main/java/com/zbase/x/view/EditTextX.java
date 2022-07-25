package com.zbase.x.view;

import android.content.Context;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatEditText;

import com.zbase.R;

public class EditTextX extends AppCompatEditText implements ITextViewX<EditTextX> {

    public EditTextX(@NonNull Context context) {
        super(new ContextThemeWrapper(context, R.style.Et));
    }

    public EditTextX typePhone() {
        setInputType(InputType.TYPE_CLASS_PHONE);
        return this;
    }

    public EditTextX typeNumber() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        return this;
    }

    public EditTextX typePassword() {
        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        return this;
    }

    public EditTextX imeSearch() {
        setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        return this;
    }

    public EditTextX imeSend() {
        setImeOptions(EditorInfo.IME_ACTION_SEND);
        return this;
    }

    public EditTextX imeDone() {
        setImeOptions(EditorInfo.IME_ACTION_DONE);
        return this;
    }

    @Override
    public EditTextX clickListener(OnClickListener clickListener) {
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
