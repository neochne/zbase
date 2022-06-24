package com.zbase.util;

import android.content.Context;

import com.google.android.material.badge.BadgeDrawable;

public final class BadgeDrawableBuilder {

    private final BadgeDrawable BADGE_DRAWABLE;

    public BadgeDrawableBuilder(Context context) {
        this.BADGE_DRAWABLE = BadgeDrawable.create(context);
    }

    public BadgeDrawableBuilder textColor(int color) {
        BADGE_DRAWABLE.setBadgeTextColor(color);
        return this;
    }

    public BadgeDrawableBuilder backgroundColor(int color) {
        BADGE_DRAWABLE.setBackgroundColor(color);
        return this;
    }

    public BadgeDrawableBuilder number(int number) {
        BADGE_DRAWABLE.setNumber(number);
        return this;
    }

    public BadgeDrawableBuilder visible(boolean visible) {
        BADGE_DRAWABLE.setVisible(visible);
        return this;
    }

    public BadgeDrawable create() {
        return BADGE_DRAWABLE;
    }

}
