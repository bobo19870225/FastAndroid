package com.zaomeng.zaomeng.view;

import android.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityCalendarBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SignInBean;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view.custom_view.CircleImageView;
import com.zaomeng.zaomeng.view_model.CalendarVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;


/**
 * Created by Sampson on 2019-05-05.
 * FastAndroid
 */
public class CalendarActivity extends MVVMActivity<CalendarVM, ActivityCalendarBinding> {
    private MaterialCalendarView materialCalendarView;
    private AlertDialog alertDialog;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;

    @NonNull
    @Override
    protected CalendarVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(CalendarVM.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void setView() {
        LiveData<List<LoginBean>> user = mViewModel.getUser();
        if (user != null)
            user.observe(this, loginBeans -> {
            LoginBean loginBean = loginBeans.get(0);
            mViewModel.ldUserName.setValue(loginBean.getShortName());
            CircleImageView iconUser = mViewDataBinding.iconUser;
            Glide.with(iconUser).load(loginBean.getAvatarURL()).into(iconUser);
        });
        setDialog();
        setCalendar();
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "signIn":
//                    showSignInDialog();
                    break;
            }
        });
        mViewModel.ldSignIn.observe(this, beanResource -> {
            if (beanResource.isSuccess()) {
                Bean<String> resource = beanResource.getResource();
//                showSignInDialog();//for test
                if (resource != null && resource.getHeader().getCode() == 0) {
                    showSignInDialog();
                } else {
                    if (resource != null) {
                        toast(resource.getHeader().getMsg());
                    }
                }
            } else {
                Throwable error = beanResource.getError();
                if (error != null) {
                    toast(error.toString());
                }
            }

        });

        mViewModel.getSignInList().observe(this, pageBeanResource -> {
            PageDataBean<SignInBean> signInBeanPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class, null);

                }

                @Override
                public void reLoad() {
                    mViewModel.getSignInList();
                }
            }, this);
            if (signInBeanPageDataBean != null) {
                List<SignInBean> rows = signInBeanPageDataBean.getRows();
                for (SignInBean s : rows) {
                    String operateTimeStr = s.getOperateTimeStr();
                    String[] split = operateTimeStr.split(" ");
                    String[] date = split[0].split("-");
                    if (date[0] != null && date[1] != null && date[2] != null) {
                        CalendarDay calendarDay = CalendarDay.from(Integer.valueOf(date[0]), Integer.valueOf(date[1]) - 1, Integer.valueOf(date[2]));
                        materialCalendarView.setDateSelected(calendarDay, true);//设置当前日期为选中状态
                    }
                }
                noticeCalendar();
            }

        });

    }

    private void setDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_signin, null, false);
        alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }
        Button ok = view.findViewById(R.id.ok);
        ok.setOnClickListener(v -> {
            if (alertDialog.isShowing())
                alertDialog.dismiss();
            materialCalendarView.setDateSelected(CalendarDay.today(), true);
            noticeCalendar();
        });
    }

    private void noticeCalendar() {

        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                List<CalendarDay> selectedDates = materialCalendarView.getSelectedDates();
                for (CalendarDay d : selectedDates
                ) {
                    if (d.getMonth() == day.getMonth() && d.getDay() == day.getDay())
                        return true;
                }
                return false;
            }

            @Override
            public void decorate(DayViewFacade view) {
//                view.setSelectionDrawable(getApplication().getResources().getDrawable(R.drawable.bg_calendar_select));
                view.setBackgroundDrawable(getApplication().getResources().getDrawable(R.drawable.bg_calendar_select));
                view.setDaysDisabled(true);
            }
        });

    }

    private void showSignInDialog() {
        if (!alertDialog.isShowing())
            alertDialog.show();
    }

    private void setCalendar() {
        materialCalendarView = mViewDataBinding.calendarView;
//        materialCalendarView.setVisibility(View.GONE);
        materialCalendarView.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"}); //设置周的文本
        materialCalendarView.setTitleFormatter(new DateFormatTitleFormatter(new SimpleDateFormat("yyy年MM月", Locale.CHINA)));//设置当前标题日期格式
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);//默认单选模式  这里选择多选模式
        materialCalendarView.setTileHeightDp(32);
        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return true;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.setSelectionDrawable(getApplication().getResources().getDrawable(R.color.transparent));
                view.setBackgroundDrawable(getApplication().getResources().getDrawable(R.drawable.bg_calendar));
                view.setDaysDisabled(true);
            }
        });
        final Calendar min = Calendar.getInstance();
        min.set(Calendar.DAY_OF_MONTH, 1);
        final Calendar max = Calendar.getInstance();
        max.set(Calendar.DAY_OF_MONTH, 31);
        materialCalendarView.state().edit()
                .setMinimumDate(min)
                .setMaximumDate(max)
                .commit();
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "签到";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_calendar;
    }
}
