package com.zbase.view.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zbase.R;
import com.zbase.entity.NumberPickerValue;
import com.zbase.interfaces.DateSelectListener;
import com.zbase.util.DateTimeUtils;
import com.zbase.x.ColorX;
import com.zbase.x.lp.ConstraintLayoutParamsX;
import com.zbase.x.view.NumberPickerX;
import com.zbase.x.view.TextViewX;
import com.zbase.x.view.ViewX;

import java.util.Calendar;

public final class DateTimePickDialogView extends DialogView<DateTimePickDialogView> {

    public DateTimePickDialogView(@NonNull Context context) {
        super(context);
        /*
         * View id
         */
        final int YEAR_PICKER_ID = getAboveBtnViewId();
        final int MONTH_PICKER_ID = generateViewId();
        final int DAY_PICKER_ID = generateViewId();
        final int DAY_TV_ID = generateViewId();
        final int HOUR_PICKER_ID = generateViewId();
        final int MINUTE_PICKER_ID = generateViewId();
        final int SECOND_PICKER_ID = generateViewId();
        setTag(R.id.dialog_month_picker, MONTH_PICKER_ID);
        setTag(R.id.dialog_day_picker, DAY_PICKER_ID);
        setTag(R.id.dialog_day_tv, DAY_TV_ID);
        setTag(R.id.dialog_hour_picker, HOUR_PICKER_ID);
        setTag(R.id.dialog_minute_picker, MINUTE_PICKER_ID);
        setTag(R.id.dialog_second_picker, SECOND_PICKER_ID);

        /*
         * Year
         */
        NumberPickerValue yearValue = DateTimeUtils.getYears(2);
        NumberPickerX yearPicker = createPicker(context, YEAR_PICKER_ID, yearValue.getValues(), 4, yearValue.getCurIndex());
        TextViewX yearTv = createTextView(context, generateViewId(), "年");

        /*
         * Month
         */
        NumberPickerValue monthValue = DateTimeUtils.getMonths();
        NumberPickerX monthPicker = createPicker(context, MONTH_PICKER_ID, monthValue.getValues(), 11, monthValue.getCurIndex());
        TextViewX monthTv = createTextView(context, generateViewId(), "月");

        /*
         * Day
         */
        NumberPickerValue dayValue = DateTimeUtils.getDays();
        NumberPickerX dayPicker = createPicker(context, DAY_PICKER_ID, dayValue.getValues(), 30, dayValue.getCurIndex());
        TextViewX dayTv = createTextView(context, DAY_TV_ID, "日");

        /*
         * Add year,month,day
         */
        int titleDividerId = generateViewId();
        this
                .addChildView(new ViewX(context)
                                .id(titleDividerId)
                                .backgroundColor(ColorX.DIVIDER)
                        , new ConstraintLayoutParamsX(ConstraintLayoutParamsX.MATCH_PARENT, 1).top2bottom(getTitleId()))
                .addChildView(yearPicker
                        , new ConstraintLayoutParamsX()
                                .width(0)
                                .weightHorizontal(1)
                                .chainStyleHorizontal(ConstraintLayoutParamsX.CHAIN_SPREAD_INSIDE)
                                .top2bottom(titleDividerId)
                                .start2startParent()
                                .end2start(yearTv.getId()))
                .addChildView(yearTv,
                        new ConstraintLayoutParamsX()
                                .top2top(YEAR_PICKER_ID)
                                .bottom2bottom(YEAR_PICKER_ID)
                                .start2end(YEAR_PICKER_ID)
                                .end2start(MONTH_PICKER_ID))
                .addChildView(monthPicker
                        , new ConstraintLayoutParamsX()
                                .width(0)
                                .weightHorizontal(1)
                                .top2top(YEAR_PICKER_ID)
                                .bottom2bottom(YEAR_PICKER_ID)
                                .start2end(yearTv.getId())
                                .end2start(monthTv.getId()))
                .addChildView(monthTv
                        , new ConstraintLayoutParamsX()
                                .top2top(YEAR_PICKER_ID)
                                .bottom2bottom(YEAR_PICKER_ID)
                                .start2end(MONTH_PICKER_ID)
                                .end2start(DAY_PICKER_ID))
                .addChildView(dayPicker
                        , new ConstraintLayoutParamsX()
                                .width(0)
                                .weightHorizontal(1)
                                .top2top(YEAR_PICKER_ID)
                                .bottom2bottom(YEAR_PICKER_ID)
                                .start2end(monthTv.getId())
                                .end2start(DAY_TV_ID))
                .addChildView(dayTv
                        , new ConstraintLayoutParamsX()
                                .top2top(YEAR_PICKER_ID)
                                .bottom2bottom(YEAR_PICKER_ID)
                                .start2end(DAY_PICKER_ID));
    }

