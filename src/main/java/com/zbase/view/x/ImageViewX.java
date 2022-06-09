package com.zbase.view.x;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import java.io.File;

public final class ImageViewX extends AppCompatImageView implements IViewX<ImageViewX>{

    public ImageViewX(Context context) {
        super(context);
    }

    public ImageViewX(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageViewX src(int resId) {
        setImageResource(resId);
        return this;
    }

    public ImageViewX uri(String path) {
        setImageURI(Uri.fromFile(new File(path)));
        return this;
    }

    public ImageViewX bitmap(Bitmap bitmap) {
        setImageBitmap(bitmap);
        return this;
    }

    public ImageViewX drawable(Drawable drawable) {
        setImageDrawable(drawable);
        return this;
    }

    public ImageViewX scaleType(ImageView.ScaleType scaleType) {
        setScaleType(scaleType);
        return this;
    }

}
