package com.zbase.view.x;

import android.view.View;

public interface IEditTextX<T extends View> extends ITextViewX<T>{

    T inputPhone();

    T inputNumber();

    T inputNumberDecimal();

    T imeSearch();

    T imeSend();

    T imeDone();

}
