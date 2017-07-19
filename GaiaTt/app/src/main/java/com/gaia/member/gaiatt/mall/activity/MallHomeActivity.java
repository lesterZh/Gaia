package com.gaia.member.gaiatt.mall.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.BaseFragmentPagerAdapter;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.mall.fragment.GoodsListFragment;
import com.gaia.member.gaiatt.mall.fragment.ServiceListFragment;
import com.gaia.member.gaiatt.mygaia.ui.CustomIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: MallHomeActivity
 * @Package com.gaia.member.gaiatt.mall
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/19 12:03
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: MallHomeActivity
 * Description: 商城的进入界面
 * @date 2016/6/7 12:03
 */
public class MallHomeActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView mTvTitle;
    @Bind(R.id.indicator_mall_home)
    CustomIndicator mIndicatorMallHome;
    @Bind(R.id.vp_mall_home)
    ViewPager mVpMallHome;

    List<Fragment> fragmentList;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_home);
        ButterKnife.bind(this);

        mTvTitle.setText("商城");
        //初始化viewPager
        fragmentList = new ArrayList<>();
        fragmentList.add(new GoodsListFragment());
        fragmentList.add(new ServiceListFragment());

        fragmentManager = getSupportFragmentManager();
        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(fragmentManager, fragmentList);
        mVpMallHome.setAdapter(fragmentPagerAdapter);
        mVpMallHome.setCurrentItem(0);

        //初始化indicator
        List<String> titles = new ArrayList<>();
        titles.add("商品");
        titles.add("服务");
        mIndicatorMallHome.setTitles(titles);
        mIndicatorMallHome.bindViewPager(mVpMallHome);
    }
}
