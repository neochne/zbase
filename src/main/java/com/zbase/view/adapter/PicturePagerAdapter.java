package com.zbase.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zbase.entity.LocalMedia;
import com.zbase.view.x.ImageViewX;

import java.util.List;

public final class PicturePagerAdapter extends ZBaseRvAdapter<PicturePagerAdapter.PicturePagerHolder>{

    private final List<LocalMedia> PICTURE_LIST;

    public PicturePagerAdapter(List<LocalMedia> pictureList) {
        this.PICTURE_LIST = pictureList;
    }

    @NonNull
    @Override
    public PicturePagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PicturePagerHolder(new ImageViewX(parent.getContext())
                .lp(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)));
    }

    @Override
    public void onBindViewHolder(@NonNull PicturePagerHolder holder, int position) {
        ((ImageViewX)holder.itemView).uri(PICTURE_LIST.get(position).getPath());
    }

    @Override
    public int getItemCount() {
        return PICTURE_LIST.size();
    }

    static class PicturePagerHolder extends RecyclerView.ViewHolder {

        public PicturePagerHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
