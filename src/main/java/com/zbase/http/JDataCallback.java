package com.zbase.http;

import android.content.Context;

import com.zbase.activity.ZBaseActivity;
import com.zbase.interfaces.Event1Listener;
import com.zbase.util.JsonUtils;
import com.zbase.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class JDataCallback extends ContextCallback {

    private Event1Listener<JSONArray> mJArrayRespListener;

    private Event1Listener<JSONObject> mJObjectRespListener;

    private String mEmptyArrayPrompt = "数据为空";

    private String mNoMoreDataPrompt = "已无更多数据";

    private String mLoadingPrompt = "加载中...";

    private int mPage = 1;

    public JDataCallback(Context context) {
        super(context);
    }

    public JDataCallback objectListener(Event1Listener<JSONObject> listener) {
        this.mJObjectRespListener = listener;
        return this;
    }

    public JDataCallback arrayListener(Event1Listener<JSONArray> listener) {
        this.mJArrayRespListener = listener;
        return this;
    }

    public JDataCallback emptyArrayPrompt(String prompt) {
        this.mEmptyArrayPrompt = prompt;
        return this;
    }

    public JDataCallback noMoreDataPrompt(String prompt) {
        this.mNoMoreDataPrompt = prompt;
        return this;
    }

    public JDataCallback loadingPrompt(String prompt) {
        this.mLoadingPrompt = prompt;
        return this;
    }

    @Override
    public void onBizSuccess(JSONObject bodyJsonObject) {
        super.onBizSuccess(bodyJsonObject);
        if (mJObjectRespListener != null) {
            JSONObject dataJsonObject = JsonUtils.getJSONObject(bodyJsonObject, "data");
            mJObjectRespListener.done(dataJsonObject);
        } else {
            JSONArray dataJsonArray = JsonUtils.getJSONArray(bodyJsonObject, "data");
            handleMultiPageArray(dataJsonArray, mNoMoreDataPrompt);
            mJArrayRespListener.done(dataJsonArray);
        }
    }

    /*
     * Handle multiple page data
     */
    public void handleMultiPageArray(JSONArray dataJsonArray, String noMore) {
        if (dataJsonArray.length() < 1) {
            if (mPage == 1) {
                ToastUtils.showOnWorkThread(CONTEXT, mEmptyArrayPrompt);
            } else {
                ToastUtils.showOnWorkThread(CONTEXT, noMore);
            }
        }
    }

    public int plus1Page() {
        return ++mPage;
    }

    public void resetPage() {
        mPage = 0;
    }

    /*
     * If context is ZBaseActivity, show loading on start
     */
    @Override
    public void onStart() {
        super.onStart();
        if (CONTEXT instanceof ZBaseActivity) {
            ZBaseActivity zBaseActivity = (ZBaseActivity) CONTEXT;
            zBaseActivity.runOnUiThread(() -> zBaseActivity.showLoading(mLoadingPrompt));
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (CONTEXT instanceof ZBaseActivity) {
            ZBaseActivity zBaseActivity = (ZBaseActivity) CONTEXT;
            zBaseActivity.runOnUiThread(zBaseActivity::cancelLoading);
        }
    }
}
