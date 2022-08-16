package com.zbase.x.able;

import android.os.Bundle;

import com.zbase.x.FragmentX;

public interface ArgumentAble extends ContextAble{

    default FragmentX arguments(Bundle args) {
        getFragmentX().setArguments(args);
        return (FragmentX) this;
    }

}
