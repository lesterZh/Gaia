package com.gaia.member.gaiatt.mygaia.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.mall.ShoppingCartUtil;
import com.gaia.member.gaiatt.mall.activity.GoodsDetailActivity;
import com.gaia.member.gaiatt.mall.activity.ShoppingCartActivity;
import com.gaia.member.gaiatt.mall.bean.GoodsBean;
import com.gaia.member.gaiatt.mygaia.activity.BalanceActivity;
import com.gaia.member.gaiatt.mygaia.activity.CouponActivity;
import com.gaia.member.gaiatt.mygaia.activity.MyComplaintActivity;
import com.gaia.member.gaiatt.mygaia.activity.MyOrderActivity;
import com.gaia.member.gaiatt.mygaia.activity.PointsActivity;
import com.gaia.member.gaiatt.mygaia.activity.PointsExchangeActivity;
import com.gaia.member.gaiatt.mygaia.activity.SettingActivity;
import com.gaia.member.gaiatt.mygaia.activity.ShowVipInfoActivity;
import com.gaia.member.gaiatt.mygaia.activity.UncommentOrderActivity;
import com.gaia.member.gaiatt.mygaia.ui.CustomGridView;
import com.gaia.member.gaiatt.utils.ActivityUtils;
import com.gaia.member.gaiatt.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Title: ShowVipInfoActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 我的盖亚  主界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/7.
 * @version V1.0
 */
public class MyGaiaHomeFragment extends Fragment {
    @Bind(R.id.gv_recommend_goods)
    CustomGridView gvRecommendGoods;
    @Bind(R.id.scroll_view)
    ScrollView scrollView;
    @Bind(R.id.ll_balance_entry)
    LinearLayout llBalanceEntry;
    @Bind(R.id.ll_favorable_ticket)
    LinearLayout llFavorableTicket;
    @Bind(R.id.ll_points_entry)
    LinearLayout llPointsEntry;
    @Bind(R.id.ll_my_order_entry)
    LinearLayout llMyOrderEntry;
    @Bind(R.id.ll_my_complaint_entry)
    LinearLayout llMyComplaintEntry;
    @Bind(R.id.ll_uncomment_order_entry)
    LinearLayout llUncommentOrderEntry;
    @Bind(R.id.iv_setting)
    ImageView ivSetting;
    @Bind(R.id.ll_points_exchange_entry)
    LinearLayout llPointsExchangeEntry;
    @Bind(R.id.ll_coupon_entry)
    LinearLayout llCouponEntry;
    @Bind(R.id.iv_vip)
    ImageView ivVip;
    @Bind(R.id.tv_look_vip)
    TextView tvLookVip;
    @Bind(R.id.ll_return_order_entry)
    LinearLayout llReturnOrderEntry;
    @Bind(R.id.ll_all_order_entry)
    RelativeLayout llAllOrderEntry;


