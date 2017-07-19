/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: AroundGaiaActivity
 * @Package com.gaia.member.gaiatt.gaiaclinic.activity
 * @Description: 身边盖亚模块活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.fragment.AroundGaiaListFragment;
import com.gaia.member.gaiatt.gaiaclinic.fragment.AroundGaiaMapFragment;
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
 * @ClassName: AroundGaiaActivity
 *Description: 身边盖亚模块活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */

public class AroundGaiaActivity extends FragmentActivity {

    //身边盖亚导航栏
    @Bind(R.id.around_gaia_indicator)
    CustomIndicator aroundGaiaIndicator;
    //身边盖亚ViewPager
    @Bind(R.id.vp_around_container)
    CustomViewPager vpAroundContainer;
    //fragment容器
    private List<Fragment> listFrag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_around_gaia);
        ButterKnife.bind(this);
        initVp();//初始化ViewPager
    }

    /**
     * 初始设置Indicator滑动数据
     */
    private void initVp() {
        //初始化列表
        listFrag = new ArrayList<Fragment>();
        //导航栏文案列表
        List<String> titleList = new ArrayList<String>();
        //获取导航文字设置
        String[] titleString= GetResourcesUtils.getStringArrary(this,R.array.customIndicator_aroundgaia);
        //判断对象非空
        if (titleString != null) {
            for (int i = 0; i < titleString.length; i++) {
                titleList.add(titleString[i]);
            }

        }
        //添加fragment
        listFrag.add(new AroundGaiaListFragment());//身边盖亚列表
        listFrag.add(new AroundGaiaMapFragment());//身边盖亚地图
        //ViewPager适配器
        FragmentViewPageAdapter adapter = new FragmentViewPageAdapter(getSupportFragmentManager(),
                     listFrag);
        vpAroundContainer.setAdapter(adapter);
        //设置当前默认导航位置
        vpAroundContainer.setCurrentItem(0);
        //防止viewPager滑动
        vpAroundContainer.setScanScroll(false);
        aroundGaiaIndicator.setTitles(titleList);
        //导航栏绑定ViewPager
        aroundGaiaIndicator.bindViewPager(vpAroundContainer);
    }

    /**
     * 返回按钮
     */
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl() {
        onBackPressed();
    }
    /**
     * 搜索按钮
     * */
    @OnClick(R.id.titlebar_search_ll)
    void titlebarSearchLl() {
        Intent intent = new Intent(AroundGaiaActivity.this, AroundSearchActivity.class);
        startActivity(intent);
    }

}