    /**
     * @param limit Use java.util.Calendar class's:
     *              <p>
     *              DATE
     *              HOUR
     *              MINUTE
     *              SECOND
     *              <p>
     *              constants to limit date select scope
     */
    public DateTimePickDialogView limit(int limit) {
        final int YEAR_PICKER_ID = getAboveBtnViewId();
        final int DAY_TV_ID = getViewId(R.id.dialog_day_tv);
        final int HOUR_PICKER_ID = getViewId(R.id.dialog_hour_picker);
        final int HOUR_TV_ID = generateViewId();
        final int MINUTE_PICKER_ID = getViewId(R.id.dialog_minute_picker);
        final int MINUTE_TV_ID = generateViewId();
        final int SECOND_PICKER_ID = getViewId(R.id.dialog_second_picker);
        final int SECOND_TV_ID = generateViewId();
        switch (limit) {
            case Calendar.SECOND: {
                NumberPickerValue secondValue = DateTimeUtils.getSeconds();
                NumberPickerX secondPicker = createPicker(getContext(), SECOND_PICKER_ID, secondValue.getValues(), 59, secondValue.getCurIndex());
                TextViewX secondTv = createTextView(getContext(), SECOND_TV_ID, "秒");
                this
                        .addChildView(secondPicker
                                , new ConstraintLayoutParamsX()
                                        .width(0)
                                        .weightHorizontal(1)
                                        .start2end(MINUTE_TV_ID)
                                        .end2start(SECOND_TV_ID)
                                        .top2top(YEAR_PICKER_ID)
                                        .bottom2bottom(YEAR_PICKER_ID))
                        .addChildView(secondTv
                                , new ConstraintLayoutParamsX()
                                        .top2top(YEAR_PICKER_ID)
                                        .bottom2bottom(YEAR_PICKER_ID)
                                        .start2end(SECOND_PICKER_ID)
                                        .end2endParent());
            }
            case Calendar.MINUTE: {
                NumberPickerValue minuteValue = DateTimeUtils.getMinutes();
                NumberPickerX minutePicker = createPicker(getContext(), MINUTE_PICKER_ID, minuteValue.getValues(), 59, minuteValue.getCurIndex());
                TextViewX minuteTv = createTextView(getContext(), MINUTE_TV_ID, "分");
                ConstraintLayoutParamsX minuteTvLp;
                this
                        .addChildView(minutePicker
                                , new ConstraintLayoutParamsX()
                                        .width(0)
                                        .weightHorizontal(1)
                                        .start2end(HOUR_TV_ID)
                                        .end2start(MINUTE_TV_ID)
                                        .top2top(YEAR_PICKER_ID)
                                        .bottom2bottom(YEAR_PICKER_ID))
                        .addChildView(minuteTv
                                , minuteTvLp = new ConstraintLayoutParamsX()
                                        .top2top(YEAR_PICKER_ID)
                                        .bottom2bottom(YEAR_PICKER_ID)
                                        .start2end(MINUTE_PICKER_ID));
                if (findViewById(SECOND_PICKER_ID) == null) {
                    minuteTvLp.end2endParent();
                } else {
                    minuteTvLp.end2start(SECOND_PICKER_ID);
                }
            }
            case Calendar.HOUR: {
                NumberPickerValue hourValue = DateTimeUtils.getHours();
                NumberPickerX hourPicker = createPicker(getContext(), HOUR_PICKER_ID, hourValue.getValues(), 23, hourValue.getCurIndex());
                TextViewX hourTv = createTextView(getContext(), HOUR_TV_ID, "时");
                ConstraintLayoutParamsX hourTvLp;
                this
                        .addChildView(hourPicker
                                , new ConstraintLayoutParamsX()
                                        .width(0)
                                        .weightHorizontal(1)
                                        .start2end(DAY_TV_ID)
                                        .end2start(HOUR_TV_ID)
                                        .top2top(YEAR_PICKER_ID)
                                        .bottom2bottom(YEAR_PICKER_ID))
                        .addChildView(hourTv
                                , hourTvLp = new ConstraintLayoutParamsX()
                                        .top2top(YEAR_PICKER_ID)
                                        .bottom2bottom(YEAR_PICKER_ID)
                                        .start2end(HOUR_PICKER_ID));
                if (findViewById(MINUTE_PICKER_ID) == null) {
                    hourTvLp.end2endParent();
                } else {
                    hourTvLp.end2start(MINUTE_PICKER_ID);
                }
                ((ConstraintLayoutParamsX) findViewById(DAY_TV_ID).getLayoutParams()).end2start(HOUR_PICKER_ID);
                break;
            }
            default:
                ((ConstraintLayoutParamsX) findViewById(DAY_TV_ID).getLayoutParams()).end2endParent();
                break;
        }
        return this;
    }

