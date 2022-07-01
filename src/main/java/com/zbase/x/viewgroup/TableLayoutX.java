package com.zbase.x.viewgroup;

import android.content.Context;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TableLayoutX extends TabLayout implements IViewGroupX<TableLayoutX> {

    public TableLayoutX(Context context) {
        super(context);
    }

    public TableLayoutX attachVp2(ViewPager2 vp2, TabLayoutMediator.TabConfigurationStrategy tabConfigurationStrategy) {
        new TabLayoutMediator(this, vp2, tabConfigurationStrategy).attach();
        return this;
    }

}
