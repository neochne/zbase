/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class CipherUtils {

    private CipherUtils(){}

    public static String encryptWithMD5(String plaintext){
        final byte[] asciiCodeBytes = plaintext.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(asciiCodeBytes);
            byte[] messageDigest = md5MsgDigest.digest();
            StringBuilder hexBuilder = new StringBuilder();
            for (byte element : messageDigest) {
                String hexString = Integer.toHexString(0xFF & element);
                if (hexString.length() == 1) {
                    hexBuilder.append('0');
                }
                hexBuilder.append(hexString);
            }
            plaintext = hexBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return plaintext;
    }

    public static String str2SHA(String str){
        if (TextUtils.isEmpty(str)) return "";
        byte[] digesta = null;
        try {
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(str.getBytes());
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (null == digesta) return "";
        return byte2HEX(digesta);
    }

    public static String byte2HEX(byte[] b){
        if (null == b) return "";
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    public static String encrypt2AES(String encData, String secretKey, String vector){
        if (TextUtils.isEmpty(secretKey) || 16 != secretKey.length()) return "";
        byte[] encrypted = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = secretKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BAS
    }

    public static String decryptAES(String aesStr,String key,String p){
        if (!TextUtils.isEmpty(aesStr) && TextUtils.isEmpty(key)) return aesStr;
        if (TextUtils.isEmpty(aesStr) && TextUtils.isEmpty(key)) return "";
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(p.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(aesStr, Base64.DEFAULT);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");
        } catch (Exception ex) {
            return null;
        }
    }

}
