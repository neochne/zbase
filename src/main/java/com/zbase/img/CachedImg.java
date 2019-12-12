package com.zbase.img;

import android.graphics.Bitmap;

/**
 * Created by neo on Wed,Sep 25,2019,at 16:04
 */
public class CachedImg {

    private Bitmap bmp;
    private String dirPath;
    private String imgName;
    private int quality;
    private Bitmap.CompressFormat format;

    public CachedImg(Bitmap bmp, String dirPath, String imgName, int quality, Bitmap.CompressFormat format) {
        this.bmp = bmp;
        this.dirPath = dirPath;
        this.imgName = imgName;
        this.quality = quality;
        this.format = format;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Bitmap.CompressFormat getFormat() {
        return format;
    }

    public void setFormat(Bitmap.CompressFormat format) {
        this.format = format;
    }

}
