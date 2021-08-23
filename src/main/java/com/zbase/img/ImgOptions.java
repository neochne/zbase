/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.img;

import android.content.Context;
import android.view.View;

import java.io.File;

import androidx.fragment.app.Fragment;

public class ImgOptions {

    private Context mContext;
    private Fragment mFragment;
    private View mView;
    private View mTargetView;
    private String mUrl = "";
    private File mFile;
    private int mResId;
    private boolean mCircle;
    private boolean mFitCenter;
    private boolean mCenterCrop;
    private boolean mCenterInside;
    private ImgLoadListener mLoadListener;
    private static int sPlaceholderResId;

    /*
     * ========
     * setter
     * ========
     */
    ImgOptions(Context context){
        this.mContext = context;
    }

    ImgOptions(Fragment fragment){
        this.mFragment = fragment;
    }

    ImgOptions(View view){
        this.mView = view;
    }

    public ImgOptions load(String url) {
        this.mUrl = url;
        return this;
    }

    public ImgOptions load(int resId) {
        this.mResId = resId;
        return this;
    }

    public ImgOptions load(File file) {
        this.mFile = file;
        return this;
    }

    public ImgOptions circle(){
        this.mCircle = true;
        return this;
    }

    public ImgOptions fitCenter(){
        this.mFitCenter = true;
        return this;
    }

    public ImgOptions centerCrop(){
        this.mCenterCrop = true;
        return this;
    }

    public ImgOptions centerInside(){
        this.mCenterInside = true;
        return this;
    }

    public ImgOptions loadListener(ImgLoadListener loadListener){
        this.mLoadListener = loadListener;
        return this;
    }

    public static void setPlaceholderResId(int placeholderResId){
        sPlaceholderResId = placeholderResId;
    }

    /*
     * ========
     * getter
     * ========
     */
    public Context getContext() {
        return mContext;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public View getView() {
        return mView;
    }

    public View getTargetView() {
        return mTargetView;
    }

    public String getUrl() {
        return mUrl;
    }

    public File getFile() {
        return mFile;
    }

    public int getResId() {
        return mResId;
    }

    public boolean isCircle() {
        return mCircle;
    }

    public boolean isFitCenter() {
        return mFitCenter;
    }

    public boolean isCenterCrop() {
        return mCenterCrop;
    }

    public boolean isCenterInside() { return mCenterInside; }

    public ImgLoadListener getLoadListener() { return mLoadListener; }

    public static int getPlaceholderResId() {
        return sPlaceholderResId;
    }

    public void into(View targetView){
        this.mTargetView = targetView;
        ImgLoader.getImgLoadable().load(this);
    }

}
