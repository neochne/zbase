package com.zbase.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import com.zbase.R;
import com.zbase.util.ResourceUtils;
import com.zbase.util.TextViewUtils;

import java.util.Arrays;
import java.util.List;

public final class Dialogs {

    private static AlertDialog sProgressDialog;

    private Dialogs() {
    }

    /**
     * 加载对话框
     */
    public static void showLoadingDialog(Context context) {
        showLoadingDialog(context, "加载中...");
    }

    /**
     * 加载对话框
     */
    public static void showLoadingDialog(Context context, String msg) {
        /*
         * container
         */
        LinearLayout containerLinearLayout = new LinearLayout(context);
        containerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        int padding = ResourceUtils.getPixel(context, R.dimen.loading_dialog_padding);
        containerLinearLayout.setPadding(padding, padding - 10, padding, padding - 10);

        /*
         * ProgressBar
         */
        ProgressBar circleProgressBar = new ProgressBar(context);
        circleProgressBar.getIndeterminateDrawable().setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN));
        circleProgressBar.setScaleX(.8f);
        circleProgressBar.setScaleY(.8f);
        containerLinearLayout.addView(circleProgressBar);

        /*
         * Text
         */
        LinearLayout.LayoutParams progressTextViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        progressTextViewLayoutParams.topMargin = ResourceUtils.getPixel(context, R.dimen.loading_dialog_text_margin);
        TextView progressTextView = new TextView(context);
        progressTextView.setLayoutParams(progressTextViewLayoutParams);
        TextViewUtils.setTextSizeInPixel(context, progressTextView, R.dimen.loading_dialog_text_size);
        progressTextView.setText(msg);
        progressTextView.setTextColor(Color.WHITE);
        containerLinearLayout.addView(progressTextView);

        sProgressDialog = new AlertDialog.Builder(context, R.style.ProgressDialogTheme)
                .setView(containerLinearLayout)
                .create();
        sProgressDialog.setCanceledOnTouchOutside(false);
        sProgressDialog.show();
    }

    public static void cancelLoadingDialog() {
        if (sProgressDialog != null) {
            sProgressDialog.cancel();
            sProgressDialog = null;
        }
    }

    /**
     * 提示对话框
     */
    public static void showPromptDialog(Context context,
                                        String message,
                                        View.OnClickListener positiveListener) {
        showPromptDialog(context,"提示",message,"取消","确定",null,positiveListener);
    }

    /**
     * 提示对话框
     */
    public static void showPromptDialog(Context context,
                                        String title,
                                        String message,
                                        String negative,
                                        String positive,
                                        View.OnClickListener negativeListener,
                                        View.OnClickListener positiveListener) {
        /*
         * Dialog
         */
        PromptDialogView promptDialogView = new PromptDialogView(context, title, message, negative, positive);
        Dialog promptDialog = showDialog(context,promptDialogView);
        promptDialogView.setNegativeClickListener(v -> {
            promptDialog.cancel();
            if (negativeListener == null) {return;}
            negativeListener.onClick(v);
        });
        promptDialogView.setPositiveClickListener(v -> {
            promptDialog.cancel();
            positiveListener.onClick(v);
        });
    }

    /**
     * 提示对话框（only one positive button）
     */
    public static void showPromptDialog(Context context,
                                        String title,
                                        String message,
                                        String positive,
                                        View.OnClickListener positiveListener) {
        PromptDialogView promptDialogView = new PromptDialogView(context, title, message, positive);
        Dialog promptDialog = showDialog(context,promptDialogView);
        promptDialogView.setPositiveClickListener(v -> {
            promptDialog.cancel();
            positiveListener.onClick(v);
        });
    }

    public interface OnTextSelectListener {
        void onTextSelect(String text, int pos);
    }

    /**
     * 单项选择对话框
     */
    public static void showSingleSelectDialog(Context context,
                                              String title,
                                              String[] items,
                                              OnTextSelectListener listener) {
        showSingleSelectDialog(context, title, Arrays.asList(items), listener);
    }

    /**
     * 单项选择对话框
     */
    public static void showSingleSelectDialog(Context context,
                                              String title,
                                              final List<String> items,
                                              final OnTextSelectListener listener) {
        /*
         * Container
         */
        ConstraintLayout containerConstraintLayout = new ConstraintLayout(context);
        containerConstraintLayout.setBackgroundColor(Color.WHITE);

        /*
         * Title
         */
        ConstraintLayout.LayoutParams titleLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        titleLayoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        titleLayoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        titleLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        titleLayoutParams.topMargin = ResourceUtils.getPixel(context,R.dimen.select_dialog_title_margin);
        TextView titleTextView = new TextView(context);
        titleTextView.setId(ViewCompat.generateViewId());
        titleTextView.setTextColor(Color.BLACK);
        TextViewUtils.setTextSizeInPixel(context, titleTextView, R.dimen.dialog_title_text_size);
        titleTextView.setText(title);
        titleTextView.setTypeface(Typeface.DEFAULT_BOLD);
        titleTextView.setLayoutParams(titleLayoutParams);
        containerConstraintLayout.addView(titleTextView);

        /*
         * Cancel icon
         */
        ConstraintLayout.LayoutParams cancelLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        cancelLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        cancelLayoutParams.topToTop = titleTextView.getId();
        cancelLayoutParams.bottomToBottom = titleTextView.getId();
        cancelLayoutParams.rightMargin = ResourceUtils.getPixel(context,R.dimen.select_dialog_cancel_icon_right_margin);
        ImageView cancelImageView = new ImageView(context);
        cancelImageView.setLayoutParams(cancelLayoutParams);
        cancelImageView.setImageResource(R.drawable.ic_baseline_clear_24);
        containerConstraintLayout.addView(cancelImageView);

        /*
         * Horizontal divider
         */
        ConstraintLayout.LayoutParams horizontalDividerLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 1);
        horizontalDividerLayoutParams.topToBottom = titleTextView.getId();
        horizontalDividerLayoutParams.topMargin = ResourceUtils.getPixel(context,R.dimen.select_dialog_title_margin);
        View horizontalDivider = new View(context);
        horizontalDivider.setId(ViewCompat.generateViewId());
        horizontalDivider.setLayoutParams(horizontalDividerLayoutParams);
        horizontalDivider.setBackgroundColor(ResourceUtils.getColor(context, R.color.divider));
        containerConstraintLayout.addView(horizontalDivider);

        /*
         * List
         */
        ConstraintLayout.LayoutParams listLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        listLayoutParams.topToBottom = horizontalDivider.getId();
        ListView selectListView = new ListView(context);
        selectListView.setDivider(new ColorDrawable(ResourceUtils.getColor(context,R.color.divider)));
        selectListView.setDividerHeight(1);
        selectListView.setLayoutParams(listLayoutParams);
        int itemPadding = ResourceUtils.getPixel(context,R.dimen.select_dialog_item_text_padding);
        selectListView.setAdapter(new BaseAdapter() {

            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public String getItem(int position) {
                return items.get(position);
            }

            @Override
            public long getItemId(int position) {
                return items.size();
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
        containerConstraintLayout.addView(selectListView);

        /*
         * Dialog
         */
        Dialog selectDialog = showDialog(context,containerConstraintLayout);

        /*
         * Listener
         */
        cancelImageView.setOnClickListener((v)->selectDialog.cancel());

        selectListView.setOnItemClickListener((parent, view, position, id) -> {
            selectDialog.cancel();
            listener.onTextSelect(items.get(position), position);
        });
    }

    /**
     * 对话框
     */
    public static AlertDialog showDialog(Context context,View dialogView) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
        InsetDrawable inset = new InsetDrawable(ResourceUtils.getDrawableById(context, R.drawable.bg_prompt_dialog), ResourceUtils.getPixel(context, R.dimen.prompt_dialog_left_right_margin));
        dialog.getWindow().setBackgroundDrawable(inset);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }

}
