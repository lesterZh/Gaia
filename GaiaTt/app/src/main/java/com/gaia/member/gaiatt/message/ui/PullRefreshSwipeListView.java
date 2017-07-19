package com.gaia.member.gaiatt.message.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gaia.member.gaiatt.interfaces.PullOrLoadAbleInterface;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: PullRefreshSwipeListView
 * @Package com.gaia.member.gaiatt.message.ui
 * @Description: 可下拉刷新 上拉加载更多 滑动删除的listview
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/6 13:50
 */
public class PullRefreshSwipeListView extends SwipeMenuListView implements PullOrLoadAbleInterface {
    public PullRefreshSwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canRefresh()
    {
        if (getCount() == 0)
        {
            return true;
        } else if (getFirstVisiblePosition() == 0
                && getChildAt(0).getTop() >= 0)
        {
            return true;
        } else
            return false;
    }

    @Override
    public boolean canLoadMore()
    {
        if (getCount() == 0)
        {
            return true;
        } else if (getLastVisiblePosition() == (getCount() - 1))
        {
            if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
                    && getChildAt(
                    getLastVisiblePosition()
                            - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
                return true;
        }
        return false;
    }

}
