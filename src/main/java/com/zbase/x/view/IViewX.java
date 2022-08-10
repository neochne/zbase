package com.zbase.x.view;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;

public interface IViewX<T extends View> {

    default T id(int id) {
        T v = (T) this;
        v.setId(id);
        return v;
    }

    default T tag(Object tag) {
        T v = (T) this;
        v.setTag(tag);
        return v;
    }

    default T tag(int key, Object tag) {
        T v = (T) this;
        v.setTag(key, tag);
        return v;
    }

    default T disable() {
        T v = (T) this;
        v.setEnabled(false);
        return v;
    }

    default T background(Drawable drawable) {
        T v = (T) this;
        v.setBackground(drawable);
        return v;
    }

    default T backgroundColor(int color) {
        T v = (T) this;
        v.setBackgroundColor(color);
        return v;
    }

    default T backgroundResource(int res) {
        T v = (T) this;
        v.setBackgroundResource(res);
        return v;
    }

    default T minimumHeight(int height) {
        T v = (T) this;
        v.setMinimumHeight(height);
        return v;
    }

    default T minimumWidth(int width) {
        T v = (T) this;
        v.setMinimumWidth(width);
        return v;
    }

    default T padding(int l, int t, int r, int b) {
        T v = (T) this;
        v.setPadding(l, t, r, b);
        return v;
    }

    default T padding(int padding) {
        T v = (T) this;
        v.setPadding(padding, padding, padding, padding);
        return v;
    }

    default T scaleX(float scale) {
        T v = (T) this;
        v.setScaleX(scale);
        return v;
    }

    default T scaleY(float scale) {
        T v = (T) this;
        v.setScaleY(scale);
        return v;
    }

    default T lp(ViewGroup.LayoutParams lp) {
        T v = (T) this;
        v.setLayoutParams(lp);
        return v;
    }

    default T focus() {
        T v = (T) this;
        v.requestFocus();
        return v;
    }

    default T visible() {
        T v = (T) this;
        v.setVisibility(View.VISIBLE);
        return v;
    }

    default T invisible() {
        T v = (T) this;
        v.setVisibility(View.INVISIBLE);
        return v;
    }

    default T gone() {
        T v = (T) this;
        v.setVisibility(View.GONE);
        return v;
    }

    default boolean isVisible() {
        return ((T) this).getVisibility() == View.VISIBLE;
    }

    default T toFront() {
        T v = (T) this;
        v.bringToFront();
        return v;
    }

    default T attachBadgeDrawable(BadgeDrawable badgeDrawable) {
        T v = (T) this;
        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("UnsafeExperimentalUsageError")
            @Override
            public void onGlobalLayout() {
                BadgeUtils.attachBadgeDrawable(badgeDrawable, v);
                v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return v;
    }

    @SuppressLint("UnsafeExperimentalUsageError")
    default T detachBadgeDrawable(BadgeDrawable badgeDrawable) {
        T v = (T) this;
        BadgeUtils.detachBadgeDrawable(badgeDrawable, v);
        return v;
    }

    default T showBadgeDrawable(BadgeDrawable badgeDrawable) {
        T v = (T) this;
        badgeDrawable.setVisible(true);
        return v;
    }

    default T hideBadgeDrawable(BadgeDrawable badgeDrawable) {
        T v = (T) this;
        badgeDrawable.setVisible(false);
        return v;
    }

    default T clickListener(View.OnClickListener clickListener) {
        T v = (T) this;
        v.setOnClickListener(clickListener);
        return v;
    }

    default T longClickListener(View.OnLongClickListener longClickListener) {
        T v = (T) this;
        v.setOnLongClickListener(longClickListener);
        return v;
    }

    default T globalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener) {
        T v = (T) this;
        v.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
        return v;
    }

    default T removeGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener) {
        T v = (T) this;
        v.getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
        return v;
    }

}
