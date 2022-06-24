package com.zbase.view.x;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintLayoutParamsX extends ConstraintLayout.LayoutParams implements IViewGroupLayoutParamsX<ConstraintLayoutParamsX> {

    public ConstraintLayoutParamsX() {
        super(WRAP_CONTENT, WRAP_CONTENT);
    }

    public ConstraintLayoutParamsX(int width, int height) {
        super(width, height);
    }

    public ConstraintLayoutParamsX start2start(int id) {
        this.startToStart = id;
        return this;
    }

    public ConstraintLayoutParamsX start2end(int id) {
        this.startToEnd = id;
        return this;
    }

    public ConstraintLayoutParamsX top2top(int id) {
        this.topToTop = id;
        return this;
    }

    public ConstraintLayoutParamsX top2bottom(int id) {
        this.topToBottom = id;
        return this;
    }

    public ConstraintLayoutParamsX end2end(int id) {
        this.endToEnd = id;
        return this;
    }

    public ConstraintLayoutParamsX end2start(int id) {
        this.endToStart = id;
        return this;
    }

    public ConstraintLayoutParamsX bottom2bottom(int id) {
        this.bottomToBottom = id;

        return this;
    }

    public ConstraintLayoutParamsX bottom2top(int id) {
        this.bottomToTop = id;
        return this;
    }

    public ConstraintLayoutParamsX chainStyleHorizontal(int style) {
        horizontalChainStyle = style;
        return this;
    }

    public ConstraintLayoutParamsX chainStyleVertical(int style) {
        verticalChainStyle = style;
        return this;
    }

    public ConstraintLayoutParamsX weightHorizontal(int weight) {
        horizontalWeight = weight;
        return this;
    }

    public ConstraintLayoutParamsX weightVertical(int weight) {
        verticalWeight = weight;
        return this;
    }

}
