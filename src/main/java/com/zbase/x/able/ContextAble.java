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
        return (ActivityX) this;
    }

    default FragmentX getFragmentX() {
        return (FragmentX) this;
    }

    default Object getTag(int key) {
        if (isActivityX()) {
            return getActivityX().getWindow().getDecorView().getTag(key);
        } else if (isFragmentX()) {
            return getFragmentX().requireActivity().getWindow().getDecorView().getTag(key);
        }
        throw unsupportedException();
    }

    default void setTag(int key, Object tag) {
        if (isActivityX()) {
            getActivityX().getWindow().getDecorView().setTag(key, tag);
        } else if (isFragmentX()) {
            getFragmentX().requireActivity().getWindow().getDecorView().setTag(key, tag);
        }
    }

    default RuntimeException unsupportedException() {
        return new RuntimeException("Unsupported context");
    }

}
