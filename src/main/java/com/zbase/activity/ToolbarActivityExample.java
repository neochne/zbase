package com.zbase.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zbase.R;
import com.zbase.view.ToolbarLayout;

public class ToolbarActivityExample extends AppCompatActivity {

    public void setContentViewWithDefaultToolbar(View view) {
        super.setContentView(createToolbarLayout().setContentView(view));
    }

    public ToolbarLayout createToolbarLayout() {
        return new ToolbarLayout(this)
                .setTitle(getTitle().toString(), Color.WHITE, Color.BLACK, 16, Typeface.DEFAULT_BOLD)
                .setBackIcon(R.drawable.ic_toolbar_left_ripple, 0, v -> {
                });
    }

}
