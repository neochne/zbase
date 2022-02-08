/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

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
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matches = idCardNum.matches(regularExpression);
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
