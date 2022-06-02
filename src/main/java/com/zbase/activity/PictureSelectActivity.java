package com.zbase.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.zbase.R;
import com.zbase.entity.LocalMedia;
import com.zbase.util.ImageUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.util.SystemBarUtils;
import com.zbase.util.ToastUtils;
import com.zbase.view.RoundedButton;
import com.zbase.view.adapter.PicturePagerAdapter;
import com.zbase.view.adapter.PictureSelectAdapter;
import com.zbase.view.decor.GridSpacingItemDecoration;
import com.zbase.view.x.CheckBoxX;
import com.zbase.view.x.ConstraintLayoutParamsX;
import com.zbase.view.x.ConstraintLayoutX;
import com.zbase.view.x.FrameLayoutParamsX;
import com.zbase.view.x.ImageViewX;
import com.zbase.view.x.LinearLayoutParamsX;
import com.zbase.view.x.LinearLayoutX;
import com.zbase.view.x.ListViewX;
import com.zbase.view.x.RecyclerViewX;
import com.zbase.view.x.RelativeLayoutParamsX;
import com.zbase.view.x.RelativeLayoutX;
import com.zbase.view.x.TextViewX;

import java.util.ArrayList;
import java.util.List;

public final class PictureSelectActivity extends ZBaseActivity {

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarUtils.setStatusBarColor(getWindow(), Color.BLACK);
        SystemBarUtils.setNavBarColor(getWindow(), Color.BLACK);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * Container Layout
         */
        findViewById(android.R.id.content).setBackgroundColor(Color.BLACK);
        int containerPadding = ResourceUtils.getPixel(this, R.dimen.activity_padding);

        /*
         * Id
         */
        int bucketLayoutId = View.generateViewId();
        int pictureRvId = View.generateViewId();
        int previewTvId = View.generateViewId();
        int bucketLvId = View.generateViewId();

        /*
         * List
         */
        List<List<LocalMedia>> allMediaList = new ArrayList<>();
        List<LocalMedia> bucketMediaList = new ArrayList<>();
        PictureSelectAdapter pictureAdapter = new PictureSelectAdapter(this, bucketMediaList);
        pictureAdapter.setItemClickListener((adapterView, view, i, l) -> {
            showPreviewView(bucketMediaList);
        });
        pictureAdapter.setItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        ImageUtils.loadLocalImage(this, mediaList -> {
            if (mediaList == null || mediaList.size() < 1) {
                ToastUtils.show(PictureSelectActivity.this, ResourceUtils.getString(PictureSelectActivity.this, R.string.error_get_local_media));
                return;
            }
            allMediaList.addAll(mediaList);
            bucketMediaList.addAll(mediaList.get(0));
            pictureAdapter.notifyDataSetChanged();
        });

        // Preview Text
        int containerPaddingMultiple = (int) (containerPadding * 1.5);

