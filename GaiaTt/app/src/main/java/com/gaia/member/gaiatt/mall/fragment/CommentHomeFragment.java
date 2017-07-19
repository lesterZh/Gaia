package com.gaia.member.gaiatt.mall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.BaseFragmentPagerAdapter;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.mall.bean.CommentBean;
import com.gaia.member.gaiatt.mygaia.ui.CustomIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: CommentHomeFragment
 * @Package com.gaia.member.gaiatt.mall
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/6/20 9:26
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: CommentHomeFragment
 * Description: 评论的主界面 fragment
 * @date 2016/6/20 9:26
 */
public class CommentHomeFragment extends Fragment {

    @Bind(R.id.indicator_comment_home)
    CustomIndicator mIndicatorCommentHome;
    @Bind(R.id.vp_commnet_home)
    ViewPager mVpCommnetHome;

    List<Fragment> fragmentList;
    FragmentManager fragmentManager;

    BaseActivity mContext;

    ArrayList<CommentBean> mCommentAllList = new ArrayList<>();
    ArrayList<CommentBean> mCommentGoodList = new ArrayList<>();
    ArrayList<CommentBean> mCommentMiddleList = new ArrayList<>();
    ArrayList<CommentBean> mCommentBadList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_home, container, false);

        mContext = (BaseActivity) getActivity();
        ButterKnife.bind(this, view);

        //获得评论数据
        initData();

        //初始化viewPager
        fragmentList = new ArrayList<>();
        Fragment commentAllFragment = new CommentBaseFragment();//全部评论
        Fragment commentGoodFragment = new CommentBaseFragment();//好评
        Fragment commentMiddleFragment = new CommentBaseFragment();//中评
        Fragment commentBadFragment = new CommentBaseFragment();//差评

        Bundle bundle = new Bundle();
        bundle.putSerializable("data", mCommentAllList);
        commentAllFragment.setArguments(bundle);

        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("data", mCommentGoodList);
        commentGoodFragment.setArguments(bundle2);

        Bundle bundle3 = new Bundle();
        bundle3.putSerializable("data", mCommentMiddleList);
        commentMiddleFragment.setArguments(bundle3);

        Bundle bundle4 = new Bundle();
        bundle4.putSerializable("data", mCommentBadList);
        commentBadFragment.setArguments(bundle4);

        fragmentList.add(commentAllFragment);
        fragmentList.add(commentGoodFragment);
        fragmentList.add(commentMiddleFragment);
        fragmentList.add(commentBadFragment);

        fragmentManager = mContext.getSupportFragmentManager();

        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(fragmentManager, fragmentList);
        mVpCommnetHome.setAdapter(fragmentPagerAdapter);
        mVpCommnetHome.setCurrentItem(0);

        //初始化indicator
        List<String> titles = new ArrayList<>();
        titles.add("全部评论"+mCommentAllList.size());
        titles.add("好评"+mCommentGoodList.size());
        titles.add("中评"+mCommentMiddleList.size());
        titles.add("差评"+mCommentBadList.size());

        mIndicatorCommentHome.setTitles(titles);
        mIndicatorCommentHome.bindViewPager(mVpCommnetHome);

        return view;
    }

    private void initData() {
        for (int i=1; i<100; i++) {
            CommentBean bean = new CommentBean();
            bean.setBuyDate("2016-4-"+(i%12)+" 11:22:"+i%60);
            bean.setCommentDate("2016-3-4 11:22:" + i % 60);
            bean.setStar(i % 5 + 1);
            bean.setType((int) (Math.random()*3));
            bean.setComment("我是第"+i+"个评论");
            bean.setUserId("用户"+i*1234);
            mCommentAllList.add(bean);
        }

        for (CommentBean bean : mCommentAllList) {
            if (bean.getType() == 0) {
                mCommentGoodList.add(bean);
            } else if (bean.getType() == 1) {
                mCommentMiddleList.add(bean);
            } else {
                mCommentBadList.add(bean);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
