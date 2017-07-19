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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.mall.bean.GoodsBean;
import com.gaia.member.gaiatt.ui.PullOrLoadGridView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: ServiceListFragment
 * @Package com.gaia.member.gaiatt.mall
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/19 12:03
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: ServiceListFragment
 * Description: 商城主界面中的积分兑换 fragment
 * @date 2016/6/7 12:03
 */
public class ServiceListFragment extends Fragment {
    @Bind(R.id.gv_mall_goods_list)
    PullOrLoadGridView mGvMallGoodsList;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout mPullToRefreshLayout;

    private GoodsAdapter goodsAdapter;
    private List<GoodsBean> goodsList = new ArrayList<GoodsBean>();

    private Context mContext;
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mall_goods_list, container, false);
        ButterKnife.bind(this, rootView);

        initData();
        initView();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        rootView.scrollTo(0, 0);
    }

    private void initData() {
        mContext = getActivity();
        for (int i=0; i<50; i++) {
            GoodsBean bean = new GoodsBean();
            bean.setGoodsName("我是第"+i+"个商品");
            bean.setPrice(100+i);
            bean.setGoodsDesc("描述信息"+i);
            goodsList.add(bean);
        }
    }

    private void initView() {
        goodsAdapter = new GoodsAdapter(mContext, goodsList);
        mGvMallGoodsList.setAdapter(goodsAdapter);

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
                }.sendEmptyMessageDelayed(0, 1000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class GoodsAdapter extends BaseAdapter {
        Context context;
        List<GoodsBean> datas;

        public GoodsAdapter(Context context, List<GoodsBean> datas) {
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
                convertView = View.inflate(context, R.layout.item_gird_recommand_goods, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.btnAddShoppingCart.setText("兑换");
            viewHolder.btnAddShoppingCart.setBackgroundResource(R.drawable.bg_cyan_solid_buton);

            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.iv_recommand_goods)
            ImageView ivRecommandGoods;
            @Bind(R.id.tv_goods_name)
            TextView tvGoodsName;
            @Bind(R.id.tv_goods_price)
            TextView tvGoodsPrice;
            @Bind(R.id.tv_goods_desc)
            TextView tvGoodsDesc;
            @Bind(R.id.btn_add_shopping_cart)
            Button btnAddShoppingCart;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