        // Create Content View
        ConstraintLayoutX containerLayout = new ConstraintLayoutX(this);
        setContentView(containerLayout
                .addChildView(new LinearLayoutX(this)
                                .id(bucketLayoutId)
                                .padding(ResourceUtils.getPixel(this, R.dimen.bucket_left_padding), ResourceUtils.getPixel(this, R.dimen.bucket_top_padding), ResourceUtils.getPixel(this, R.dimen.bucket_right_padding), ResourceUtils.getPixel(this, R.dimen.bucket_bottom_padding))
                                .gravity(Gravity.CENTER)
                                .background(ResourceUtils.getDrawable(this, R.drawable.bg_picture_select_bucket))
                                .addChildView(new TextViewX(this).text("相机胶卷").textColor(Color.WHITE))
                                .addChildView(new ImageViewX(this).src(R.drawable.ic_down_white).scaleType(ImageView.ScaleType.FIT_XY))
                                .clickListener(v -> showBucketListView(containerLayout, bucketLayoutId, bucketLvId, containerPadding, allMediaList)),
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
                                .backgroundColor(ResourceUtils.getColor(this, R.color.pic_preview_rv))
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

    public void showBucketListView(ConstraintLayoutX containerLayout, int bucketLayoutId, int bucketLvLayoutId, int containerPadding, List<List<LocalMedia>> allMediaList) {
        LinearLayoutX lastBucketLvLayout = containerLayout.findViewById(bucketLvLayoutId);
        LinearLayoutX bucketLvLayout = lastBucketLvLayout == null ? new LinearLayoutX(this) : lastBucketLvLayout;
        if (lastBucketLvLayout != null) {
            bucketLvLayout.setVisibility(View.VISIBLE);
            return;
        }
        int padding = ResourceUtils.getPixel(containerLayout.getContext(), R.dimen.bucket_list_item_padding);
        containerLayout.addChildView(bucketLvLayout
                        .id(bucketLvLayoutId)
                        .backgroundColor(ResourceUtils.getColor(this, R.color.pic_preview_bucket_list))
                        .clickListener(v -> bucketLvLayout.setVisibility(View.GONE))
                        .addChildView(new ListViewX(this)
                                .backgroundColor(Color.BLACK)
                                .divider(new ColorDrawable(Color.DKGRAY))
                                .dividerHeight(1)
                                .adapter(new BaseAdapter() {
                                    @Override
                                    public int getCount() {
                                        return allMediaList.size();
                                    }

                                    @Override
                                    public List<LocalMedia> getItem(int i) {
                                        return allMediaList.get(i);
                                    }

                                    @Override
                                    public long getItemId(int i) {
                                        return i;
                                    }

                                    @Override
                                    public View getView(int i, View view, ViewGroup viewGroup) {
                                        List<LocalMedia> mediaList = getItem(i);
                                        String bucketName = i == 0 ? "相机胶卷" : mediaList.get(0).getBucketName();
                                        TextViewX bucketTv = new TextViewX(PictureSelectActivity.this).textColor(Color.WHITE).textSize(16).text(bucketName);
                                        TextViewX countTv = new TextViewX(PictureSelectActivity.this).textColor(Color.GRAY).text(String.format("（%s）", mediaList.size()));
                                        ImageViewX checkIv = new ImageViewX(PictureSelectActivity.this);
                                        return new LinearLayoutX(PictureSelectActivity.this)
                                                .gravity(Gravity.CENTER_VERTICAL)
                                                .padding(padding, padding, padding, padding)
                                                .addChildView(bucketTv)
                                                .addChildView(countTv, new LinearLayoutParamsX().weight(1))
                                                .addChildView(checkIv);
                                    }
                                }), new LinearLayoutParamsX(LinearLayoutParamsX.MATCH_PARENT, LinearLayoutParamsX.WRAP_CONTENT)),
                new ConstraintLayoutParamsX()
                        .width(0)
                        .height(0)
                        .start2start(ConstraintLayoutParamsX.PARENT_ID)
                        .end2end(ConstraintLayoutParamsX.PARENT_ID)
                        .top2bottom(bucketLayoutId)
                        .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID));
    }

    public void showPreviewView(List<LocalMedia> selectedMediaList) {
        int rootLayoutId = 86;
        ConstraintLayoutX lastRootLayout = findViewById(rootLayoutId);
        ConstraintLayoutX rootConstraintLayout = lastRootLayout == null ? new ConstraintLayoutX(this).id(rootLayoutId) : lastRootLayout;
        if (lastRootLayout != null) {
            rootConstraintLayout.setVisibility(View.VISIBLE);
            return;
        }

        /*
         * Bar
         */
        int barColor = ResourceUtils.getColor(this, R.color.pic_preview_bucket_list);
        int barPadding = ResourceUtils.getPixel(this, R.dimen.pic_preview_bar_padding);

        /*
         * Top Bar
         */
        RelativeLayoutX topRl = new RelativeLayoutX(this)
                .backgroundColor(barColor)
                .padding(barPadding, barPadding, barPadding, barPadding)
                .addChildView(new ImageViewX(this)
                                .src(R.drawable.ic_back_white)
                                .clickListener(v -> rootConstraintLayout.setVisibility(View.GONE)),
                        new RelativeLayoutParamsX()
                                .rule(RelativeLayout.ALIGN_PARENT_LEFT)
                                .rule(RelativeLayout.CENTER_VERTICAL))
                .addChildView(new CheckBoxX(this)
                                .color(ResourceUtils.getColor(this, R.color.pic_preview_check_box_color)),
                        new RelativeLayoutParamsX()
                                .rule(RelativeLayout.ALIGN_PARENT_RIGHT)
                                .rule(RelativeLayout.CENTER_VERTICAL));

        /*
         * Bottom Bar
         */
        RelativeLayoutX bottomRl = new RelativeLayoutX(this)
                .backgroundColor(barColor)
                .padding(barPadding, barPadding, barPadding, barPadding)
                .addChildView(new RoundedButton(this)
                                .solidColor(ResourceUtils.getColor(this, R.color.pic_preview_confirm_btn_color))
                                .text("确定")
                                .textColor(Color.WHITE),
                        new RelativeLayoutParamsX()
                                .rule(RelativeLayout.ALIGN_PARENT_RIGHT)
                                .rule(RelativeLayout.CENTER_VERTICAL)
                                .height(ResourceUtils.getPixel(this, R.dimen.btn_height)));

        /*
         * Picture Pager
         */
        ViewPager2 picPager = new ViewPager2(this);
        picPager.setBackgroundColor(Color.BLACK);
        picPager.setAdapter(new PicturePagerAdapter(selectedMediaList));

        /*
         * Add
         */
        addContentView(rootConstraintLayout
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
