/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthRecordActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 健康记录活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.adapter.FragmentViewPageAdapter;
import com.gaia.member.gaiatt.healthmanage.fragment.CalendarFragment;
import com.gaia.member.gaiatt.healthmanage.fragment.DietFragment;
import com.gaia.member.gaiatt.healthmanage.fragment.SleepFragment;
import com.gaia.member.gaiatt.healthmanage.fragment.SmokeFragment;
import com.gaia.member.gaiatt.healthmanage.fragment.SportFragment;
import com.gaia.member.gaiatt.healthmanage.fragment.WineFragment;
import com.gaia.member.gaiatt.ui.ChartViewPager;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaia.com.componentlibrary.custom.CustomIndicator;
/**
 * @ClassName: HealthRecordActivity
 *Description: 健康记录活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthRecordActivity extends FragmentActivity {
    //导航栏
    @Bind(R.id.shape_indicator)
    CustomIndicator shapeIndicator;
    //ViewPager
    @Bind(R.id.vPager)
    ChartViewPager vPager;
    //日历按钮
    @Bind(R.id.titlebar_calendar_ll)
    LinearLayout titlebarCalendarLl;
    //日历弹窗视图组
    @Bind(R.id.desp_dialog_layout)
    LinearLayout despDialogLayout;
    //日历图标
    @Bind(R.id.titlebar_calendar_img)
    ImageView titlebarCalendarImg;
    //日历弹窗
    @Bind(R.id.calendar_dialog_layout)
    LinearLayout calendarDialogLayout;
    //饮食选择器弹窗
    @Bind(R.id.diet_dialog_layout)
    LinearLayout dietDialogLayout;
    //返回按钮
    @Bind(R.id.titlebar_back_img)
    ImageView titlebarBackImg;
    private List<Fragment> listViews; // Tab页面列表
    private CalendarFragment calendarFragment;//日历
    private DietFragment dietFragment;//饮食

    //获取饮食选择器弹窗
    public LinearLayout getDietDialogLayout() {
        return dietDialogLayout;
    }
    //导航栏颜色
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_record);
        //绑定控件
        ButterKnife.bind(this);
        //设置日历按钮图标
        titlebarCalendarImg.setBackgroundResource(R.drawable.selector_icon_calendar);
        //初始化ViewPager
        initViewPager();

    }


    ;

    /**
     * 初始化ViewPager
     * */
    private void initViewPager() {
        //初始化导航栏标题
        List<String> titleList = new ArrayList<>();
        String[] titleString =GetResourcesUtils.getStringArrary(this,R.array.customIndicator_health_record);//导航栏数据源
        //设置标题
        for (int i = 0; i < titleString.length; i++) {
            titleList.add(titleString[i]);
        }
        listViews = new ArrayList<>();
        dietFragment = new DietFragment();//饮食
        listViews.add(new SportFragment());//运动
        listViews.add(new SleepFragment());//睡眠
        listViews.add(new SmokeFragment());//吸烟
        listViews.add(new WineFragment());//饮酒
        listViews.add(dietFragment);
        //适配和绑定导航栏
        vPager.setAdapter(new FragmentViewPageAdapter(getSupportFragmentManager(), listViews));
        vPager.setCurrentItem(0);
        shapeIndicator.setTitles(titleList);
        shapeIndicator.bindViewPager(vPager);
        vPager.setOnPageChangeListener(mOnPagerChageListener);

    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl() {
        onBackPressed();
    }

    //日历
    @OnClick(R.id.titlebar_calendar_ll)
    void titlebarCalendarLl() {
        //获取日历对象
        if (calendarFragment == null) {
            calendarFragment = new CalendarFragment();
            calendarFragment.setDespDialogLayout(despDialogLayout, mHandler);
        }
        //显示日历弹窗
        despDialogLayout.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //设置动画
        transaction.setCustomAnimations(R.anim.top_in, 0);
        transaction.replace(R.id.calendar_dialog_layout, calendarFragment);
        transaction.commit();
    }

    //获取日历数据
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CalendarFragment.CALENDARMSG:
                    if (dietFragment != null) {
                        //设置饮食界面日期
                        TextView tvDietCalendar = (TextView) dietFragment.getView().findViewById(R.id.tv_diet_calendar);
                        tvDietCalendar.setText(msg.obj.toString());
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * ViewPager改变监听
     * 设置滑动页面数据
     * */
    ViewPager.OnPageChangeListener mOnPagerChageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
        @Override
        public void onPageSelected(int position) {
            if (position == 4) {
                //滑动到饮食界面改变风格
                //选中字体为蓝色
                shapeIndicator.setSelectTextColor(R.color.blue);
                //没选中为灰色
                shapeIndicator.setUnSelectTextColor(R.color.text_index);
                //导航选中图标为蓝色
                shapeIndicator.setIndicatorColor(R.color.blue);
                //显示日历按钮
                titlebarCalendarLl.setVisibility(View.VISIBLE);
                //返回按钮为蓝色图标
                titlebarBackImg.setBackgroundResource(R.drawable.selector_icon_nav_back);
            } else {
                shapeIndicator.setSelectTextColor(android.R.color.white);
                shapeIndicator.setUnSelectTextColor(android.R.color.white);
                shapeIndicator.setIndicatorColor(android.R.color.white);
                //隐藏日历按钮
                titlebarCalendarLl.setVisibility(View.INVISIBLE);
                //但会按钮为白色
                titlebarBackImg.setBackgroundResource(R.drawable.selector_icon_plan_back);
            }
        }
    };
}
