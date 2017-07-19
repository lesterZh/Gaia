package com.gaia.member.gaiatt.healthmanage.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gaia.member.gaiatt.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: CalendarFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 日历选择器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class CalendarFragment extends Fragment implements OnDateSelectedListener, OnMonthChangedListener {

    private View view;
    @Bind(R.id.calendarView)
    MaterialCalendarView calendarView;

    private LinearLayout despDialogLayout;//弹窗布局
    private Handler   dateHandler;//获取日历信息

    public static  final int CALENDARMSG=29;//handler What

    public LinearLayout getDespDialogLayout() {
        return despDialogLayout;
    }

    public void setDespDialogLayout(LinearLayout despDialogLayout,Handler   dateHandler) {
        this.despDialogLayout = despDialogLayout;
        this.dateHandler = dateHandler;
    }

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.plan_calendar_fragment, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    //初始化日历视图
    private void initView() {

        Calendar calendar = Calendar.getInstance();
        calendarView.setSelectedDate(calendar.getTime());

        /*calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1);
        calendarView.setMinimumDate(calendar.getTime());

        calendar.set(calendar.get(Calendar.YEAR) + 2, Calendar.OCTOBER, 31);
        calendarView.setMaximumDate(calendar.getTime());*/

        calendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                return day.getYear() + "年" + (day.getMonth()+1) + "月";
            }
        });
        calendarView.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"});
        calendarView.setBackgroundColor(Color.WHITE);
        calendarView.setWeekDayTextAppearance(R.style.WeekDayTextAppearance);
        calendarView.setHeaderTextAppearance(R.style.TiTleTextAppearance);
        calendarView.setArrowColor(Color.parseColor("#88666666"));
        calendarView.setOnDateChangedListener(this);
        calendarView.setOnMonthChangedListener(this);
    }



    //日期选择监听
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction translaction = manager.beginTransaction();
        translaction.remove(this);
        translaction.commit();
        despDialogLayout.setVisibility(View.GONE);
        Message msg=Message.obtain();
        msg.what=CALENDARMSG;
        msg.obj=getSelectedDatesString();
        dateHandler.sendMessage(msg);
    }
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
    }

    private String getSelectedDatesString() {
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }
}
