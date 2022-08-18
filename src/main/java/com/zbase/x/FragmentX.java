package com.zbase.x;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zbase.util.ThreadUtils;
import com.zbase.x.able.ActivityResultAble;
import com.zbase.x.able.ArgumentAble;
import com.zbase.x.able.AsyncCreateFragmentViewAble;
import com.zbase.x.able.HttpAble;
import com.zbase.x.able.LoadingAble;
import com.zbase.x.able.LogAble;
import com.zbase.x.able.ToastAble;
import com.zbase.x.able.WindowAble;

public abstract class FragmentX<V extends View> extends Fragment implements HttpAble
        , AsyncCreateFragmentViewAble<V>
        , ActivityResultAble
        , LoadingAble
        , WindowAble
        , ToastAble
        , ArgumentAble
        , LogAble {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = requireContext();
        V view = onCreateViewSync(context);
        // Is need async create view
        if (async()) {
            ThreadUtils.getSinglePool().execute(() -> onCreateViewAsync(context, view));
        }
        // Is need register activity result launcher
        if (withActivityResultLauncher()) {
            registerActivityResultLauncher(createActivityResultCallback());
        }
        return view;
    }

    protected ActivityX requireActivityX() {
        return (ActivityX) requireContext();
    }

}
