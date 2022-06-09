package com.zbase.view.x;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

}
