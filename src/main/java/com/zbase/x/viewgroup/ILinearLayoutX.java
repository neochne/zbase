package com.zbase.x.viewgroup;

import android.widget.LinearLayout;

public interface ILinearLayoutX<T extends LinearLayout> extends IViewGroupX<T> {

    default T gravity(int gravity) {
        T ll = (T) this;
        ll.setGravity(gravity);
        return ll;
    }

    default T orientation(int orientation) {
        T ll = (T) this;
        ll.setOrientation(orientation);
        return ll;
    }

}
