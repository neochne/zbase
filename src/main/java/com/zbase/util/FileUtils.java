/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

public final class FileUtils {

    private FileUtils() {
    }

    public static boolean createDir(File file) {
        return file.exists() || file.mkdirs();
    }

    public static boolean createFile(File file) {
        String parent = file.getParent();
        if (StringUtils.isEmpty(parent)) {
            return false;
        }
        File dir = new File(parent);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return false;
            }
        }
        if (file.exists()) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param dirPath  目录
     * @param fileName 文件名（包含文件格式）
     * @return 图片文件
     */
    public static File createPictureFile(Context context, String dirPath, String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            fileName = System.currentTimeMillis() + ".jpg";
        }
        File pictureFile = new File(String.format("%s/%s/%s", getCachedPath(context), dirPath, fileName));
        return createFile(pictureFile) ? pictureFile : null;
    }

    /**
     * @param dirPath  目录
     * @param fileName 文件名（包含文件格式）
     * @return 日志文件
     */
    public static File createLogFile(Context context, String dirPath, String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            fileName = DateUtils.formatDate(new Date(), "yyyy-MM-dd-HH:mm:ss") + ".log";
        }
        File logFile = new File(constructFilePath(context, dirPath, fileName));
        return createFile(logFile) ? logFile : null;
    }

    /**
     * @param dirPath  目录
     * @param fileName 文件名（包含文件格式）
     * @return apk 文件
     */
    public static File createApkFile(Context context, String dirPath, String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            fileName = "app.apk";
        }
        File apkFile = new File(constructFilePath(context, dirPath, fileName));
        return createFile(apkFile) ? apkFile : null;
    }

    /**
     * @param dirPath  目录
     * @param fileName 文件名（包含文件格式）
     * @return apk 文件
     */
    public static File createTextFile(Context context, String dirPath, String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            fileName = System.currentTimeMillis() + ".txt";
        }
        File textFile = new File(constructFilePath(context, dirPath, fileName));
        return createFile(textFile) ? textFile : null;
    }

    public static String constructFilePath(Context context, String dirPath, String fileName) {
        return String.format("%s/%s/%s", getCachedPath(context), dirPath, fileName);
    }

    public static boolean deleteFile(File file) {
        return (!file.exists() || file.delete());
    }

    public static void deleteAllFile(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null)
                for (File f : files) {
                    if (f.isDirectory()) { // 判断是否为文件夹
                        deleteAllFile(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (f.exists()) { // 判断是否存在
                            deleteAllFile(f);
                            try {
                                f.delete();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            file.delete();
        }
    }

    public static boolean saveTextFile(String text, File textFile) {
        if (!createFile(textFile)) {
            return false;
        }
        byte[] bytes = text.getBytes();
        try (OutputStream bos = new BufferedOutputStream(new FileOutputStream(textFile))) {
            bos.write(bytes);
            bos.flush();
            bos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readTextFromFile(File textFile) {
        if (!textFile.exists()) return "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(textFile)))) {
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readTextFromAssetsFile(Context context, String assetsFilePath) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(assetsFilePath)))) {
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static long getFileSizeInKB(File file) {
        return file.length() / 1024;
    }

    public static long getFileSizeInM(File file) {
        return file.length() / 1024 / 1024;
    }

    public static byte[] getFileByteArray(File file) {
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public interface ProgressListener {
        void onProgress(int progress);
    }

    public static void saveFileWithProgress(File file, InputStream is, long fileTotalByte, ProgressListener progressListener) {
        try (OutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = new byte[8 * 1024];
            int perReadBytes;
            long readTotalBytes = 0L;
            while ((perReadBytes = is.read(buffer)) != -1) {
                bos.write(buffer, 0, perReadBytes);
                readTotalBytes += perReadBytes;
                int progress = (int) ((readTotalBytes * 100) / fileTotalByte);
                progressListener.onProgress(progress);
                if (progress >= 100) {
                    break;
                }
            }
            is.close();
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getCachedPath(Context context) {
        if (isSdCardAvailable()) {
            return Environment.getExternalStorageDirectory();
        } else {
            return context.getFilesDir();
        }
    }

    public static boolean isSdCardAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sd.canWrite();
        } else {
            return false;
        }
    }

    /**
     * @param activity      activity
     * @param applicationId the applicationId properties in app/build.gradle,
     *                      use app module's BuildConfig.APPLICATION_ID to reference it
     * @param apk           apk file
     */
    public static void installApkFile(FragmentActivity activity, String applicationId, File apk) {
        final String PACKAGE_MIME_TYPE = "application/vnd.android.package-archive";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // >= 7.0
            String authority = String.format("%s.local.fileprovider", applicationId);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(FileProvider.getUriForFile(activity, authority, apk), PACKAGE_MIME_TYPE);
            activity.startActivity(intent);
        } else { // < 7.0
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(apk), PACKAGE_MIME_TYPE);
            activity.startActivity(intent);
        }
    }

}
