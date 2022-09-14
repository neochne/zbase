package com.zbase.util;

import android.content.Context;

import com.zbase.charting.charts.BarChart;
import com.zbase.charting.charts.Chart;
import com.zbase.charting.charts.HorizontalBarChart;
import com.zbase.charting.components.Description;
import com.zbase.charting.components.Legend;
import com.zbase.charting.components.LegendEntry;
import com.zbase.charting.components.XAxis;
import com.zbase.charting.components.YAxis;
import com.zbase.charting.data.BarData;
import com.zbase.charting.data.BarDataSet;
import com.zbase.charting.data.BarEntry;
import com.zbase.charting.formatter.IndexAxisValueFormatter;
import com.zbase.charting.formatter.PercentFormatter;
import com.zbase.x.json.JSONArray;
import com.zbase.x.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class ChartUtils {

    private ChartUtils() {
    }

    public static HorizontalBarChart createSingleDatasetHorizontalBarChart(Context context, String[] xLabels) {
        HorizontalBarChart singleDatasetHorBarChart = new HorizontalBarChart(context);
        configBarChart(singleDatasetHorBarChart, xLabels);
        /*
         * 初始化数据
         */
        List<BarEntry> entryList = new ArrayList<>();
        for (int i = 0, l = xLabels.length; i < l; i++) {
            entryList.add(new BarEntry(i, 0));
        }
        BarDataSet barDataSet = new BarDataSet(entryList, "");
        singleDatasetHorBarChart.setData(new BarData(barDataSet));
        return singleDatasetHorBarChart;
    }

    public static HorizontalBarChart createMultiDatasetHorizontalBarChart(Context context, String[] xLabels, String[] legends, int[] colors) {
        HorizontalBarChart multiDatasetHorBarChart = new HorizontalBarChart(context);
        configBarChart(multiDatasetHorBarChart, xLabels);
        /*
         * Chart
         */
        multiDatasetHorBarChart.setScaleEnabled(true);
        multiDatasetHorBarChart.setTouchEnabled(true);
        multiDatasetHorBarChart.setDragEnabled(true);

        /*
         * x 轴
         */
        XAxis xl = multiDatasetHorBarChart.getXAxis();
        xl.setDrawGridLines(true);
        xl.setCenterAxisLabels(true);
        xl.setAxisMinimum(0);
        xl.setAxisMaximum(xLabels.length);
        xl.setGranularity(1f);
        xl.setGranularityEnabled(true);

        /*
         * y 轴（左）
         */
        YAxis yl = multiDatasetHorBarChart.getAxisLeft();
        yl.setAxisMinimum(0);

        /*
         * y 轴（右）
         */
        YAxis yr = multiDatasetHorBarChart.getAxisRight();
        yr.setAxisMinimum(0);

        /*
         * Legend
         */
        Legend legend = multiDatasetHorBarChart.getLegend();
        legend.setEnabled(true);
        legend.setWordWrapEnabled(true);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        // Fix legend cut off
        legend.setYOffset(10f);

        /*
         * 初始化数据
         */
        int xLabelLen = xLabels.length;
        int datasetLen = legends.length;
        BarDataSet[] barDataSets = new BarDataSet[datasetLen];
        LegendEntry[] legendEntries = new LegendEntry[datasetLen];
        for (int i = 0; i < datasetLen; i++) {
            List<BarEntry> entryList = new ArrayList<>(xLabelLen);
            for (int j = 0; j < xLabelLen; j++) {
                entryList.add(new BarEntry(j, 0));
            }
            BarDataSet barDataSet = new BarDataSet(entryList, "");
            barDataSet.setColor(colors[i]);
            if (i == 3) {
                barDataSet.setValueFormatter(new PercentFormatter(2));
            }
            barDataSets[i] = barDataSet;
            legendEntries[i] = new LegendEntry(legends[i], Legend.LegendForm.DEFAULT, Float.NaN, Float.NaN, null, colors[i]);
        }
        legend.setCustom(legendEntries);
        BarData data = new BarData(barDataSets);
        multiDatasetHorBarChart.setData(data);

        float groupSpace = 0.08f;
        float barSpace = 0.03f;
        float barWidth = (xLabelLen - datasetLen * barSpace * xLabelLen - xLabelLen * groupSpace) / (xLabelLen * datasetLen);

        multiDatasetHorBarChart.getData().setBarWidth(barWidth);
        multiDatasetHorBarChart.groupBars(0, groupSpace, barSpace);
        multiDatasetHorBarChart.invalidate();
        return multiDatasetHorBarChart;
    }

    public static void updateHorizontalBarChartData(HorizontalBarChart chart,
                                                    String[] xLabels,
                                                    int datasetIndex,
                                                    JSONArray newDataArray,
                                                    String newDataLabelKey,
                                                    String newDataKey) {
        if (newDataArray != null && newDataArray.length() > 0) {
            for (int i = 0, xLabelsLength = xLabels.length; i < xLabelsLength; i++) {
                String localArea = xLabels[i];
                for (int j = 0, l = newDataArray.length(); j < l; j++) {
                    JSONObject faultAreaJsonObject = newDataArray.optJSONObject(j);
                    if (localArea.equals(faultAreaJsonObject.optString(newDataLabelKey))) {
                        float num = faultAreaJsonObject.optFloat(newDataKey);
                        chart.getData().getDataSets().get(datasetIndex).getEntryForIndex(i).setY(num);
                        break;
                    }
                }
            }
        } else {
            for (int i = 0, xLabelsLength = xLabels.length; i < xLabelsLength; i++) {
                chart.getData().getDataSets().get(datasetIndex).getEntryForIndex(i).setY(0);
            }
        }
    }

    public static void notifyDataSetChanged(Chart<?> chart) {
        chart.getData().notifyDataChanged();
        chart.notifyDataSetChanged();
        ThreadUtils.runOnUiThread(chart::invalidate);
    }

    private static void configBarChart(BarChart barChart, String[] xLabels) {
        barChart.setScaleEnabled(false);
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setAutoScaleMinMaxEnabled(true);
        barChart.animateY(800);
        barChart.setFitBars(true);

        /*
         * x 轴
         */
        XAxis xl = barChart.getXAxis();
        xl.setDrawGridLines(false);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setLabelCount(xLabels.length);
        xl.setValueFormatter(new IndexAxisValueFormatter(xLabels));

        /*
         * y 轴（左）
         */
        YAxis yl = barChart.getAxisLeft();
        yl.setDrawLabels(false);
        yl.setDrawGridLines(false);
        yl.setAxisMinimum(0);

        /*
         * y 轴（右）
         */
        YAxis yr = barChart.getAxisRight();
        yr.setDrawLabels(false);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0);

        /*
         * 图例
         */
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        /*
         * 描述
         */
        Description description = barChart.getDescription();
        description.setEnabled(false);
    }

}
