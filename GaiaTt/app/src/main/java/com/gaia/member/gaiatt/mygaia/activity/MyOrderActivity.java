package com.gaia.member.gaiatt.mygaia.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.BaseFragmentPagerAdapter;
import com.gaia.member.gaiatt.mygaia.fragment.MyOrderServiceFragment;
import com.gaia.member.gaiatt.mygaia.ui.CustomIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: MyOrderActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 我的盖亚_我的预约 包含 服务 和 医生2个fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/6. 11:22
 * @version V1.0
 */
public class MyOrderActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @Bind(R.id.indicator_my_order)
    CustomIndicator indicatorMyOrder;
    @Bind(R.id.vp_my_order)
    ViewPager viewPagerMyOrder;

    private Activity mContext;
    List<Fragment> fragmentList;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        tvTitleTitleBar.setText("我的预约");
        //标题了右侧的图标设定



        //初始化viewPager
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyOrderServiceFragment());
        fragmentList.add(new MyOrderServiceFragment());

        fragmentManager = getSupportFragmentManager();
        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(fragmentManager, fragmentList);
        viewPagerMyOrder.setAdapter(fragmentPagerAdapter);
        viewPagerMyOrder.setCurrentItem(0);

        //初始化indicator
        List<String> titles = new ArrayList<>();
        titles.add("服务");
        titles.add("医生");
        indicatorMyOrder.setTitles(titles);
        indicatorMyOrder.bindViewPager(viewPagerMyOrder);
    }
}
