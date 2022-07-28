package com.zbase.util;

import java.text.DecimalFormat;

public final class NumberUtils {

    private NumberUtils() {}

    /**
     * 会四舍五入
     */
    public static String format2Decimal(long number,int scale) {
        switch (scale) {
            case 0:
                return new DecimalFormat("0").format(number);
            case 1:
                return new DecimalFormat("0.0").format(number);
            case 2:
                return new DecimalFormat("0.00").format(number);
            case 3:
                return new DecimalFormat("0.000").format(number);
        }
        return "0";
    }

    /**
     * 会四舍五入
     */
    public static String format2Decimal(double number,int scale) {
        switch (scale) {
            case 0:
                return new DecimalFormat("0").format(number);
            case 1:
                return new DecimalFormat("0.0").format(number);
            case 2:
                return new DecimalFormat("0.00").format(number);
            case 3:
                return new DecimalFormat("0.000").format(number);
        }
        return "0";
    }

}
