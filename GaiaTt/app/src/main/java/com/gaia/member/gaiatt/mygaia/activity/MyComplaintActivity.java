package com.gaia.member.gaiatt.mygaia.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.BaseFragmentPagerAdapter;
import com.gaia.member.gaiatt.healthmanage.activity.HealthAddNewComplaintActivity;
import com.gaia.member.gaiatt.mygaia.fragment.MyComplaintAllFragment;
import com.gaia.member.gaiatt.mygaia.ui.CustomIndicator;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: CouponActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 我的投诉
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/6. 11:22
 * @version V1.0
 */
public class MyComplaintActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @Bind(R.id.indicator_my_complaint)
    CustomIndicator indicatorMyComplaint;
    @Bind(R.id.viewPagerMyComplaint)
    ViewPager viewPagerMyComplaint;

    private Activity mContext;
    List<Fragment> fragmentList;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_my_complaint);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvTitleTitleBar.setText("投诉");

        ivRightTitleBar.setVisibility(View.VISIBLE);
        ivRightTitleBar.setImageResource(R.drawable.icon_nav_new);
        ivRightTitleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新增投诉
                ActivityUtils.gotoIntent(mContext, HealthAddNewComplaintActivity.class);
            }
        });

        //初始化viewPager
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyComplaintAllFragment());
        fragmentList.add(new MyComplaintAllFragment());
        fragmentList.add(new MyComplaintAllFragment());

        fragmentManager = getSupportFragmentManager();
        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(fragmentManager, fragmentList);
        viewPagerMyComplaint.setAdapter(fragmentPagerAdapter);
        viewPagerMyComplaint.setCurrentItem(0);

        //初始化indicator
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("处理中");
        titles.add("已完成");
        indicatorMyComplaint.setTitles(titles);
        indicatorMyComplaint.bindViewPager(viewPagerMyComplaint);
    }
}
