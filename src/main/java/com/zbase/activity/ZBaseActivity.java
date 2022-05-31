package com.zbase.activity;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zbase.R;
import com.zbase.util.ResourceUtils;
import com.zbase.util.ViewUtils;
import com.zbase.view.x.FrameLayoutParamsX;

public class ZBaseActivity extends AppCompatActivity {

    public void showLoading() {
        showLoading("加载中...");
    }

    public void showLoading(String text) {
        /*
         * Is Show?
         */
        int loadingId = 666;
        View loadingView = findViewById(loadingId);
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            return;
        }
        /*
         * Background
         */
        LinearLayout backgroundLayout = new LinearLayout(this);
        backgroundLayout.setId(loadingId);
        backgroundLayout.setPadding(50, 40, 50, 40);
        backgroundLayout.setOrientation(LinearLayout.VERTICAL);
        backgroundLayout.setGravity(Gravity.CENTER);
        backgroundLayout.setBackground(ResourceUtils.getDrawable(this, R.drawable.bg_loading_dialog));
        /*
         * Circle ProgressBar
         */
        ProgressBar circleProgressBar = new ProgressBar(this);
        ViewUtils.setProgressBarColor(circleProgressBar, Color.WHITE);
        backgroundLayout.addView(circleProgressBar);
        /*
         * Prompt Text
         */
        TextView promptTextView = new TextView(this);
        promptTextView.setTextColor(Color.WHITE);
        promptTextView.setText(text);
        backgroundLayout.addView(promptTextView, new FrameLayoutParamsX().margins(0, 20, 0, 0));
        // add to content view
        addContentView(backgroundLayout, new FrameLayoutParamsX().gravity(Gravity.CENTER));
    }

    public void cancelLoading() {
        int loadingId = 666;
        View loadingView = findViewById(loadingId);
        if (loadingView == null) {
            return;
        }
        loadingView.setVisibility(View.GONE);
    }

}
