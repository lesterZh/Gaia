package com.gaia.member.gaiatt.makeorder.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.BaseFragmentPagerAdapter;
import com.gaia.member.gaiatt.makeorder.fragment.MakeOrderDoctorFragment;
import com.gaia.member.gaiatt.makeorder.fragment.MakeOrderServiceFragment;
import com.gaia.member.gaiatt.mygaia.ui.CustomIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: MakeOrderActivity
 * @Package com.gaia.member.gaiatt.makeorder.activity
 * @Description:   预约模块主activity
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/13.
 */
public class MakeOrderActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @Bind(R.id.indicator_make_order)
    CustomIndicator indicatorMakeOrder;
    @Bind(R.id.vp_make_order)
    ViewPager vpMakeOrder;

    private List<String> titles = new ArrayList<>();
    List<Fragment> fragmentList;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        ButterKnife.bind(this);


        tvTitleTitleBar.setText("预约");
        ivRightTitleBar.setImageResource(R.drawable.icon_search_selector);
        ivRightTitleBar.setVisibility(View.VISIBLE);

        //初始化viewPager
        fragmentList = new ArrayList<>();
        fragmentList.add(new MakeOrderServiceFragment());
        fragmentList.add(new MakeOrderDoctorFragment());

        fragmentManager = getSupportFragmentManager();
        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(fragmentManager, fragmentList);
        vpMakeOrder.setAdapter(fragmentPagerAdapter);
        vpMakeOrder.setCurrentItem(0);


        titles.add("服务");
        titles.add("医生");
        indicatorMakeOrder.setTitles(titles);
        indicatorMakeOrder.bindViewPager(vpMakeOrder);

    }


}
