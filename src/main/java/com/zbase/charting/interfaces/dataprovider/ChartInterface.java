package com.zbase.charting.interfaces.dataprovider;

import android.graphics.RectF;

import com.zbase.charting.data.ChartData;
import com.zbase.charting.formatter.IValueFormatter;
import com.zbase.charting.utils.MPPointF;

/**
 * Interface that provides everything there is to know about the dimensions,
 * bounds, and range of the chart.
 *
 * @author Philipp Jahoda
 */
public interface ChartInterface {

    /**
     * Returns the minimum x value of the chart, regardless of zoom or translation.
     *
     * @return
     */
    float getXChartMin();

    /**
     * Returns the maximum x value of the chart, regardless of zoom or translation.
     *
     * @return
     */
    float getXChartMax();

    float getXRange();

    /**
     * Returns the minimum y value of the chart, regardless of zoom or translation.
     *
     * @return
     */
    float getYChartMin();

    /**
     * Returns the maximum y value of the chart, regardless of zoom or translation.
     *
     * @return
     */
    float getYChartMax();

    /**
     * Returns the maximum distance in scren dp a touch can be away from an entry to cause it to get highlighted.
     *
     * @return
     */
    float getMaxHighlightDistance();

    int getWidth();

    int getHeight();

    MPPointF getCenterOfView();

    MPPointF getCenterOffsets();

    RectF getContentRect();

    IValueFormatter getDefaultValueFormatter();

    ChartData getData();

    int getMaxVisibleCount();
}
