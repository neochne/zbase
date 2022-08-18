/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.zbase.view.ScrollListener;

import java.util.Objects;

public final class ViewUtils {

    private ViewUtils() {
    }

    public static void disableViews(ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(false);
            if (child instanceof ViewGroup) {
                disableViews((ViewGroup) child);
            }
        }
    }

    public static void isChildScrollToBottom(AbsListView absListView, ScrollListener listener) {
        absListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                ListView listView = (ListView) view;
                int lastVisiblePosition = listView.getLastVisiblePosition();
                int sumCount = listView.getAdapter().getCount();
                int bottom = listView.getChildAt(listView.getChildCount() - 1).getBottom();
                int top = listView.getChildAt(0).getTop();
                int height = listView.getHeight();
                if (top != 0
                        && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (lastVisiblePosition == sumCount - 1 && bottom <= height)) {
                    listener.onScrollToBottom();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    public static boolean isChildScrollToBottom(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int count = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();
        if (layoutManager instanceof LinearLayoutManager && count > 0) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            return linearLayoutManager.findLastCompletelyVisibleItemPosition() == count - 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] lastItems = new int[2];
            staggeredGridLayoutManager
                    .findLastCompletelyVisibleItemPositions(lastItems);
            int lastItem = Math.max(lastItems[0], lastItems[1]);
            return lastItem == count - 1;
        }
        return false;
    }

    public static boolean isChildScrollToBottom(ScrollView scrollView) {
        View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
        if (view != null) {
            int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
            return diff == 0;
        }
        return false;
    }

    public static boolean isChildScrollToBottom(NestedScrollView nestedScrollView) {
        View view = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
        if (view != null) {
            int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
            return diff == 0;
        }
        return false;
    }

    /**
     * 先调此方法，然后通过 view.getMeasuredWidth() 和 view.getMeasuredHeight 方法
     * 可以在任何时候获取到一个 view 的长宽
     */
    public static void measureView(View view) {
        view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    public static void addVp2SelectListener(ViewPager2 vp2, ViewPager2.OnPageChangeCallback listener) {
        vp2.registerOnPageChangeCallback(listener);
    }

    public static void removeVp2SelectListener(ViewPager2 vp2, ViewPager2.OnPageChangeCallback listener) {
        vp2.unregisterOnPageChangeCallback(listener);
    }

}
