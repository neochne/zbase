/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VerifyUtils {

    private VerifyUtils() {
    }

    /**
     * 手机号码
     */
    public static boolean isMobilePhoneNum(String cellPhoneNum) {
        if (StringUtils.isEmpty(cellPhoneNum)) return false;
        String reg = "^((\\+?86)|(\\(\\+86\\)))?(13[0123456789][0-9]{8}|15[012356789][0-9]{8}|16[012356789][0-9]{8}|18[0123456789][0-9]{8}|19[02356789][0-9]{8}|17[0123456789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(cellPhoneNum);
        return matcher.matches();
    }

    /**
     * 座机号码
     */
    public static boolean isPhoneNum(String phoneNum) {
        if (StringUtils.isEmpty(phoneNum)) return false;
        Pattern pattern = Pattern.compile("^([0-9]{3,4}-)?[0-9]{7,8}(-[0-9]{4})?$");
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    /**
     * 银行卡
     */
    public static boolean isBandCardNum(String bankCardNum) {
        String nonCheckCodeCardId = bankCardNum.substring(0, bankCardNum.length() - 1);
        if (nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            return false;
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return bankCardNum.charAt(bankCardNum.length() - 1) ==
                ((luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0'));
    }

    /**
     * 身份证
     */
    public static boolean isIdCardNum(String idCardNum) {
        if (idCardNum == null || "".equals(idCardNum)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾
        boolean matches = idCardNum.matches(regularExpression);
        //判断第18位校验值
        if (matches) {
            if (idCardNum.length() == 18) {
                try {
                    char[] charArray = idCardNum.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    return idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase());
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return matches;
    }

}
