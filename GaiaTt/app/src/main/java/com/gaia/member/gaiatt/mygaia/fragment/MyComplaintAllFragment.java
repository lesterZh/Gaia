package com.gaia.member.gaiatt.mygaia.fragment;

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
import android.widget.ListView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.mygaia.activity.ComplaintDetailInfoActivity;
import com.gaia.member.gaiatt.mygaia.bean.MyComplaintBean;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: ShowVipInfoActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 我的投诉中的一个 全部投诉的fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/7.
 * @version V1.0
 */
public class MyComplaintAllFragment extends Fragment {

    @Bind(R.id.lv_my_complaint_all)
    ListView lvMyComplaintAll;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    private Context mContext;
    private List<MyComplaintBean> complaintList = new ArrayList<>();
    private MyComplaintAllAdapter myComplaintAllAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = View.inflate(mContext, R.layout.fragment_mygaia_my_complaint_all, null);


        ButterKnife.bind(this, view);

        initData();

        initView();
        return view;
    }

    private void initData() {
        for (int i=0; i<20; i++) {
            MyComplaintBean complaintBean = new MyComplaintBean();
            complaintList.add(complaintBean);
        }
    }

    private void initView() {
        myComplaintAllAdapter = new MyComplaintAllAdapter(mContext, 0, complaintList);
        lvMyComplaintAll.setAdapter(myComplaintAllAdapter);
        lvMyComplaintAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里其实要传递数据的
                ActivityUtils.gotoIntent((Activity) mContext, ComplaintDetailInfoActivity.class);
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


    class MyComplaintAllAdapter extends ArrayAdapter<MyComplaintBean> {

        Context context;
        public MyComplaintAllAdapter(Context context, int resource, List<MyComplaintBean> objects) {
            super(context, resource, objects);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_list_mygaia_my_complaint_all, null);
            } else {

            }

            MyComplaintBean complaintBean = getItem(position);

            return convertView;
        }
    }

}
