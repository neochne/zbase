package com.zbase.charting.interfaces.dataprovider;

import com.zbase.charting.components.YAxis.AxisDependency;
import com.zbase.charting.data.BarLineScatterCandleBubbleData;
import com.zbase.charting.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
