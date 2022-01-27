/*
 * Copyright (c) 2019. All rights reserved.
 */

package com.zbase.util;

import android.text.TextUtils;

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
        if (null == file) return false;
        return file.isFile();
    }

    public static boolean isDir(File file) {
        if (null == file) return false;
        return file.isDirectory();
    }

    public static boolean isFileOrDirExists(File file) {
        if (null == file) return false;
        return file.exists();
    }

    public static boolean createDir(File file) {
        if (isFileOrDirExists(file)) return true;
        return file.mkdirs();
    }

    public static boolean createFile(File file) {
        File dirFile = new File(file.getParent());
        if (!isFileOrDirExists(dirFile)) {
            if (!dirFile.mkdirs()) {
                return false;
            }
        }
        if (isFileOrDirExists(file)) {
            return true;
        }
        boolean r = false;
        try {
            r = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static boolean saveTxt2Device(String json, String dirPath, String fileName) {
        if (TextUtils.isEmpty(json)
                || TextUtils.isEmpty(dirPath)
                || TextUtils.isEmpty(fileName)) return false;
        if (!createDir(new File(dirPath))) return false;
        File txt = new File(dirPath + fileName);
        byte[] bytes = json.getBytes();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(txt);
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != fos) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static String readTxtFromDevice(File txtFile) {
        if (!isFileOrDirExists(txtFile)) return "";
        InputStream is = null;
        try {
            is = new FileInputStream(txtFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static boolean delete(File file) {
        if (!isFileOrDirExists(file)) return false;
        return file.delete();
    }

    public static void deleteAll(File file) {
        if (isFileOrDirExists(file)) {
            File files[] = file.listFiles();
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

}
