package com.zbase.x.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.zbase.view.ScrollListener;
import com.zbase.util.ViewUtils;
import com.zbase.x.ColorX;

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

    public ListViewX noDivider() {
        setDivider(null);
        setDividerHeight(0);
        return this;
    }

    public ListViewX noClickColor() {
        setCacheColorHint(ColorX.TRANSPARENT);
        setSelector(android.R.color.transparent);
        return this;
    }

    public ListViewX adapter(BaseAdapter adapter) {
        setAdapter(adapter);
        return this;
    }

    public ListViewX header(View view) {
        addHeaderView(view);
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

}
