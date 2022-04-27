package com.zbase.view.x;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

public final class ImageViewX extends AppCompatImageView {

    public ImageViewX(Context context) {
        super(context);
    }

    public ImageViewX src(int resId) {
        setImageResource(resId);
        return this;
    }

}
