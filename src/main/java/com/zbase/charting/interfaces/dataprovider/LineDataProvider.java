package com.zbase.charting.interfaces.dataprovider;

import com.zbase.charting.components.YAxis;
import com.zbase.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
