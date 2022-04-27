package com.zbase.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;

public final class SystemBarUtils {

    private SystemBarUtils() {
    }

    /**
     * 隐藏状态栏
     */
    public static void hideStatusBar(FragmentActivity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 隐藏导航栏，点击屏幕时会再出现
     */
    public static void hideNavBar(FragmentActivity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /**
     * 延伸 Activity 界面到 StatusBar
     */
    public static void stretch2StatusBar(FragmentActivity activity) {
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 延伸 Activity 界面到 NavigationBar
     */
    public static void stretch2NavBar(FragmentActivity activity) {
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }

    /**
     * 设置状态栏颜色
     */
    public static void setStatusBarColor(FragmentActivity activity, int color) {
        activity.getWindow().setStatusBarColor(color);
    }

    /**
     * 设置状态栏内容（文字、图标）为黑色
     */
    public static void setStatusBarContentWithBlack(FragmentActivity activity) {
        Window window = activity.getWindow();
        new WindowInsetsControllerCompat(window, window.getDecorView()).setAppearanceLightStatusBars(true);
    }

    /**
     * 设置导航栏内容为黑色
     */
public static void setNavBarContentWithBlack(FragmentActivity activity) {
    Window window = activity.getWindow();
    new WindowInsetsControllerCompat(window, window.getDecorView()).setAppearanceLightNavigationBars(true);
}

    /**
     * 设置导航栏颜色
     */
public static void setNavBarColor(FragmentActivity activity, int color) {
    activity.getWindow().setNavigationBarColor(color);
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

    /**
     * 沉浸（将状态栏、标题栏、导航栏全部隐藏掉）
     *
     * 当用户在状态栏或导航栏区域向屏幕中心滑动时，系统栏会重新显示出来，显示后就不会再自动隐藏
     *
     * 配合无白屏主题使用效果更佳
     * <item name="android:windowDisablePreview">true</item>
     * <item name="android:windowBackground">@android:color/white</item>
     */
    public static void immersive(FragmentActivity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    /**
     * 沉浸（将状态栏、标题栏、导航栏全部隐藏掉）
     *
     * 当用户在状态栏或导航栏区域向屏幕中心滑动时，系统栏会重新显示出来，过一会儿又会自动隐藏
     */
    public static void immersiveSticky(FragmentActivity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /**
     * 退出沉浸模式
     */
    public static void exitImmersive(FragmentActivity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}
