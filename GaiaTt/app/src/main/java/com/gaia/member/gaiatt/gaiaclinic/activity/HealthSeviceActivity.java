/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthSeviceActivity
 * @Package com.gaia.member.gaiatt.gaiaclinic.activity
 * @Description: 健康服务活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.fragment.BuyFragment;
import com.gaia.member.gaiatt.gaiaclinic.fragment.HaveBuysFragment;
import com.gaia.member.gaiatt.healthmanage.adapter.FragmentViewPageAdapter;
import com.gaia.member.gaiatt.ui.CustomViewPager;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaia.com.componentlibrary.custom.CustomIndicator;
/**
 * @ClassName: HealthSeviceActivity
 *Description: 健康服务活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class HealthSeviceActivity extends FragmentActivity {

    //标题
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    //隐藏头边按钮
    @Bind(R.id.titlebar_search_ll)
    LinearLayout titlebarSearchLl;
    //导航栏
    @Bind(R.id.around_healthsevice_indicator)
    CustomIndicator aroundHealthseviceIndicator;
    //ViewPager
    @Bind(R.id.vp_healthsevice_container)
    ViewPager vpHealthseviceContainer;
    //fragment容器
    private List<Fragment> listFrag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_sevice);
        ButterKnife.bind(this);
        initView();//初始化视图
        initVp();//初始化ViewPager
    }


    protected void initView() {
        //设置标题
        titlebarTv.setText(GetResourcesUtils.getString(this,R.string.health_service_title));
        //隐藏标题栏右边按钮
        titlebarSearchLl.setVisibility(View.GONE);
        //初始化列表
        listFrag = new ArrayList<>();

    }

    /**
     * 初始设置Indicator滑动数据
     * */
    private void initVp(){
        //导航栏文案列表
        List<String> titleList=new ArrayList<>();
        String[] titleString= GetResourcesUtils
                .getStringArrary(this, R.array.customIndicator_healthservice);
        for (int i = 0; i <titleString.length ; i++) {
            titleList.add(titleString[i]);
        }
        //已购买
        listFrag.add(new HaveBuysFragment());
        //购买
        listFrag.add(new BuyFragment());
        FragmentViewPageAdapter adapter = new FragmentViewPageAdapter(
                getSupportFragmentManager(), listFrag);
        vpHealthseviceContainer.setAdapter(adapter);
        //当前视图为1
        vpHealthseviceContainer.setCurrentItem(1);
        aroundHealthseviceIndicator.setTitles(titleList);
        aroundHealthseviceIndicator.bindViewPager(vpHealthseviceContainer);
    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl(){
        onBackPressed();
    }
}
