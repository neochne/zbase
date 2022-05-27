/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import androidx.core.graphics.BitmapCompat;

import com.zbase.entity.LocalMedia;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class ImageUtils {

    private ImageUtils() {
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

    public interface OnQueryMediaCallback {
        void onComplete(Map<Long, List<LocalMedia>> mediaMap);
    }

    public static void loadLocalImage(Context context,OnQueryMediaCallback queryCallback) {
        loadLocalMedia(context,"media_type=?",new String[]{String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)},queryCallback);
    }

    public static void loadLocalVideo(Context context,OnQueryMediaCallback queryCallback) {
        loadLocalMedia(context,"media_type=?",new String[]{String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)},queryCallback);
    }
    public static void loadLocalMedia(Context context, OnQueryMediaCallback queryCallback) {
        loadLocalMedia(context,"media_type=? OR media_type=?",new String[]{String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)},queryCallback);
    }

    public static void loadLocalMedia(Context context, String condition, String[] conditionArgs, OnQueryMediaCallback queryCallback) {
        if (!FileUtils.isSdCardAvailable()) {
            ToastUtils.show(context, "未检测到储存卡，相关功能将无法使用");
            queryCallback.onComplete(new LinkedHashMap<>());
            return;
        }
        /*
         * Column
         */
        String columnData = "_data";
        String columnDisplayName = "_display_name";
        String columnBucketId = "bucket_id";
        String columnBucketDisplayName = "bucket_display_name";
        String columnSize = "_size";

        /*
         * Query Args
         */
        Uri externalUri = MediaStore.Files.getContentUri("external");
        String[] mediaProjections = new String[]{
                columnData,
                columnDisplayName,
                columnBucketId,
                columnBucketDisplayName,
                columnSize
        };
        String mediaSortOrder = "_id DESC";
        try (Cursor mediaCursor = context.getContentResolver().query(externalUri, mediaProjections, condition, conditionArgs, mediaSortOrder)) {
            if (mediaCursor == null) {
                return;
            }
            int count = mediaCursor.getCount();
            if (count < 1) {
                return;
            }
            Map<Long, List<LocalMedia>> mediaMap = new HashMap<>();
            mediaCursor.moveToFirst();
            do {
                /*
                 * db data
                 */
                String path = mediaCursor.getString(mediaCursor.getColumnIndexOrThrow(columnData));
                String name = mediaCursor.getString(mediaCursor.getColumnIndexOrThrow(columnDisplayName));
                long bucketId = mediaCursor.getLong(mediaCursor.getColumnIndexOrThrow(columnBucketId));
                String bucketDisplayName = mediaCursor.getString(mediaCursor.getColumnIndexOrThrow(columnBucketDisplayName));
                long size = mediaCursor.getInt(mediaCursor.getColumnIndexOrThrow(columnSize));

                /*
                 * entity
                 */
                LocalMedia media = new LocalMedia();
                media.setPath(path);
                media.setName(name);
                media.setFolder(bucketDisplayName);
                media.setSize(size);
                if (mediaMap.containsKey(bucketId)) {
                    Objects.requireNonNull(mediaMap.get(bucketId)).add(media);
                } else {
                    mediaMap.put(bucketId, new ArrayList<LocalMedia>() {{
                        add(media);
                    }});
                }
            } while (mediaCursor.moveToNext());
            queryCallback.onComplete(mediaMap);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show(context, "未知异常");
            queryCallback.onComplete(new LinkedHashMap<>());
        }
    }

}
