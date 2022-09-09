package com.zbase.x.view;

import android.os.Handler;
import android.view.View;

public class OnClickListenerX implements View.OnClickListener {

    private Handler mHandler;

    private boolean mBusy;

    private int mClicks;

    @Override
    public void onClick(final View view) {
        if (!mBusy) {
            mBusy = true;
            mClicks++;
            if (mHandler == null) {
                mHandler = new Handler();
            }
            mHandler.postDelayed(() -> {
                if (mClicks >= 2) {
                    onDoubleClick(view);
                }
                if (mClicks == 1) {
                    onSingleClick(view);
                }
                mClicks = 0;
            }, 200);
            mBusy = false;
        }
    }

    /**
     * If you setOnLongClickListener and OnClickListenerX in the same time,
     * please return true in onLongClick method,
     * otherwise,onSingleClick will be called also
     */
    public void onSingleClick(View view) {
    }

    public void onDoubleClick(View view) {
    }

}
