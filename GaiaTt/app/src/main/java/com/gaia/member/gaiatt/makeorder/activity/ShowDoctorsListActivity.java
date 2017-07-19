package com.gaia.member.gaiatt.makeorder.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.bean.DoctorInfoBean;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: ShowDoctorInfoActivity
 * @Package com.gaia.member.gaiatt.makeorder.activity
 * @Description:  显示医生列表
 *
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/14.
 */
public class ShowDoctorsListActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.lv_doctors)
    PullOrLoadListView lvDoctors;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    private int doctorNum = 30;

    private List<DoctorInfoBean> doctorList = new ArrayList<>();
    private  ShowDoctorListAdapter mShowDoctorListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_list);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initView() {
        tvTitleTitleBar.setText("儿外科" + "（" + doctorNum + "个）");
        mShowDoctorListAdapter = new ShowDoctorListAdapter(mContext, 0, doctorList);
        lvDoctors.setAdapter(mShowDoctorListAdapter);

        lvDoctors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                UiUtils.showToast(mContext,position+" click");
                ShowDoctorInfoActivity.actionStart(mContext, doctorList.get(position));
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

    private void initData() {
        for (int i=0; i<30; i++) {
            doctorList.add(new DoctorInfoBean());
        }
    }


    class ShowDoctorListAdapter extends ArrayAdapter<DoctorInfoBean> {

        public ShowDoctorListAdapter(Context context, int resource, List<DoctorInfoBean> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_list_make_order_doctor, null);
            }

            return convertView;
        }
    }
}
