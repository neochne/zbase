package com.zbase.util;

import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;

public final class TextViewUtils {

    private TextViewUtils() {
    }

    public static void setTextSizeInPixel(Context context, TextView textView,int id) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,ResourceUtils.getPixel(context,id));
    }

}
