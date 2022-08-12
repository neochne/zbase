package com.zbase.x;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zbase.R;
import com.zbase.http.JDataCallback;
import com.zbase.http.YesHttp;
import com.zbase.util.ResourceUtils;
import com.zbase.util.ThreadUtils;
import com.zbase.util.ViewUtils;
import com.zbase.view.ToolbarLayout;
import com.zbase.x.lp.FrameLayoutParamsX;

public class ActivityX extends AppCompatActivity {
    /*
     * Async configure
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (async()) {
            ThreadUtils.getSinglePool().execute(() -> {
                if (withToolbar()) {
                    onCreateViewWithToolbarAsync(createToolbar());
                } else {
                    onCreateViewAsync();
                }
            });
        } else {
            if (withToolbar()) {
                onCreateViewWithToolbarSync(createToolbar());
            } else {
                onCreateViewSync();
            }
        }

        // Activity launcher
        if (withActivityResultLauncher()) {
            ActivityResultLauncher<Intent> launcher = register4ActivityResult(registerActivityResultCallback());
            getWindow().getDecorView().setTag(R.id.activity_result_launcher, launcher);
        }
    }

    protected boolean async() {
        return false;
    }

    protected void onCreateViewAsync() {
    }

    protected void onCreateViewSync() {
    }

    /*
     * Toolbar configure
     */
    protected boolean withToolbar() {
        return false;
    }

    protected void onCreateViewWithToolbarAsync(ToolbarLayout toolbarLayout) {
    }

    protected void onCreateViewWithToolbarSync(ToolbarLayout toolbarLayout) {
    }

    protected void setToolbarLayout(View view) {
        setContentView(view, new FrameLayoutParamsX(FrameLayoutParamsX.MATCH_PARENT, FrameLayoutParamsX.MATCH_PARENT));
    }

    protected ToolbarLayout createToolbar() {
        return null;
    }

    /*
     * ActivityResultLauncher
     */
    protected boolean withActivityResultLauncher() {
        return false;
    }

    protected ActivityResultCallbackX registerActivityResultCallback() {
        return null;
    }

    protected ActivityResultLauncher<Intent> getActivityResultLauncher() {
        return (ActivityResultLauncher<Intent>) getWindow().getDecorView().getTag(R.id.activity_result_launcher);
    }

    /*
     * Loading
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
        ViewUtils.setProgressBarColor(circleProgressBar, ColorX.WHITE);
        loadingLayout.addView(circleProgressBar);
        /*
         * Prompt Text
         */
        TextView promptTextView = new TextView(this);
        promptTextView.setId(loadingTvId);
        promptTextView.setTextColor(ColorX.WHITE);
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
     * Start activity for result
     */
    public ActivityResultLauncher<Intent> register4ActivityResult(ActivityResultCallback<ActivityResult> callback) {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    public void startActivity4Result(ActivityResultLauncher<Intent> launcher, Intent intent) {
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
