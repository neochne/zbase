package com.zbase.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zbase.R;
import com.zbase.util.ResourceUtils;

public final class ToolbarLayout extends ConstraintLayoutX {

    private final int TITLE_TEXT_VIEW_ID = 0xa0a0a0a0;

    public ToolbarLayout(@NonNull Context context) {
        super(context);
    }

    public ToolbarLayout setTitle(String titleText,
                                  int titleTextColor,
                                  int titleBackgroundColor,
                                  int titleSizeSp,
                                  Typeface titleTypeface) {
        Context context = getContext();
        TextView titleTextView = new TextView(context);
        LayoutParams titleLayoutParams = add1View(titleTextView,TITLE_TEXT_VIEW_ID);
        titleLayoutParams.width = 0;
        titleLayoutParams.height = ResourceUtils.getPixel(context, R.dimen.toolbar_height);
        titleLayoutParams.startToStart = LayoutParams.PARENT_ID;
        titleLayoutParams.topToTop = LayoutParams.PARENT_ID;
        titleLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleTextView.setTextSize(titleSizeSp);
        titleTextView.setBackgroundColor(titleBackgroundColor);
        titleTextView.setGravity(Gravity.CENTER);
        titleTextView.setTypeface(titleTypeface);
        return this;
    }

    public ToolbarLayout setBackIcon(int backIconId, int leftMargin,View.OnClickListener backClickListener) {
        @SuppressLint("ResourceType") TextView titleTextView = findViewById(0xa0a0a0a0);
        ImageView backImageView = new ImageView(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar);
        LayoutParams backLayoutParams = add1View(backImageView);
        backLayoutParams.startToStart = LayoutParams.PARENT_ID;
        backLayoutParams.topToTop = titleTextView.getId();
        backLayoutParams.bottomToBottom = titleTextView.getId();
        backLayoutParams.leftMargin = leftMargin;
        backImageView.setImageResource(backIconId);
        backImageView.setOnClickListener(backClickListener);
        return this;
    }

    public ToolbarLayout setRightIcon(int rightIconId, int rightMargin,View.OnClickListener rightClickListener) {
        ImageView rightImageView = new ImageView(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar);
        LayoutParams rightLayoutParams = add1View(rightImageView);
        rightLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        rightLayoutParams.topToTop = TITLE_TEXT_VIEW_ID;
        rightLayoutParams.bottomToBottom = TITLE_TEXT_VIEW_ID;
        rightLayoutParams.rightMargin = rightMargin;
        rightImageView.setImageResource(rightIconId);
        rightImageView.setOnClickListener(rightClickListener);
        return this;
    }

    public ToolbarLayout setRightText(String rightText,
                                      int rightTextColor,
                                      int rightTextSizeSp,
                                      int rightMargin,
                                      View.OnClickListener rightClickListener) {
        TextView rightTextView = new TextView(getContext(), null, androidx.appcompat.R.style.Widget_AppCompat_Toolbar);
        LayoutParams rightLayoutParams = add1View(rightTextView);
        rightLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        rightLayoutParams.topToTop = TITLE_TEXT_VIEW_ID;
        rightLayoutParams.bottomToBottom = TITLE_TEXT_VIEW_ID;
        rightLayoutParams.rightMargin = rightMargin;
        rightTextView.setText(rightText);
        rightTextView.setTextColor(rightTextColor);
        rightTextView.setTextSize(rightTextSizeSp);
        rightTextView.setOnClickListener(rightClickListener);
        return this;
    }

    public ToolbarLayout setContentView(View contentView) {
        LayoutParams contentLayoutParams = add1View(contentView);
        contentLayoutParams.width = 0;
        contentLayoutParams.height = 0;
        contentLayoutParams.startToStart = LayoutParams.PARENT_ID;
        contentLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        contentLayoutParams.topToBottom = TITLE_TEXT_VIEW_ID;
        contentLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        return this;
    }

    public ConstraintLayoutParamsX appendViewBelowToolbar(View view) {
        ConstraintLayoutParamsX viewLayoutParams = add1View(view);
        viewLayoutParams.topToBottom = TITLE_TEXT_VIEW_ID;
        return viewLayoutParams;
    }

    public ConstraintLayoutParamsX appendView(View view) {
        return add1View(view);
    }

}
