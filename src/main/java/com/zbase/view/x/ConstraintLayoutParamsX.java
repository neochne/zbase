package com.zbase.view.x;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintLayoutParamsX extends ConstraintLayout.LayoutParams {

    public ConstraintLayoutParamsX() {
        super(WRAP_CONTENT, WRAP_CONTENT);
    }

    public ConstraintLayoutParamsX(int width, int height) {
        super(width, height);
    }

    public ConstraintLayoutParamsX margins(int margin) {
        setMargins(margin,margin,margin,margin);
        return this;
    }

    public ConstraintLayoutParamsX width(int w) {
        this.width = w;
        return this;
    }

    public ConstraintLayoutParamsX height(int h) {
        this.height = h;
        return this;
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

    public ConstraintLayoutParamsX leftMargin(int v) {
        leftMargin = v;
        return this;
    }

    public ConstraintLayoutParamsX topMargin(int v) {
        topMargin = v;
        return this;
    }

    public ConstraintLayoutParamsX rightMargin(int v) {
        rightMargin = v;
        return this;
    }

    public ConstraintLayoutParamsX bottomMargin(int v) {
        bottomMargin = v;
        return this;
    }

}
