package com.gaia.member.gaiatt.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.ListViewAdapter;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baiyuanwei on 16/4/7.
 */
public class ListViewActivity extends AppBaseActivity {

    @Bind(R.id.gaia_search_list_view)
    ListView listView;

    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;


    private ListViewAdapter listViewAdapter;

    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.list_view_layout);
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
    }

    @Override
    protected void loadData() {
        for (int i = 'a';i<'z';i++){
            datas.add(""+(char)i);
        }
        listViewAdapter = new ListViewAdapter(ListViewActivity.this,datas);
        listView.setAdapter(listViewAdapter);
    }

    private void refresh(){
        for (int i = 0;i<3;i++){
            datas.add(0,"refresh"+i);
        }
        listViewAdapter.notifyDataSetChanged();
    }

    private void loadMore(){
        for (int i = 0;i<3;i++){
            datas.add("loadMore"+i);
        }
        listViewAdapter.notifyDataSetChanged();
    }
}
