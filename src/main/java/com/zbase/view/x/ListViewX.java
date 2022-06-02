package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.zbase.interfaces.ScrollListener;
import com.zbase.util.ViewUtils;

public final class ListViewX extends ListView implements IViewX<ListViewX> {

    public ListViewX(@NonNull Context context) {
        super(context);
    }

    public ListViewX divider(Drawable drawable) {
        setDivider(drawable);
        return this;
    }

    public ListViewX dividerHeight(int height) {
        setDividerHeight(height);
        return this;
    }

    public ListViewX adapter(BaseAdapter adapter) {
        setAdapter(adapter);
        return this;
    }

    public ListViewX itemClickListener(AdapterView.OnItemClickListener listener) {
        setOnItemClickListener(listener);
        return this;
    }

    public ListViewX scrollListener(ScrollListener listener) {
        ViewUtils.isChildScrollToBottom(this, listener);
        return this;
    }

    @Override
    public ListViewX id(int id) {
        setId(id);
        return this;
    }

    @Override
    public ListViewX tag(Object tag) {
        setTag(tag);
        return this;
    }

    @Override
    public ListViewX tag(int key, Object tag) {
        setTag(key, tag);
        return this;
    }

    @Override
    public ListViewX disable() {
        setEnabled(false);
        return this;
    }

    @Override
    public ListViewX background(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Override
    public ListViewX backgroundColor(int color) {
        setBackgroundColor(color);
        return this;
    }

    @Override
    public ListViewX backgroundResource(int res) {
        setBackgroundResource(res);
        return this;
    }

    @Override
    public ListViewX minimumHeight(int height) {
        setMinimumHeight(height);
        return this;
    }

    @Override
    public ListViewX minimumWidth(int width) {
        setMinimumWidth(width);
        return this;
    }

    @Override
    public ListViewX padding(int l, int t, int r, int b) {
        setPadding(l, t, r, b);
        return this;
    }

    @Override
    public ListViewX lp(ViewGroup.LayoutParams lp) {
        setLayoutParams(lp);
        return this;
    }

    @Override
    public ListViewX focus() {
        requestFocus();
        return this;
    }

    @Override
    public ListViewX clickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public ListViewX longClickListener(OnLongClickListener longClickListener) {
        this.setOnLongClickListener(longClickListener);
        return this;
    }

}
