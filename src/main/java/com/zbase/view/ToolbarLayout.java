package com.zbase.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import com.zbase.R;
import com.zbase.util.ResourceUtils;

public final class ToolbarLayout extends ConstraintLayout {

    private TextView mTitleTextView;

    private ToolbarLayout(@NonNull Context context) {
        super(context);
    }

    public ToolbarLayout(@NonNull Context context,
                         String title,
                         int titleColor,
                         int titleSizeSp,
                         boolean titleBold,
                         int toolbarColor) {
        super(context);
        mTitleTextView = generateTitleTextView(context,title,titleColor,titleSizeSp,titleBold,toolbarColor);
        addView(mTitleTextView);
    }

    public ToolbarLayout(@NonNull Context context,
                         String title,
                         int titleColor,
                         int titleSizeSp,
                         boolean titleBold,
                         int toolbarColor,
                         int backIconId,
                         View.OnClickListener backClickListener) {
        super(context);
        mTitleTextView = generateTitleTextView(context,title,titleColor,titleSizeSp,titleBold,toolbarColor);
        addView(mTitleTextView);
        ImageView backImageView = new ImageView(context);
        backImageView.setImageResource(backIconId);
        backImageView.setOnClickListener(backClickListener);
        addTitleLeftView(backImageView,0);
    }

    private TextView generateTitleTextView(Context context,
                                           String title,
                                           int titleColor,
                                           int titleSizeSp,
                                           boolean titleBold,
                                           int toolbarColor) {
        LayoutParams titleLayoutParams = new LayoutParams(0, ResourceUtils.getPixel(context, R.dimen.toolbar_height));
        titleLayoutParams.startToStart = LayoutParams.PARENT_ID;
        titleLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        titleLayoutParams.topToTop = LayoutParams.PARENT_ID;
        TextView titleTextView = new TextView(context);
        titleTextView.setId(ViewCompat.generateViewId());
        titleTextView.setText(title);
        titleTextView.setTextColor(titleColor);
        titleTextView.setTextSize(titleSizeSp);
        titleTextView.setBackgroundColor(toolbarColor);
        titleTextView.setGravity(Gravity.CENTER);
        if (titleBold) {
            titleTextView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        titleTextView.setLayoutParams(titleLayoutParams);
        return titleTextView;
    }

    public void addTitleLeftView(View leftView,int leftMargin) {
        LayoutParams leftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        leftLayoutParams.startToStart = LayoutParams.PARENT_ID;
        leftLayoutParams.topToTop = mTitleTextView.getId();
        leftLayoutParams.bottomToBottom = mTitleTextView.getId();
        leftLayoutParams.leftMargin = leftMargin;
        leftView.setLayoutParams(leftLayoutParams);
        addView(leftView);
    }

    public void addTitleLeftView(View leftView,int start2endViewId,int leftMargin) {
        LayoutParams leftLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        leftLayoutParams.startToEnd = start2endViewId;
        leftLayoutParams.topToTop = mTitleTextView.getId();
        leftLayoutParams.bottomToBottom = mTitleTextView.getId();
        leftLayoutParams.leftMargin = leftMargin;
        leftView.setLayoutParams(leftLayoutParams);
        addView(leftView);
    }

    public void addTitleRightView(View rightView,int rightMargin) {
        LayoutParams rightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        rightLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        rightLayoutParams.topToTop = mTitleTextView.getId();
        rightLayoutParams.bottomToBottom = mTitleTextView.getId();
        rightLayoutParams.rightMargin = rightMargin;
        rightView.setLayoutParams(rightLayoutParams);
        addView(rightView);
    }

    public void addTitleRightView(View rightView,int end2startViewId,int rightMargin) {
        LayoutParams rightLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        rightLayoutParams.endToStart = end2startViewId;
        rightLayoutParams.topToTop = mTitleTextView.getId();
        rightLayoutParams.bottomToBottom = mTitleTextView.getId();
        rightLayoutParams.rightMargin = rightMargin;
        rightView.setLayoutParams(rightLayoutParams);
        addView(rightView);
    }

    public ToolbarLayout setContentView(View contentView) {
        LayoutParams contentLayoutParams = (LayoutParams) contentView.getLayoutParams();
        contentLayoutParams.topToBottom = mTitleTextView.getId();
        contentLayoutParams.startToStart = LayoutParams.PARENT_ID;
        contentLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        addView(contentView);
        return this;
    }

    public void appendView(View view) {
        LayoutParams contentLayoutParams = (LayoutParams) view.getLayoutParams();
        if (contentLayoutParams.topToBottom == LayoutParams.UNSET) {
            contentLayoutParams.topToBottom = mTitleTextView.getId();
        }
        addView(view);
    }

}
