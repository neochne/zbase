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

public abstract class FragmentX<V extends View> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = requireContext();
        V view = onSyncCreateView(context);
        if (async()) {
            ThreadUtils.getSinglePool().execute(() -> onAsyncCreateView(context, view));
        }
        return view;
    }

    protected void onAsyncCreateView(Context context, V rootView) {
    }

    public void runOnMainThread(Runnable r) {
        ThreadUtils.runOnUiThread(r);
    }

    protected boolean async() {
        return false;
    }

    public FragmentX<V> arguments(Bundle args) {
        setArguments(args);
        return this;
    }

    protected abstract V onSyncCreateView(Context context);

}
