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
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.mall.activity.MallHomeActivity;
import com.gaia.member.gaiatt.mygaia.bean.PointsBean;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端开发组-zhangHaiTao
 * @version V1.0
 * @Title: PointsActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 我的盖亚_积分
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/7. 11:22
 */
public class PointsActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;
    @Bind(R.id.lv_points)
    PullOrLoadListView lvPoints;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    private Activity mContext;


    private List<PointsBean> pointsList = new ArrayList<>();
    private PointsAdapter pointsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_points);
        ButterKnife.bind(this);

        loadData();
        initView();
    }

    private void loadData() {
        for (int i = 0; i < 10; i++) {
            PointsBean bean = new PointsBean();
            bean.setDesc("我是第 " + i + " 条描述的消息哦……");
            pointsList.add(bean);
        }
    }

    private void initView() {
        tvTitleToolBar.setText("积分");
        ivRightTitleBar.setVisibility(View.VISIBLE);
        ivRightTitleBar.setImageResource(R.drawable.icon_cart_normal);
        ivRightTitleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.gotoIntent(mContext, MallHomeActivity.class);
            }
        });

        pointsAdapter = new PointsAdapter(mContext, 0, pointsList);
        lvPoints.setAdapter(pointsAdapter);

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


    class PointsAdapter extends ArrayAdapter<PointsBean> {
        Context context;

        public PointsAdapter(Context context, int resource, List<PointsBean> objects) {
            super(context, resource, objects);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_list_mygaia_points, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            PointsBean points = getItem(position);
            viewHolder.tvPointsDesc.setText(points.getDesc());

            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.tv_points_title)
            TextView tvPointsTitle;
            @Bind(R.id.tv_points_date)
            TextView tvPointsDate;
            @Bind(R.id.tv_points_desc)
            TextView tvPointsDesc;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
