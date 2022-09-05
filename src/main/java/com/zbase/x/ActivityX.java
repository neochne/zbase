package com.zbase.x;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zbase.util.ThreadUtils;
import com.zbase.x.able.ActivityResultAble;
import com.zbase.x.able.ActivityStateAble;
import com.zbase.x.able.AsyncCreateActivityViewAble;
import com.zbase.x.able.CacheAble;
import com.zbase.x.able.HttpAble;
import com.zbase.x.able.LoadingAble;
import com.zbase.x.able.LogAble;
import com.zbase.x.able.ToastAble;
import com.zbase.x.able.ToolbarAble;
import com.zbase.x.able.WindowAble;

public class ActivityX extends AppCompatActivity implements HttpAble
        , AsyncCreateActivityViewAble
        , ToolbarAble
        , ActivityResultAble
        , ActivityStateAble
        , LoadingAble
        , WindowAble
        , ToastAble
        , LogAble
        , CacheAble {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Is need async create view
        if (async()) {
            ThreadUtils.getSinglePool().execute(() -> {
                if (withToolbar()) {
                    onCreateViewWithToolbarAsync(createToolbar());
                } else {
                    onCreateViewAsync();
                }
            });
        } else {
            if (withToolbar()) {
                onCreateViewWithToolbarSync(createToolbar());
            } else {
                onCreateViewSync();
            }
        }
        // Is need register activity result launcher
        if (withActivityResultLauncher()) {
            registerActivityResultLauncher(createActivityResultCallback());
        }
    }

}
