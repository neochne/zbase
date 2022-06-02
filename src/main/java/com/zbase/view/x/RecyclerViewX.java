package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class RecyclerViewX extends RecyclerView implements IViewX<RecyclerViewX> {

    public RecyclerViewX(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewX layoutManager(LayoutManager manager) {
        setLayoutManager(manager);
        return this;
    }

    public RecyclerViewX itemDecoration(ItemDecoration decor) {
        addItemDecoration(decor);
        return this;
    }

    public RecyclerViewX adapter(Adapter adapter) {
        setAdapter(adapter);
        return this;
    }

    @Override
    public RecyclerViewX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public RecyclerViewX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public RecyclerViewX tag(int key, Object tag) {
        setTag(key,tag);
        return this;
    }

    @Override
    public RecyclerViewX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public RecyclerViewX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public RecyclerViewX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public RecyclerViewX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public RecyclerViewX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public RecyclerViewX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public RecyclerViewX padding(int l, int t, int r, int b) {
        setPadding(l,t,r,b);
        return this;
    }

    @Override
    public RecyclerViewX lp(ViewGroup.LayoutParams lp) {
        setLayoutParams(lp);
        return this;
    }

    @Override
    public RecyclerViewX focus() {
        requestFocus();
        return this;
    }

    @Override
    public RecyclerViewX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public RecyclerViewX longClickListener(View.OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }
    
}
