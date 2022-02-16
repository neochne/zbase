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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public final class FileUtils {

    private FileUtils() {
    }

    public static boolean isFile(File file) {
        return file.isFile();
    }

    public static boolean isDir(File file) {
        return file.isDirectory();
    }

    public static boolean isFileOrDirExists(File file) {
        return file.exists();
    }

    public static boolean createDir(File file) {
        return isFileOrDirExists(file) || file.mkdirs();
    }

    public static boolean createFile(File file) {
        String parent = file.getParent();
        if (StringUtils.isEmpty(parent)) {
            return false;
        }
        File dir = new File(parent);
        if (!isFileOrDirExists(dir)) {
            if (!dir.mkdirs()) {
                return false;
            }
        }
        if (isFileOrDirExists(file)) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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

    public static String readTextFromFile(File txtFile) {
        if (!isFileOrDirExists(txtFile)) return "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile)))) {
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

    public static boolean deleteFile(File file) {
        return (!isFileOrDirExists(file) || file.delete());
    }

    public static void deleteAll(File file) {
        if (isFileOrDirExists(file)) {
            File[] files = file.listFiles();
            if (files != null)
                for (File f : files) {
                    if (f.isDirectory()) { // 判断是否为文件夹
                        deleteAll(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (f.exists()) { // 判断是否存在
                            deleteAll(f);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public interface ProgressListener {
        void onProgress(int progress);
    }

    public static void writeFileWithProgress(File file, InputStream is, long fileTotalByte, ProgressListener progressListener) {
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

    public static void installApk(FragmentActivity activity, File apk) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // >= 7.0
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(FileProvider.getUriForFile(activity,"com.zbase.fileprovider", apk), "application/vnd.android.package-archive");
            activity.startActivity(intent);
        } else { // < 7.0
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
            activity.startActivity(intent);
        }
    }

}
