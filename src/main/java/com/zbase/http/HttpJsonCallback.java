package com.zbase.http;

import com.zbase.R;
import com.zbase.consumer.Consumer2;
import com.zbase.x.ActivityX;
import com.zbase.x.json.JSONArray;
import com.zbase.x.json.JSONObject;

public class HttpJsonCallback extends HttpCallbackX {

    public HttpJsonCallback(ActivityX activityX) {
        super(activityX);
        emptyArrayPrompt("数据为空");
        noMoreDataPrompt("已无更多数据");
        loadingPrompt("加载中...");
        setTag(R.id.http_page, 1);
    }

    public HttpJsonCallback objConsumer(Consumer2<JSONObject, Boolean> consumer) {
        setTag(R.id.http_consumer, consumer);
        return this;
    }

    public <T> HttpJsonCallback arrayConsumer(Consumer2<JSONArray, Boolean> consumer) {
        setTag(R.id.http_consumer, consumer);
        return this;
    }

    public HttpJsonCallback emptyArrayPrompt(String prompt) {
        setTag(R.id.http_empty_array_prompt, prompt);
        return this;
    }

    public HttpJsonCallback noMoreDataPrompt(String prompt) {
        setTag(R.id.http_no_more_data_prompt, prompt);
        return this;
    }

    public HttpJsonCallback loadingPrompt(String prompt) {
        setTag(R.id.http_loading_prompt, prompt);
        return this;
    }

    public HttpJsonCallback chatter(boolean enable) {
        setTag(R.id.http_chatter, enable);
        return this;
    }

    @Override
    public void onBizSuccess(JSONObject bodyJson) {
        super.onBizSuccess(bodyJson);
        Object data = bodyJson.opt("data");
        if (data instanceof JSONObject) {
            Consumer2<JSONObject, Boolean> consumer = (Consumer2<JSONObject, Boolean>) getTag(R.id.http_consumer);
            JSONObject dataJsonObject = bodyJson.optJSONObject("data", null);
            consumer.accept(dataJsonObject, dataJsonObject == null);
        } else if (data instanceof JSONArray) {
            Consumer2<JSONArray, Boolean> consumer = (Consumer2<JSONArray, Boolean>) getTag(R.id.http_consumer);
            JSONArray dataJsonArray = bodyJson.optJSONArray("data");
            consumer.accept(dataJsonArray, ACTIVITYX.handleArray(dataJsonArray));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ACTIVITYX.runOnUiThread(() -> {
            if (isChatter()) {
                ACTIVITYX.showLoading((String) getTag(R.id.http_loading_prompt));
            }
        });
    }

    @Override
    public void onFinish() {
        super.onFinish();
        ACTIVITYX.runOnUiThread(ACTIVITYX::cancelLoading);
    }

}
