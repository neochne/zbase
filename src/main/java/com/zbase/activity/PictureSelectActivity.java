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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.zbase.R;
import com.zbase.entity.LocalMedia;
import com.zbase.util.ImageUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.util.SystemBarUtils;
import com.zbase.view.RoundedButton;
import com.zbase.view.decor.GridSpacingItemDecoration;
import com.zbase.view.x.CheckBoxX;
import com.zbase.view.x.ConstraintLayoutParamsX;
import com.zbase.view.x.ConstraintLayoutX;
import com.zbase.view.x.FrameLayoutParamsX;
import com.zbase.view.x.FrameLayoutX;
import com.zbase.view.x.ImageViewX;
import com.zbase.view.x.LinearLayoutX;
import com.zbase.view.x.RecyclerViewX;
import com.zbase.view.x.RelativeLayoutParamsX;
import com.zbase.view.x.RelativeLayoutX;
import com.zbase.view.x.TextViewX;

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
        int containerPadding = ResourceUtils.getPixel(this, R.dimen.activity_padding);
        SystemBarUtils.setStatusBarColor(getWindow(), Color.BLACK);
        SystemBarUtils.hideNavBar(getWindow());

        /*
         * Id
         */
        int bucketLayoutId = View.generateViewId();
        int pictureRvId = View.generateViewId();
        int previewTvId = View.generateViewId();

        /*
         * List
         */
        List<LocalMedia> selectBucketLocalMediaList = new ArrayList<>();
        List<LocalMedia> allBucketNameList = new ArrayList<>();
        PictureAdapter pictureAdapter = new PictureAdapter(selectBucketLocalMediaList);
        ImageUtils.loadLocalImage(this, mediaMap -> {
            List<List<LocalMedia>> allLocalMediaList = new ArrayList<>(mediaMap.values());
            for (List<LocalMedia> localMediaList : allLocalMediaList) {
                selectBucketLocalMediaList.addAll(localMediaList);
            }
            pictureAdapter.notifyDataSetChanged();
        });

        // Preview Text
        int containerPaddingMultiple = (int) (containerPadding * 1.5);

        // Create Content View
        setContentView(new ConstraintLayoutX(this)
                .addChildView(new LinearLayoutX(this)
                                .id(bucketLayoutId)
                                .padding(ResourceUtils.getPixel(this, R.dimen.bucket_left_padding), ResourceUtils.getPixel(this, R.dimen.bucket_top_padding), ResourceUtils.getPixel(this, R.dimen.bucket_right_padding), ResourceUtils.getPixel(this, R.dimen.bucket_bottom_padding))
                                .gravity(Gravity.CENTER)
                                .background(ResourceUtils.getDrawable(this, R.drawable.bg_picture_select_bucket))
                                .addChildView(new TextViewX(this).text("相机胶卷").textColor(Color.WHITE))
                                .addChildView(new ImageViewX(this).src(R.drawable.ic_down_white).scaleType(ImageView.ScaleType.FIT_XY)),
                        new ConstraintLayoutParamsX()
                                .top2top(ConstraintLayoutParamsX.PARENT_ID)
                                .bottom2top(pictureRvId)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .topMargin(containerPadding)
                                .chainStyleVertical(ConstraintLayoutParamsX.CHAIN_SPREAD))
                .addChildView(new ImageViewX(this).src(R.drawable.ic_clear_white).clickListener(v -> finish()),
                        new ConstraintLayoutParamsX()
                                .top2top(bucketLayoutId)
                                .bottom2bottom(bucketLayoutId)
                                .leftMargin(containerPadding)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID))
                .addChildView(new RecyclerViewX(this)
                                .id(pictureRvId)
                                .layoutManager(new GridLayoutManager(this, 4))
                                .itemDecoration(new GridSpacingItemDecoration(4, 20, true))
                                .backgroundColor(ResourceUtils.getColor(this, R.color.black_353535))
                                .adapter(pictureAdapter),
                        new ConstraintLayoutParamsX().width(0)
                                .height(0)
                                .top2bottom(bucketLayoutId)
                                .bottom2top(previewTvId)
                                .topMargin(ResourceUtils.getPixel(this, R.dimen.pic_rv_top_margin))
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .weightVertical(1))
                .addChildView(new TextViewX(this).id(previewTvId).text("预览").textColor(Color.WHITE),
                        new ConstraintLayoutParamsX().start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID)
                                .leftMargin(containerPaddingMultiple)
                                .topMargin(containerPaddingMultiple)
                                .bottomMargin(containerPaddingMultiple)
                                .top2bottom(pictureRvId))
                .addChildView(new TextViewX(this).text("确定").textColor(Color.WHITE),
                        new ConstraintLayoutParamsX().end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .top2bottom(pictureRvId)
                                .top2top(previewTvId)
                                .bottom2bottom(previewTvId)
                                .rightMargin(containerPaddingMultiple))

        );
    }

    public static class PictureAdapter extends RecyclerView.Adapter<PictureVH> {

        private final List<LocalMedia> mPictureList;

        public PictureAdapter(List<LocalMedia> pictureList) {
            this.mPictureList = pictureList;
        }

        @NonNull
        @Override
        public PictureVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            int wh = ResourceUtils.getPixel(context, R.dimen.select_pic_w_h);
            return new PictureVH(new FrameLayoutX(context, wh, wh)
                    .addChildView(new ImageViewX(context).id(66).scaleType(ImageView.ScaleType.CENTER_CROP))
                    .addChildView(new CheckBoxX(context).id(67),new FrameLayoutParamsX().gravity(Gravity.TOP | Gravity.END)));
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
            itemView.setOnClickListener(v -> showPreviewView((PictureSelectActivity) itemView.getContext()));
        }

    }

    public static void showPreviewView(PictureSelectActivity activity) {
        int rootLayoutId = 86;
        ConstraintLayoutX lastRootLayout = activity.findViewById(rootLayoutId);
        ConstraintLayoutX rootConstraintLayout = lastRootLayout == null ? new ConstraintLayoutX(activity).id(rootLayoutId) : lastRootLayout;
        if (lastRootLayout != null) {
            rootConstraintLayout.setVisibility(View.VISIBLE);
            return;
        }

        /*
         * Bar
         */
        int barColor = ResourceUtils.getColor(activity, R.color.pic_preview_bar_color);
        int barPadding = ResourceUtils.getPixel(activity, R.dimen.pic_preview_bar_padding);

        /*
         * Top Bar
         */
        RelativeLayoutX topRl = new RelativeLayoutX(activity)
                .backgroundColor(barColor)
                .padding(barPadding, barPadding, barPadding, barPadding)
                .addChildView(new ImageViewX(activity)
                                .src(R.drawable.ic_back_white)
                                .clickListener(v -> rootConstraintLayout.setVisibility(View.GONE)),
                        new RelativeLayoutParamsX()
                                .rule(RelativeLayout.ALIGN_PARENT_LEFT)
                                .rule(RelativeLayout.CENTER_VERTICAL))
                .addChildView(new CheckBoxX(activity)
                                .color(ResourceUtils.getColor(activity, R.color.pic_preview_check_box_color)),
                        new RelativeLayoutParamsX()
                                .rule(RelativeLayout.ALIGN_PARENT_RIGHT)
                                .rule(RelativeLayout.CENTER_VERTICAL));

        /*
         * Bottom Bar
         */
        RelativeLayoutX bottomRl = new RelativeLayoutX(activity)
                .backgroundColor(barColor)
                .padding(barPadding, barPadding, barPadding, barPadding)
                .addChildView(new RoundedButton(activity)
                                .solidColor(ResourceUtils.getColor(activity, R.color.pic_preview_confirm_btn_color))
                                .text("确定")
                                .textColor(Color.WHITE),
                        new RelativeLayoutParamsX()
                                .rule(RelativeLayout.ALIGN_PARENT_RIGHT)
                                .rule(RelativeLayout.CENTER_VERTICAL)
                                .height(ResourceUtils.getPixel(activity, R.dimen.btn_height)));

        /*
         * Picture Pager
         */
        ViewPager2 picPager = new ViewPager2(activity);
        picPager.setBackgroundColor(Color.BLACK);

        /*
         * Add
         */
        activity.addContentView(rootConstraintLayout
                        .addChildView(picPager, new ConstraintLayoutParamsX()
                                .width(0)
                                .height(0)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .top2top(ConstraintLayoutParamsX.PARENT_ID)
                                .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID))
                        .addChildView(topRl, new ConstraintLayoutParamsX()
                                .width(0)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .top2top(ConstraintLayoutParamsX.PARENT_ID))
                        .addChildView(bottomRl, new ConstraintLayoutParamsX()
                                .width(0)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID)),
                new FrameLayoutParamsX(FrameLayoutParamsX.MATCH_PARENT, FrameLayoutParamsX.MATCH_PARENT));
    }

}
