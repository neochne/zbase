package com.zbase.charting.interfaces.dataprovider;

import com.zbase.charting.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
