/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: ConsultingActivity
 * @Package com.gaia.member.gaiatt.gaiaclinic.activity
 * @Description: 健康咨询活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.fragment.HaveConsultingFragment;
import com.gaia.member.gaiatt.gaiaclinic.fragment.MyConsultingByAreaFragment;
import com.gaia.member.gaiatt.healthmanage.adapter.FragmentViewPageAdapter;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaia.com.componentlibrary.custom.CustomIndicator;

/**
 * @ClassName: ConsultingActivity
 *Description: 健康咨询活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class ConsultingActivity extends FragmentActivity {

    //咨询界面导航栏
    @Bind(R.id.consulting_indicator)
    CustomIndicator consultingIndicator;
    //ViewPager
    @Bind(R.id.vp_consulting_container)
    ViewPager vpConsultingContainer;
    //fragment容器
    private List<Fragment> listFrag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulting);
        ButterKnife.bind(this);
        initVp();//初始化ViewPager
    }

    /**
     * 初始设置Indicator滑动数据
     * */
    private void initVp() {
        //初始化列表
        listFrag = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        //导航栏设置
        String[] titleString= GetResourcesUtils.getStringArrary(this,R.array.customIndicator_consulting);
        for (int i = 0; i < titleString.length; i++) {
            titleList.add(titleString[i]);
        }
        //地区
        listFrag.add(new MyConsultingByAreaFragment());
        //已咨询
        listFrag.add(new HaveConsultingFragment());
        //listFrag.add(new ConversationFragment());//界面要崩溃
        FragmentViewPageAdapter adapter = new FragmentViewPageAdapter(getSupportFragmentManager(),
                    listFrag);
        vpConsultingContainer.setAdapter(adapter);
        //初始位置
        vpConsultingContainer.setCurrentItem(0);
        consultingIndicator.setTitles(titleList);
        consultingIndicator.bindViewPager(vpConsultingContainer);
    }




    //返回按钮
    @OnClick(R.id.connsulting_detail_back_ll)
    void connsultingDetailBackLl() {
        onBackPressed();
    }


}
