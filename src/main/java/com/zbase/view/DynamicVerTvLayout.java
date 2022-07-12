package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.zbase.R;
import com.zbase.util.JsonUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.LinearLayoutX;

import org.json.JSONObject;

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

    @Override
    public DynamicVerTvLayout padding(int l, int t, int r, int b) {
        super.padding(l, t, r, b);
        return this;
    }

    public DynamicVerTvLayout text(JSONObject itemObject, String[] keys, String[] names) {
        text(itemObject,Color.BLACK, ResourceUtils.getColor(getContext(), R.color.tv_default_color),keys,names);
        return this;
    }

    public DynamicVerTvLayout text(JSONObject itemObject, int nameColor, int valueColor, String[] keys, String[] names) {
        for (int i = 0, l = keys.length; i < l; i++) {
            text((TextViewX) getChildAt(i),names[i],JsonUtils.getString(itemObject, keys[i]),nameColor,valueColor);
        }
        return this;
    }

    public DynamicVerTvLayout text(TextViewX tvx, String name, String value, int nameColor, int valueColor) {
        String source = name + value;
        SpannableString ss = new SpannableString(source);
        ss.setSpan(new ForegroundColorSpan(nameColor), 0, name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(valueColor), name.length(), source.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvx.text(ss);
        return this;
    }

}
