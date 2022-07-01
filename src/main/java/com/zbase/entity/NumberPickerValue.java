package com.zbase.entity;

public final class NumberPickerValue {

    private int curIndex;

    private String[] values;

    public NumberPickerValue(int curIndex, String[] values) {
        this.curIndex = curIndex;
        this.values = values;
    }

    public int getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

}
