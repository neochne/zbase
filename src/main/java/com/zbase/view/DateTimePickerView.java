package com.zbase.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;

import com.zbase.R;
import com.zbase.interfaces.DateSelectListener;
import com.zbase.util.DateTimeUtils;
import com.zbase.util.ResourceUtils;
import com.zbase.view.x.ConstraintLayoutParamsX;
import com.zbase.view.x.ConstraintLayoutX;
import com.zbase.view.x.NumberPickerX;
import com.zbase.view.x.TextViewX;
import com.zbase.view.x.ViewX;

import java.util.Calendar;

public final class DateTimePickerView extends ConstraintLayoutX {

    public DateTimePickerView(@NonNull Context context) {
        super(context);
    }

    public DateTimePickerView(@NonNull Context context,
                              String title,
                              int limit,
                              View.OnClickListener clickListener,
                              DateSelectListener selectListener) {
        super(context);
        setBackgroundColor(Color.WHITE);
        /*
         * Title
         */
        int titleVerticalPadding = ResourceUtils.getPixel(context, R.dimen.dialog_title_text_vertical_padding);
        TextViewX titleTv = createTextView(context, title)
                .textColor(Color.BLACK).textSize(16)
                .padding(0, titleVerticalPadding, 0, titleVerticalPadding)
                .typeFace(Typeface.DEFAULT_BOLD);
        ViewX titleDivider = createDivider(context);

        /*
         * Year
         */
        NumberPickerX yearPicker = createPicker(context, DateTimeUtils.getYears(2), 0, 4, 2);
        TextViewX yearTv = createTextView(context, "年");

        /*
         * Month
         */
        NumberPickerX monthPicker = createPicker(context, DateTimeUtils.getMonths(), 0, 11, DateTimeUtils.sCurMonthIndex);
        TextViewX monthTv = createTextView(context, "月");
        ViewX pickerDivider = createDivider(context);

        /*
         * Day
         */
        NumberPickerX dayPicker = createPicker(context, DateTimeUtils.getDays(), 0, 30, DateTimeUtils.sCurDayIndex);
        TextViewX dayTv = createTextView(context, "日");

        /*
         * Cancel button
         */
        TextViewX cancelTv = createTextView(context, "取消")
                .textColor(Color.BLACK)
                .padding(0, 20, 0, 20)
                .gravity(Gravity.CENTER)
                .clickListener(clickListener);
        ViewX btnDivider = createDivider(context);
        TextViewX confirmTv = createTextView(context, "确认")
                .textColor(Color.BLACK)
                .padding(0, 20, 0, 20)
                .gravity(Gravity.CENTER);

        /*
         * Add year,month,day
         */
        this
                .addChildView(titleTv,
                        new ConstraintLayoutParamsX()
                                .top2top(ConstraintLayoutParamsX.PARENT_ID)
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID))
                .addChildView(titleDivider,
                        new ConstraintLayoutParamsX()
                                .width(0)
                                .height(1)
                                .top2bottom(titleTv.getId())
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID))
                .addChildView(yearPicker,
                        new ConstraintLayoutParamsX()
                                .width(0)
                                .weightHorizontal(1)
                                .chainStyleHorizontal(ConstraintLayoutParamsX.CHAIN_SPREAD_INSIDE)
                                .top2bottom(titleDivider.getId())
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2start(yearTv.getId()))
                .addChildView(yearTv,
                        new ConstraintLayoutParamsX()
                                .top2top(yearPicker.getId())
                                .bottom2bottom(yearPicker.getId())
                                .start2end(yearPicker.getId())
                                .end2start(monthPicker.getId()))
                .addChildView(monthPicker,
                        new ConstraintLayoutParamsX()
                                .width(0)
                                .weightHorizontal(1)
                                .top2top(yearPicker.getId())
                                .bottom2bottom(yearPicker.getId())
                                .start2end(yearTv.getId())
                                .end2start(monthTv.getId()))
                .addChildView(monthTv,
                        new ConstraintLayoutParamsX()
                                .top2top(yearPicker.getId())
                                .bottom2bottom(yearPicker.getId())
                                .start2end(monthPicker.getId())
                                .end2start(dayPicker.getId()))
                .addChildView(dayPicker,
                        new ConstraintLayoutParamsX()
                                .width(0)
                                .weightHorizontal(1)
                                .top2top(yearPicker.getId())
                                .bottom2bottom(yearPicker.getId())
                                .start2end(monthTv.getId())
                                .end2start(dayTv.getId()))
                .addChildView(dayTv,
                        new ConstraintLayoutParamsX()
                                .top2top(yearPicker.getId())
                                .bottom2bottom(yearPicker.getId())
                                .start2end(dayPicker.getId()))
                .addChildView(pickerDivider,
                        new ConstraintLayoutParamsX()
                                .width(0)
                                .height(1)
                                .top2bottom(yearPicker.getId())
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2end(ConstraintLayoutParamsX.PARENT_ID))
                .addChildView(cancelTv,
                        new ConstraintLayoutParamsX()
                                .width(0)
                                .chainStyleHorizontal(LayoutParams.CHAIN_SPREAD_INSIDE)
                                .top2bottom(pickerDivider.getId())
                                .start2start(ConstraintLayoutParamsX.PARENT_ID)
                                .end2start(confirmTv.getId()))
                .addChildView(btnDivider,
                        new ConstraintLayoutParamsX()
                                .width(1)
                                .height(0)
                                .start2end(cancelTv.getId())
                                .end2start(confirmTv.getId())
                                .top2bottom(pickerDivider.getId())
                                .bottom2bottom(ConstraintLayoutParamsX.PARENT_ID))
                .addChildView(confirmTv,
                        new ConstraintLayoutParamsX()
                                .width(0)
                                .top2top(cancelTv.getId())
                                .bottom2bottom(cancelTv.getId())
                                .start2end(cancelTv.getId())
                                .end2end(ConstraintLayoutParamsX.PARENT_ID));
        /*
         * Add hour,minute,second by limit
         */
        NumberPickerX secondPicker = null;
        int secondTvId = 0;
        NumberPickerX minutePicker = null;
        int minuteTvId = 0;
        NumberPickerX hourPicker = null;
        int hourTvId = 0;
        switch (limit) {
            /*
             * Second
             */
            case Calendar.SECOND:
                /*
                 * Generate id
                 */
                secondTvId = generateViewId();
                minuteTvId = generateViewId();

                /*
                 * Create second view
                 */
                String[] seconds = DateTimeUtils.getSeconds();
                secondPicker = createPicker(context, seconds, 0, 59, DateTimeUtils.sCurSecondIndex);
                TextViewX secondTv = createTextView(context, secondTvId, "秒");
                this.
                        addChildView(secondPicker,
                                new ConstraintLayoutParamsX()
                                        .width(0)
                                        .weightHorizontal(1)
                                        .start2end(minuteTvId)
                                        .end2start(secondTvId)
                                        .top2top(yearPicker.getId())
                                        .bottom2bottom(yearPicker.getId()))
                        .addChildView(secondTv,
                                new ConstraintLayoutParamsX()
                                        .top2top(yearPicker.getId())
                                        .bottom2bottom(yearPicker.getId())
                                        .start2end(secondPicker.getId())
                                        .end2end(ConstraintLayoutParamsX.PARENT_ID));
                /*
                 * Minute
                 */
            case Calendar.MINUTE:
                /*
                 * Generate id
                 */
                hourTvId = generateViewId();
                if (minuteTvId == 0) {
                    minuteTvId = generateViewId();
                }

                /*
                 * Create minute view
                 */
                String[] minutes = DateTimeUtils.getMinutes();
                minutePicker = createPicker(context, minutes, 0, 59, DateTimeUtils.sCurMinuteIndex);
                TextViewX minuteTv = createTextView(context, minuteTvId, "分");
                this.
                        addChildView(minutePicker,
                                new ConstraintLayoutParamsX()
                                        .width(0)
                                        .weightHorizontal(1)
                                        .start2end(hourTvId)
                                        .end2start(minuteTvId)
                                        .top2top(yearPicker.getId())
                                        .bottom2bottom(yearPicker.getId()))
                        .addChildView(minuteTv,
                                new ConstraintLayoutParamsX()
                                        .top2top(yearPicker.getId())
                                        .bottom2bottom(yearPicker.getId())
                                        .start2end(minutePicker.getId()));
                if (secondTvId != 0) {
                    ((ConstraintLayoutParamsX) minuteTv.getLayoutParams()).end2start(secondPicker.getId());
                } else {
                    ((ConstraintLayoutParamsX) minuteTv.getLayoutParams()).end2end(ConstraintLayoutParamsX.PARENT_ID);
                }
                /*
                 * Hour
                 */
            case Calendar.HOUR:
                /*
                 * Generate id
                 */
                if (hourTvId == 0) {
                    hourTvId = generateViewId();
                }

                /*
                 * Create hour view
                 */
                String[] hours = DateTimeUtils.getHours();
                hourPicker = createPicker(context, hours, 0, 23, DateTimeUtils.sCurHourIndex);
                TextViewX hourTv = createTextView(context, hourTvId, "时");
                this.
                        addChildView(hourPicker,
                                new ConstraintLayoutParamsX()
                                        .width(0)
                                        .weightHorizontal(1)
                                        .start2end(dayTv.getId())
                                        .end2start(hourTvId)
                                        .top2top(yearPicker.getId())
                                        .bottom2bottom(yearPicker.getId()))
                        .addChildView(hourTv,
                                new ConstraintLayoutParamsX()
                                        .top2top(yearPicker.getId())
                                        .bottom2bottom(yearPicker.getId())
                                        .start2end(hourPicker.getId()));
                if (minuteTvId != 0) {
                    ((ConstraintLayoutParamsX) hourTv.getLayoutParams()).end2start(minutePicker.getId());
                } else {
                    ((ConstraintLayoutParamsX) hourTv.getLayoutParams()).end2end(ConstraintLayoutParamsX.PARENT_ID);
                }
                ((ConstraintLayoutParamsX) dayTv.getLayoutParams())
                        .end2start(hourPicker.getId());
                break;
            default:
                ((ConstraintLayoutParamsX) dayTv.getLayoutParams())
                        .end2end(ConstraintLayoutParamsX.PARENT_ID);
        }

        NumberPickerX finalHourPicker = hourPicker;
        NumberPickerX finalMinutePicker = minutePicker;
        NumberPickerX finalSecondPicker = secondPicker;
        confirmTv.setOnClickListener(v -> {
            String year = yearPicker.getDisplayedValues()[yearPicker.getValue()];
            String month = monthPicker.getDisplayedValues()[monthPicker.getValue()];
            String day = dayPicker.getDisplayedValues()[dayPicker.getValue()];
            String date = year + "-" + month + "-" + day;
            String hour = "";
            String minute = "";
            String second = "";
            if (finalHourPicker != null) {
                hour = finalHourPicker.getDisplayedValues()[finalHourPicker.getValue()];
                date += (" " + hour);
            }
            if (finalMinutePicker != null) {
                minute = finalMinutePicker.getDisplayedValues()[finalMinutePicker.getValue()];
                date += (":" + minute);
            }
            if (finalSecondPicker != null) {
                second = finalSecondPicker.getDisplayedValues()[finalSecondPicker.getValue()];
                date += (":" + second);
            }
            clickListener.onClick(v);
            selectListener.done(year, month, day, hour, minute, second, date);
        });
    }

    private TextViewX createTextView(Context context, String text) {
        return new TextViewX(context).id(generateViewId()).text(text);
    }

    private TextViewX createTextView(Context context, int id, String text) {
        return new TextViewX(context).id(id).text(text);
    }

    private NumberPickerX createPicker(Context context, String[] displayValues, int minIndex, int maxIndex, int curIndex) {
        return new NumberPickerX(context)
                .id(generateViewId())
                .displayValues(displayValues)
                .minValue(minIndex)
                .maxValue(maxIndex)
                .value(curIndex);
    }

    private NumberPickerX createPicker(Context context, int id, String[] displayValues, int minIndex, int maxIndex, int curIndex) {
        return new NumberPickerX(context)
                .id(id)
                .displayValues(displayValues)
                .minValue(minIndex)
                .maxValue(maxIndex)
                .value(curIndex);
    }

    private ViewX createDivider(Context context) {
        return new ViewX(context)
                .id(generateViewId())
                .backgroundColor(Color.LTGRAY);
    }

}
