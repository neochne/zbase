package com.zbase.view;

import androidx.constraintlayout.widget.ConstraintLayout;

public final class ConstraintLayoutParamsX extends ConstraintLayout.LayoutParams {

    public ConstraintLayoutParamsX(int width, int height) {
        super(width, height);
    }

    /*
     * start
     */
    public ConstraintLayoutParamsX start2start(int id) {
        this.startToStart = id;
        return this;
    }

    public ConstraintLayoutParamsX start2end(int id) {
        this.startToEnd = id;
        return this;
    }

    /*
     * top
     */
    public ConstraintLayoutParamsX top2top(int id) {
        this.topToTop = id;
        return this;
    }

    public ConstraintLayoutParamsX top2bottom(int id) {
        this.topToBottom = id;
        return this;
    }

    /*
     * end
     */
    public ConstraintLayoutParamsX end2end(int id) {
        this.endToEnd = id;
        return this;
    }

    public ConstraintLayoutParamsX end2start(int id) {
        this.endToStart = id;
        return this;
    }

    /*
     * bottom
     */
    public ConstraintLayoutParamsX bottom2bottom(int id) {
        this.bottomToBottom = id;
        return this;
    }

    public ConstraintLayoutParamsX bottom2top(int id) {
        this.bottomToTop = id;
        return this;
    }

}
