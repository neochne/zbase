package com.zbase.view.x;

import android.content.Context;

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

}
