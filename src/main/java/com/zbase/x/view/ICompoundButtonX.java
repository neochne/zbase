package com.zbase.x.view;

import android.widget.CompoundButton;

public interface ICompoundButtonX<T extends CompoundButton> extends IViewX<T> {

    default T checkedListener(CompoundButton.OnCheckedChangeListener listener) {
        T t = (T) this;
        t.setOnCheckedChangeListener(listener);
        return t;
    }


}
