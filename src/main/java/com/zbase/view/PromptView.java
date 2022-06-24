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
import com.zbase.util.ViewUtils;

public final class PromptView extends ConstraintLayout {

    public PromptView(@NonNull Context context) {
        super(context);
    }

    public PromptView(@NonNull Context context,
                      String title,
                      String message,
                      String negative,
                      String positive,
                      View.OnClickListener negativeListener,
                      View.OnClickListener positiveListener) {
        super(context);

        // Common
        View horizontalDivider = initCommonView(context, title, message);

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
        int operateTextViewPadding = ResourceUtils.getPixel(context, R.dimen.dialog_btn_text_vertical_padding);
        TextView negativeTextView = new TextView(context);
        negativeTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        negativeTextView.setGravity(Gravity.CENTER);
        ViewUtils.setTextSizeInPixel(context, negativeTextView, R.dimen.dialog_title_text_size);
        negativeTextView.setText(negative);
        negativeTextView.setLayoutParams(negativeLayoutParams);
        negativeTextView.setTypeface(Typeface.DEFAULT_BOLD);
        negativeTextView.setTextColor(Color.BLACK);
        negativeTextView.setBackground(ResourceUtils.getDrawable(context, R.drawable.bg_pressed));
        negativeTextView.setOnClickListener(negativeListener);
        addView(negativeTextView);


        /*
         * Positive
         */
        LayoutParams positiveLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        positiveLayoutParams.startToEnd = verticalDivider.getId();
        positiveLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        positiveLayoutParams.topToBottom = horizontalDivider.getId();
        positiveLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        TextView positiveTextView = new TextView(context);
        ViewUtils.setTextSizeInPixel(context, positiveTextView, R.dimen.dialog_title_text_size);
        positiveTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        positiveTextView.setText(positive);
        positiveTextView.setGravity(Gravity.CENTER);
        positiveTextView.setLayoutParams(positiveLayoutParams);
        positiveTextView.setTextColor(ResourceUtils.getColor(context, R.color.prompt_dialog_positive));
        positiveTextView.setTypeface(Typeface.DEFAULT_BOLD);
        positiveTextView.setBackground(ResourceUtils.getDrawable(context, R.drawable.bg_pressed));
        positiveTextView.setOnClickListener(positiveListener);
        addView(positiveTextView);
    }

    public PromptView(@NonNull Context context,
                      String title,
                      String message,
                      String positive,
                      View.OnClickListener positiveListener) {
        super(context);
        View horizontalDivider = initCommonView(context, title, message);
        /*
         * Positive
         */
        LayoutParams positiveLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        positiveLayoutParams.startToStart = LayoutParams.PARENT_ID;
        positiveLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        positiveLayoutParams.topToBottom = horizontalDivider.getId();
        positiveLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        int operateTextViewPadding = ResourceUtils.getPixel(context, R.dimen.dialog_btn_text_vertical_padding);
        TextView positiveTextView = new TextView(context);
        ViewUtils.setTextSizeInPixel(context, positiveTextView, R.dimen.dialog_title_text_size);
        positiveTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        positiveTextView.setText(positive);
        positiveTextView.setGravity(Gravity.CENTER);
        positiveTextView.setLayoutParams(positiveLayoutParams);
        positiveTextView.setTextColor(ResourceUtils.getColor(context, R.color.prompt_dialog_positive));
        positiveTextView.setTypeface(Typeface.DEFAULT_BOLD);
        positiveTextView.setBackground(ResourceUtils.getDrawable(context, R.drawable.bg_pressed));
        positiveTextView.setOnClickListener(positiveListener);
        addView(positiveTextView);
    }

    private View initCommonView(Context context, String title, String message) {
        setBackgroundColor(Color.WHITE);
        /*
         * Title
         */
        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleLayoutParams.topToTop = LayoutParams.PARENT_ID;
        titleLayoutParams.startToStart = LayoutParams.PARENT_ID;
        titleLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        int verticalPadding = ResourceUtils.getPixel(context, R.dimen.dialog_title_text_vertical_padding);
        TextView titleTextView = new TextView(context);
        titleTextView.setPadding(0, verticalPadding, 0, verticalPadding);
        titleTextView.setId(ViewCompat.generateViewId());
        titleTextView.setTextColor(Color.BLACK);
        ViewUtils.setTextSizeInPixel(context, titleTextView, R.dimen.dialog_title_text_size);
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
        TextView messageTextView = new TextView(context);
        int msgPadding = ResourceUtils.getPixel(context, R.dimen.dialog_content_padding);
        messageTextView.setPadding(msgPadding, msgPadding, msgPadding, msgPadding);
        messageTextView.setId(ViewCompat.generateViewId());
        messageTextView.setText(message);
        messageTextView.setGravity(Gravity.CENTER);
        ViewUtils.setTextSizeInPixel(context, messageTextView, R.dimen.dialog_content_text_size);
        TextViewCompat.setLineHeight(messageTextView, ResourceUtils.getPixel(context, R.dimen.message_line_height));
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

}
