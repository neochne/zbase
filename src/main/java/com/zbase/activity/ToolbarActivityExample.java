package com.zbase.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zbase.R;
import com.zbase.view.ToolbarLayout;

public abstract class ToolbarActivityExample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createContentView(createToolbar()));
    }

    public abstract ToolbarLayout createContentView(ToolbarLayout toolbarLayout);

    ToolbarLayout createToolbar() {
        return new ToolbarLayout(this)
                .setTitle(getTitle().toString(), Color.WHITE, Color.BLACK, 16, Typeface.DEFAULT_BOLD)
                .setBackIcon(R.drawable.ic_back_gray_ripple, 0, v -> {
                });
    }

}
