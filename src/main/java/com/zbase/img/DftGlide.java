package com.zbase.img;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.zbase.utils.StringUtils;

import java.io.File;

/**
 * Created by neo on Thu,Aug 29,2019,at 13:33
 */
public class DftGlide implements ImgLoadable {

    @Override
    public void load(ImgOptions imgOptions) {
        if (imgOptions == null) {
            return;
        }
        RequestManager rm = null;
        if (imgOptions.getContext() != null) {
            rm = Glide.with(imgOptions.getContext());
        } else if (imgOptions.getFragment() != null) {
            rm = Glide.with(imgOptions.getFragment());
        } else if (imgOptions.getView() != null) {
            rm = Glide.with(imgOptions.getView());
        }
        if (rm == null) {
            return;
        }
        RequestBuilder<Drawable> rb = null;
        String url = imgOptions.getUrl();
        if (!StringUtils.isEmpty(url)) {
            rb = rm.load(url);
        }
        File file = imgOptions.getFile();
        if (file != null) {
            rb = rm.load(file);
        }
        int resId = imgOptions.getResId();
        if (resId != 0) {
            rb = rm.load(resId);
        }
        if (rb == null){
            return;
        }
        if (imgOptions.isCircle()){
            rb = rb.apply(RequestOptions.circleCropTransform());
        }
        if (imgOptions.isFitCenter()){
            rb = rb.fitCenter();
        }
        if (imgOptions.isCenterCrop()){
            rb = rb.centerCrop();
        }
        if (imgOptions.isCenterInside()){
            rb = rb.centerInside();
        }
        int placeholderImgResId = ImgOptions.getPlaceholderResId();
        if (placeholderImgResId != 0){
            rb = rb.placeholder(placeholderImgResId);
        }
        final ImgLoadListener loadListener = imgOptions.getLoadListener();
        if (loadListener != null){
            rb = rb.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    loadListener.onFail(e);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    loadListener.onSuccess();
                    return false;
                }
            });
        }
        rb.into((ImageView) imgOptions.getTargetView());
    }

    @Override
    public void clear() {
    }

}
