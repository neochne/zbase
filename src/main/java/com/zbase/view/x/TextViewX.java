package com.zbase.view.x;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public TextViewX text(String text) {
        setText(text);
        return this;
    }

    @Override
    public TextViewX text(int res) {
        setText(res);
        return this;
    }

    @Override
    public TextViewX textSize(float sp) {
        setTextSize(sp);
        return this;
    }

    @Override
    public TextViewX hint(String hint) {
        setHint(hint);
        return this;
    }

    @Override
    public TextViewX hint(int res) {
        setHint(res);
        return this;
    }

    @Override
    public TextViewX textColor(int color) {
        setTextColor(color);
        return this;
    }

    @Override
    public TextViewX hintTextColor(int color) {
        setHintTextColor(color);
        return this;
    }

    @Override
    public TextViewX singleLine() {
        setSingleLine();
        setMaxLines(1);
        return this;
    }

    @Override
    public TextViewX maxLines(int line) {
        setMaxLines(line);
        return this;
    }

    @Override
    public TextViewX gravity(int gravity) {
        setGravity(gravity);
        return this;
    }

    @Override
    public TextViewX minHeight(int height) {
        setMinHeight(height);
        return this;
    }

    @Override
    public TextViewX minWidth(int width) {
        setMinWidth(width);
        return this;
    }

    @Override
    public TextViewX ellipsize(TextUtils.TruncateAt where) {
        setEllipsize(where);
        return this;
    }

    @Override
    public TextViewX typeFace(Typeface typeface) {
        setTypeface(typeface);
        return this;
    }

    @Override
    public TextViewX enableContentScroll() {
        setMovementMethod(new ScrollingMovementMethod());
        return this;
    }

    @Override
    public TextViewX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public TextViewX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public TextViewX tag(int key, Object tag) {
        setTag(key, tag);
        return this;
    }

    @Override
    public TextViewX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public TextViewX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public TextViewX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public TextViewX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public TextViewX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public TextViewX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public TextViewX padding(int l, int t, int r, int b) {
        setPadding(l, t, r, b);
        return this;
    }

    @Override
    public TextViewX lp(ViewGroup.LayoutParams lp) {
        setLayoutParams(lp);
        return this;
    }

    @Override
    public TextViewX focus() {
        requestFocus();
        return this;
    }

    @Override
    public TextViewX visible() {
        setVisibility(View.VISIBLE);
        return this;
    }

    @Override
    public TextViewX invisible() {
        setVisibility(View.INVISIBLE);
        return this;
    }

    @Override
    public TextViewX gone() {
        setVisibility(View.GONE);
        return this;
    }

    @Override
    public boolean isVisible() {
        return getVisibility() == View.VISIBLE;
    }

    @Override
    public TextViewX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public TextViewX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }

}
