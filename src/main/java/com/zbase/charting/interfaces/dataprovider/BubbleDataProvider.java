package com.zbase.charting.interfaces.dataprovider;

import com.zbase.charting.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
