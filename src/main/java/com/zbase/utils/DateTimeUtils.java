/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class DateTimeUtils {

    private DateTimeUtils(){}

    /**
     * @param pattern eg: yyyy-MM-dd HH:mm:ss（ H:24小时制 h：12小时制）
     */
    public static String date2str(Date date,String pattern){
        if (date == null || StringUtils.isEmpty(pattern)) {return "";}
        return new SimpleDateFormat(pattern,Locale.CHINA).format(date);
    }

    /**
     * @param dateStr eg: 2018-10-16 12:26:32
     * @param pattern eg: 1. yyyy-MM-dd HH:mm:ss（ H:24小时制 h：12小时制）
     *                    2. yyyy-MM-dd'T'HH:mm:ss.SSS'Z'（格林威治时间格式，例：2011-01-11T00:00:00.000Z）
     */
    public static Date str2date(String dateStr,String pattern){
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(pattern)){return new Date();}
        try {
            return new SimpleDateFormat(pattern,Locale.CHINA).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 第二个日期是否大于第一个日期
     */
    public static boolean is2GreatThan1(Date firstDate,Date secondDate){
        if (firstDate == null || secondDate == null){return false;}
        return (secondDate.getTime() - firstDate.getTime()) > 0;
    }

    /**
     * 日期间隔时间，单位：天
     */
    public static String intervalDays(Date smallDate,Date bigDate){
        if (smallDate == null || bigDate == null){return "";}
        return intervalDays(bigDate.getTime(), smallDate.getTime());
    }

    /**
     * 日期间隔时间，单位：天
     */
    public static String intervalDays(long smallMS,long bigMS){
        int days = (int) ((bigMS - smallMS) / (1000*3600*24));
        return String.valueOf(days);
    }

    /**
     * 获取毫秒中的小时部分
     */
    public static String getHoursPart(long ms){
        return String.valueOf(TimeUnit.MILLISECONDS.toHours(ms));
    }

    /**
     * 获取毫秒中的分钟部分
     */
    public static String getMinutesPart(long ms){
        return String.valueOf(TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)));
    }

    /**
     * 获取毫秒中的秒部分
     */
    public static String getSecondsPart(long ms){
        return String.valueOf(TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    /**
     * 毫秒转换成天
     */
    public static long ms2Days(long ms){
        return TimeUnit.MILLISECONDS.toDays(ms);
    }

    /**
     * 毫秒转换成小时
     */
    public static long ms2Hours(long ms){
        return TimeUnit.MILLISECONDS.toHours(ms);
    }

    /**
     * 毫秒转换成分钟
     */
    public static long ms2Minutes(long ms){
        return TimeUnit.MILLISECONDS.toMinutes(ms);
    }

    /**
     * 毫秒转换成钞
     */
    public static long ms2Seconds(long ms){
        return TimeUnit.MILLISECONDS.toSeconds(ms);
    }

    /**
     * 计算年龄
     */
    public static String calcAge(Date birthDate,Date curDate) {
        if (birthDate == null || curDate == null) {return "";}
        Calendar birthCalendar = Calendar.getInstance();
        Calendar curCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);
        curCalendar.setTime(curDate);
        int birthYear = birthCalendar.get(Calendar.YEAR);
        int curYear = curCalendar.get(Calendar.YEAR);
        int age = curYear - birthYear;
        int curMonth = curCalendar.get(Calendar.MONTH);
        int dobMonth = birthCalendar.get(Calendar.MONTH);
        if (dobMonth > curMonth) { // this year can't be counted!
            age--;
        } else if (dobMonth == curMonth) { // same month? check for day
            int curDay = curCalendar.get(Calendar.DAY_OF_MONTH);
            int dobDay = birthCalendar.get(Calendar.DAY_OF_MONTH);
            if (dobDay > curDay) { // this year can't be counted!
                age--;
            }
        }
        return String.valueOf(age);
    }

}
