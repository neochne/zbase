package com.zbase.x;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

public final class SpannableStringX extends SpannableString {

    public SpannableStringX(CharSequence source) {
        super(source);
    }

    public SpannableStringX colorSpan(int start,int end,int color) {
        setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableStringX sizeSpan(int start,int end,int dp) {
        setSpan(new AbsoluteSizeSpan(dp,true), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

}
