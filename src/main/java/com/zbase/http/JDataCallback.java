package com.zbase.http;

import android.content.Context;

import com.zbase.activity.ZBaseActivity;
import com.zbase.interfaces.Event1Listener;
import com.zbase.util.ToastUtils;
import com.zbase.x.json.JSONArrayX;
import com.zbase.x.json.JSONObjectX;

public class JDataCallback extends ContextCallback {

    private Event1Listener<JSONArrayX> mJArrayRespListener;

    private Event1Listener<JSONObjectX> mJObjectRespListener;

    private String mEmptyArrayPrompt = "数据为空";

    private String mNoMoreDataPrompt = "已无更多数据";

    private String mLoadingPrompt = "加载中...";

    private int mPage = 1;

    public JDataCallback(Context context) {
        super(context);
    }

    public JDataCallback objectListener(Event1Listener<JSONObjectX> listener) {
        this.mJObjectRespListener = listener;
        return this;
    }

    public JDataCallback arrayListener(Event1Listener<JSONArrayX> listener) {
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
    public void onBizSuccess(JSONObjectX bodyJson) {
        super.onBizSuccess(bodyJson);
        if (mJObjectRespListener != null) {
            JSONObjectX dataJsonObject = bodyJson.get1JsonObject("data");
            mJObjectRespListener.done(dataJsonObject);
        } else {
            JSONArrayX dataJsonArray = bodyJson.get1JsonArray("data");
            handleMultiPageArray(dataJsonArray, mNoMoreDataPrompt);
            mJArrayRespListener.done(dataJsonArray);
        }
    }

    /*
     * Handle multiple page data
     */
    public void handleMultiPageArray(JSONArrayX dataJsonArray, String noMore) {
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
            zBaseActivity.runOnUiThread(() -> {
                if (mFeedback) {
                    zBaseActivity.showLoading(mLoadingPrompt);
                }
            });
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

    public JDataCallback feedback(boolean enable) {
        mFeedback = enable;
        return this;
    }

}