    private Activity mContext;
    private RecommandGoodsAdapter recommandGoodsAdapter;
    private List<GoodsBean> recommandGoodsList = new ArrayList<GoodsBean>();
    private MyGaiaHomeClickListener myGaiaHomeClickListener = new MyGaiaHomeClickListener();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mygaia_home, null);
        ButterKnife.bind(this, view);
        mContext = getActivity();


        initData();

        initView();

        return view;
    }

    private void initData() {
        for (int i=0; i<50; i++) {
            GoodsBean bean = new GoodsBean();
            bean.setGoodsName("第"+i+"个推荐商品");
            bean.setPrice(200+i);
            bean.setGoodsDesc("描述信息"+i);
            bean.setGoodId("00"+i);
            recommandGoodsList.add(bean);
        }

    }

    private void initView() {
        recommandGoodsAdapter = new RecommandGoodsAdapter(mContext, recommandGoodsList);
        gvRecommendGoods.setAdapter(recommandGoodsAdapter);
        gvRecommendGoods.setFocusable(false);//禁止scrollView自定滚动到GridView位置

        llBalanceEntry.setOnClickListener(myGaiaHomeClickListener);
        llFavorableTicket.setOnClickListener(myGaiaHomeClickListener);
        llPointsEntry.setOnClickListener(myGaiaHomeClickListener);
        llMyOrderEntry.setOnClickListener(myGaiaHomeClickListener);
        llMyComplaintEntry.setOnClickListener(myGaiaHomeClickListener);
        llUncommentOrderEntry.setOnClickListener(myGaiaHomeClickListener);
        llCouponEntry.setOnClickListener(myGaiaHomeClickListener);
        llPointsExchangeEntry.setOnClickListener(myGaiaHomeClickListener);
        llReturnOrderEntry.setOnClickListener(myGaiaHomeClickListener);
        llAllOrderEntry.setOnClickListener(myGaiaHomeClickListener);


        gvRecommendGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UiUtils.showToast(mContext, "click " + position);
                GoodsDetailActivity.actionStart(mContext,
                        recommandGoodsList.get(position));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
//        gvRecommendGoods.setFocusable(true);
    }


    /**
     * 进入设置
     */
    @OnClick(R.id.iv_setting)
    public void ivSetting() {
        ActivityUtils.gotoIntent(mContext, SettingActivity.class);
    }

    @OnClick({R.id.iv_vip, R.id.tv_look_vip})
    public void tvLookVip(View view) {
        switch (view.getId()) {
            case R.id.iv_vip:
                ActivityUtils.gotoIntent(mContext, ShowVipInfoActivity.class);
                break;
            case R.id.tv_look_vip:
                ActivityUtils.gotoIntent(mContext, ShowVipInfoActivity.class);
                break;
        }
    }


    class MyGaiaHomeClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_balance_entry://余额
                    ActivityUtils.gotoIntent(mContext, BalanceActivity.class);
                    break;
                case R.id.ll_favorable_ticket://优惠券
                    ActivityUtils.gotoIntent(mContext, CouponActivity.class);
                    break;
                case R.id.ll_points_entry://积分
                    ActivityUtils.gotoIntent(mContext, PointsActivity.class);
                    break;
                case R.id.ll_my_order_entry://我的订单
                    ActivityUtils.gotoIntent(mContext, MyOrderActivity.class);
                    break;
                case R.id.ll_my_complaint_entry://我的投诉
                    ActivityUtils.gotoIntent(mContext, MyComplaintActivity.class);
                    break;
                case R.id.ll_uncomment_order_entry://未评论订单
                    ActivityUtils.gotoIntent(mContext, UncommentOrderActivity.class);
                    break;
                case R.id.ll_return_order_entry: //返修退换订单
                    ActivityUtils.gotoIntent(mContext, UncommentOrderActivity.class);
                    break;
                case R.id.ll_all_order_entry: //全部订单
                    ActivityUtils.gotoIntent(mContext, UncommentOrderActivity.class);
                    break;
                case R.id.ll_coupon_entry: //优惠券入口
                    ActivityUtils.gotoIntent(mContext, CouponActivity.class);
                    break;
                case R.id.ll_points_exchange_entry: //积分兑换
                    ActivityUtils.gotoIntent(mContext, PointsExchangeActivity.class);
                    break;

            }
        }
    }

    class RecommandGoodsAdapter extends BaseAdapter {
        Context context;
        List<GoodsBean> datas;

        public RecommandGoodsAdapter(Context context, List<GoodsBean> datas) {
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

            final GoodsBean goodsBean = datas.get(position);

            viewHolder.tvGoodsName.setText(goodsBean.getGoodsName());
            viewHolder.tvGoodsPrice.setText("¥"+goodsBean.getPrice());
            viewHolder.tvGoodsDesc.setText(goodsBean.getGoodsDesc());

            viewHolder.btnAddShoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShoppingCartUtil.addGoods(goodsBean);
                    ActivityUtils.gotoIntent(mContext, ShoppingCartActivity.class);
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
