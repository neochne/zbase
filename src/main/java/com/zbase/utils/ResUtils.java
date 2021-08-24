/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 读取 assets 目录资源
 */
public final class ResUtils {

    private ResUtils(){}

    /**
     * @param path eg: xxx.json
     */
    public static String readTxt(Context context,String path){
        if (null == context || TextUtils.isEmpty(path)) return "";
        StringBuilder sbTxt = new StringBuilder("");
        InputStream txtInputStream = null;
        BufferedReader br = null;
        try {
            txtInputStream = context.getAssets().open(path);
            br = new BufferedReader(new InputStreamReader(txtInputStream));
            String line;
            while((line = br.readLine()) != null) {
                sbTxt.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != txtInputStream)
                    txtInputStream.close();
                if (null != br)
                    br.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sbTxt.toString();
    }

}
