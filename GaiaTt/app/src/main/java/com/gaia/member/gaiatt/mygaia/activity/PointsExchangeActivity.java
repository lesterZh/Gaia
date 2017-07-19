package com.gaia.member.gaiatt.mygaia.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.mall.activity.MallHomeActivity;
import com.gaia.member.gaiatt.mygaia.bean.RecommandGoodsBean;
import com.gaia.member.gaiatt.ui.PullOrLoadGridView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: PointsExchangeActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/20 11:22
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: PointsExchangeActivity
 * Description: 积分兑换
 * @date 2016/5/20 11:22
 */

public class PointsExchangeActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.gv_exchange_goods)
    PullOrLoadGridView gvExchangeGoods;
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;


    private GoodsAdapter goodsAdapter;
    private List<RecommandGoodsBean> recommandGoodsList = new ArrayList<RecommandGoodsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_exchange);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initView() {
        tvTitleTitleBar.setText("积分兑换");
        goodsAdapter = new GoodsAdapter(mContext, recommandGoodsList);
        gvExchangeGoods.setAdapter(goodsAdapter);

        ivRightTitleBar.setVisibility(View.VISIBLE);
        ivRightTitleBar.setImageResource(R.drawable.icon_cart_normal);
        ivRightTitleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.gotoIntent(mContext, MallHomeActivity.class);
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
        for (int i = 0; i < 7; i++) {
            RecommandGoodsBean recommandGoodsBean = new RecommandGoodsBean();
            recommandGoodsList.add(recommandGoodsBean);
        }
    }


    class GoodsAdapter extends BaseAdapter {
        Context context;
        List<RecommandGoodsBean> datas;

        public GoodsAdapter(Context context, List<RecommandGoodsBean> datas) {
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

            viewHolder.btnAddShoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("提示")
                            .setMessage("确定兑换？")
                            .setCancelable(true)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    PayResultActivity.actionStart(mContext, true, "兑换结果", "兑换成功");
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
            });
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
