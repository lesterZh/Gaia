package com.gaia.member.gaiatt.makeorder.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.bean.MakeOrderServiceBean;
import com.gaia.member.gaiatt.makeorder.bean.OrderDoctorInfoBean;
import com.gaia.member.gaiatt.utils.UiUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayView;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: ShowServiceInfoActivity
 * @Package com.gaia.member.gaiatt.makeorder.activity
 * @Description:  显示可预约的服务信息
 *
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/14.
 */
public class ShowServiceInfoActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @Bind(R.id.iv_service_image)
    ImageView ivServiceImage;
    @Bind(R.id.tv_service_name)
    TextView tvServiceName;
    @Bind(R.id.tv_service_location)
    TextView tvServiceLocation;
    @Bind(R.id.tv_order_price)
    TextView tvOrderPrice;
    @Bind(R.id.tv_service_desc)
    TextView tvServiceDesc;
    @Bind(R.id.tv_area_limit)
    TextView tvAreaLimit;
    @Bind(R.id.calendar_order_select)
    MaterialCalendarView calendarOrderSelect;

    List<CalendarDay> orderDayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service_info);
        ButterKnife.bind(this);

        tvTitleTitleBar.setText("预约服务");
        String serviceDesc = "新生儿畸形 新生儿感冒发烧 老人痴呆 新生儿畸形 新生儿感冒发烧 老人痴呆 " ;
        tvServiceDesc.setText(Html.fromHtml("<font color='#333333'>服务内容：</font>"+serviceDesc));

        initCalendar();

    }

    @Override
    protected void onStart() {
        super.onStart();
        DayView.setMarkedDay(true);//在此次开启可预约功能
    }

    @Override
    protected void onStop() {
        super.onStop();
        DayView.setMarkedDay(false);//在此次关闭可预约功能
    }

    private void initCalendar() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        orderDayList.add(CalendarDay.from(mCalendar));
        mCalendar.add(Calendar.DAY_OF_MONTH, -7);
        orderDayList.add(CalendarDay.from(mCalendar));
        mCalendar.add(Calendar.DAY_OF_MONTH, 3);
        orderDayList.add(CalendarDay.from(mCalendar));
        mCalendar.add(Calendar.DAY_OF_MONTH, 6);
        orderDayList.add(CalendarDay.from(mCalendar));
        mCalendar.add(Calendar.DAY_OF_MONTH, 2);
        orderDayList.add(CalendarDay.from(mCalendar));
        mCalendar.add(Calendar.MONTH, 1);
        orderDayList.add(CalendarDay.from(mCalendar));
        mCalendar.add(Calendar.MONTH, -1);
        orderDayList.add(CalendarDay.from(mCalendar));
        mCalendar.add(Calendar.MONTH, 2);
        orderDayList.add(CalendarDay.from(mCalendar));

        DayView.markedDayList = orderDayList;
        calendarOrderSelect.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                return day.getYear() + "年" + day.getMonth() + "月";
            }
        });
        calendarOrderSelect.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"});
        calendarOrderSelect.setBackgroundColor(Color.WHITE);
        calendarOrderSelect.setSelectionColor(Color.WHITE);
        calendarOrderSelect.setArrowColor(Color.parseColor("#88666666"));
        calendarOrderSelect.setWeekDayTextAppearance(R.style.WeekDayTextAppearance);
        calendarOrderSelect.setHeaderTextAppearance(R.style.TiTleTextAppearance);
        calendarOrderSelect.setDateTextAppearance(R.style.DateTextAppearance);
        calendarOrderSelect.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (orderDayList.contains(date)) {
//                    UiUtils.showToast(mContext, "进入预约");
                    SubmitOrderActivity.actionStart(mContext, date.getYear()
                            + "-" + (date.getMonth() + 1) + "-" + date.getDay(), new OrderDoctorInfoBean());
                } else {
                    UiUtils.showToast(mContext, "仅可选可预约日期");
                }
            }
        });



    }


    /**
     * 本acitivity的入口调用方法
     *
     * @param context
     * @param bean 传递的参数
     */
    public static void actionStart(Context context, MakeOrderServiceBean bean) {
        Intent intent = new Intent(context, ShowServiceInfoActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
    }
}
