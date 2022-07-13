package com.zbase.charting.interfaces.dataprovider;

import com.zbase.charting.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
