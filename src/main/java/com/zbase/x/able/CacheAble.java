package com.zbase.x.able;

import android.content.SharedPreferences;

import com.zbase.util.FileUtils;
import com.zbase.util.SpUtils;

import java.io.File;

public interface CacheAble extends ContextAble {

    default SharedPreferences.Editor getSpEditor(String file) {
        return SpUtils.getEditor(getActivityX(), file);
    }

    default SharedPreferences getSp(String file) {
        return SpUtils.getSharedPreferences(getActivityX(), file);
    }

    default boolean saveTextToLocal(String text, File file) {
        return FileUtils.saveTextFile(text, file);
    }

    default String readTextFromLocal(File file) {
        return FileUtils.readTextFromFile(file);
    }

}
