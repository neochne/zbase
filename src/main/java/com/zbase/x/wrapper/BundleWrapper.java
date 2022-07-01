package com.zbase.x.wrapper;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;

public final class BundleWrapper {

    private final Bundle BUNDLE;

    public BundleWrapper() {
        this.BUNDLE = new Bundle();
    }

    public BundleWrapper addString(String k, String v) {
        BUNDLE.putString(k, v);
        return this;
    }

    public BundleWrapper addStringArrayList(String k, ArrayList<String> list) {
        BUNDLE.putStringArrayList(k, list);
        return this;
    }

    public BundleWrapper addInt(String k, int v) {
        BUNDLE.putInt(k, v);
        return this;
    }

    public BundleWrapper addIntArray(String k, int[] array) {
        BUNDLE.putIntArray(k, array);
        return this;
    }

    public BundleWrapper addIntArrayList(String k, ArrayList<Integer> list) {
        BUNDLE.putIntegerArrayList(k, list);
        return this;
    }

    public BundleWrapper addFloat(String k, float v) {
        BUNDLE.putFloat(k, v);
        return this;
    }

    public BundleWrapper addFloatArray(String k, float[] array) {
        BUNDLE.putFloatArray(k, array);
        return this;
    }

    public BundleWrapper addParcelable(String k, Parcelable p) {
        BUNDLE.putParcelable(k, p);
        return this;
    }

    public BundleWrapper addParcelableList(String k, ArrayList<Parcelable> list) {
        BUNDLE.putParcelableArrayList(k, list);
        return this;
    }

    public BundleWrapper addSparseParcelableArray(String k, SparseArray<? extends Parcelable> array) {
        BUNDLE.putSparseParcelableArray(k, array);
        return this;
    }

    public BundleWrapper addParcelableArray(String k, Parcelable[] array) {
        BUNDLE.putParcelableArray(k, array);
        return this;
    }

    public BundleWrapper addSerializable(String k, Serializable s) {
        BUNDLE.putSerializable(k, s);
        return this;
    }

    public Bundle create() {
        return BUNDLE;
    }

}
