package com.zbase.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zbase.R;
import com.zbase.utils.DeviceUtils;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AlertDialog;

public final class Dialogs {

    private static AlertDialog sProgressDialog;

    private Dialogs() {
    }

    /*
     * 加载对话框（start）
     */
    public static void showProgressDialog(Context context) {
        showProgressDialog(context, "加载中...");
    }

    public static void showProgressDialog(Context context, String msg) {
        View dialogView = View.inflate(context, R.layout.dialog_progress, null);
        ((TextView) dialogView.findViewById(R.id.tv_msg)).setText(msg);
        sProgressDialog = new AlertDialog.Builder(context, R.style.ProgressDialogTheme)
                .setView(dialogView)
                .create();
        sProgressDialog.setCanceledOnTouchOutside(false);
        sProgressDialog.show();
    }

    public static void cancelProgressDialog() {
        if (sProgressDialog != null) {
            sProgressDialog.cancel();
            sProgressDialog = null;
        }
    }
    /*
     * 加载对话框（end）
     */

    /*
     * 提示对话框（start）
     */
    public static void showPromptDialog(Activity activity,
                                        String msg,
                                        final View.OnClickListener positiveListener) {
        showPromptDialog(activity, msg, positiveListener, null);
    }

    public static void showPromptDialog(Activity activity,
                                        String msg,
                                        final View.OnClickListener positiveListener,
                                        final DialogInterface.OnCancelListener onCancelListener) {
        showPromptDialog(activity, "提示", msg, "取消", "确定", null, positiveListener, onCancelListener);
    }

    public static void showPromptDialog(Activity activity,
                                        String title,
                                        String msg,
                                        String negative,
                                        String positive,
                                        final View.OnClickListener negativeListener,
                                        final View.OnClickListener positiveListener) {
        showPromptDialog(activity, title, msg, negative, positive, negativeListener, positiveListener, null);
    }

    public static void showPromptDialog(Activity activity,
                                        String title,
                                        String msg,
                                        String negative,
                                        String positive,
                                        final View.OnClickListener negativeListener,
                                        final View.OnClickListener positiveListener,
                                        final DialogInterface.OnCancelListener onCancelListener) {
        final AlertDialog promptDialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.dialog_prompt, null);
        promptDialog.setView(view);
        promptDialog.show();
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        ((TextView) view.findViewById(R.id.tv_msg)).setText(msg);
        TextView negativeTextView = view.findViewById(R.id.tv_negative);
        TextView positiveTextView = view.findViewById(R.id.tv_positive);
        negativeTextView.setText(negative);
        positiveTextView.setText(positive);
        negativeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.cancel();
                if (negativeListener != null) {
                    negativeListener.onClick(v);
                }
            }
        });
        positiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialog.cancel();
                positiveListener.onClick(v);
            }
        });
        promptDialog.setOnCancelListener(onCancelListener);
    }

    /*
     * 提示对话框（end）
     */


    /*
     * 单项选择对话框（start）
     */

    public interface OnTextSelectListener {
        void onTextSelect(String text, int pos);
    }

    public static void showSingleSelectDialog(Activity activity,
                                              String title,
                                              String[] items,
                                              OnTextSelectListener listener) {
        showSingleSelectDialog(activity, title, Arrays.asList(items), listener);
    }

    public static void showSingleSelectDialog(Activity activity,
                                              String title,
                                              final List<String> items,
                                              final OnTextSelectListener listener) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        View view = alertDialog.getLayoutInflater().inflate(R.layout.dialog_select, null);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        view.findViewById(R.id.rl_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        ListView lv = view.findViewById(R.id.lv);
        lv.setAdapter(new BaseAdapter() {

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
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv, null);
                tv.setText(getItem(position));
                return tv;
            }

        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.cancel();
                listener.onTextSelect(items.get(position), position);
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
        Window w = alertDialog.getWindow();
        if (w != null && items.size() > 8) {
            WindowManager.LayoutParams lp = w.getAttributes();
            lp.height = (int) (DeviceUtils.getDeviceHeight(activity) * 0.5);
            w.setAttributes(lp);
        }
    }
    /*
     * 单项选择对话框（end）
     */

}
