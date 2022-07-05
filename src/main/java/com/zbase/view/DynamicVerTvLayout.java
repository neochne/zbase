package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.zbase.util.JsonUtils;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.LinearLayoutX;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public final class DynamicVerTvLayout extends LinearLayoutX {

    public DynamicVerTvLayout(Context context, int count) {
        super(context);
        orientation(VERTICAL);
        int marginBottom = 0;
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                marginBottom = 20;
            }
            addChildView(new TextViewX(getContext()), new LinearLayoutParamsX().margins(0, 20, 0, marginBottom));
        }
    }

    public DynamicVerTvLayout text(JSONObject itemObject, String... names) {
        int i = 0;
        Iterator<String> iterator = itemObject.keys();
        while (iterator.hasNext()) {
            String name = names[i];
            String key = iterator.next();
            String value = JsonUtils.getString(itemObject, key);
            SpannableString ss = new SpannableString(name + value);
            ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ((TextViewX) getChildAt(i)).text(ss);
            i++;
        }
        return this;
    }

    public DynamicVerTvLayout text(JSONArray jsonArray, String... names) {
        for (int i = 0, l = jsonArray.length(); i < l; i++) {
            String name = names[i];
            String value = JsonUtils.getString(jsonArray, i);
            SpannableString ss = new SpannableString(name + value);
            ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ((TextViewX) getChildAt(i)).text(ss);
        }
        return this;
    }

    public DynamicVerTvLayout text(JSONArray jsonArray, String key, String... names) {
        for (int i = 0, l = jsonArray.length(); i < l; i++) {
            String name = names[i];
            String value = JsonUtils.getString(JsonUtils.getJSONObject(jsonArray, i), key);
            SpannableString ss = new SpannableString(name + value);
            ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ((TextViewX) getChildAt(i)).text(ss);
        }
        return this;
    }

}
