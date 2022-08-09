package com.zbase.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
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
import com.zbase.util.DensityUtils;
import com.zbase.util.ImageUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.util.SystemBarUtils;
import com.zbase.util.ToastUtils;
import com.zbase.util.ViewUtils;
import com.zbase.view.RoundedButton;
import com.zbase.view.adapter.PicturePagerAdapter;
import com.zbase.view.adapter.PictureSelectAdapter;
import com.zbase.view.decor.GridSpacingItemDecoration;
import com.zbase.x.ColorX;
import com.zbase.x.lp.ConstraintLayoutParamsX;
import com.zbase.x.lp.FrameLayoutParamsX;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.lp.RelativeLayoutParamsX;
import com.zbase.x.view.CheckBoxX;
import com.zbase.x.view.ImageViewX;
import com.zbase.x.view.ListViewX;
import com.zbase.x.view.RecyclerViewX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.ConstraintLayoutX;
import com.zbase.x.viewgroup.LinearLayoutX;
import com.zbase.x.viewgroup.RelativeLayoutX;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        int containerPadding = DensityUtils.dp2px2int(this, 10);
        int bucketItemPadding = DensityUtils.dp2px2int(this, 10);

        /*
         * Id
         */
        int bucketLayoutId = View.generateViewId();
        int pictureRvId = View.generateViewId();
        int previewTvId = View.generateViewId();
        int previewRootLayoutId = View.generateViewId();
        int previewPagerId = View.generateViewId();
        int previewIndicatorTvId = View.generateViewId();

        /*
         * List
         */
        List<List<LocalMedia>> allMediaList = new ArrayList<>();
        List<LocalMedia> selectedBucketMediaList = new ArrayList<>();
        PictureSelectAdapter pictureAdapter = new PictureSelectAdapter(this, selectedBucketMediaList)
                .setItemClickListener((adapterView, view, i, l) -> {
                    showPreviewView(selectedBucketMediaList, previewRootLayoutId, previewPagerId, previewIndicatorTvId, i);
                })
                .setItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }

                });

        /*
         * Bucket Layout
         */
        ImageViewX downIv = new ImageViewX(this);
        TextViewX bucketNameTv = new TextViewX(this);

        // Preview Text
        int containerPaddingMultiple = (int) (containerPadding * 1.5);

        /*
         * Bucket List
         */
        LinearLayoutX bucketLvParentLayout = new LinearLayoutX(this);
        ListViewX bucketLv = new ListViewX(this);
        bucketLv.setOnItemClickListener((adapterView, view, i, l) -> {
            selectedBucketMediaList.clear();
            selectedBucketMediaList.addAll(allMediaList.get(i));
            pictureAdapter.notifyDataSetChanged();
            bucketNameTv.setText(i == 0 ? "相机胶卷" : selectedBucketMediaList.get(0).getBucketName());
            hideBucketLv(bucketLvParentLayout, bucketLv, downIv);
        });

        /*
         * Create Content View
         */
        ConstraintLayoutX containerLayout = new ConstraintLayoutX(this);
        setContentView(containerLayout
                .addChildView(new LinearLayoutX(this)
                                .id(bucketLayoutId)
                                .padding(DensityUtils.dp2px2int(this, 9), DensityUtils.dp2px2int(this, 3), DensityUtils.dp2px2int(this, 1), DensityUtils.dp2px2int(this, 3))
                                .gravity(Gravity.CENTER)
                                .background(ResourceUtils.getDrawable(this, R.drawable.bg_picture_select_bucket))
                                .addChildView(bucketNameTv.text("相机胶卷").textColor(Color.WHITE))
                                .addChildView(downIv.src(R.drawable.ic_down_white).scaleType(ImageView.ScaleType.FIT_XY))
                                .clickListener(v -> {
                                    if (bucketLvParentLayout.isVisible()) {
                                        hideBucketLv(bucketLvParentLayout, bucketLv, downIv);
                                    } else {
                                        showBucketLv(bucketLvParentLayout, bucketLv, downIv);
                                    }
                                }),
                        new ConstraintLayoutParamsX()
                                .top2top(ConstraintLayoutParamsX.PARENT_ID)
                                .bottom2top(pictureRvId)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .margins(0, containerPadding, 0, 0)
                                .chainStyleVertical(ConstraintLayoutParamsX.CHAIN_SPREAD))
                .addChildView(new ImageViewX(this)
                                .src(R.drawable.ic_clear_white)
                                .clickListener(v -> finish()),
                        new ConstraintLayoutParamsX()
                                .top2top(bucketLayoutId)
                                .bottom2bottom(bucketLayoutId)
                                .margins(containerPadding, 0, 0, 0)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID))
                .addChildView(new RecyclerViewX(this)
                                .id(pictureRvId)
                                .layoutManager(new GridLayoutManager(this, 4))
                                .itemDecoration(new GridSpacingItemDecoration(4, 20, true))
                                .backgroundColor(ColorX.HEX_FF353535)
                                .adapter(pictureAdapter),
                        new ConstraintLayoutParamsX().width(0)
                                .height(0)
                                .top2bottom(bucketLayoutId)
                                .bottom2top(previewTvId)
                                .margins(0, DensityUtils.dp2px2int(this, 5), 0, 0)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .weightVertical(1))
                .addChildView(new TextViewX(this)
                                .id(previewTvId)
                                .text("预览")
                                .textColor(Color.WHITE),
                        new ConstraintLayoutParamsX()
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID)
                                .margins(containerPaddingMultiple, containerPaddingMultiple, 0, containerPaddingMultiple)
                                .top2bottom(pictureRvId))
                .addChildView(new TextViewX(this)
                                .text("确定")
                                .textColor(Color.WHITE),
                        new ConstraintLayoutParamsX()
                                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                                .top2bottom(pictureRvId)
                                .top2top(previewTvId)
                                .bottom2bottom(previewTvId)
                                .margins(0, 0, containerPaddingMultiple, 0))
        );

        // Load Image
        ImageUtils.loadLocalImage(this, mediaList -> {
            if (mediaList == null || mediaList.size() < 1) {
                ToastUtils.show(PictureSelectActivity.this, ResourceUtils.getString(PictureSelectActivity.this, R.string.error_get_local_media));
                return;
            }
            allMediaList.addAll(mediaList);
            selectedBucketMediaList.addAll(mediaList.get(0));
            pictureAdapter.notifyDataSetChanged();
            containerLayout.addChildView(bucketLvParentLayout
                            .backgroundColor(ColorX.HEX_A0000000)
                            .clickListener(v -> hideBucketLv(bucketLvParentLayout, bucketLv, downIv))
                            .invisible()
                            .addChildView(bucketLv
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
                                                            .padding(bucketItemPadding, bucketItemPadding, bucketItemPadding, bucketItemPadding)
                                                            .addChildView(bucketTv)
                                                            .addChildView(countTv, new LinearLayoutParamsX().weight(1))
                                                            .addChildView(checkIv);
                                                }
                                            }),
                                    new LinearLayoutParamsX(LinearLayoutParamsX.MATCH_PARENT, LinearLayoutParamsX.WRAP_CONTENT)),
                    new ConstraintLayoutParamsX()
                            .width(0)
                            .height(0)
                            .start2start(ConstraintLayoutParamsX.PARENT_ID)
                            .end2end(ConstraintLayoutParamsX.PARENT_ID)
                            .top2bottom(bucketLayoutId)
                            .bottom2top(previewTvId));
        });
    }

    private void showBucketLv(LinearLayoutX bucketLvLayout, ListViewX bucketLv, ImageViewX downIv) {
        bucketLvLayout.setVisibility(View.VISIBLE);
        ObjectAnimator.ofFloat(bucketLv, View.TRANSLATION_Y, -bucketLv.getHeight(), 0).setDuration(200).start();
        ObjectAnimator.ofFloat(downIv, View.ROTATION, 0, 180).setDuration(200).start();
    }

    private void hideBucketLv(LinearLayoutX bucketLvLayout, ListViewX bucketLv, ImageViewX downIv) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(bucketLv, View.TRANSLATION_Y, 0, -bucketLv.getHeight()).setDuration(200);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                bucketLvLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objectAnimator.start();
        ObjectAnimator.ofFloat(downIv, View.ROTATION, 180, 360).setDuration(200).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void showPreviewView(List<LocalMedia> selectedMediaList,
                                int previewRootLayoutId,
                                int previewPagerId,
                                int previewIndicatorTvId,
                                int curIndex) {
        /*
         * Last View
         */
        ConstraintLayoutX lastRootLayout = findViewById(previewRootLayoutId);
        ViewPager2 lastPicPager = findViewById(previewPagerId);
        TextViewX lastIndicatorTv = findViewById(previewIndicatorTvId);

        /*
         * Current View
         */
        ConstraintLayoutX rootConstraintLayout = lastRootLayout == null ? new ConstraintLayoutX(this) : lastRootLayout;
        ViewPager2 picPager = lastPicPager == null ? new ViewPager2(this) : lastPicPager;
        TextViewX indicatorTv = lastIndicatorTv == null ? new TextViewX(this) : lastIndicatorTv;

        // Last view is null?
        if (lastRootLayout != null) {
            rootConstraintLayout.setVisibility(View.VISIBLE);
            Objects.requireNonNull(picPager.getAdapter()).notifyDataSetChanged();
        } else {
            /*
             * Bar
             */
            int barColor = ColorX.HEX_EF000000;
            int barPadding = DensityUtils.dp2px2int(this, 8);

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
                    .addChildView(indicatorTv
                                    .id(previewIndicatorTvId)
                                    .textColor(Color.WHITE),
                            new RelativeLayoutParamsX()
                                    .rule(RelativeLayout.CENTER_IN_PARENT))
                    .addChildView(new CheckBoxX(this)
                                    .color(ColorX.HEX_FF549588),
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
                                    .solidColor(ColorX.HEX_FF549588)
                                    .text("确定")
                                    .textColor(Color.WHITE),
                            new RelativeLayoutParamsX()
                                    .rule(RelativeLayout.ALIGN_PARENT_RIGHT)
                                    .rule(RelativeLayout.CENTER_VERTICAL)
                                    .height(DensityUtils.dp2px2int(this, 40)));

            /*
             * Picture Pager
             */
            picPager.setId(previewPagerId);
            picPager.setBackgroundColor(Color.BLACK);
            picPager.setAdapter(new PicturePagerAdapter(selectedMediaList)
                    .setItemClickListener((adapterView, view, i, l) -> {
                        if (topRl.isVisible()) {
                            topRl.invisible();
                            bottomRl.invisible();
                        } else {
                            topRl.visible();
                            bottomRl.visible();
                        }
                    }));
            ViewUtils.addVp2SelectListener(picPager, new ViewPager2.OnPageChangeCallback() {

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    indicatorTv.setText(String.format("%s/%s", position + 1, selectedMediaList.size()));
                }

            });

            /*
             * Add
             */
            addContentView(rootConstraintLayout
                            .id(previewRootLayoutId)
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
        picPager.setCurrentItem(curIndex, false);
    }

}
