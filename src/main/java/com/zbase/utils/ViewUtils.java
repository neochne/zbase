/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ScrollView;

public final class ViewUtils {

    private ViewUtils(){}

    public static void isEnableViews(ViewGroup vg,boolean enable){
        if (enable) return;
        for (int i = 0; i < vg.getChildCount(); i++){
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup){
                isEnableViews((ViewGroup)child, enable);
            }
        }
    }

    public static boolean isChildScrollToBottom(AbsListView absListView){
        int count = absListView.getAdapter().getCount();
        int firstPos = absListView.getFirstVisiblePosition();
        int lastPos = absListView.getLastVisiblePosition();
        if (lastPos > 0 && count > 0 && lastPos == count - 1) {
            return true;
        }
        if (firstPos == 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop()) {
            return false;
        }
        return false;
    }

    public static boolean isChildScrollToBottom(RecyclerView recyclerView){
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int count = recyclerView.getAdapter().getItemCount();
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

    public static boolean isChildScrollToBottom(ScrollView scrollView){
        View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
        if (view != null) {
            int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
            return diff == 0;
        }
        return false;
    }

    public static boolean isChildScrollToBottom(NestedScrollView nestedScrollView){
        View view = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
        if (view != null) {
            int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
            return diff == 0;
        }
        return false;
    }

}
