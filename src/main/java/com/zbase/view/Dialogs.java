package com.zbase.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.InsetDrawable;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.zbase.R;
import com.zbase.util.ResourceUtils;

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
    public static void showLoadingDialog(Context context, String message) {
        LoadingDialogView loadingDialogView = new LoadingDialogView(context,message);
        sProgressDialog = new AlertDialog.Builder(context, R.style.ProgressDialogTheme)
                .setView(loadingDialogView)
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
                                              final List<String> itemList,
                                              final OnTextSelectListener listener) {
        SingleSelectDialogView singleSelectDialogView = new SingleSelectDialogView(context,title,itemList);
        Dialog selectDialog = showDialog(context,singleSelectDialogView);
        singleSelectDialogView.setCancelClickListener((v)->selectDialog.cancel());
        singleSelectDialogView.setItemSelectListener((parent, view, position, id) -> {
            selectDialog.cancel();
            listener.onTextSelect(itemList.get(position), position);
        });
    }

    /**
     * 自定义对话框
     */
    public static AlertDialog showDialog(Context context,View dialogView) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
        InsetDrawable insetDrawable = new InsetDrawable(ResourceUtils.getDrawable(context, R.drawable.bg_prompt_dialog), ResourceUtils.getPixel(context, R.dimen.prompt_dialog_left_right_margin));
        dialog.getWindow().setBackgroundDrawable(insetDrawable);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }

}
