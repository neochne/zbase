package com.zbase.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;

import com.zbase.R;
import com.zbase.util.DensityUtils;
import com.zbase.x.ColorX;
import com.zbase.x.drawable.StateListDrawableX;
import com.zbase.x.lp.ConstraintLayoutParamsX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.view.ViewX;
import com.zbase.x.viewgroup.ConstraintLayoutX;

public class DialogView<T extends DialogView<?>> extends ConstraintLayoutX {

    public DialogView(@NonNull Context context) {
        super(context);
        // Title id，setTag 方法的 key 必须用系统生成的 id，不然会异常
        int titleId = generateViewId();
        setTag(R.id.dialog_title, titleId);
        // Above btn view id
        setTag(R.id.dialog_above_btn, generateViewId());

        /*
         * View
         */
        int titleVerPadding = DensityUtils.dp2px2int(context, 10);
        this.backgroundColor(Color.WHITE)
                .addChildView(new TextViewX(context)
                                .id(titleId)
                                .textColor(Color.BLACK)
                                .textSize(17)
                                .typeFace(Typeface.DEFAULT_BOLD)
                                .padding(0, titleVerPadding, 0, titleVerPadding)
                        , new ConstraintLayoutParamsX()
                                .top2topParent()
                                .start2startParent()
                                .end2endParent());
    }

    public T title(String title) {
        ((TextViewX) findViewById(getTitleId())).text(title);
        return (T) this;
    }

    public T enablePositiveAndNegativeButton(String negativeText
            , String positiveText
            , View.OnClickListener negativeClickListener
            , View.OnClickListener positiveClickListener) {
        // Button press state drawable
        StateListDrawableX negativePressedSateDrawable = new StateListDrawableX()
                .stateColors(new int[]{android.R.attr.state_pressed}, ColorX.DIVIDER)
                .stateColors(new int[]{}, ColorX.WHITE);
        StateListDrawableX positivePressedSateDrawable = new StateListDrawableX()
                .stateColors(new int[]{android.R.attr.state_pressed}, ColorX.DIVIDER)
                .stateColors(new int[]{}, ColorX.WHITE);

        /*
         * View id
         */
        int horDividerId = generateViewId();
        int verDividerId = generateViewId();

        // View
        int btnTextVerticalPadding = DensityUtils.dp2px2int(getContext(), 13);
        this
                .addChildView(new ViewX(getContext())
                                .id(horDividerId)
                                .backgroundColor(ColorX.DIVIDER)
                        , new ConstraintLayoutParamsX(ConstraintLayoutParamsX.MATCH_PARENT, 1)
                                .top2bottom(getAboveBtnViewId()))
                .addChildView(new ViewX(getContext())
                                .id(verDividerId)
                                .backgroundColor(ColorX.DIVIDER)
                        , new ConstraintLayoutParamsX(1, 0)
                                .start2startParent()
                                .end2endParent()
                                .top2bottom(horDividerId)
                                .bottom2bottomParent())
                .addChildView(new TextViewX(getContext())
                                .padding(0, btnTextVerticalPadding, 0, btnTextVerticalPadding)
                                .gravity(Gravity.CENTER)
                                .textSize(17)
                                .typeFace(Typeface.DEFAULT_BOLD)
                                .textColor(ColorX.BLACK)
                                .background(negativePressedSateDrawable)
                                .text(negativeText)
                                .clickListener(negativeClickListener)
                        , new ConstraintLayoutParamsX()
                                .width(0)
                                .chainStyleHorizontal(ConstraintLayoutParamsX.CHAIN_SPREAD)
                                .weightHorizontal(1)
                                .start2startParent()
                                .end2start(verDividerId)
                                .top2bottom(horDividerId)
                                .bottom2bottomParent())
                .addChildView(new TextViewX(getContext())
                                .padding(0, btnTextVerticalPadding, 0, btnTextVerticalPadding)
                                .gravity(Gravity.CENTER)
                                .textSize(17)
                                .typeFace(Typeface.DEFAULT_BOLD)
                                .textColor(ColorX.HEX_FF606D96)
                                .background(positivePressedSateDrawable)
                                .text(positiveText)
                                .clickListener(positiveClickListener)
                        , new ConstraintLayoutParamsX()
                                .width(0)
                                .weightHorizontal(1)
                                .start2end(verDividerId)
                                .end2endParent()
                                .top2bottom(horDividerId)
                                .bottom2bottomParent());
        return (T) this;
    }

    public T enablePositiveButton(String positiveText, View.OnClickListener positiveClickListener) {
        // Button configure
        StateListDrawableX pressedSateDrawable = new StateListDrawableX()
                .stateColors(new int[]{android.R.attr.state_pressed}, ColorX.DIVIDER)
                .stateColors(new int[]{}, ColorX.WHITE);

        /*
         * View id
         */
        int horDividerId = generateViewId();

        // View
        int btnTextVerticalPadding = DensityUtils.dp2px2int(getContext(), 13);
        this
                .addChildView(new ViewX(getContext())
                                .id(horDividerId)
                                .backgroundColor(ColorX.DIVIDER)
                        , new ConstraintLayoutParamsX(ConstraintLayoutParamsX.MATCH_PARENT, 1)
                                .top2bottom(getAboveBtnViewId()))
                .addChildView(new TextViewX(getContext())
                                .padding(0, btnTextVerticalPadding, 0, btnTextVerticalPadding)
                                .gravity(Gravity.CENTER)
                                .textSize(17)
                                .typeFace(Typeface.DEFAULT_BOLD)
                                .textColor(ColorX.HEX_FF606D96)
                                .background(pressedSateDrawable)
                                .text(positiveText)
                                .clickListener(positiveClickListener)
                        , new ConstraintLayoutParamsX()
                                .width(0)
                                .start2startParent()
                                .end2endParent()
                                .top2bottom(horDividerId)
                                .bottom2bottomParent());
        return (T) this;
    }

    int getTitleId() {
        return getViewId(R.id.dialog_title);
    }

    int getAboveBtnViewId() {
        return getViewId(R.id.dialog_above_btn);
    }

    int getViewId(int key) {
        return (int) getTag(key);
    }

}
