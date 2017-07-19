/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: FragmentViewPageAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 导航栏数据源适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
/**
 * @ClassName: FragmentViewPageAdapter
 *Description: 导航栏数据源适配器
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class FragmentViewPageAdapter extends FragmentPagerAdapter {
    //数据源
    private List<Fragment> fragments;


    /**
     * 导航栏数据源适配器构造函数
     * @param  fm Fragment管理器
     * */
    public FragmentViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 导航栏数据源适配器构造函数
     * @param  fm Fragment管理器
     *    @param  fragments 数据源
     * */
    public FragmentViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    /**
     *获取列表子选项
     * @param  position 位置
     * */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * 获取数量
     * */
    @Override
    public int getCount() {
        return fragments.size();
    }
}
