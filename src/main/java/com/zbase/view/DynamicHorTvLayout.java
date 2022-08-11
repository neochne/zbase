package com.zbase.view;

import android.content.Context;
import android.view.Gravity;

import com.zbase.util.DensityUtils;
import com.zbase.x.ColorX;
import com.zbase.x.json.JSONObject;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.LinearLayoutX;

import java.util.Iterator;

public final class DynamicHorTvLayout extends LinearLayoutX {

    public DynamicHorTvLayout(Context context) {
        super(context);
    }

    public DynamicHorTvLayout count(int count) {
        final int verPadding = DensityUtils.dp2px2int(getContext(), 15);
        backgroundColor(ColorX.WHITE)
                .padding(0, verPadding, 0, verPadding);
        for (int i = 0; i < count; i++) {
            addChildView(new TextViewX(getContext())
                            .gravity(Gravity.CENTER),
                    new LinearLayoutParamsX(0, LinearLayoutParamsX.WRAP_CONTENT)
                            .weight(1));
        }
        return this;
    }

    public DynamicHorTvLayout text(JSONObject itemObject) {
        int i = 0;
        Iterator<String> iterator = itemObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            ((TextViewX) getChildAt(i)).text(itemObject.optString(key));
            i++;
        }
        return this;
    }

    public DynamicHorTvLayout text(JSONObject itemObject, String[] keys) {
        for (int i = 0, l = keys.length; i < l; i++) {
            ((TextViewX) getChildAt(i)).gravity(Gravity.CENTER).text(itemObject.optString(keys[i]));
        }
        return this;
    }

    public DynamicHorTvLayout textDftHorPadding(int index) {
        textHorPadding(index, DensityUtils.dp2px2int(getContext(), 13));
        return this;
    }

    public DynamicHorTvLayout textHorPadding(int index, int padding) {
        ((TextViewX) getChildAt(index)).padding(padding, 0, padding, 0);
        return this;
    }

    public DynamicHorTvLayout textGravity(int index, int gravity) {
        ((TextViewX) getChildAt(index)).gravity(gravity);
        return this;
    }

    public DynamicHorTvLayout textColor(int index, int color) {
        ((TextViewX) getChildAt(index)).textColor(color);
        return this;
    }

}
