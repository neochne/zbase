package com.zbase.x.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class TextViewX extends AppCompatTextView implements ITextViewX<TextViewX> {

    public TextViewX(@NonNull Context context) {
        super(context);
    }

    public TextViewX(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
