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
import com.zbase.util.ResourceUtils;
import com.zbase.util.ViewUtils;

import java.util.List;

public final class SingleSelectView extends ConstraintLayout {

    public SingleSelectView(@NonNull Context context) {
        super(context);
    }

    public SingleSelectView(@NonNull Context context,
                            String title,
                            List<String> itemList,
                            View.OnClickListener cancelIconListener,
                            AdapterView.OnItemClickListener itemClickListener) {
        super(context);

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
        int verticalPadding = ResourceUtils.getPixel(context,R.dimen.dialog_title_text_vertical_padding);
        TextView titleTextView = new TextView(context);
        titleTextView.setPadding(0,verticalPadding,0,verticalPadding);
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
        cancelLayoutParams.rightMargin = ResourceUtils.getPixel(context,R.dimen.dialog_cancel_icon_right_margin);
        ImageView cancelImageView = new ImageView(context);
        cancelImageView.setLayoutParams(cancelLayoutParams);
        cancelImageView.setImageResource(R.drawable.ic_clear_gray);
        cancelImageView.setOnClickListener(cancelIconListener);
        addView(cancelImageView);

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
         * List
         */
        LayoutParams listLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        listLayoutParams.topToBottom = horizontalDivider.getId();
        ListView itemListView = new ListView(context);
        itemListView.setDivider(new ColorDrawable(ResourceUtils.getColor(context,R.color.divider)));
        itemListView.setDividerHeight(1);
        itemListView.setLayoutParams(listLayoutParams);
        int itemPadding = ResourceUtils.getPixel(context,R.dimen.list_item_vertical_padding);
        itemListView.setAdapter(new BaseAdapter() {

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
                return itemList.size();
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView itemTextView = new TextView(context);
                itemTextView.setPadding(0,itemPadding,0,itemPadding);
                itemTextView.setText(getItem(position));
                itemTextView.setGravity(Gravity.CENTER);
                return itemTextView;
            }

        });
        itemListView.setOnItemClickListener(itemClickListener);
        addView(itemListView);
    }

}
