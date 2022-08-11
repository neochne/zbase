package com.zbase.view.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.zbase.R;
import com.zbase.interfaces.Event3Listener;
import com.zbase.util.DensityUtils;
import com.zbase.x.ColorX;
import com.zbase.x.drawable.ColorDrawableX;
import com.zbase.x.json.JSONArray;
import com.zbase.x.json.JSONObject;
import com.zbase.x.lp.ConstraintLayoutParamsX;
import com.zbase.x.view.ImageViewX;
import com.zbase.x.view.ListViewX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.view.ViewX;

import java.util.ArrayList;

public final class SingleSelectDialogView extends DialogView<SingleSelectDialogView> {

    public SingleSelectDialogView(@NonNull Context context) {
        super(context);
        int titleDividerId = generateViewId();
        this
                .addChildView(new ImageViewX(context)
                                .src(R.drawable.ic_clear_gray)
                                .clickListener(v -> ((OnClickListener) getTag()).onClick(v))
                        , new ConstraintLayoutParamsX()
                                .end2endParent()
                                .top2top(getTitleId())
                                .bottom2top(titleDividerId)
                                .margins(0, 0, DensityUtils.dp2px2int(context, 10), 0))
                .addChildView(new ViewX(context)
                                .id(titleDividerId)
                                .backgroundColor(ColorX.DIVIDER)
                        , new ConstraintLayoutParamsX(ConstraintLayoutParamsX.MATCH_PARENT, 1)
                                .top2bottom(getTitleId()))
                .addChildView(new ListViewX(context)
                                .id(getAboveBtnViewId())
                                .divider(new ColorDrawableX(ColorX.DIVIDER))
                                .dividerHeight(1)
                        , new ConstraintLayoutParamsX()
                                .wMatchParent()
                                .top2bottom(titleDividerId));
    }

    public SingleSelectDialogView cancelIconClickListener(View.OnClickListener cancelClickListener) {
        setTag(cancelClickListener);
        return this;
    }

    /**
     * @param data Select data resource,must be the type below code use.
     *             if data is java.util.ArrayList collection,don't use Arras.asList method to get data,
     *             because it return java.util.Arrays$ArrayList type,not java.util.Arrays$ArrayList
     * @param key  If data is object json array,need provide this param
     */
    public SingleSelectDialogView data(Object data, String key, Event3Listener<String, Integer, JSONObject> itemSelectListener) {
        /*
         * Data type
         */
        final int STRING_JSON_ARRAY = 1;
        final int OBJECT_JSON_ARRAY = 2;
        final int STRING_ARRAY = 3;
        final int STRING_LIST = 4;
        int type = 0;
        /*
         * Check data type
         */
        if (data instanceof JSONArray) {
            Object obj = ((JSONArray) data).opt(0);
            if (obj instanceof String) {
                type = STRING_JSON_ARRAY;
            } else if (obj instanceof JSONObject) {
                type = OBJECT_JSON_ARRAY;
            }
        } else if (data instanceof java.util.ArrayList<?>) {
            type = STRING_LIST;
        } else if (data instanceof String[]) {
            type = STRING_ARRAY;
        } else {
            throw new RuntimeException("Data invalid");
        }

        /*
         * View
         */
        int itemPadding = DensityUtils.dp2px2int(getContext(), 10);
        int finalType = type;
        ((ListViewX) findViewById(getAboveBtnViewId()))
                .adapter(new BaseAdapter() {

                    @Override
                    public int getCount() {
                        switch (finalType) {
                            case STRING_JSON_ARRAY:
                            case OBJECT_JSON_ARRAY:
                                return ((JSONArray) data).length();
                            case STRING_ARRAY:
                                return ((String[]) data).length;
                            case STRING_LIST:
                                return ((ArrayList<String>) data).size();
                            default:
                                return 0;
                        }
                    }

                    @Override
                    public Object getItem(int position) {
                        switch (finalType) {
                            case STRING_JSON_ARRAY:
                                return ((JSONArray) data).optString(position);
                            case OBJECT_JSON_ARRAY:
                                return ((JSONArray) data).optJSONObject(position).optString(key);
                            case STRING_ARRAY:
                                return ((String[]) data)[position];
                            case STRING_LIST:
                                return ((ArrayList<String>) data).get(position);
                            default:
                                return null;
                        }
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        return new TextViewX(getContext())
                                .padding(0, itemPadding, 0, itemPadding)
                                .gravity(Gravity.CENTER)
                                .text(String.valueOf(getItem(position)));
                    }

                })
                .itemClickListener((adapterView, view, i, l) -> {
                    ((OnClickListener) getTag()).onClick(view);
                    switch (finalType) {
                        case STRING_JSON_ARRAY:
                        case STRING_ARRAY:
                        case STRING_LIST:
                            itemSelectListener.done(String.valueOf(adapterView.getItemAtPosition(i)), i, null);
                            break;
                        case OBJECT_JSON_ARRAY:
                            itemSelectListener.done(String.valueOf(adapterView.getItemAtPosition(i)), i, ((JSONArray) data).optJSONObject(i));
                    }
                });
        return this;
    }

}
