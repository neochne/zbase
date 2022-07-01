package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import com.zbase.R;
import com.zbase.interfaces.Event2Listener;
import com.zbase.interfaces.Event3Listener;
import com.zbase.util.JsonUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.util.ViewUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public final class SingleSelectView extends ConstraintLayout {

    private View.OnClickListener mCancelClickListener;

    public SingleSelectView(@NonNull Context context) {
        super(context);
    }

    public SingleSelectView(@NonNull Context context,
                            String title,
                            JSONArray itemJsonArray,
                            String key,
                            Event3Listener<String, Integer, JSONObject> itemSelectListener) {
        super(context);
        int itemPadding = ResourceUtils.getPixel(context, R.dimen.list_item_vertical_padding);
        initView(context, title, new BaseAdapter() {

            @Override
            public int getCount() {
                return itemJsonArray.length();
            }

            @Override
            public JSONObject getItem(int position) {
                return JsonUtils.getJSONObject(itemJsonArray, position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView itemTextView = new TextView(context);
                itemTextView.setPadding(0, itemPadding, 0, itemPadding);
                itemTextView.setText(JsonUtils.getString(getItem(position), key));
                itemTextView.setGravity(Gravity.CENTER);
                return itemTextView;
            }

        }, (adapterView, view, i, l) -> {
            mCancelClickListener.onClick(view);
            JSONObject selectJsonObject = JsonUtils.getJSONObject(itemJsonArray, i);
            itemSelectListener.done(JsonUtils.getString(selectJsonObject, key), i, selectJsonObject);
        });
    }

    public SingleSelectView(@NonNull Context context,
                            String title,
                            JSONArray itemJsonArray,
                            Event2Listener<String, Integer> itemSelectListener) {
        super(context);
        int itemPadding = ResourceUtils.getPixel(context, R.dimen.list_item_vertical_padding);
        initView(context, title, new BaseAdapter() {

            @Override
            public int getCount() {
                return itemJsonArray.length();
            }

            @Override
            public String getItem(int position) {
                return JsonUtils.getString(itemJsonArray, position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView itemTextView = new TextView(context);
                itemTextView.setPadding(0, itemPadding, 0, itemPadding);
                itemTextView.setText(getItem(position));
                itemTextView.setGravity(Gravity.CENTER);
                return itemTextView;
            }

        }, (adapterView, view, i, l) -> {
            mCancelClickListener.onClick(view);
            itemSelectListener.done(JsonUtils.getString(itemJsonArray, i), i);
        });
    }

    public SingleSelectView(@NonNull Context context,
                            String title,
                            String[] items,
                            Event2Listener<String, Integer> itemSelectListener) {
        super(context);
        int itemPadding = ResourceUtils.getPixel(context, R.dimen.list_item_vertical_padding);
        initView(context, title, new BaseAdapter() {

            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public String getItem(int position) {
                return items[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView itemTextView = new TextView(context);
                itemTextView.setPadding(0, itemPadding, 0, itemPadding);
                itemTextView.setText(getItem(position));
                itemTextView.setGravity(Gravity.CENTER);
                return itemTextView;
            }

        }, (adapterView, view, i, l) -> {
            mCancelClickListener.onClick(view);
            itemSelectListener.done(items[i], i);
        });
    }

    public SingleSelectView(@NonNull Context context,
                            String title,
                            List<String> itemList,
                            Event2Listener<String, Integer> itemSelectListener) {
        super(context);
        int itemPadding = ResourceUtils.getPixel(context, R.dimen.list_item_vertical_padding);
        initView(context, title, new BaseAdapter() {

            @Override
            public int getCount() {
                return itemList.size();
            }

            @Override
            public String getItem(int position) {
                return itemList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView itemTextView = new TextView(context);
                itemTextView.setPadding(0, itemPadding, 0, itemPadding);
                itemTextView.setText(getItem(position));
                itemTextView.setGravity(Gravity.CENTER);
                return itemTextView;
            }

        }, (adapterView, view, i, l) -> {
            mCancelClickListener.onClick(view);
            itemSelectListener.done(itemList.get(i), i);
        });
    }

    private void initView(Context context, String title, BaseAdapter adapter, AdapterView.OnItemClickListener itemClickListener) {
        /*
         * Container
         */
        setBackgroundColor(Color.WHITE);

        /*
         * Title
         */
        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleLayoutParams.topToTop = LayoutParams.PARENT_ID;
        titleLayoutParams.startToStart = LayoutParams.PARENT_ID;
        titleLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        int verticalPadding = ResourceUtils.getPixel(context, R.dimen.dialog_title_text_vertical_padding);
        TextView titleTextView = new TextView(context);
        titleTextView.setPadding(0, verticalPadding, 0, verticalPadding);
        titleTextView.setId(ViewCompat.generateViewId());
        titleTextView.setTextColor(Color.BLACK);
        ViewUtils.setTextSizeInPixel(context, titleTextView, R.dimen.dialog_title_text_size);
        titleTextView.setText(title);
        titleTextView.setTypeface(Typeface.DEFAULT_BOLD);
        titleTextView.setLayoutParams(titleLayoutParams);
        addView(titleTextView);

        /*
         * Cancel icon
         */
        LayoutParams cancelLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        cancelLayoutParams.endToEnd = LayoutParams.PARENT_ID;
        cancelLayoutParams.topToTop = titleTextView.getId();
        cancelLayoutParams.bottomToBottom = titleTextView.getId();
        cancelLayoutParams.rightMargin = ResourceUtils.getPixel(context, R.dimen.dialog_cancel_icon_right_margin);
        ImageView cancelIv = new ImageView(context);
        cancelIv.setLayoutParams(cancelLayoutParams);
        cancelIv.setImageResource(R.drawable.ic_clear_gray);
        cancelIv.setOnClickListener(v -> mCancelClickListener.onClick(v));
        addView(cancelIv);

        /*
         * Horizontal divider
         */
        LayoutParams horizontalDividerLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        horizontalDividerLayoutParams.topToBottom = titleTextView.getId();
        View horizontalDivider = new View(context);
        horizontalDivider.setId(ViewCompat.generateViewId());
        horizontalDivider.setLayoutParams(horizontalDividerLayoutParams);
        horizontalDivider.setBackgroundColor(ResourceUtils.getColor(context, R.color.divider));
        addView(horizontalDivider);

        /*
         * ListView
         */
        LayoutParams listLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        listLayoutParams.topToBottom = horizontalDivider.getId();
        ListView itemListView = new ListView(context);
        itemListView.setDivider(new ColorDrawable(ResourceUtils.getColor(context, R.color.divider)));
        itemListView.setDividerHeight(1);
        itemListView.setLayoutParams(listLayoutParams);
        itemListView.setAdapter(adapter);
        itemListView.setOnItemClickListener(itemClickListener);
        addView(itemListView);
    }

    public void setCancelClickListener(View.OnClickListener cancelClickListener) {
        mCancelClickListener = cancelClickListener;
    }

}
