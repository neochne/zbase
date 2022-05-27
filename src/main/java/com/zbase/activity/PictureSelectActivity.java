package com.zbase.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zbase.R;
import com.zbase.entity.LocalMedia;
import com.zbase.util.ImageUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.util.SystemBarUtils;
import com.zbase.view.PictureSelectView;
import com.zbase.view.list.GridSpacingItemDecoration;
import com.zbase.view.x.ConstraintLayoutX;
import com.zbase.view.x.ImageViewX;
import com.zbase.view.x.TextViewX;
import com.zbase.view.x.params.ConstraintLayoutParamsX;
import com.zbase.view.x.params.FrameLayoutParamsX;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class PictureSelectActivity extends ZBaseActivity {

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * Container Layout
         */
        findViewById(android.R.id.content).setBackgroundColor(Color.BLACK);
        ConstraintLayoutX containerLayout = new ConstraintLayoutX(this);
        int containerPadding = ResourceUtils.getPixel(this, R.dimen.activity_padding);
        SystemBarUtils.setStatusBarColor(getWindow(), Color.BLACK);
        SystemBarUtils.hideNavBar(getWindow());

        /*
         * Bucket
         */
        int pictureRvId = View.generateViewId();
        TextViewX bucketTv = new TextViewX(this).textColor(Color.WHITE);
        bucketTv.setText("相机胶卷");
        ImageViewX bucketDownIv = new ImageViewX(this).src(R.drawable.ic_down_white);
        bucketDownIv.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout bucketLayout = new LinearLayout(this);
        bucketLayout.setBackground(ResourceUtils.getDrawable(this, R.drawable.bg_picture_select_bucket));
        int bucketLeftPadding = ResourceUtils.getPixel(this, R.dimen.bucket_left_padding);
        int bucketTopPadding = ResourceUtils.getPixel(this, R.dimen.bucket_top_padding);
        int bucketRightPadding = ResourceUtils.getPixel(this, R.dimen.bucket_right_padding);
        int bucketBottomPadding = ResourceUtils.getPixel(this, R.dimen.bucket_bottom_padding);
        bucketLayout.setPadding(bucketLeftPadding, bucketTopPadding, bucketRightPadding, bucketBottomPadding);
        bucketLayout.addView(bucketTv);
        bucketLayout.addView(bucketDownIv);
        bucketLayout.setGravity(Gravity.CENTER);
        containerLayout.add1View(bucketLayout, View.generateViewId())
                .top2top(ConstraintLayoutParamsX.PARENT_ID)
                .bottom2top(pictureRvId)
                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                .topMargin(containerPadding)
                .chainStyleVertical(ConstraintLayoutParamsX.CHAIN_SPREAD);

        /*
         * Back
         */
        ImageViewX backIv = new ImageViewX(this).src(R.drawable.ic_clear_white);
        containerLayout.add1View(backIv)
                .top2top(bucketLayout.getId())
                .bottom2bottom(bucketLayout.getId())
                .leftMargin(containerPadding)
                .start2start(ConstraintLayoutParamsX.PARENT_ID);
        backIv.setOnClickListener(v -> finish());


        /*
         * Picture List
         */
        int previewTvId = View.generateViewId();
        RecyclerView pictureRv = new RecyclerView(this);
        pictureRv.setLayoutManager(new GridLayoutManager(this, 4));
        pictureRv.addItemDecoration(new GridSpacingItemDecoration(4, 20, true));
        pictureRv.setBackgroundColor(ResourceUtils.getColor(this, R.color.black_353535));
        containerLayout.add1View(pictureRv, pictureRvId)
                .width(0)
                .height(0)
                .top2bottom(bucketLayout.getId())
                .bottom2top(previewTvId)
                .topMargin(ResourceUtils.getPixel(this, R.dimen.pic_rv_top_margin))
                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                .weightVertical(6);
        List<LocalMedia> selectBucketLocalMediaList = new ArrayList<>();
        List<LocalMedia> allBucketNameList = new ArrayList<>();
        PictureAdapter pictureAdapter = new PictureAdapter(selectBucketLocalMediaList);
        pictureRv.setAdapter(pictureAdapter);
        ImageUtils.loadLocalImage(this, mediaMap -> {
            List<List<LocalMedia>> allLocalMediaList = new ArrayList<>(mediaMap.values());
            for (List<LocalMedia> localMediaList : allLocalMediaList) {
                selectBucketLocalMediaList.addAll(localMediaList);
            }
            pictureAdapter.notifyDataSetChanged();
        });

        /*
         * Preview Text
         */
        int containerPaddingMultiple = (int) (containerPadding * 1.5);
        TextViewX previewTv = new TextViewX(this).text("预览").textColor(Color.WHITE);
        containerLayout.add1View(previewTv, previewTvId)
                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID)
                .leftMargin(containerPaddingMultiple)
                .topMargin(containerPaddingMultiple)
                .bottomMargin(containerPaddingMultiple)
                .top2bottom(pictureRv.getId());

        /*
         * Confirm Text
         */
        TextViewX confirmTv = new TextViewX(this).text("确定").textColor(Color.WHITE);
        containerLayout.add1View(confirmTv)
                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                .top2bottom(pictureRv.getId())
                .top2top(previewTv.getId())
                .rightMargin(containerPaddingMultiple)
                .bottom2bottom(previewTv.getId());
        setContentView(containerLayout, new FrameLayoutParamsX(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    public static class PictureAdapter extends RecyclerView.Adapter<PictureVH> {

        private final List<LocalMedia> mPictureList;

        public PictureAdapter(List<LocalMedia> pictureList) {
            this.mPictureList = pictureList;
        }

        @NonNull
        @Override
        public PictureVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PictureVH(new PictureSelectView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(@NonNull PictureVH holder, int position) {
            String picPath = mPictureList.get(position).getPath();
            holder.mPicIv.setImageURI(Uri.fromFile(new File(picPath)));
        }

        @Override
        public int getItemCount() {
            return mPictureList.size();
        }

    }

    public static class PictureVH extends RecyclerView.ViewHolder {

        public final ImageViewX mPicIv;

        public final CheckBox mCheckBox;

        public PictureVH(@NonNull View itemView) {
            super(itemView);
            int picIvId = 66;
            mPicIv = itemView.findViewById(picIvId);
            int checkBoxId = 67;
            mCheckBox = itemView.findViewById(checkBoxId);
            itemView.setOnClickListener(v -> showPreviewDialog(itemView.getContext()));
        }

    }

    public static void showPreviewDialog(Context activity) {
        ConstraintLayoutX rootLayout = new ConstraintLayoutX(activity);
        rootLayout.setBackgroundColor(Color.BLACK);
        /*
         * Back
         */
        ImageViewX backIv = new ImageViewX(activity).src(R.drawable.ic_back_white);
        rootLayout.add1View(backIv)
                .top2top(ConstraintLayoutParamsX.PARENT_ID)
                .start2start(ConstraintLayoutParamsX.PARENT_ID);
        /*
         * CheckBox
         */
        CheckBox checkBox = new CheckBox(activity);
        rootLayout.add1View(checkBox)
                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                .top2top(ConstraintLayoutParamsX.PARENT_ID)
                .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID);
        final AlertDialog dialog = new AlertDialog.Builder(activity, android.R.style.Theme_Material_NoActionBar)
                .setView(rootLayout)
                .create();
        SystemBarUtils.hideNavBar(dialog.getWindow());
        dialog.setCancelable(false);
        dialog.show();
        backIv.setOnClickListener(v->dialog.dismiss());
    }

}
