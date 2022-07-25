package com.zbase.x;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

public final class SpannableStringX extends SpannableString {

    public SpannableStringX(CharSequence source) {
        super(source);
    }

    public SpannableStringX colorSpanStart0(int end, int color) {
        return colorSpan(0, end, color);
    }

    public SpannableStringX colorSpanEndLen(int start, int color) {
        return colorSpan(start, length(), color);
    }

    public SpannableStringX colorSpan(int start, int end, int color) {
        setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableStringX sizeSpanStart0(int end, int dp) {
        return sizeSpan(0, end, dp);
    }

    public SpannableStringX sizeSpanEndLen(int start, int dp) {
        return sizeSpan(start, length(), dp);
    }

    public SpannableStringX sizeSpan(int start, int end, int dp) {
        setSpan(new AbsoluteSizeSpan(dp, true), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

}
