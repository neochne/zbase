package com.zbase.x.able;

import com.zbase.x.ActivityX;
import com.zbase.x.FragmentX;

public interface ContextAble {

    default boolean isActivityX() {
        return this instanceof ActivityX;
    }

    default boolean isFragmentX() {
        return this instanceof FragmentX;
    }

    default ActivityX getActivityX() {
        if (isActivityX()) {
            return (ActivityX) this;
        } else if (isFragmentX()) {
            return (ActivityX) getFragmentX().requireActivity();
        }
        throw unsupportedException();
    }

    default FragmentX getFragmentX() {
        return (FragmentX) this;
    }

    default Object getTag(int key) {
        return getActivityX().getWindow().getDecorView().getTag(key);
    }

    default void setTag(int key, Object tag) {
        getActivityX().getWindow().getDecorView().setTag(key, tag);
    }

    default RuntimeException unsupportedException() {
        return new RuntimeException("Unsupported context");
    }

}
