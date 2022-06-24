package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zbase.R;
import com.zbase.util.ResourceUtils;
import com.zbase.util.ViewUtils;

public final class LoadingView extends LinearLayout {

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(@NonNull Context context, String message) {
        super(context);

        /*
         * Container
         */
        setOrientation(LinearLayout.VERTICAL);
        int padding = ResourceUtils.getPixel(context, R.dimen.loading_dialog_padding);
        setPadding(padding, padding - 10, padding, padding - 10);

        /*
         * ProgressBar
         */
        ProgressBar circleProgressBar = new ProgressBar(context);
        circleProgressBar.getIndeterminateDrawable().setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN));
        circleProgressBar.setScaleX(.8f);
        circleProgressBar.setScaleY(.8f);
        addView(circleProgressBar);

        /*
         * Text
         */
        LayoutParams progressTextViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        progressTextViewLayoutParams.topMargin = ResourceUtils.getPixel(context, R.dimen.loading_dialog_text_top_margin);
        TextView progressTextView = new TextView(context);
        progressTextView.setLayoutParams(progressTextViewLayoutParams);
        ViewUtils.setTextSizeInPixel(context, progressTextView, R.dimen.dialog_content_text_size);
        progressTextView.setText(message);
        progressTextView.setTextColor(Color.WHITE);
        addView(progressTextView);
    }

}
