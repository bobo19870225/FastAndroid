package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityCalendarBinding;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.CalendarVM;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * Created by Sampson on 2019-05-05.
 * FastAndroid
 */
public class CalendarActivity extends MVVMActivity<CalendarVM, ActivityCalendarBinding> {
    private MaterialCalendarView materialCalendarView;

    @NonNull
    @Override
    protected CalendarVM createdViewModel() {
        return ViewModelProviders.of(this).get(CalendarVM.class);
    }

    @Override
    protected void setView() {
        materialCalendarView = mViewDataBinding.calendarView;
        materialCalendarView.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"}); //设置周的文本
        materialCalendarView.setTitleFormatter(new DateFormatTitleFormatter(new SimpleDateFormat("yyy年MM月", Locale.CHINA)));//设置当前标题日期格式
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);//默认单选模式  这里选择多选模式
        materialCalendarView.setDateSelected(CalendarDay.from(2019, 4, 25), true);//设置当前日期为选中状态
        materialCalendarView.setTileHeightDp(32);
        final Calendar min = Calendar.getInstance();
        min.set(Calendar.DAY_OF_MONTH, 1);
        final Calendar max = Calendar.getInstance();
        max.set(Calendar.DAY_OF_MONTH, 31);
        materialCalendarView.state().edit()
                .setMinimumDate(min)
                .setMaximumDate(max)
                .commit();
        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                List<CalendarDay> selectedDates = materialCalendarView.getSelectedDates();
                for (CalendarDay d : selectedDates
                ) {
                    if (d.getMonth() == day.getMonth() && d.getDay() == day.getDay())
                        return false;
                }
                return true;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.setBackgroundDrawable(getApplication().getResources().getDrawable(R.drawable.bg_calendar));
                view.setDaysDisabled(true);
            }
        });
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_calendar;
    }
}
