package com.zbase.http;

import com.zbase.R;
import com.zbase.util.StringUtils;
import com.zbase.util.ToastUtils;
import com.zbase.x.ActivityX;
import com.zbase.x.json.JSONObject;

public class HttpCallbackX implements HttpCallback {

    final ActivityX ACTIVITYX;

    public HttpCallbackX(ActivityX activityX) {
        this.ACTIVITYX = activityX;
        setTag(R.id.http_chatter, true);
    }

    @Override
    public void onSuccess(Response response) {
        JSONObject bodyJson = response.getBodyJSONObject();
        if (bodyJson.optInt("code") != 0) {
            if (isChatter()) {
                ToastUtils.showOnWorkThread(ACTIVITYX, bodyJson.optString("message"));
            }
            return;
        }
        onBizSuccess(bodyJson);
    }

    @Override
    public void onFail(Response response) {
        if (isChatter()) {
            ToastUtils.showOnWorkThread(ACTIVITYX, "请求失败：" + response.getHttpCode());
        }
    }

    @Override
    public void onException(Exception exception) {
        String err = exception.getMessage();
        if (StringUtils.isEmpty(err)) {
            Throwable cause = exception.getCause();
            err = cause != null ? cause.getMessage() : "-1";
        }
        if (isChatter()) {
            ToastUtils.showOnWorkThread(ACTIVITYX, "请求异常：" + err);
        }
    }

    Object getTag(int key) {
        return ACTIVITYX.getWindow().getDecorView().getTag(key);
    }

    void setTag(int key, Object object) {
        ACTIVITYX.getWindow().getDecorView().setTag(key, object);
    }

    boolean isChatter() {
        return (boolean) getTag(R.id.http_chatter);
    }

}
