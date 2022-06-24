package com.zbase.view.x;

import android.content.Context;
import android.widget.NumberPicker;

public final class NumberPickerX extends NumberPicker implements IViewX<NumberPickerX> {

    public NumberPickerX(Context context) {
        super(context);
    }

    public NumberPickerX value(int index) {
        setValue(index);
        return this;
    }

    public NumberPickerX minValue(int index) {
        setMinValue(index);
        return this;
    }

    public NumberPickerX maxValue(int index) {
        setMaxValue(index);
        return this;
    }

    public NumberPickerX displayValues(String[] values) {
        setDisplayedValues(values);
        return this;
    }

}
