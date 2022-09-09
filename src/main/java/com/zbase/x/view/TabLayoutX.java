package com.zbase.x.view;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.TooltipCompat;

import com.google.android.material.tabs.TabLayout;
import com.zbase.x.viewgroup.IViewGroupX;

public final class TabLayoutX extends TabLayout implements IViewGroupX<TabLayoutX> {

    public TabLayoutX(@NonNull Context context) {
        super(context);
    }

    public LinearLayout getTabStrip() {
        return (LinearLayout) getChildAt(0);
    }

    public TabLayoutX removeAllTooltipText() {
        LinearLayout tabStrip = getTabStrip();
        for (int i = 0,l = tabStrip.getChildCount(); i < l; i++) {
            TooltipCompat.setTooltipText(getTabStrip().getChildAt(i),  null);
        }
        return this;
    }

    public TabLayoutX setTabLongClickListener(int position,OnLongClickListener listener) {
        getTabStrip().getChildAt(position).setOnLongClickListener(listener);
        return this;
    }

    public TabLayoutX setTabDoubleClickListener(int position,OnClickListenerX listener) {
        getTabStrip().getChildAt(position).setOnClickListener(listener);
        return this;
    }

}
