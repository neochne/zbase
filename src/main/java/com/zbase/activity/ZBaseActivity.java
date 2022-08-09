package com.zbase.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.zbase.R;
import com.zbase.http.JDataCallback;
import com.zbase.http.YesHttp;
import com.zbase.util.ResourceUtils;
import com.zbase.util.ViewUtils;
import com.zbase.x.ColorX;
import com.zbase.x.lp.FrameLayoutParamsX;

public class ZBaseActivity extends AppCompatActivity {

    /*
     * Loading ...
     */
    public void showLoading() {
        showLoading("加载中...");
    }

    public void showLoading(String text) {
        /*
         * Is Show?
         */
        int loadingLayoutId = 0xAA666601;
        int loadingTvId = 0xAA666602;
        View loadingLayoutLast = findViewById(loadingLayoutId);
        if (loadingLayoutLast != null) {
            ((TextView) findViewById(loadingTvId)).setText(text);
            loadingLayoutLast.setVisibility(View.VISIBLE);
            loadingLayoutLast.bringToFront();
            return;
        }
        /*
         * Background
         */
        LinearLayout loadingLayout = new LinearLayout(this);
        loadingLayout.setId(loadingLayoutId);
        loadingLayout.setPadding(50, 40, 50, 40);
        loadingLayout.setOrientation(LinearLayout.VERTICAL);
        loadingLayout.setGravity(Gravity.CENTER);
        loadingLayout.setBackground(ResourceUtils.getDrawable(this, R.drawable.bg_loading_dialog));
        /*
         * Circle ProgressBar
         */
        ProgressBar circleProgressBar = new ProgressBar(this);
        ViewUtils.setProgressBarColor(circleProgressBar, Color.WHITE);
        loadingLayout.addView(circleProgressBar);
        /*
         * Prompt Text
         */
        TextView promptTextView = new TextView(this);
        promptTextView.setId(loadingTvId);
        promptTextView.setTextColor(Color.WHITE);
        promptTextView.setText(text);
        loadingLayout.addView(promptTextView, new FrameLayoutParamsX().margins(0, 20, 0, 0));
        // add to content view
        addContentView(loadingLayout, new FrameLayoutParamsX().gravity(Gravity.CENTER));
        loadingLayout.bringToFront();
    }

    public void cancelLoading() {
        int loadingId = 0xAA666601;
        View loadingView = findViewById(loadingId);
        if (loadingView == null) {
            return;
        }
        loadingView.setVisibility(View.GONE);
    }

    /*
     * Force screen portrait
     */
    @SuppressLint("SourceLockedOrientationActivity")
    public void forceScreenPortrait() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /*
     * Change view background color
     */
    public void grayViewBackground(View view) {
        view.setBackgroundColor(ColorX.HEX_FFECECEC);
    }

    public void grayContentViewBackground() {
        setContentViewBackgroundColor(ColorX.HEX_FFECECEC);
    }

    public void setContentViewBackgroundColor(int color) {
        findViewById(android.R.id.content).setBackgroundColor(color);
    }

    /*
     * Start activity for result
     */
    public ActivityResultLauncher<Intent> register4ActivityResult(ActivityResultCallback<ActivityResult> callback) {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    public void startActivity4Result(ActivityResultLauncher<Intent> launcher,Intent intent) {
        launcher.launch(intent);
    }

    /*
     * Http request
     */
    public void get(String url,
                    String[] queryParams,
                    JDataCallback callback) {
        YesHttp
                .request(url)
                .addQueryNamesAndValues(queryParams)
                .getAsync(callback);
    }

    public void postForm(String url, String[] formParams, JDataCallback callback) {
        YesHttp
                .request(url)
                .addQueryNamesAndValues(formParams)
                .postAsync(callback);
    }

    public void postBody(String url, Object[] bodyParams, JDataCallback callback) {
        YesHttp
                .request(url)
                .addBodyNamesAndValues(bodyParams)
                .postAsync(callback);
    }

}
