package com.gaia.member.gaiatt.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.RecyclerViewAdapter;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.ui.DividerItemDecoration;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baiyuanwei on 16/4/6.
 */
public class RecyclerViewActivity extends AppBaseActivity {

    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    @Bind(R.id.refresh_loader_recycler_view)
    RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;

    private List<String> datas = new ArrayList<>();

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.recycler_view_layout);
        ButterKnife.bind(this);


        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                new Handler()
                {
                    @Override
                    public void handleMessage(Message msg)
                    {
                        refresh();
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 2000);

            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                new Handler()
                {
                    @Override
                    public void handleMessage(Message msg)
                    {
                        loadMore();
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 2000);
            }
        });

        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                int visibleItemCount =  mLinearLayoutManager.getChildCount();
                int totalItemCount = mLinearLayoutManager.getItemCount();
                int pastVisibleItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                if ((pastVisibleItems + visibleItemCount)>=totalItemCount){
                    recyclerViewAdapter.setIsFooter(true);
                }else {
                    recyclerViewAdapter.setIsFooter(false);
                }

                if (pastVisibleItems == 0){
                    recyclerViewAdapter.setIsHeader(true);
                }else {
                    recyclerViewAdapter.setIsHeader(false);
                }
            }
        });

    }

    @Override
    protected void loadData() {

        for (int i = 'a'; i < 'z'; i++) {
            datas.add("" + (char) i);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(RecyclerViewActivity.this, datas);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void refresh() {

        for (int i = 0; i < 5; i++) {
            datas.add(0, "refresh" + i);
        }
        recyclerViewAdapter.notifyDataSetChanged();
    }
    private void loadMore() {

        ArrayList<String> da = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            da.add("load_more" + i);
        }
        datas.addAll(da);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
