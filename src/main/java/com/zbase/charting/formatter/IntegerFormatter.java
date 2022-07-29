
package com.zbase.charting.formatter;

import com.zbase.charting.components.AxisBase;
import com.zbase.charting.data.Entry;
import com.zbase.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * This IValueFormatter is just for convenience and simply puts a "%" sign after
 * each value. (Recommeded for PieChart)
 *
 * @author Philipp Jahoda
 */
public class IntegerFormatter implements IValueFormatter, IAxisValueFormatter {

    protected DecimalFormat mFormat;

    public IntegerFormatter() {
        mFormat = new DecimalFormat("###,###,##0");
    }

    /**
     * Allow a custom decimalformat
     *
     * @param format
     */
    public IntegerFormatter(DecimalFormat format) {
        this.mFormat = format;
    }

    // IValueFormatter
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value);
    }

    // IAxisValueFormatter
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value);
    }

    public int getDecimalDigits() {
        return 1;
    }
}
