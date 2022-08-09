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
import com.zbase.util.DensityUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.x.ColorX;

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
        verticalDivider.setBackgroundColor(ColorX.HEX_EEEEEE);
        addView(verticalDivider);

        /*
         * Negative
         */
        LayoutParams negativeLayoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        negativeLayoutParams.startToStart = LayoutParams.PARENT_ID;
        negativeLayoutParams.endToStart = verticalDivider.getId();
        negativeLayoutParams.topToBottom = horizontalDivider.getId();
        negativeLayoutParams.bottomToBottom = LayoutParams.PARENT_ID;
        int operateTextViewPadding = DensityUtils.dp2px2int(context,13);
        TextView negativeTextView = new TextView(context);
        negativeTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        negativeTextView.setGravity(Gravity.CENTER);
        negativeTextView.setTextSize(17);
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
        positiveTextView.setTextSize(17);
        positiveTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        positiveTextView.setText(positive);
        positiveTextView.setGravity(Gravity.CENTER);
        positiveTextView.setLayoutParams(positiveLayoutParams);
        positiveTextView.setTextColor(ColorX.HEX_606D96);
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
        int operateTextViewPadding =  DensityUtils.dp2px2int(context,13);
        TextView positiveTextView = new TextView(context);
        positiveTextView.setTextSize(17);
        positiveTextView.setPadding(0, operateTextViewPadding, 0, operateTextViewPadding);
        positiveTextView.setText(positive);
        positiveTextView.setGravity(Gravity.CENTER);
        positiveTextView.setLayoutParams(positiveLayoutParams);
        positiveTextView.setTextColor(ColorX.HEX_606D96);
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
        int verticalPadding = DensityUtils.dp2px2int(context,10);
        TextView titleTextView = new TextView(context);
        titleTextView.setPadding(0, verticalPadding, 0, verticalPadding);
        titleTextView.setId(ViewCompat.generateViewId());
        titleTextView.setTextColor(Color.BLACK);
        titleTextView.setTextSize(17);
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
        int msgPadding =  DensityUtils.dp2px2int(context,10);
        messageTextView.setPadding(msgPadding, msgPadding, msgPadding, msgPadding);
        messageTextView.setId(ViewCompat.generateViewId());
        messageTextView.setText(message);
        messageTextView.setGravity(Gravity.CENTER);
        messageTextView.setTextSize(14);
        TextViewCompat.setLineHeight(messageTextView,  DensityUtils.dp2px2int(context,20));
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
        horizontalDivider.setBackgroundColor(ColorX.HEX_EEEEEE);
        addView(horizontalDivider);
        return horizontalDivider;
    }

}
