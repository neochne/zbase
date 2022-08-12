package com.zbase.x.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.zbase.R;
import com.zbase.interfaces.DateSelectListener;
import com.zbase.interfaces.Event3Listener;
import com.zbase.util.DensityUtils;
import com.zbase.util.SystemBarUtils;
import com.zbase.view.dialog.DateTimePickDialogView;
import com.zbase.view.dialog.LoadingDialogView;
import com.zbase.view.dialog.PromptDialogView;
import com.zbase.view.dialog.SingleSelectDialogView;
import com.zbase.x.ColorX;
import com.zbase.x.drawable.GradientDrawableX;
import com.zbase.x.drawable.InsetDrawableX;
import com.zbase.x.json.JSONObject;

public final class DialogX extends AlertDialog {

    public DialogX(@NonNull Context context) {
        super(context);
    }

    public DialogX(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public DialogX view(View view) {
        setView(view);
        getWindow().getDecorView().setTag(view);
        return this;
    }

    public Object getView() {
        return getWindow().getDecorView().getTag();
    }

    /**
     * Only for dialog view is LoadingDialogView
     */
    public DialogX showLoading(String text) {
        LoadingDialogView loadingDialogView = (LoadingDialogView) getView();
        loadingDialogView.text(text);
        show();
        return this;
    }

    public DialogX backgroundDrawable(Drawable drawable) {
        getWindow().setBackgroundDrawable(drawable);
        return this;
    }

    public DialogX attr(int x, int y, int width, int height, int gravity) {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.x = x;
        wlp.y = y;
        wlp.width = width;
        wlp.height = height;
        wlp.gravity = gravity;
        window.setAttributes(wlp);
        return this;
    }

    public DialogX hideNavBar() {
        SystemBarUtils.hideNavBar(getWindow());
        return this;
    }

    public DialogX canceledOnTouchOutside(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        return this;
    }

    public DialogX cancelable(boolean cancel) {
        setCancelable(cancel);
        return this;
    }

    public DialogX dialogCancelListener(OnCancelListener cancelListener) {
        setOnCancelListener(cancelListener);
        return this;
    }

    /**
     * 加载对话框
     */
    public static DialogX createLoadingDialog(Context context, String text) {
        return createDialog(context, R.style.ProgressDialogTheme, null)
                .view(new LoadingDialogView(context).text(text))
                .canceledOnTouchOutside(false);
    }

    /**
     * 提示对话框
     */
    public static DialogX createPromptDialog(Context context,
                                             String message,
                                             View.OnClickListener positiveListener) {
        return createPromptDialog(context, "提示", message, "取消", "确定", null, positiveListener);
    }

    /**
     * 提示对话框
     */
    public static DialogX createPromptDialog(Context context,
                                             String title,
                                             String message,
                                             String negative,
                                             String positive,
                                             View.OnClickListener negativeListener,
                                             View.OnClickListener positiveListener) {
        DialogX promptDialog = createHorMarginRadiusDialog(context);
        return promptDialog
                .view(new PromptDialogView(context)
                        .title(title)
                        .text(message)
                        .enablePositiveAndNegativeButton(negative, positive)
                        .negativeClickListener(v -> {
                            promptDialog.cancel();
                            if (negativeListener == null) {
                                return;
                            }
                            negativeListener.onClick(v);
                        })
                        .positiveClickListener(v -> {
                            promptDialog.cancel();
                            positiveListener.onClick(v);
                        }));
    }

    /**
     * 提示对话框（Only one positive button）
     */
    public static DialogX createPositivePromptDialog(Context context,
                                                     String message,
                                                     View.OnClickListener positiveListener) {
        DialogX promptDialog = createHorMarginRadiusDialog(context);
        return promptDialog
                .view((new PromptDialogView(context)
                        .title("提示")
                        .text(message)
                        .enablePositiveButton("确定")
                        .positiveClickListener(v -> {
                            promptDialog.cancel();
                            positiveListener.onClick(v);
                        })));
    }

    /**
     * 单项选择对话框
     */
    public static DialogX createSingleSelectDialog(Context context,
                                                   String title,
                                                   Object data,
                                                   String key,
                                                   Event3Listener<String, Integer, JSONObject> itemSelectListener) {
        DialogX selectDialog = createHorMarginRadiusDialog(context);
        return selectDialog.view(new SingleSelectDialogView(context)
                .title(title)
                .data(data, key)
                .itemSelectListener(itemSelectListener)
                .cancelClickListener(v -> selectDialog.cancel()));
    }

    /**
     * 日期选择对话框
     */
    public static DialogX createDateTimePickDialog(Context context, String title, int limit, DateSelectListener selectListener) {
        DialogX datePickerDialog = createRadiusDialog(context);
        return datePickerDialog
                .view(new DateTimePickDialogView(context)
                        .title(title)
                        .limit(limit)
                        .cancelListener(v -> datePickerDialog.cancel())
                        .selectListener(selectListener));
    }

    /**
     * 圆角对话框（左右有边距）
     */
    public static DialogX createHorMarginRadiusDialog(Context context) {
        GradientDrawableX radiusDrawable = GradientDrawableX.createRadiusDrawable(ColorX.WHITE, DensityUtils.dp2px2int(context, 8));
        InsetDrawableX insetDrawable = new InsetDrawableX(radiusDrawable, DensityUtils.dp2px2int(context, 20));
        return createDialog(context, 0, insetDrawable);
    }


    /**
     * 圆角对话框
     */
    public static DialogX createRadiusDialog(Context context) {
        GradientDrawableX radiusDrawable = GradientDrawableX.createRadiusDrawable(ColorX.WHITE, DensityUtils.dp2px2int(context, 8));
        return createDialog(context, 0, radiusDrawable);
    }

    /**
     * 对话框
     */
    public static DialogX createDialog(Context context, int theme, Drawable backgroundDrawable) {
        return new DialogX(context, theme).backgroundDrawable(backgroundDrawable);
    }

}
