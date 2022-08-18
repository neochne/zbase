package com.zbase.x.able;

import com.zbase.util.ResourceUtils;

public interface ResourceAble extends ContextAble {

    default String string(int string) {
        return ResourceUtils.getString(getActivityX(), string);
    }

}
