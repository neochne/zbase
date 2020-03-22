/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

public final class StringUtils {

    private StringUtils() {
    }

    public static String trim(String str) {
        if (isEmpty(str)) return "";
        return str.replaceAll("\r", "")
                .replaceAll("\n", "")
                .replaceAll(" ", "");
    }

    public static boolean isEmpty(String s){
        return s == null || "".equals(s.trim()) || "null".equals(s.trim());
    }

    public static boolean isEmptyOrZero(String num){
        return isEmpty(num)
                || "0".equals(num)
                || "0.0".equals(num)
                || "0.00".equals(num)
                || "0.000".equals(num);
    }

    public static String null2Empty(String s){
        return isEmpty(s) ? "" : s;
    }

    public static String null2Zero(String s){
        return isEmpty(s) ? "0" : s;
    }

    public static String getValueAscStr(Map<String, String> dataMap) {
        List<Map.Entry<String, String>> dataEntries = new ArrayList<>(dataMap.entrySet());
        Collections.sort(dataEntries, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Entry<String, String> o1, Entry<String, String> o2) {
                return (o1.getValue().compareTo(o2.getValue()));
            }
        });
        StringBuilder ascStringBuilder = new StringBuilder("");
        for (Map.Entry<String, String> entry:dataEntries){
            ascStringBuilder.append(entry.getValue());
        }
        return ascStringBuilder.toString();
    }

}
