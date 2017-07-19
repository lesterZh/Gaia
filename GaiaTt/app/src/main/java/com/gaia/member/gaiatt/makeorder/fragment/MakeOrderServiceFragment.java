package com.gaia.member.gaiatt.makeorder.fragment;

import android.app.Activity;
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
import com.gaia.member.gaiatt.makeorder.activity.MakeOrderServiceListActivity;
import com.gaia.member.gaiatt.makeorder.bean.MakeOrderServiceBean;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangHaiTao on 2016/5/13.
 */
public class MakeOrderServiceFragment extends Fragment {
    Activity mContext;
    @Bind(R.id.lv_make_order_service)
    PullOrLoadListView lvMakeOrderService;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    private List<MakeOrderServiceBean> makeOrderServiceBeanList = new ArrayList<>();
    private MakeOrderSeviceAdapter makeOrderSeviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = (Activity) getContext();
        View view = View.inflate(mContext, R.layout.fragment_make_order_service, null);
        ButterKnife.bind(this, view);


        initData();
        initView();
        return view;
    }

    private void initData() {
        for (int i=0; i<30; i++) {
            makeOrderServiceBeanList.add(new MakeOrderServiceBean());
        }
    }

    private void initView() {
        makeOrderSeviceAdapter = new MakeOrderSeviceAdapter(mContext, 0, makeOrderServiceBeanList);
        lvMakeOrderService.setAdapter(makeOrderSeviceAdapter);

        lvMakeOrderService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MakeOrderServiceListActivity.actionStart(mContext, makeOrderServiceBeanList.get(position));
            }
        });

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class MakeOrderSeviceAdapter extends ArrayAdapter<MakeOrderServiceBean> {

        Context mContext;
        public MakeOrderSeviceAdapter(Context context, int resource, List<MakeOrderServiceBean> objects) {
            super(context, resource, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_list_make_order_service, null);
            }
            return convertView;
        }
    }




}
