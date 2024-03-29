package com.zbase.view.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

public abstract class ZBaseRvAdapter<VH extends RecyclerView.ViewHolder,T extends ZBaseRvAdapter<VH,T>> extends RecyclerView.Adapter<VH> {

    private AdapterView.OnItemClickListener mItemClickListener;

    private AdapterView.OnItemLongClickListener mItemLongClickListener;

    public AdapterView.OnItemSelectedListener mItemSelectedListener;

    public T setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
        return (T) this;
    }

    protected void setOnItemClickListener(View itemView, int position) {
        itemView.setOnClickListener(view -> mItemClickListener.onItemClick(null, itemView, position, position));
    }

    public T setItemLongClickListener(AdapterView.OnItemLongClickListener itemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener;
        return (T) this;
    }

    protected void setOnItemLongClickListener(View itemView, int position) {
        itemView.setOnLongClickListener(view -> mItemLongClickListener.onItemLongClick(null, itemView, position, position));
    }

    public T setItemSelectedListener(AdapterView.OnItemSelectedListener itemSelectedListener) {
        this.mItemSelectedListener = itemSelectedListener;
        return (T) this;
    }

    protected void setOnItemSelectedListener(CompoundButton btn, int position) {
        btn.setOnCheckedChangeListener((compoundButton, b) -> mItemSelectedListener.onItemSelected(null, btn, position, b ? 1 : 0));
    }

}
