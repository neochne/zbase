package com.zbase.view;

import android.content.Context;
import android.view.View;

import com.zbase.R;
import com.zbase.util.ResourceUtils;
import com.zbase.x.lp.ConstraintLayoutParamsX;
import com.zbase.x.view.ImageViewX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.view.ViewX;
import com.zbase.x.viewgroup.ConstraintLayoutX;

public final class TvItemLayout extends ConstraintLayoutX {

    public TvItemLayout(Context context) {
        super(context);
    }

    public TvItemLayout startText(String text, int color) {
        return startText(text, color, ResourceUtils.getPixel(getContext(), R.dimen.tv_item_layout_left_margin), ResourceUtils.getPixel(getContext(), R.dimen.tv_item_layout_vertical_padding));
    }

    public TvItemLayout startText(String text, int color, int verticalMargin) {
        return startText(text, color, ResourceUtils.getPixel(getContext(), R.dimen.tv_item_layout_left_margin), verticalMargin);
    }

    public TvItemLayout startText(String text, int color, int leftMargin, int verticalPadding) {
        addChildView(new TextViewX(getContext())
                        .text(text)
                        .textColor(color)
                        .padding(0, verticalPadding, 0, verticalPadding),
                new ConstraintLayoutParamsX()
                        .margins(leftMargin, 0, 0, 0)
                        .start2start(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(ConstraintLayoutParamsX.PARENT_ID)
                        .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID));
        return this;
    }

    public TvItemLayout centerText(String text, int color) {
        return centerText(text, color, ResourceUtils.getPixel(getContext(), R.dimen.tv_item_layout_vertical_padding));
    }

    public TvItemLayout centerText(String text, int color, int verticalPadding) {
        addChildView(new TextViewX(getContext())
                        .text(text)
                        .textColor(color)
                        .padding(0, verticalPadding, 0, verticalPadding),
                new ConstraintLayoutParamsX()
                        .start2start(ConstraintLayoutParamsX.PARENT_ID)
                        .end2end(ConstraintLayoutParamsX.PARENT_ID)
                        .top2top(ConstraintLayoutParamsX.PARENT_ID)
                        .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID));
        return this;
    }

    public TvItemLayout enableRightArrow() {
        return endView(new ImageViewX(getContext()).src(R.drawable.ic_arrow_right_gray));
    }

    public TvItemLayout endView(View view) {
        int rightMargin = ResourceUtils.getPixel(getContext(), R.dimen.tv_item_layout_right_margin);
        return endView(view, 0, 0, rightMargin, 0);
    }

    public TvItemLayout endView(View view, int l, int t, int r, int b) {
        addChildView(view, new ConstraintLayoutParamsX()
                .margins(l, t, r, b)
                .end2end(ConstraintLayoutParamsX.PARENT_ID)
                .top2top(ConstraintLayoutParamsX.PARENT_ID)
                .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID));
        return this;
    }

    public TvItemLayout enableDivider() {
        return enableDivider(0, 0, 0, 0,
                ResourceUtils.getPixel(getContext(), R.dimen.tv_item_layout_divider_height),
                ResourceUtils.getColor(getContext(), R.color.gray_ECECEC));
    }

    public TvItemLayout enableLeftMarginDivider() {
        return enableDivider(ResourceUtils.getPixel(getContext(), R.dimen.tv_item_layout_left_margin),
                0,
                0,
                0,
                ResourceUtils.getPixel(getContext(), R.dimen.tv_item_layout_divider_height),
                ResourceUtils.getColor(getContext(), R.color.gray_ECECEC));
    }

    public TvItemLayout enableDivider(int l, int t, int r, int b, int height, int color) {
        addChildView(new ViewX(getContext())
                        .backgroundColor(color),
                new ConstraintLayoutParamsX()
                        .width(0)
                        .height(height)
                        .margins(l, t, r, b)
                        .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID)
                        .start2start(ConstraintLayoutParamsX.PARENT_ID)
                        .end2end(ConstraintLayoutParamsX.PARENT_ID));
        return this;
    }

}
