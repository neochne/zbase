package com.zbase.img;

/**
 * Created by neo on Thu,Sep 19,2019,at 10:56
 */
public interface ImgLoadListener {

    void onSuccess();

    void onFail(Exception e);

}
