package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

import com.zbase.R;
import com.zbase.util.JsonUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.LinearLayoutX;

import org.json.JSONObject;

import java.util.Iterator;

public final class DynamicHorTvLayout extends LinearLayoutX {

    public DynamicHorTvLayout(Context context, int count) {
        super(context);
        final int verPadding = ResourceUtils.getPixel(context, R.dimen.dht_layout_vertical_padding);
        backgroundColor(Color.WHITE)
                .padding(0,verPadding,0,verPadding);
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

    public DynamicHorTvLayout text(JSONObject itemObject,String[] keys) {
        for (int i = 0,l = keys.length; i < l; i++) {
            ((TextViewX) getChildAt(i)).gravity(Gravity.CENTER).text(JsonUtils.getString(itemObject, keys[i]));
        }
        return this;
    }

    public DynamicHorTvLayout textDftHorPadding(int index) {
        textHorPadding(index,ResourceUtils.getPixel(getContext(), R.dimen.dht_layout_horizontal_padding));
        return this;
    }

    public DynamicHorTvLayout textHorPadding(int index,int padding) {
        ((TextViewX)getChildAt(index)).padding(padding,0,padding,0);
        return this;
    }

    public DynamicHorTvLayout textGravity(int index,int gravity) {
        ((TextViewX)getChildAt(index)).gravity(gravity);
        return this;
    }

    public DynamicHorTvLayout textColor(int index,int color) {
        ((TextViewX)getChildAt(index)).textColor(color);
        return this;
    }

}
