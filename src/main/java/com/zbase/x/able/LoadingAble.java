package com.zbase.x.able;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zbase.R;
import com.zbase.util.DensityUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.x.ActivityX;
import com.zbase.x.ColorX;
import com.zbase.x.lp.FrameLayoutParamsX;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.view.ProgressBarX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.LinearLayoutX;

public interface LoadingAble extends ContextAble {

    default void showLoading() {
        showLoading("加载中...");
    }

    default void showLoading(String text) {
        ActivityX activityX;
        if (isActivityX()) {
            activityX = getActivityX();
        } else if (isFragmentX()) {
            activityX = (ActivityX) getFragmentX().requireContext();
        } else {
            throw unsupportedException();
        }
        /*
         * Is last show ?
         */
        Object loadingLayoutTag = getTag(R.id.activityx_loading_layout);
        if (loadingLayoutTag != null) {
            View loadingLayoutLast = activityX.findViewById((int) loadingLayoutTag);
            ((TextView) activityX.findViewById((int) getTag(R.id.activityx_loading_tv))).setText(text);
            loadingLayoutLast.setVisibility(View.VISIBLE);
            loadingLayoutLast.bringToFront();
            return;
        }

        /*
         * Create
         */
        int loadingLayoutId = View.generateViewId();
        int loadingTvId = View.generateViewId();
        setTag(R.id.activityx_loading_layout, loadingLayoutId);
        setTag(R.id.activityx_loading_tv, loadingTvId);
        int horPadding = DensityUtils.dp2px2int(activityX, 20);
        int verPadding = DensityUtils.dp2px2int(activityX, 10);
        int tvTopMargin = DensityUtils.dp2px2int(activityX, 8);
        LinearLayoutX loadingLayout = new LinearLayoutX(activityX)
                .id(loadingLayoutId)
                .padding(horPadding, verPadding, horPadding, verPadding)
                .orientation(LinearLayoutX.VERTICAL)
                .gravity(Gravity.CENTER)
                .background(ResourceUtils.getDrawable(activityX,R.drawable.bg_loading_dialog))
                .addChildView(new ProgressBarX(activityX).color(ColorX.WHITE))
                .addChildView(new TextViewX(activityX)
                                .id(loadingTvId)
                                .textColor(ColorX.WHITE).
                                text(text)
                        , new LinearLayoutParamsX().margins(0, tvTopMargin, 0, 0));
        activityX.addContentView(loadingLayout, new FrameLayoutParamsX().gravity(Gravity.CENTER));
        loadingLayout.bringToFront();
    }

    default void cancelLoading() {
        ActivityX activityX;
        if (isActivityX()) {
            activityX = getActivityX();
        } else if (isFragmentX()) {
            activityX = (ActivityX) getFragmentX().requireContext();
        } else {
            throw unsupportedException();
        }
        Object loadingLayoutTag = getTag(R.id.activityx_loading_layout);
        if (loadingLayoutTag == null) {
            return;
        }
        View loadingLayout = activityX.findViewById((int) loadingLayoutTag);
        if (loadingLayout == null) {
            return;
        }
        loadingLayout.setVisibility(View.GONE);
    }

}
