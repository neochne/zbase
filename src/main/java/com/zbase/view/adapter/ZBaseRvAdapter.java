package com.zbase.view.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

public abstract class ZBaseRvAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private AdapterView.OnItemClickListener mItemClickListener;

    private AdapterView.OnItemLongClickListener mItemLongClickListener;

    public AdapterView.OnItemSelectedListener mItemSelectedListener;

    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    protected void setOnItemClickListener(View itemView, int position) {
        itemView.setOnClickListener(view -> mItemClickListener.onItemClick(null, itemView, position, position));
    }

    public void setItemLongClickListener(AdapterView.OnItemLongClickListener itemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener;
    }

    protected void setOnItemLongClickListener(View itemView, int position) {
        itemView.setOnLongClickListener(view -> mItemLongClickListener.onItemLongClick(null, itemView, position, position));
    }

    public void setItemSelectedListener(AdapterView.OnItemSelectedListener itemSelectedListener) {
        this.mItemSelectedListener = itemSelectedListener;
    }

    protected void setOnItemSelectedListener(CompoundButton btn, int position) {
        btn.setOnCheckedChangeListener((compoundButton, b) -> mItemSelectedListener.onItemSelected(null, btn, position, b ? 1 : 0));
    }

}
