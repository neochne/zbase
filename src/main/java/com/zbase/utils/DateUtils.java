/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class DateUtils {

    private DateUtils(){}
   
    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern, Locale.CHINA).format(date);
    }

    /**
     * @param pattern 1. yyyy-MM-dd HH:mm:ss（ H:24小时制 h：12小时制）
     *                2. yyyy-MM-dd'T'HH:mm:ss.SSS'Z'（格林威治时间格式）
     */
    public static Date formatDate(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern,Locale.CHINA).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDate2Day(String date) {
        try {
            return formatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(date), "yyyy-MM-dd");
        } catch (ParseException e) {
            return "";
        }
    }

    public static boolean is1gt2(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            return is1gt2(Objects.requireNonNull(sdf.parse(date1)), Objects.requireNonNull(sdf.parse(date2)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean is1gte2(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            return is1gte2(Objects.requireNonNull(sdf.parse(date1)), Objects.requireNonNull(sdf.parse(date2)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean is1gt2(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) > 0;
    }

    public static boolean is1gte2(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) >= 0;
    }

    public static String intervalDays(Date big,Date small){
        return intervalDays(big.getTime(), small.getTime());
    }

    public static String intervalDays(String big, String small) {
        return intervalDays(Objects.requireNonNull(formatDate(big, "yyyy-MM-dd")).getTime(),
                Objects.requireNonNull(formatDate(small, "yyyy-MM-dd")).getTime());
    }

    public static String intervalDays(long big,long small){
        return String.valueOf((int) ((big - small) / (1000*3600*24)));
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
    
    public static String calcAgeByBirth(Date birthDate) {
        /// birth
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);
        int birthYear = birthCalendar.get(Calendar.YEAR);
        int birthMonth = birthCalendar.get(Calendar.MONTH);
        /// birth
        /// current
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(new Date());
        int curYear = curCalendar.get(Calendar.YEAR);
        int curMonth = curCalendar.get(Calendar.MONTH);
        /// current
        /// calc
        int age = curYear - birthYear;
        if (birthMonth > curMonth) {
            age--;
        } else if (birthMonth == curMonth) {
            int birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH);
            int curDay = curCalendar.get(Calendar.DAY_OF_MONTH);
            if (birthDay > curDay) {
                age--;
            }
        }
        /// calc
        return String.valueOf(age);
    }

}
