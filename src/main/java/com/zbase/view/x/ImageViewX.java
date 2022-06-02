package com.zbase.view.x;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public ImageViewX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public ImageViewX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public ImageViewX tag(int key, Object tag) {
        setTag(key,tag);
        return this;
    }

    @Override
    public ImageViewX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public ImageViewX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public ImageViewX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public ImageViewX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public ImageViewX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public ImageViewX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public ImageViewX padding(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    @Override
    public ImageViewX lp(ViewGroup.LayoutParams lp) {
        setLayoutParams(lp);
        return this;
    }

    @Override
    public ImageViewX focus() {
        requestFocus();
        return this;
    }

    @Override
    public ImageViewX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public ImageViewX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }

}
