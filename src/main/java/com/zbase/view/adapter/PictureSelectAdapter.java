package com.zbase.view.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zbase.entity.LocalMedia;
import com.zbase.util.DensityUtils;
import com.zbase.x.ColorX;
import com.zbase.x.lp.FrameLayoutParamsX;
import com.zbase.x.view.CheckBoxX;
import com.zbase.x.view.ImageViewX;
import com.zbase.x.viewgroup.FrameLayoutX;

import java.util.List;

public final class PictureSelectAdapter extends ZBaseRvAdapter<PictureSelectAdapter.PictureSelectHolder, PictureSelectAdapter> {

    private final Context CONTEXT;

    private final List<LocalMedia> PICTURE_LIST;

    private final int WH;

    public PictureSelectAdapter(Context context, List<LocalMedia> pictureList) {
        this.CONTEXT = context;
        this.PICTURE_LIST = pictureList;
        this.WH = DensityUtils.dp2px2int(context,80);
    }

    @NonNull
    @Override
    public PictureSelectAdapter.PictureSelectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PictureSelectAdapter.PictureSelectHolder(new FrameLayoutX(CONTEXT, WH, WH)
                .addChildView(new ImageViewX(CONTEXT)
                                .id(66)
                                .scaleType(ImageView.ScaleType.CENTER_CROP),
                        new FrameLayoutParamsX(FrameLayoutParamsX.MATCH_PARENT, FrameLayoutParamsX.MATCH_PARENT))
                .addChildView(new CheckBoxX(CONTEXT)
                                .id(67)
                                .color(ColorX.HEX_549588),
                        new FrameLayoutParamsX().gravity(Gravity.TOP | Gravity.END)), 66, 67);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureSelectAdapter.PictureSelectHolder holder, int position) {
        holder.mPicIv.uri(PICTURE_LIST.get(position).getPath());
        setOnItemClickListener(holder.itemView, position);
        setOnItemSelectedListener(holder.mCheckBox, position);
    }

    @Override
    public int getItemCount() {
        return PICTURE_LIST.size();
    }

    static class PictureSelectHolder extends RecyclerView.ViewHolder {

        public final ImageViewX mPicIv;

        public final CheckBox mCheckBox;

        public PictureSelectHolder(@NonNull View itemView, int ivId, int checkBoxId) {
            super(itemView);
            mPicIv = itemView.findViewById(ivId);
            mCheckBox = itemView.findViewById(checkBoxId);
        }

    }

}
