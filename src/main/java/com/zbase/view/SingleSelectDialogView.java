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
import com.zbase.util.TextViewUtils;

import java.util.List;

public final class SingleSelectDialogView extends ConstraintLayout {

    private ImageView mCancelImageView;

    private ListView mSelectListView;

    public SingleSelectDialogView(@NonNull Context context) {
        super(context);
    }

    public SingleSelectDialogView(@NonNull Context context, String title, List<String> itemList) {
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
        titleLayoutParams.topMargin = ResourceUtils.getPixel(context, R.dimen.select_dialog_title_margin);
        TextView titleTextView = new TextView(context);
        titleTextView.setId(ViewCompat.generateViewId());
        titleTextView.setTextColor(Color.BLACK);
        TextViewUtils.setTextSizeInPixel(context, titleTextView, R.dimen.dialog_title_text_size);
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
        cancelLayoutParams.rightMargin = ResourceUtils.getPixel(context,R.dimen.select_dialog_cancel_icon_right_margin);
        mCancelImageView = new ImageView(context);
        mCancelImageView.setLayoutParams(cancelLayoutParams);
        mCancelImageView.setImageResource(R.drawable.ic_clear_gray);
        addView(mCancelImageView);

        /*
         * Horizontal divider
         */
        LayoutParams horizontalDividerLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        horizontalDividerLayoutParams.topToBottom = titleTextView.getId();
        horizontalDividerLayoutParams.topMargin = ResourceUtils.getPixel(context,R.dimen.select_dialog_title_margin);
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
        mSelectListView = new ListView(context);
        mSelectListView.setDivider(new ColorDrawable(ResourceUtils.getColor(context,R.color.divider)));
        mSelectListView.setDividerHeight(1);
        mSelectListView.setLayoutParams(listLayoutParams);
        int itemPadding = ResourceUtils.getPixel(context,R.dimen.select_dialog_item_text_padding);
        mSelectListView.setAdapter(new BaseAdapter() {

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
        addView(mSelectListView);
    }

    public void setCancelClickListener(View.OnClickListener clickListener) {
        mCancelImageView.setOnClickListener(clickListener);
    }

    public void setItemSelectListener(AdapterView.OnItemClickListener itemClickListener) {
        mSelectListView.setOnItemClickListener(itemClickListener);
    }

}
