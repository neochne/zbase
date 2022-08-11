package com.zbase.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import androidx.core.view.WindowInsetsControllerCompat;

import com.zbase.x.ColorX;

public final class SystemBarUtils {

    private SystemBarUtils() {
    }

    /**
     * 隐藏状态栏
     *
     * @param window Activity.getWindow() or Dialog.getWindow() or others window ...
     */
    public static void hideStatusBar(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        window.setStatusBarColor(ColorX.TRANSPARENT);
    }

    /**
     * 隐藏导航栏
     */
    public static void hideNavBar(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        window.setNavigationBarColor(ColorX.TRANSPARENT);
    }

    /**
     * 同时隐藏状态栏、导航栏（配合无白屏主题使用效果更佳）
     *
     * <item name="android:windowDisablePreview">true</item>
     * <item name="android:windowBackground">@android:ColorX/white</item>
     */
    public static void hideSysBar(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /**
     * 显示系统栏
     */
    public static void showSysBar(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 设置状态栏颜色
     */
    public static void setStatusBarColor(Window window, int ColorX) {
        window.setStatusBarColor(ColorX);
    }

    /**
     * 设置导航栏颜色
     */
    public static void setNavBarColor(Window window, int ColorX) {
        window.setNavigationBarColor(ColorX);
    }

    /**
     * 设置状态栏内容（文字、图标）为黑色
     */
    public static void setStatusBarContentWithBlack(Window window) {
        new WindowInsetsControllerCompat(window, window.getDecorView()).setAppearanceLightStatusBars(true);
    }

    /**
     * 设置导航栏内容为黑色
     */
    public static void setNavBarContentWithBlack(Window window) {
        new WindowInsetsControllerCompat(window, window.getDecorView()).setAppearanceLightNavigationBars(true);
    }

    /**
     * 获取系统工具栏高度
     */
    public static int getToolbarHeight(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    /**
     * 获取导航栏高度
     */
    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

}
