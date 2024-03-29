package com.zbase.x.lp;

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

    public ConstraintLayoutParamsX start2startParent() {
        this.startToStart = ConstraintLayoutParamsX.PARENT_ID;
        return this;
    }

    public ConstraintLayoutParamsX start2startUnset() {
        this.startToStart = ConstraintLayoutParamsX.UNSET;
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

    public ConstraintLayoutParamsX top2topParent() {
        this.topToTop = ConstraintLayoutParamsX.PARENT_ID;
        return this;
    }

    public ConstraintLayoutParamsX top2topUnset() {
        this.topToTop = ConstraintLayoutParamsX.UNSET;
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

    public ConstraintLayoutParamsX end2endParent() {
        this.endToEnd = ConstraintLayoutParamsX.PARENT_ID;
        return this;
    }

    public ConstraintLayoutParamsX end2endUnset() {
        this.endToEnd = ConstraintLayoutParamsX.UNSET;
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

    public ConstraintLayoutParamsX bottom2bottomParent() {
        this.bottomToBottom = ConstraintLayoutParamsX.PARENT_ID;
        return this;
    }

    public ConstraintLayoutParamsX bottom2bottomUnset() {
        this.bottomToBottom = ConstraintLayoutParamsX.UNSET;
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

    /**
     * @param percent 0 ~ 1,to make it work,
     *                you should set height with 0
     *                and parent height match_parent
     */
    public ConstraintLayoutParamsX percentHeight(float percent) {
        matchConstraintPercentHeight = percent;
        return this;
    }

    public ConstraintLayoutParamsX percentWidth(float percent) {
        matchConstraintPercentWidth = percent;
        return this;
    }

    /**
     * @param orientation one of the types:
     *                    {@link ConstraintLayout.LayoutParams#VERTICAL} or
     *                    {@link ConstraintLayout.LayoutParams#HORIZONTAL},
     *                    this param must be set if you use
     *                    {@link androidx.constraintlayout.widget.Guideline}
     */
    public ConstraintLayoutParamsX guideOrientation(int orientation) {
        this.orientation = orientation;
        return this;
    }

    public ConstraintLayoutParamsX guidePercent(float percent) {
        this.guidePercent = percent;
        return this;
    }

}
