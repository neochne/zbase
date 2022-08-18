package com.zbase.view.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.zbase.R;
import com.zbase.consumer.Consumer3;
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
                                .clickListener(v -> getCancelClickListener().onClick(v))
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

    public SingleSelectDialogView cancelClickListener(View.OnClickListener cancelClickListener) {
        setTag(R.id.dialog_cancel_click_listener, cancelClickListener);
        return this;
    }

    private View.OnClickListener getCancelClickListener() {
        return ((OnClickListener) getTag(R.id.dialog_cancel_click_listener));
    }

    public SingleSelectDialogView itemSelectListener(Consumer3<String, Integer, JSONObject> itemSelectListener) {
        ((ListViewX) findViewById(getAboveBtnViewId()))
                .itemClickListener((adapterView, view, i, l) -> {
                    getCancelClickListener().onClick(view);
                    Object jsonObjectTag = getTag(R.id.dialog_data_type_json_object);
                    Object jsonStringTag = getTag(R.id.dialog_data_type_json_string);
                    Object listStringTag = getTag(R.id.dialog_data_type_list_string);
                    Object arrayStringTag = getTag(R.id.dialog_data_type_array_string);
                    boolean isJsonObject = jsonObjectTag != null && (boolean) jsonObjectTag;
                    boolean isJsonString = jsonStringTag != null && (boolean) jsonStringTag;
                    boolean isListString = listStringTag != null && (boolean) listStringTag;
                    boolean isArrayString = arrayStringTag != null && (boolean) arrayStringTag;
                    if (isJsonString || isListString || isArrayString) {
                        itemSelectListener.accept(String.valueOf(adapterView.getItemAtPosition(i)), i, null);
                    } else if (isJsonObject) {
                        itemSelectListener.accept(String.valueOf(adapterView.getItemAtPosition(i)), i, (JSONObject) view.getTag());
                    }
                });
        return this;
    }

    /**
     * @param data Select data resource,must be the type below code use.
     *             if data is java.util.ArrayList collection,don't use Arras.asList method to get data,
     *             because it return java.util.Arrays$ArrayList type,not java.util.Arrays$ArrayList
     * @param key  If data is object json array,need provide this param
     */
    public SingleSelectDialogView data(Object data, String key) {
        /*
         * Determine data type
         */
        if (data instanceof JSONArray) {
            Object obj = ((JSONArray) data).opt(0);
            if (obj instanceof String) {
                setTag(R.id.dialog_data_type_json_string, true);
            } else if (obj instanceof JSONObject) {
                setTag(R.id.dialog_data_type_json_object, true);
            }
        } else if (data instanceof java.util.ArrayList<?>) {
            setTag(R.id.dialog_data_type_list_string, true);
        } else if (data instanceof String[]) {
            setTag(R.id.dialog_data_type_array_string, true);
        } else {
            throw new RuntimeException("Data invalid");
        }

        /*
         * View
         */
        Object jsonObjectTag = getTag(R.id.dialog_data_type_json_object);
        Object jsonStringTag = getTag(R.id.dialog_data_type_json_string);
        Object listStringTag = getTag(R.id.dialog_data_type_list_string);
        Object arrayStringTag = getTag(R.id.dialog_data_type_array_string);
        boolean isJsonObject = jsonObjectTag != null && (boolean) jsonObjectTag;
        boolean isJsonString = jsonStringTag != null && (boolean) jsonStringTag;
        boolean isListString = listStringTag != null && (boolean) listStringTag;
        boolean isArrayString = arrayStringTag != null && (boolean) arrayStringTag;
        int itemPadding = DensityUtils.dp2px2int(getContext(), 10);
        ((ListViewX) findViewById(getAboveBtnViewId()))
                .adapter(new BaseAdapter() {

                    @Override
                    public int getCount() {
                        if (isJsonString || isJsonObject) {
                            return ((JSONArray) data).length();
                        } else if (isArrayString) {
                            return ((String[]) data).length;
                        } else if (isListString) {
                            return ((ArrayList<String>) data).size();
                        } else {
                            return 0;
                        }
                    }

                    @Override
                    public Object getItem(int position) {
                        if (isJsonString) {
                            return ((JSONArray) data).optString(position);
                        } else if (isJsonObject) {
                            return ((JSONArray) data).optJSONObject(position).optString(key);
                        } else if (isArrayString) {
                            return ((String[]) data)[position];
                        } else if (isListString) {
                            return ((ArrayList<String>) data).get(position);
                        } else {
                            return JSONObject.create();
                        }
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        TextViewX itemTv = new TextViewX(getContext())
                                .padding(0, itemPadding, 0, itemPadding)
                                .gravity(Gravity.CENTER)
                                .text(String.valueOf(getItem(position)));
                        if (isJsonObject) {
                            itemTv.setTag(((JSONArray) data).optJSONObject(position));
                        }
                        return itemTv;
                    }

                });
        return this;
    }

}
