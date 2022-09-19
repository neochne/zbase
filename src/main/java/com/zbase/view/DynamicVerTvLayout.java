package com.zbase.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.zbase.util.DensityUtils;
import com.zbase.x.ColorX;
import com.zbase.x.json.JSONObject;
import com.zbase.x.lp.LinearLayoutParamsX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.viewgroup.LinearLayoutX;

public final class DynamicVerTvLayout extends LinearLayoutX {

    public DynamicVerTvLayout(Context context) {
        super(context);
    }

    public DynamicVerTvLayout count(int count) {
        backgroundColor(ColorX.WHITE)
                .orientation(VERTICAL);
        int horMargin = DensityUtils.dp2px2int(getContext(),10);
        int verMargin = DensityUtils.dp2px2int(getContext(),10);
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                addChildView(new TextViewX(getContext()), new LinearLayoutParamsX().margins(horMargin, verMargin, 0, verMargin));
                break;
            }
            addChildView(new TextViewX(getContext()), new LinearLayoutParamsX().margins(horMargin, verMargin, 0, 0));
        }
        return this;
    }

    @Override
    public DynamicVerTvLayout padding(int l, int t, int r, int b) {
        super.padding(l, t, r, b);
        return this;
    }

    public DynamicVerTvLayout text(JSONObject itemObject, String[] keys, String[] names) {
        text(itemObject,ColorX.BLACK, ColorX.TV_DEFAULT_COLOR,keys,names);
        return this;
    }

    public DynamicVerTvLayout text(JSONObject itemObject, int nameColor, int valueColor, String[] keys, String[] names) {
        for (int i = 0, l = keys.length; i < l; i++) {
            text((TextViewX) getChildAt(i),names[i],itemObject.optString(keys[i]),nameColor,valueColor);
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

    @Override
    public DynamicVerTvLayout backgroundColor(int color) {
        super.backgroundColor(color);
        return this;
    }

}
