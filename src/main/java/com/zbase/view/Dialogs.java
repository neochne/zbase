package com.zbase.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.zbase.R;
import com.zbase.util.ResourceUtils;
import com.zbase.x.view.DialogX;

// TODO: 2022/6/24 Dialog 1. 提取标题，操作按钮为公共组件
//                        2. Dialog View 实现改为 X 库
//                        3. Dialog 改为只传 View
//                        4. Dialogs 类合并到 DialogX
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
        sProgressDialog = createDialog(context, R.style.ProgressDialogTheme, null)
                .view(new LoadingView(context, message))
                .canceledOnTouchOutside(false)
                .display();
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
        showPromptDialog(context, "提示", message, "取消", "确定", null, positiveListener);
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
        DialogX promptDialog = createHorMarginRadiusDialog(context);
        promptDialog
                .view(new PromptView(context,
                        title,
                        message,
                        negative,
                        positive,
                        v -> {
                            promptDialog.cancel();
                            if (negativeListener == null) {
                                return;
                            }
                            negativeListener.onClick(v);
                        },
                        v -> {
                            promptDialog.cancel();
                            positiveListener.onClick(v);
                        }))
                .show();
    }

    /**
     * 提示对话框（only one positive button）
     */
    public static void showPromptDialog(Context context,
                                        String title,
                                        String message,
                                        String positive,
                                        View.OnClickListener positiveListener) {
        DialogX promptDialog = createHorMarginRadiusDialog(context);
        promptDialog
                .view(new PromptView(context,
                        title,
                        message,
                        positive,
                        v -> {
                            promptDialog.cancel();
                            positiveListener.onClick(v);
                        }))
                .show();
    }

    /**
     * 单项选择对话框
     */
    public static DialogX createSingleSelectDialog(SingleSelectView singleSelectView) {
        DialogX selectDialog = createHorMarginRadiusDialog(singleSelectView.getContext()).view(singleSelectView);
        singleSelectView.setCancelClickListener(v -> selectDialog.cancel());
        return selectDialog;
    }

    /**
     * 日期选择对话框
     */
    public static DialogX createDateTimePickDialog(DateTimePickerView dateTimePickerView) {
        DialogX dateTimePickerDialog = createRadiusDialog(dateTimePickerView.getContext()).view(dateTimePickerView);
        dateTimePickerView.setOnCancelAndConfirmClickListener(v -> dateTimePickerDialog.cancel());
        return dateTimePickerDialog;
    }

    /**
     * 圆角对话框（左右有边距）
     */
    public static DialogX createHorMarginRadiusDialog(Context context) {
        return createRadiusDialog(context)
                .backgroundDrawable(new InsetDrawable(ResourceUtils.getDrawable(context, R.drawable.bg_prompt_dialog),
                        ResourceUtils.getPixel(context, R.dimen.dialog_window_horizontal_margin)));
    }


    /**
     * 圆角对话框
     */
    public static DialogX createRadiusDialog(Context context) {
        return createDialog(context, 0, ResourceUtils.getDrawable(context, R.drawable.bg_prompt_dialog));
    }

    /**
     * 对话框
     */
    public static DialogX createDialog(Context context, int theme, Drawable drawable) {
        return new DialogX(context, theme)
                .backgroundDrawable(drawable);
    }

}
