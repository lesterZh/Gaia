package com.gaia.member.gaiatt.mygaia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.mygaia.activity.OrderDetailActivity;
import com.gaia.member.gaiatt.mygaia.bean.MyOrderServiceBean;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: ShowVipInfoActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 我的盖亚_我的预约  服务fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/7.
 * @version V1.0
 */
public class MyOrderServiceFragment extends Fragment {
    @Bind(R.id.lv_my_order_service)
    PullOrLoadListView lvMyOrderService;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    private List<MyOrderServiceBean> myOrderServiceList = new ArrayList<>();
    private MyOrderSeviceAdapter myOrderSeviceAdapter;
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_mygaia_my_order_service, null);
        ButterKnife.bind(this, view);

        initData();
        initView();
        return view;
    }

    private void initData() {
        for (int i=0; i<10; i++) {
            MyOrderServiceBean serviceBean = new MyOrderServiceBean();
            myOrderServiceList.add(serviceBean);
        }
    }

    private void initView() {
        lvMyOrderService.setAdapter(new MyOrderSeviceAdapter(mContext,0,  myOrderServiceList));
        //设置下拉刷新 和上拉加载更多的操作
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //TODO

                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //TODO

                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }
        });
        lvMyOrderService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityUtils.gotoIntent(getActivity(), OrderDetailActivity.class);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class MyOrderSeviceAdapter extends ArrayAdapter<MyOrderServiceBean> {

        List<MyOrderServiceBean> myOrderServiceList;
        Context mContext;
        public MyOrderSeviceAdapter(Context context, int resource, List<MyOrderServiceBean> objects) {
            super(context, resource, objects);
            myOrderServiceList = objects;
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_list_mygaia_my_order_service, null);
            }
            return convertView;
        }
    }
}
