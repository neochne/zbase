package com.zbase.view.x;

import android.annotation.SuppressLint;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public interface IEditTextX<T extends EditText> extends ITextViewX<T>{

    default T inputPhone() {
        T t = (T) this;
        t.setInputType(InputType.TYPE_CLASS_PHONE);
        return t;
    }

    default T inputNumber() {
        T t = (T) this;
        t.setInputType(InputType.TYPE_CLASS_NUMBER);
        return t;
    }

    default T inputNumberDecimal() {
        T t = (T) this;
        t.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        return t;
    }

    default T imeSearch() {
        T t = (T) this;
        t.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        return t;
    }

    default T imeSend() {
        T t = (T) this;
        t.setImeOptions(EditorInfo.IME_ACTION_SEND);
        return t;
    }

    default T imeDone() {
        T t = (T) this;
        t.setImeOptions(EditorInfo.IME_ACTION_DONE);
        return t;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    default T clickListener(View.OnClickListener clickListener) {
        T t = (T) this;
        t.setKeyListener(null);
        t.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                clickListener.onClick(view);
            }
            return true;
        });
        return t;
    }

}
