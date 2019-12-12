/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.img;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.zbase.http.HttpGlobalConfigure;
import com.zbase.http.OkHttpClientFactory;

import java.io.InputStream;

public final class ImgLoader {

    private static ImgLoadable imgLoadable = null;

    private ImgLoader(){}

    public static void init(Context context, ImgLoadable imgLoadable, HttpGlobalConfigure configure){
        ImgLoader.imgLoadable = imgLoadable;
        Glide.get(context).getRegistry().replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientFactory.getSslOkHttpClient(configure)));
    }

    public static ImgOptions with(Context context){
        return new ImgOptions(context);
    }

    public static ImgOptions with(Fragment fragment){
        return new ImgOptions(fragment);
    }

    public static ImgOptions with(View view){
        return new ImgOptions(view);
    }

    static ImgLoadable getImgLoadable() {
        if (imgLoadable == null) {throw new RuntimeException("you must init ImgLoadable first");}
        return imgLoadable;
    }

}
