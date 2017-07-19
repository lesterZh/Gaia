package com.gaia.member.gaiatt.message.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.MessageHomeListBean;
import com.gaia.member.androidlib.utils.LogUtil;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.login.activity.HomeActivityNew;
import com.gaia.member.gaiatt.makeorder.bean.AppointServiceBean;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.AllFragmentManagement;
import com.gaia.member.gaiatt.utils.UserUtils;
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
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: MessageHomeActivity
 * @Package com.gaia.member.gaiatt.message.acitivity
 * @Description: 消息模块的fragment 供home界面加载
 * <p/>
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/16.
 */

/**
 * @author Android客户端组-ZhangHaiTao
 * @ClassName: MessageHomeFragement
 * Description: 消息模块的fragment 供home界面加载
 * @date 2016/6/23 16:18
 */
public class MessageHomeFragement extends Fragment {
    private static final int REFRESH = 0;
    @Bind(R.id.iv_back_title_bar)
    ImageView ivBackToolBar;//标题图标
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;//标题
    @Bind(R.id.lv_msg_home_list)
    PullOrLoadListView mLvMsgHomeList;//listview
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout mPullToRefreshLayout;//下拉控件

    private MsgHomeAdapter msgHomeAdapter;//适配器

    private int unreadAll = 0;//未读消息数

//    @Bind(R.id.ll_message)
//    LinearLayout llMessage;
//    @Bind(R.id.ll_follow_up_message)
//    LinearLayout llFollowUpMessage;
//    @Bind(R.id.ll_latest_activity)
//    LinearLayout llLatestActivity;

    private HomeActivityNew mContext;//上下文
    List<MessageHomeListBean.ParamBean.ListBean> msgList = new ArrayList<>();//列表

    //更新listView和未读消息总数
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH :
                    msgHomeAdapter.notifyDataSetChanged();
                    mContext.setUnreadMsgNum(unreadAll);
                    break;
            }
        }
    };

    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_home, null);
        AllFragmentManagement.fragmentList.add(this);
        ButterKnife.bind(this, view);

        mContext = (HomeActivityNew) getActivity();
        initView();
        return view;
    }

    private void initView() {
        ivBackToolBar.setVisibility(View.GONE);//隐藏图标
        tvTitleToolBar.setText("消息");//设置标题

        //设置listview的适配器
        msgHomeAdapter = new MsgHomeAdapter(mContext, 0, msgList);
        mLvMsgHomeList.setAdapter(msgHomeAdapter);

        //测试数据
        for (int i = 1; i <= 4; i++) {
            MessageHomeListBean.ParamBean.ListBean bean =
                    new MessageHomeListBean.ParamBean.ListBean();
            bean.setDesc("第"+i+"个消息描述");
            bean.setName("通知" + i);
            bean.setUnread(i*3);
            unreadAll += i*3;

            msgList.add(bean);
        }
        mHandler.sendEmptyMessage(REFRESH);

        //设置下拉刷新 和上拉加载更多的操作
        mPullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
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
                }.sendEmptyMessageDelayed(0, 0);
            }
        });


//        llMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityUtils.gotoIntent(getActivity(), ListMessageActivity.class);
//            }
//        });
//
//        llFollowUpMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityUtils.gotoIntent(getActivity(), ListMessageActivity.class);
//            }
//        });
//
//        llLatestActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityUtils.gotoIntent(getActivity(), ListMessageActivity.class);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 适配器
     */
    class MsgHomeAdapter extends ArrayAdapter<MessageHomeListBean.ParamBean.ListBean> {

        public MsgHomeAdapter(Context context, int resource, List<MessageHomeListBean.ParamBean.ListBean> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder;
            if (convertView == null) {//新建view
                convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_message_home, parent, false);

                mViewHolder = new ViewHolder(convertView);
                convertView.setTag(mViewHolder);
            } else {//view复用
                mViewHolder = (ViewHolder) convertView.getTag();
            }

            MessageHomeListBean.ParamBean.ListBean bean = getItem(position);

            //设定view
            mViewHolder.mTvName.setText(bean.getName());
            mViewHolder.mTvDesc.setText(bean.getDesc());
            //设定icon

            //设定未读消息
            setUnreadMsgNum(mViewHolder.mTvUnreadNum, bean.getUnread());
            return convertView;
        }

        /**
         * 设定未读消息
         * @param tvUnreadNum
         * @param unread
         */
        private void setUnreadMsgNum(TextView tvUnreadNum, int unread) {
            if (unread == 0) {//消息数为0 隐藏
                tvUnreadNum.setVisibility(View.INVISIBLE);
            } else if (unread > 99){//显示未读消息数
                tvUnreadNum.setText("99+");
            } else {//显示未读消息数
                tvUnreadNum.setText(unread + "");
            }
        }

        class ViewHolder {
            @Bind(R.id.iv_icon)
            ImageView mIvIcon;//图标
            @Bind(R.id.tv_unread_num)
            TextView mTvUnreadNum;//未读消息数
            @Bind(R.id.tv_name)
            TextView mTvName;//消息名
            @Bind(R.id.tv_desc)
            TextView mTvDesc;//消息描述

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 获取我的消息主界面列表
     *
     * @param mContext
     * @param appointService 预约信息
     */
    private void postGetMessageHomeList(final Context mContext, AppointServiceBean appointService) {
        try {

            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("token").value(UserUtils.getUserToken())
                    .key("userId").value(UserUtils.getUserId())
                    .endObject();

            String jsonStr = jsonStringer.toString(); //构造请求体
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);//构造请求体
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetMessageHomeList(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<MessageHomeListBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e("error", "" + e.getMessage());
                        }

                        @Override
                        public void onNext(MessageHomeListBean bean) {
                            if (bean.isSuccess()) {
                                //获取消息列表
                                msgList = bean.getParam().getList();

                                //获取总的未读消息数
                                for (MessageHomeListBean.ParamBean.ListBean
                                        msg : bean.getParam().getList()) {
                                    unreadAll += msg.getUnread();
                                }

                            } else {//获取失败

                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
