package com.gaia.member.gaiatt.mygaia.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.CouponsListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.mygaia.bean.FavorableTicketBean;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Title: CouponActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 优惠券
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/6. 11:22
 * @version V1.0
 */
public class CouponActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;
    @Bind(R.id.lv_tickets)
    PullOrLoadListView lvTickets;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;


    List<FavorableTicketBean> tickets = new ArrayList<>();
    private Activity mContext;
    private FavorableTicketAdapter favorableTicketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);

        for (int i = 0; i < 25; i++) {
            FavorableTicketBean bean = new FavorableTicketBean();
            tickets.add(bean);
        }
        initView();
//        initData();

    }

    //初始化数据
    private void initData() {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("userId").value("10000987487387498237")
                    .key("currentPage").value(1)
                    .key("couponsType").value(1)
                    .key("couponsStatus").value(1)
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetCouponsList(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CouponsListBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(CouponsListBean couponsListBean) {
                            if (couponsListBean.isSuccess()) {
                                Toast.makeText(CouponActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(CouponActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        tvTitleToolBar.setText("优惠券");

        favorableTicketAdapter = new FavorableTicketAdapter(mContext, tickets);
        lvTickets.setAdapter(favorableTicketAdapter);

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


    class FavorableTicketAdapter extends BaseAdapter {
        Context context;
        List<FavorableTicketBean> datas;

        FavorableTicketAdapter(Context context, List<FavorableTicketBean> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_list_mygaia_coupon, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //动态修改内容
            viewHolder.tvDeadlineTime.setText("亲，优惠哦");
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.tv_ticket_name)
            TextView tvTicketName;
            @Bind(R.id.tv_discount)
            TextView tvDiscount;
            @Bind(R.id.tv_deadline_time)
            TextView tvDeadlineTime;
            @Bind(R.id.tv_desc)
            TextView tvDesc;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
