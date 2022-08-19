package com.zbase.x.able;

import com.zbase.util.ResourceUtils;

public interface ResourceAble extends ContextAble {

    default String string(int id) {
        return ResourceUtils.getString(getActivityX(), id);
    }

    default int color(int id) {
        return ResourceUtils.getColor(getActivityX(), id);
    }

}
