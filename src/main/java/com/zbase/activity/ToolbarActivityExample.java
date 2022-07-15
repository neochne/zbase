package com.zbase.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zbase.view.ToolbarLayout;
import com.zbase.x.lp.FrameLayoutParamsX;

public abstract class ToolbarActivityExample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateContentView(createToolbar());
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view, new FrameLayoutParamsX(FrameLayoutParamsX.MATCH_PARENT, FrameLayoutParamsX.MATCH_PARENT));
    }

    public abstract void onCreateContentView(ToolbarLayout toolbarLayout);

    ToolbarLayout createToolbar() {
        return new ToolbarLayout(this)
                .addTitle(getTitle().toString(), Color.WHITE, Color.BLACK, 16, Typeface.DEFAULT_BOLD)
                .addBackIcon(com.zbase.R.drawable.ic_back_gray_ripple, 0, v -> finish());
    }

}
