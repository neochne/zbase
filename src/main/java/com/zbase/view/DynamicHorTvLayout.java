package com.zbase.view;

import android.content.Context;
import android.view.Gravity;

import com.zbase.util.JsonUtils;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.LinearLayoutX;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public final class DynamicHorTvLayout extends LinearLayoutX {

    public DynamicHorTvLayout(Context context, int count) {
        super(context);
        padding(0, 30, 0, 30);
        for (int i = 0; i < count; i++) {
            addChildView(new TextViewX(getContext())
                            .gravity(Gravity.CENTER),
                    new LinearLayoutParamsX(0, LinearLayoutParamsX.WRAP_CONTENT)
                            .weight(1));
        }
    }

    public DynamicHorTvLayout text(JSONObject itemObject) {
        int i = 0;
        Iterator<String> iterator = itemObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            ((TextViewX) getChildAt(i)).text(JsonUtils.getString(itemObject, key));
            i++;
        }
        return this;
    }

    public DynamicHorTvLayout text(JSONArray jsonArray) {
        for (int i = 0, l = jsonArray.length(); i < l; i++) {
            ((TextViewX) getChildAt(i)).text(JsonUtils.getString(jsonArray, i));
        }
        return this;
    }

    public DynamicHorTvLayout text(JSONArray jsonArray, String key) {
        for (int i = 0, l = jsonArray.length(); i < l; i++) {
            ((TextViewX) getChildAt(i)).text(JsonUtils.getString(JsonUtils.getJSONObject(jsonArray, i), key));
        }
        return this;
    }

}
