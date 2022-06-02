package com.zbase.view.x;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatEditText;

import com.zbase.R;

public class EditTextX extends AppCompatEditText implements IEditTextX<EditTextX> {

    public EditTextX(@NonNull Context context) {
        super(new ContextThemeWrapper(context, R.style.Et));
    }

    @Override
    public EditTextX text(String text) {
        setText(text);
        return this;
    }

    @Override
    public EditTextX text(int res) {
        setText(res);
        return this;
    }

    @Override
    public EditTextX textSize(float sp) {
        setTextSize(sp);
        return this;
    }

    @Override
    public EditTextX hint(String hint) {
        setHint(hint);
        return this;
    }

    @Override
    public EditTextX hint(int res) {
        setHint(res);
        return this;
    }

    @Override
    public EditTextX textColor(int color) {
        setTextColor(color);
        return this;
    }

    @Override
    public EditTextX hintTextColor(int color) {
        setHintTextColor(color);
        return this;
    }

    @Override
    public EditTextX singleLine() {
        setSingleLine();
        setMaxLines(1);
        return this;
    }

    @Override
    public EditTextX maxLines(int line) {
        setMaxLines(line);
        return this;
    }

    @Override
    public EditTextX gravity(int gravity) {
        setGravity(gravity);
        return this;
    }

    @Override
    public EditTextX minHeight(int height) {
        setMinHeight(height);
        return this;
    }

    @Override
    public EditTextX minWidth(int width) {
        setMinWidth(width);
        return this;
    }

    @Override
    public EditTextX ellipsize(TextUtils.TruncateAt where) {
        setEllipsize(where);
        return this;
    }

    @Override
    public EditTextX typeFace(Typeface typeface) {
        setTypeface(typeface);
        return this;
    }

    @Override
    public EditTextX enableContentScroll() {
        setMovementMethod(new ScrollingMovementMethod());
        return this;
    }

    @Override
    public EditTextX inputPhone() {
        setInputType(InputType.TYPE_CLASS_PHONE);
        return this;
    }

    @Override
    public EditTextX inputNumber() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        return this;
    }

    @Override
    public EditTextX inputNumberDecimal() {
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        return this;
    }

    @Override
    public EditTextX imeSearch() {
        setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        return this;
    }

    @Override
    public EditTextX imeSend() {
        setImeOptions(EditorInfo.IME_ACTION_SEND);
        return this;
    }

    @Override
    public EditTextX imeDone() {
        setImeOptions(EditorInfo.IME_ACTION_DONE);
        return this;
    }

    @Override
    public EditTextX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public EditTextX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public EditTextX tag(int key, Object tag) {
        setTag(key, tag);
        return this;
    }

    @Override
    public EditTextX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public EditTextX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public EditTextX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public EditTextX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public EditTextX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public EditTextX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public EditTextX padding(int l, int t, int r, int b) {
        setPadding(l, t, r, b);
        return this;
    }

    @Override
    public EditTextX lp(ViewGroup.LayoutParams lp) {
        setLayoutParams(lp);
        return this;
    }

    @Override
    public EditTextX focus() {
        requestFocus();
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

    @Override
    public EditTextX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }
    
}
