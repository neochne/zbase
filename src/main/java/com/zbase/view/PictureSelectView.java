package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zbase.R;
import com.zbase.util.ResourceUtils;
import com.zbase.view.x.ImageViewX;
import com.zbase.view.x.params.FrameLayoutParamsX;

public final class PictureSelectView extends FrameLayout {

    public PictureSelectView(Context context) {
        super(context);
        int picWh = ResourceUtils.getPixel(context, R.dimen.select_pic_w_h);
        setLayoutParams(new LayoutParams(picWh,picWh));
        setBackgroundColor(Color.RED);
        /*
         * ImageView
         */
        ImageViewX picIv = new ImageViewX(context);
        int picIvId = 66;
        picIv.setId(picIvId);
        picIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(picIv);

        /*
         * CheckBox
         */
        CheckBox checkBox = new CheckBox(context);
        int checkBoxId = 67;
        checkBox.setId(checkBoxId);
        checkBox.setLayoutParams(new FrameLayoutParamsX().gravity(Gravity.TOP | Gravity.END));
        addView(checkBox);
    }

}
