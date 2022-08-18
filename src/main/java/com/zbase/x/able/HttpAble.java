package com.zbase.x.able;

import com.zbase.R;
import com.zbase.http.HttpJsonCallback;
import com.zbase.http.YesHttp;
import com.zbase.util.ToastUtils;
import com.zbase.x.json.JSONArray;

public interface HttpAble extends ContextAble {

    default void get(String url,
                     String[] queryParams,
                     HttpJsonCallback callback) {
        YesHttp
                .request(url)
                .addQueryNamesAndValues(queryParams)
                .getAsync(callback);
    }

    default void postForm(String url, String[] formParams, HttpJsonCallback callback) {
        YesHttp
                .request(url)
                .addQueryNamesAndValues(formParams)
                .postAsync(callback);
    }

    default void postBody(String url, Object[] bodyParams, HttpJsonCallback callback) {
        YesHttp
                .request(url)
                .addBodyNamesAndValues(bodyParams)
                .postAsync(callback);
    }

    /*
     * Handle multiple page data
     */
    default int getPage() {
        Object tag = getTag(R.id.http_page);
        if (tag == null) {
            return 1;
        }
        return (int) tag;
    }

    default void setPage(int page) {
        setTag(R.id.http_page, page);
    }

    default void plus1Page() {
        int page = getPage();
        setPage(++page);
    }

    default boolean handleArray(JSONArray array) {
        if (array.length() < 1) {
            if (getPage() == 1) {
                ToastUtils.showOnWorkThread(getActivityX(), (String) getTag(R.id.http_empty_array_prompt));
            } else {
                ToastUtils.showOnWorkThread(getActivityX(), (String) getTag(R.id.http_no_more_data_prompt));
            }
            return true;
        } else {
            return false;
        }
    }

}
