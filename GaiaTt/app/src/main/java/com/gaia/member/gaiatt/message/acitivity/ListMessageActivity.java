package com.gaia.member.gaiatt.message.acitivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.message.adapter.MessageAdapter;
import com.gaia.member.gaiatt.message.bean.MessageBean;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.DisplayUtil;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: ListMessageActivity
 * @Package com.gaia.member.gaiatt.message.acitivity
 * @Description:  显示一种类型消息的子消息列表，可滑动删除
 *
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/16.
 */
public class ListMessageActivity extends AppCompatActivity {

    @Bind(R.id.iv_back_title_bar)
    ImageView ivBackToolBar;
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;
    @Bind(R.id.lv_message)
    SwipeMenuListView lvMessage;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;


    Activity mContext;

    List<MessageBean> messageList = new ArrayList<>();
    MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mContext = this;
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        for (int i=0; i<20; i++) {
            MessageBean messageBean = new MessageBean();
            messageBean.setTitle("就诊预约通知");
            messageBean.setContent("亲爱的{xx}女士/男士，您好！您预约了{xx}，执业医师是{xx}老师，时间是2015年10月3日下午。地址:南湖大道诊所，如有疑问请致电：028-xxxxxxxx。");

            messageList.add(messageBean);
        }
    }

    private void initView() {
        tvTitleToolBar.setText("消息");
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xff, 0x00,
                        0x00)));
                // set item width
                openItem.setWidth(DisplayUtil.dip2px(mContext, 80));
                // set item title
                openItem.setTitle("删除");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(DisplayUtil.px2dip(mContext,80));
//                // set a icon
//                deleteItem.setIcon(R.mipmap.ic_launcher);
//                // add to menu
//                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        lvMessage.setMenuCreator(creator);
        messageAdapter = new MessageAdapter(mContext, messageList);
        lvMessage.setAdapter(messageAdapter);
        lvMessage.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        messageList.remove(position);
                        messageAdapter.notifyDataSetChanged();

                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                        break;
//                    case 1:
//                        // delete
//                        Toast.makeText(mContext, data[position]+"delet", Toast.LENGTH_SHORT).show();
//
//                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityUtils.gotoIntent(mContext, DetailMessageActivity.class);
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
}
