/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import androidx.core.graphics.BitmapCompat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public final class ImgUtils {

    private ImgUtils() {
    }

    public static Bitmap getBitmapByPath(String path, BitmapFactory.Options options) {
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * please invoke in work thread
     */
    public static Bitmap getBitmapByUrl(String url) throws IOException {
        return BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
    }

    /**
     * @param bitmap    需要保存的位图
     * @param imageFile 图片文件保存路径，需要带图片格式，eg：/storage/emulated/0/xxx.jpg
     * @param quality   压缩质量，100 为不压缩
     */
    public static boolean saveBitmap(Bitmap bitmap, File imageFile, int quality) {
        try {
            String ext = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(Uri.fromFile(imageFile)));
            Bitmap.CompressFormat format = "png".equalsIgnoreCase(ext) ? Bitmap.CompressFormat.PNG
                    : ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext)) ? Bitmap.CompressFormat.JPEG
                    : "webp".equalsIgnoreCase(ext) ? Bitmap.CompressFormat.WEBP
                    : Bitmap.CompressFormat.PNG;
            if (!FileUtils.createFile(imageFile)) {
                return false;
            }
            final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imageFile));
            boolean r = bitmap.compress(format, quality, bos);
            bos.flush();
            bos.close();
            return r;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 这个大小大于 JPEG、PNG 文件的大小
     */
    public static int getBitmapSizeInKbUnit(Bitmap bitmap) {
        return BitmapCompat.getAllocationByteCount(bitmap) / 1024;
    }

}