    public DateTimePickDialogView listener(OnClickListener btnClickListener, DateSelectListener selectListener) {
        enablePositiveAndNegativeButton("取消"
                , "确定"
                , btnClickListener
                , v -> {
                    /*
                     * Find picker
                     */
                    NumberPickerX yearPicker = (NumberPickerX) findViewById(getAboveBtnViewId());
                    NumberPickerX monthPicker = (NumberPickerX) findViewById(getViewId(R.id.dialog_month_picker));
                    NumberPickerX dayPicker = (NumberPickerX) findViewById(getViewId(R.id.dialog_day_picker));
                    NumberPickerX hourPicker = (NumberPickerX) findViewById(getViewId(R.id.dialog_hour_picker));
                    NumberPickerX minutePicker = (NumberPickerX) findViewById(getViewId(R.id.dialog_minute_picker));
                    NumberPickerX secondPicker = (NumberPickerX) findViewById(getViewId(R.id.dialog_second_picker));

                    /*
                     * Date
                     */
                    String year = yearPicker.getDisplayedValues()[yearPicker.getValue()];
                    String month = monthPicker.getDisplayedValues()[monthPicker.getValue()];
                    String day = dayPicker.getDisplayedValues()[dayPicker.getValue()];
                    String date = year + "-" + month + "-" + day;
                    String hour = "";
                    String minute = "";
                    String second = "";

                    /*
                     * Time
                     */
                    if (hourPicker != null) {
                        hour = hourPicker.getDisplayedValues()[hourPicker.getValue()];
                        date += (" " + hour);
                    }
                    if (minutePicker != null) {
                        minute = minutePicker.getDisplayedValues()[minutePicker.getValue()];
                        date += (":" + minute);
                    }
                    if (secondPicker != null) {
                        second = secondPicker.getDisplayedValues()[secondPicker.getValue()];
                        date += (":" + second);
                    }

                    /*
                     * Call back
                     */
                    btnClickListener.onClick(v);
                    selectListener.done(year, month, day, hour, minute, second, date);
                });
        return this;
    }

    private TextViewX createTextView(Context context, int id, String text) {
        return new TextViewX(context).id(id).text(text);
    }

    private NumberPickerX createPicker(Context context, int id, String[] displayValues, int maxIndex, int curIndex) {
        return new NumberPickerX(context)
                .id(id)
                .displayValues(displayValues)
                .minValue(0)
                .maxValue(maxIndex)
                .value(curIndex);
    }

}
