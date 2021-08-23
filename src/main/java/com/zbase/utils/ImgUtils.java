/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.zbase.img.CachedImg;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.BitmapCompat;

public final class ImgUtils {

    private ImgUtils() {
    }

    public static Drawable loadDrawableByResId(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    public static void loadBitmapByResIdAsync(final Context context, final int resId, final BitmapLoadCallable callable) {
        if (context == null) {
            return;
        }
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                if (callable != null) {
                    callable.onBitmap(loadBitmapByResIdSync(context, resId));
                }
            }
        });
    }

    public static void loadBitmapsByResIdsAsync(final Context context, final int resIds[], final BitmapLoadCallable callable) {
        if (context == null || resIds == null || resIds.length < 1) {
            return;
        }
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                List<Bitmap> bitmapList = new ArrayList<>();
                for (int resId : resIds) {
                    bitmapList.add(loadBitmapByResIdSync(context, resId));
                }
                if (callable != null) {
                    callable.onBitmaps(bitmapList);
                }
            }
        });
    }

    public static Bitmap loadBitmapByResIdSync(final Context context, final int resId) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public static void loadBitmapByImgFilePathAsync(final Context context, final String imgFilePath,
                                                    final BitmapFactory.Options options, final BitmapLoadCallable callable) {
        if (context == null || StringUtils.isEmpty(imgFilePath)) {
            return;
        }
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                if (callable != null) {
                    if (options == null) {
                        callable.onBitmap(loadBitmapByImgFilePathSync(context, imgFilePath));
                    } else {
                        callable.onBitmap(loadBitmapByImgFilePathSync(context, imgFilePath, options));
                    }
                }
            }
        });
    }

    public static void loadBitmapsByImgFilePathAsync(final Context context, final String[] imgFilePaths,
                                                     final BitmapFactory.Options options, final BitmapLoadCallable callable) {
        if (context == null || imgFilePaths == null || imgFilePaths.length < 1) {
            return;
        }
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Bitmap> bitmapList = new ArrayList<>();
                for (String imgFilePath : imgFilePaths) {
                    if (options == null) {
                        bitmapList.add(loadBitmapByImgFilePathSync(context, imgFilePath));
                    } else {
                        bitmapList.add(loadBitmapByImgFilePathSync(context, imgFilePath, options));
                    }
                }
                if (callable != null) {
                    callable.onBitmaps(bitmapList);
                }
            }
        });
    }

    public static Bitmap loadBitmapByImgFilePathSync(Context context, String imgFilePath) {
        if (context == null || StringUtils.isEmpty(imgFilePath)) {
            return null;
        }
        return loadBitmapByImgFilePathSync(context, imgFilePath, null);
    }

    public static Bitmap loadBitmapByImgFilePathSync(Context context, String imgFilePath, BitmapFactory.Options
            options) {
        if (context == null || StringUtils.isEmpty(imgFilePath)) {
            return null;
        }
        if (options == null) {
            return BitmapFactory.decodeFile(imgFilePath);
        } else {
            return BitmapFactory.decodeFile(imgFilePath, options);
        }
    }

    /**
     * 回调在子线程中
     */
    public static void loadBitmapByUrlAsyncWorkerThread(final String url, final BitmapLoadCallable callable) {
        Executors.newSingleThreadExecutor().submit(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                callable.onBitmap(BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream()));
                return null;
            }
        });
        // or
        /*Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    callable.onBitmap(BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    /**
     * 如果当前调用线程为主线程，则回调在主线程中
     */
    public static void loadBitmapByUrlAsync(final String url, final BitmapLoadCallable callable) {
        Future<Bitmap> bitmapFuture = Executors.newSingleThreadExecutor().submit(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                return BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
            }
        });
        try {
            // get() 方法会阻塞当前调用线程
            // 所以如果当前调用线程为主线程的话，图片别太大了
            callable.onBitmap(bitmapFuture.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void saveBitmap2DeviceAsync(final Bitmap bmp,
                                              final String dirPath,
                                              final String imgName,
                                              final int quality,
                                              final Bitmap.CompressFormat format,
                                              final BitmapSaveCallable saveCallable) {
        if (bmp == null || StringUtils.isEmpty(dirPath) || StringUtils.isEmpty(imgName)) {
            return;
        }
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                String imgPath = saveBitmap2DeviceSync(bmp, dirPath, imgName, quality, format);
                if (saveCallable != null && !StringUtils.isEmpty(imgPath)) {
                    saveCallable.onSuccess(imgPath);
                }
            }
        });
    }

    public static void saveBitmaps2DeviceAsync(final List<CachedImg> imgs) {
        if (imgs == null || imgs.size() < 1) return;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (final CachedImg cachedImg : imgs) {
            if (cachedImg == null) {
                continue;
            }
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    LogUtils.d("threadName","name = " + cachedImg.getImgName() + "-->" + Thread.currentThread().getName());
                    saveBitmap2DeviceSync(cachedImg.getBmp(),
                            cachedImg.getDirPath(),
                            cachedImg.getImgName(),
                            cachedImg.getQuality(),
                            cachedImg.getFormat());
                }
            });
        }
    }

    /**
     * @param dirPath 保存的目录，eg：/storage/emulated/0/
     * @param imgName 图片名，eg：xxx
     * @param quality 压缩质量，100 为不压缩
     * @param format  图片保存格式，见 {@link Bitmap.CompressFormat}
     */
    public static String saveBitmap2DeviceSync(Bitmap bmp,
                                               String dirPath,
                                               String imgName,
                                               int quality,
                                               Bitmap.CompressFormat format) {
        if (bmp == null || StringUtils.isEmpty(dirPath) || StringUtils.isEmpty(imgName) || format == null) {
            return "";
        }
        switch (format) {
            case PNG:
                imgName = imgName + ".png";
                break;
            case JPEG:
                imgName = imgName + ".jpg";
                break;
            case WEBP:
                imgName = imgName + ".webp";
                break;
            default:
                return "";
        }
        String imgPath = dirPath + imgName;
        try {
            File imgFile = new File(imgPath);
            if (!FileUtils.createFile(imgFile)) {
                return "";
            }
            final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imgFile));
            boolean r = bmp.compress(format, quality, bos);
            bos.flush();
            bos.close();
            return r ? imgPath : "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 这个大小大于 JPEG、PNG 文件的大小
     */
    public static int getBitmapSizeInKbUnit(Bitmap bitmap) {
        return BitmapCompat.getAllocationByteCount(bitmap) / 1024;
    }

    public static class BitmapLoadCallable {
        public void onBitmap(Bitmap bitmap) {
        }

        public void onBitmaps(List<Bitmap> bitmapList) {
        }
    }

    public interface BitmapSaveCallable {
        void onSuccess(String imgPath);
    }

}
