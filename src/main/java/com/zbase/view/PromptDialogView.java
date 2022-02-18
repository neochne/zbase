package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;

import com.zbase.R;
import com.zbase.util.ResourceUtils;
import com.zbase.util.TextViewUtils;

public final class PromptDialogView extends ConstraintLayout {

    private TextView mNegativeTextView;

    private TextView mPositiveTextView;

    public PromptDialogView(@NonNull Context context) {
        super(context);
    }

    public PromptDialogView(@NonNull Context context,
                            String title,
                            String message,
                            String negative,
                            String positive) {
        super(context);

        // Common
        View horizontalDivider = initCommonView(context,title,message);

        /*
         * Vertical divider
         */
        LayoutParams verticalLayoutParams = new LayoutParams(1, 0);
        verticalLayoutParams.topToBottom = horizontalDivider.getId();
        verticalLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        verticalLayoutParams.startToStart = LayoutParams.PARENT_ID;
        verticalLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        View verticalDivider = new View(context);
        verticalDivider.setId(ViewCompat.generateViewId());
        verticalDivider.setLayoutParams(verticalLayoutParams);
        verticalDivider.setBackgroundColor(ResourceUtils.getColor(context, R.color.divider));
        addView(verticalDivider);

        /*
         * Negative
         */
        LayoutParams negativeLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        negativeLayoutParams.startToStart = LayoutParams.PARENT_ID;
        negativeLayoutParams.endToStart = verticalDivider.getId();
        negativeLayoutParams.topToBottom = horizontalDivider.getId();
        negativeLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        int operateTextViewPadding = ResourceUtils.getPixel(context, R.dimen.prompt_dialog_operate_text_padding);
        mNegativeTextView = new TextView(context);
        mNegativeTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        mNegativeTextView.setGravity(Gravity.CENTER);
        TextViewUtils.setTextSizeInPixel(context, mNegativeTextView, R.dimen.dialog_title_text_size);
        mNegativeTextView.setText(negative);
        mNegativeTextView.setLayoutParams(negativeLayoutParams);
        mNegativeTextView.setTypeface(Typeface.DEFAULT_BOLD);
        mNegativeTextView.setTextColor(Color.BLACK);
        mNegativeTextView.setBackground(ResourceUtils.getDrawable(context, R.drawable.bg_pressed));
        addView(mNegativeTextView);


        /*
         * Positive
         */
        LayoutParams positiveLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        positiveLayoutParams.startToEnd = verticalDivider.getId();
        positiveLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        positiveLayoutParams.topToBottom = horizontalDivider.getId();
        positiveLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        mPositiveTextView = new TextView(context);
        TextViewUtils.setTextSizeInPixel(context, mPositiveTextView, R.dimen.dialog_title_text_size);
        mPositiveTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        mPositiveTextView.setText(positive);
        mPositiveTextView.setGravity(Gravity.CENTER);
        mPositiveTextView.setLayoutParams(positiveLayoutParams);
        mPositiveTextView.setTextColor(ResourceUtils.getColor(context, R.color.prompt_dialog_positive));
        mPositiveTextView.setTypeface(Typeface.DEFAULT_BOLD);
        mPositiveTextView.setBackground(ResourceUtils.getDrawable(context, R.drawable.bg_pressed));
        addView(mPositiveTextView);
    }

    public PromptDialogView(@NonNull Context context,
                            String title,
                            String message,
                            String positive) {
        super(context);
        View horizontalDivider = initCommonView(context,title,message);
        /*
         * Positive
         */
        LayoutParams positiveLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        positiveLayoutParams.startToStart = LayoutParams.PARENT_ID;
        positiveLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        positiveLayoutParams.topToBottom = horizontalDivider.getId();
        positiveLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        int operateTextViewPadding = ResourceUtils.getPixel(context, R.dimen.prompt_dialog_operate_text_padding);
        mPositiveTextView = new TextView(context);
        TextViewUtils.setTextSizeInPixel(context, mPositiveTextView, R.dimen.dialog_title_text_size);
        mPositiveTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        mPositiveTextView.setText(positive);
        mPositiveTextView.setGravity(Gravity.CENTER);
        mPositiveTextView.setLayoutParams(positiveLayoutParams);
        mPositiveTextView.setTextColor(ResourceUtils.getColor(context, R.color.prompt_dialog_positive));
        mPositiveTextView.setTypeface(Typeface.DEFAULT_BOLD);
        mPositiveTextView.setBackground(ResourceUtils.getDrawable(context, R.drawable.bg_pressed));
        addView(mPositiveTextView);
    }

    private View initCommonView(Context context,String title,String message) {
        setBackgroundColor(Color.WHITE);
        /*
         * Title
         */
        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleLayoutParams.topToTop = LayoutParams.PARENT_ID;
        titleLayoutParams.startToStart = LayoutParams.PARENT_ID;
        titleLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        titleLayoutParams.topMargin = ResourceUtils.getPixel(context, R.dimen.prompt_dialog_title_top_margin);
        TextView titleTextView = new TextView(context);
        titleTextView.setId(ViewCompat.generateViewId());
        titleTextView.setTextColor(Color.BLACK);
        TextViewUtils.setTextSizeInPixel(context, titleTextView, R.dimen.dialog_title_text_size);
        titleTextView.setText(title);
        titleTextView.setTypeface(Typeface.DEFAULT_BOLD);
        titleTextView.setLayoutParams(titleLayoutParams);
        addView(titleTextView);

        /*
         * Message
         */
        LayoutParams messageLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        messageLayoutParams.startToStart = LayoutParams.PARENT_ID;
        messageLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        messageLayoutParams.topToBottom = titleTextView.getId();
        int paddingTop = ResourceUtils.getPixel(context, R.dimen.prompt_dialog_message_padding_top);
        int padding = ResourceUtils.getPixel(context, R.dimen.prompt_dialog_message_padding);
        TextView messageTextView = new TextView(context);
        messageTextView.setPadding(padding, paddingTop, padding, padding);
        messageTextView.setId(ViewCompat.generateViewId());
        messageTextView.setText(message);
        messageTextView.setGravity(Gravity.CENTER);
        TextViewUtils.setTextSizeInPixel(context, messageTextView, R.dimen.prompt_dialog_message_text_size);
        TextViewCompat.setLineHeight(messageTextView, ResourceUtils.getPixel(context, R.dimen.prompt_dialog_message_line_height));
        messageTextView.setLayoutParams(messageLayoutParams);
        addView(messageTextView);

        /*
         * Horizontal divider
         */
        LayoutParams horizontalDividerLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        horizontalDividerLayoutParams.topToBottom = messageTextView.getId();
        View horizontalDivider = new View(context);
        horizontalDivider.setId(ViewCompat.generateViewId());
        horizontalDivider.setLayoutParams(horizontalDividerLayoutParams);
        horizontalDivider.setBackgroundColor(ResourceUtils.getColor(context, R.color.divider));
        addView(horizontalDivider);
        return horizontalDivider;
    }

    public void setNegativeClickListener(View.OnClickListener clickListener) {
        mNegativeTextView.setOnClickListener(clickListener);
    }

    public void setPositiveClickListener(View.OnClickListener clickListener) {
        mPositiveTextView.setOnClickListener(clickListener);
    }

}
