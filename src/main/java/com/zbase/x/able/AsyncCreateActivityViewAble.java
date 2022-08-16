package com.zbase.x.able;

import com.zbase.view.ToolbarLayout;

public interface AsyncCreateActivityViewAble {

    default boolean async() {
        return false;
    }

    default void onCreateViewAsync() {
    }

    default void onCreateViewSync() {
    }

    default void onCreateViewWithToolbarAsync(ToolbarLayout toolbarLayout) {
    }

    default void onCreateViewWithToolbarSync(ToolbarLayout toolbarLayout) {
    }

}
