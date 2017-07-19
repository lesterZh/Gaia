package com.gaia.member.gaiatt.mall.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.mall.bean.CommentBean;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gaia.com.componentlibrary.custom.CustomCommentStar;

/**
 * @author ZhangHaiTao
 * @ClassName: CommentBaseFragment
 * Description: TODO
 * @date 2016/6/20 9:49
 */
public class CommentBaseFragment extends Fragment {

    @Bind(R.id.lv_comment_list)
    PullOrLoadListView mLvCommentList;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    Context mContext;

    ArrayList<CommentBean> mCommentList = new ArrayList<>();
    CommentAdapter mCommentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_comment_list, container, false);
        ButterKnife.bind(this, view);

        mCommentAdapter = new CommentAdapter(mContext, 0, mCommentList);
        mLvCommentList.setAdapter(mCommentAdapter);

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

        return view;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if (args != null) {
            mCommentList = (ArrayList<CommentBean>) args.getSerializable("data");
        }

    }

    public void setData(List<CommentBean> list) {
        mCommentList.addAll(list);
//        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class CommentAdapter extends ArrayAdapter<CommentBean> {

        public CommentAdapter(Context context, int resource, List<CommentBean> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_mall_goods_comment, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            CommentBean commentBean = getItem(position);
            viewHolder.mCommentStar.setStarcount(commentBean.getStar());
            viewHolder.mTvCommentUserId.setText(commentBean.getUserId());
            viewHolder.mTvCommentContent.setText(commentBean.getComment());
            viewHolder.mTvCommentBuyingTime.setText(commentBean.getBuyDate());
            viewHolder.mTvCommentDate.setText(commentBean.getCommentDate());

            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.tv_comment_user_id)
            TextView mTvCommentUserId;
            @Bind(R.id.tv_comment_date)
            TextView mTvCommentDate;
            @Bind(R.id.comment_star)
            CustomCommentStar mCommentStar;
            @Bind(R.id.tv_comment_content)
            TextView mTvCommentContent;
            @Bind(R.id.tv_comment_buying_time)
            TextView mTvCommentBuyingTime;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
