package com.gaia.member.gaiatt.login.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.ListViewAdapter;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestRefreshLoadMoreActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.pull_icon)
    ImageView pullIcon;
    @Bind(R.id.refreshing_icon)
    ImageView refreshingIcon;
    @Bind(R.id.state_tv)
    TextView stateTv;
    @Bind(R.id.state_iv)
    ImageView stateIv;
    @Bind(R.id.head_view)
    RelativeLayout headView;
    @Bind(R.id.gaia_search_list_view)
    PullOrLoadListView gaiaSearchListView;
    @Bind(R.id.pullup_icon)
    ImageView pullupIcon;
    @Bind(R.id.loading_icon)
    ImageView loadingIcon;
    @Bind(R.id.loadstate_tv)
    TextView loadstateTv;
    @Bind(R.id.loadstate_iv)
    ImageView loadstateIv;
    @Bind(R.id.loadmore_view)
    RelativeLayout loadmoreView;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    ListViewAdapter listViewAdapter;
    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_refresh_load_more);
        ButterKnife.bind(this);

        tvTitleTitleBar.setText("刷新");

        for (int i = 0; i < 10; i++) {
            datas.add("data " + i);
        }
        listViewAdapter = new ListViewAdapter(mContext, datas);
        gaiaSearchListView.setAdapter(listViewAdapter);


        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        for (int i = 0; i < 5; i++) {
                            datas.add(0, "refresh " + i);
                        }
                        listViewAdapter.notifyDataSetChanged();

                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        for (int i = 0; i < 5; i++) {
                            datas.add("load " + i);
                        }
                        listViewAdapter.notifyDataSetChanged();
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }
        });
    }
}
