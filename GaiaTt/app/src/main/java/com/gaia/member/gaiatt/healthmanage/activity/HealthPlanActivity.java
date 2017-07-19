/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthPlanActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 干预计划列表活动界面
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
import com.gaia.member.gaiatt.healthmanage.fragment.HealthViewPageFragment;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaia.com.componentlibrary.custom.CustomIndicator;
/**
 * @ClassName: HealthPlanActivity
 *Description: 干预计划列表活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthPlanActivity extends FragmentActivity {


    //标题
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    //标题栏右边图标
    @Bind(R.id.titlebar_search_img)
    ImageView titlebarSearchImg;
    //导航栏
    @Bind(R.id.health_indicator)
    CustomIndicator healthIndicator;
    //ViewPager
    @Bind(R.id.health_plan_vp)
    ViewPager healthPlanVp;
    //日历弹窗
    @Bind(R.id.desp_dialog_layout)
    LinearLayout despDialogLayout;
    //fragment列表
    private List<Fragment> listFrag;
    //日历fragment
    private CalendarFragment calendarFragment;
    //ViewPager适配器
    private  FragmentViewPageAdapter adapter;
    //导航栏数组
    String[] titleString;
    //导航栏列表
    List<String>  titleList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_plan);
        //绑定控件
        ButterKnife.bind(this);
        //设置标题
        titlebarTv.setText(GetResourcesUtils.getString(this, R.string.health_plan_title));
        //设置日历图标
        titlebarSearchImg.setBackgroundResource(R.drawable.selector_icon_calendar);
        //初始化数据
        initData();
    }
    /**
     * 初始化列表数据
     * */
    void initData() {
        //初始化fragment列表
        if (listFrag == null) {
            listFrag = new ArrayList<Fragment>();
        }
        //初始化导航栏标题列表
        if (titleList == null) {
            titleList=new ArrayList<String>();
        }
        //导航栏数据源
        titleString= GetResourcesUtils.getStringArrary(this,R.array.customIndicator_health_plan);
        for (int i = 0; i <titleString.length; i++) {
            //设置导航栏数据源
            titleList.add(titleString[i]);
            HealthViewPageFragment fragment = new HealthViewPageFragment();
            fragment.setExtraInfo(i);
            //ViewPager数据源
            listFrag.add(fragment);
        }
        //适配数据
        adapter = new FragmentViewPageAdapter(getSupportFragmentManager(), listFrag);
        healthPlanVp.setAdapter(adapter);
        //设置当前显示子视图
        healthPlanVp.setCurrentItem(0);
        //设置导航栏标题
        healthIndicator.setTitles(titleList);
        healthIndicator.bindViewPager(healthPlanVp);
    }



    //日历
    @OnClick(R.id.titlebar_search_ll)
    void planCalendarFragment() {
        //初始化日历
        if (calendarFragment == null) {
            calendarFragment = new CalendarFragment();
            calendarFragment.setDespDialogLayout(despDialogLayout,mHandler);
        }
        //显示日历
        despDialogLayout.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //加载动画
        transaction.setCustomAnimations(R.anim.top_in, 0);
        transaction.replace(R.id.calendar_dialog_layout, calendarFragment);
        transaction.commit();
    }

    /**
     * 获取日历返回日期
     * */
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case CalendarFragment.CALENDARMSG:
                    String currentDate=(String) msg.obj;
                    setListDataByDate(currentDate);
                    break;
                    default:
                        break;
                }
            }
        };

    /**
     * 通过日历选择设置列表数据
     * @param  currentDate 当前日期
     * */
    private void setListDataByDate(String currentDate) {
        currentDate=currentDate.replace("年","-");
        currentDate=currentDate.replace("月","-");
        currentDate=currentDate.replace("日","");
        //初始化数据
    }


    //返回
    @OnClick(R.id.titlebar_back_ll)
    void healthPlanBack() {
     onBackPressed();
    }
}
