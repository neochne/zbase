package com.zbase.activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import com.zbase.R;
import com.zbase.view.ToolbarLayout;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    public void setContentView(View view) {
        view.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 0));
        super.setContentView(generateToolbarLayout().setContentView(view));
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 0));
        super.setContentView(generateToolbarLayout().setContentView(view));
    }

    public void addViews(View... views) {
        ToolbarLayout toolbarLayout = generateToolbarLayout();
        for (View view : views) {
            toolbarLayout.appendView(view);
        }
        super.setContentView(toolbarLayout);
    }

    public void addRightText(String text, View.OnClickListener clickListener) {
        TextView rightTextView = new TextView(this);
        rightTextView.setTextColor(Color.BLACK);
        rightTextView.setText(text);
        rightTextView.setOnClickListener(clickListener);
        getToolbarLayout().addTitleRightView(rightTextView, 20);
    }

    public void addRightImage(int iconId, View.OnClickListener clickListener) {
        ImageView rightImageView = new ImageView(this);
        rightImageView.setImageResource(iconId);
        rightImageView.setOnClickListener(clickListener);
        getToolbarLayout().addTitleRightView(rightImageView, 20);
    }

    public void add2RightImage(int iconId1, int iconId2, View.OnClickListener clickListener1, View.OnClickListener clickListener2) {
        ImageView rightImageView1 = new ImageView(this);
        rightImageView1.setImageResource(iconId1);
        rightImageView1.setId(ViewCompat.generateViewId());
        rightImageView1.setOnClickListener(clickListener1);
        getToolbarLayout().addTitleRightView(rightImageView1, 0);
        ImageView rightImageView2 = new ImageView(this);
        rightImageView2.setImageResource(iconId1);
        rightImageView2.setOnClickListener(clickListener2);
        getToolbarLayout().addTitleRightView(rightImageView2, rightImageView1.getId(), 0);
    }

    private ToolbarLayout generateToolbarLayout() {
        return new ToolbarLayout(this,
                getTitle().toString(),
                Color.BLACK, 15,
                true,
                Color.WHITE,
                R.drawable.ic_baseline_chevron_left_24,
                (v) -> {
                    finish();
                });
    }

    private ToolbarLayout getToolbarLayout() {
        return ((ToolbarLayout) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));
    }

}
