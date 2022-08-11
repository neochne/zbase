package com.zbase.http;

import android.content.Context;

import com.zbase.util.StringUtils;
import com.zbase.util.ToastUtils;
import com.zbase.x.json.JSONObject;

public class ContextCallback implements Callback {

    final Context CONTEXT;

    boolean mFeedback = true;

    public ContextCallback(Context context) {
        this.CONTEXT = context;
    }

    @Override
    public void onSuccess(Response response) {
        JSONObject bodyJson = response.getJson();
        if (bodyJson.optInt("code") != 0) {
            if (mFeedback) {
                ToastUtils.showOnWorkThread(CONTEXT, bodyJson.optString("message"));
            }
            return;
        }
        onBizSuccess(bodyJson);
    }

    @Override
    public void onFail(Response response) {
        if (mFeedback) {
            ToastUtils.showOnWorkThread(CONTEXT, "请求失败：" + response.getCode());
        }
    }

    @Override
    public void onException(Exception exception) {
        String err = exception.getMessage();
        if (StringUtils.isEmpty(err)) {
            Throwable cause = exception.getCause();
            err = cause != null ? cause.getMessage() : "-1";
        }
        if (mFeedback) {
            ToastUtils.showOnWorkThread(CONTEXT, "请求异常：" + err);
        }
    }

}
