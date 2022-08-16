package com.zbase.x.able;

import com.zbase.util.ResourceUtils;

public interface ResourceAble extends ContextAble{

    default String string(int string) {
        if (isActivityX()) {
            return ResourceUtils.getString(getActivityX(), string);
        } else if (isFragmentX()) {
            return ResourceUtils.getString(getFragmentX().requireContext(), string);
        }
        throw unsupportedException();
    }

}
