package com.gaia.member.gaiatt.mygaia.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.mygaia.bean.OrderBean;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: ShowVipInfoActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 我的盖亚_待评价订单
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/7. 11:22
 * @version V1.0
 */
public class UncommentOrderActivity extends AppCompatActivity {

    @Bind(R.id.lv_uncomment_order)
    ListView lvUncommentOrder;
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    UncommentOrderAdapter uncommentOrderAdapter;
    List<OrderBean> orderList = new ArrayList<>();

    Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_uncomment_order);
        ButterKnife.bind(this);


        initView();

    }

    private void initView() {
        tvTitleTitleBar.setText("待评价订单");
        for (int i = 0; i < 30; i++) {
            OrderBean orderBean = new OrderBean();
            orderList.add(orderBean);
        }
        uncommentOrderAdapter = new UncommentOrderAdapter(mContext,0, orderList);
        lvUncommentOrder.setAdapter(uncommentOrderAdapter);
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


    class UncommentOrderAdapter extends ArrayAdapter<OrderBean> {
        Context context;

        public UncommentOrderAdapter(Context context, int resource, List<OrderBean> objects) {
            super(context, resource, objects);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_list_mygaia_uncomment_order, null);
            }

            return convertView;
        }
    }


}
