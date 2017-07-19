/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: AutoPagerAdapter
 * @Package com.gaia.member.gaiatt.gaiaclinic.adapter
 * @Description: 自动跳转ViewPager适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.adapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AutoPagerAdapter
 *Description: 自动跳转ViewPager适配器
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class AutoPagerAdapter extends PagerAdapter {
    public List<View> list;
    public AutoPagerAdapter(List<View> list) {
        super();
        this.list=list;
    }

    @Override
    public int getCount() {
        // 轮播
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View  view = list.get(position % (list.size()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = list.get(position % (list.size()));
        container.removeView(view);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
