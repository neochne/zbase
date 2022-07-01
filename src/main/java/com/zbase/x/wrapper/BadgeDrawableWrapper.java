package com.zbase.x.wrapper;

import android.content.Context;

import com.google.android.material.badge.BadgeDrawable;

public final class BadgeDrawableWrapper {

    private final BadgeDrawable BADGE_DRAWABLE;

    public BadgeDrawableWrapper(Context context) {
        this.BADGE_DRAWABLE = BadgeDrawable.create(context);
    }

    public BadgeDrawableWrapper textColor(int color) {
        BADGE_DRAWABLE.setBadgeTextColor(color);
        return this;
    }

    public BadgeDrawableWrapper backgroundColor(int color) {
        BADGE_DRAWABLE.setBackgroundColor(color);
        return this;
    }

    public BadgeDrawableWrapper number(int number) {
        BADGE_DRAWABLE.setNumber(number);
        return this;
    }

    public BadgeDrawableWrapper visible(boolean visible) {
        BADGE_DRAWABLE.setVisible(visible);
        return this;
    }

    public BadgeDrawable create() {
        return BADGE_DRAWABLE;
    }

}
