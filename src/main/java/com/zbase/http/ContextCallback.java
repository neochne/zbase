package com.zbase.http;

import android.content.Context;

import com.zbase.util.JsonUtils;
import com.zbase.util.StringUtils;
import com.zbase.util.ToastUtils;

import org.json.JSONObject;

public abstract class ContextCallback implements Callback {

    final Context CONTEXT;

    public ContextCallback(Context context) {
        this.CONTEXT = context;
    }

    @Override
    public void onSuccess(Response response) {
        JSONObject bodyJsonObject = response.getBodyJsonObject();
        if (JsonUtils.getInt(bodyJsonObject, "code") != 0) {
            ToastUtils.showInWorkThread(CONTEXT, JsonUtils.getString(bodyJsonObject, "message"));
            return;
        }
        onBizSuccess(bodyJsonObject);
    }

    @Override
    public void onFail(Response response) {
        ToastUtils.showInWorkThread(CONTEXT, "请求失败：" + response.getHttpCode());
    }

    @Override
    public void onException(Exception exception) {
        String errMsg = exception.getMessage();
        if (StringUtils.isEmpty(errMsg)) {
            Throwable cause = exception.getCause();
            errMsg = cause != null ? cause.getMessage() : "-1";
        }
        ToastUtils.showInWorkThread(CONTEXT, "请求异常：" + errMsg);
    }

}