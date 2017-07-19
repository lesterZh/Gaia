package com.gaia.member.gaiatt.mall.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.mall.ShoppingCartUtil;
import com.gaia.member.gaiatt.mall.bean.GoodsBean;
import com.gaia.member.gaiatt.mygaia.ui.CustomListView;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * @Title: OrderCompleteActivity
 * @Package com.gaia.member.gaiatt.mall
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/6/16 15:03
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: OrderCompleteActivity
 * Description: 订单完成
 * @date 2016/6/16 15:03
 */

public class OrderCompleteActivity extends BaseActivity {


    List<GoodsBean> mGoodsBeanList = new ArrayList<>();
    OrderGoodsAdapter mOrderGoodsAdapter;
    @Bind(R.id.tv_title_title_bar)
    TextView mTvTitleTitleBar;
    @Bind(R.id.lv_goods_list)
    CustomListView mLvGoodsList;
    @Bind(R.id.btn_comment)
    Button mBtnComment;
    @Bind(R.id.btn_buy_again)
    Button mBtnBuyAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        ButterKnife.bind(this);

        mGoodsBeanList = ShoppingCartUtil.getSelectedGoods();

        initView();
    }

    private void initView() {
        mTvTitleTitleBar.setText("完成订单");
        mOrderGoodsAdapter = new OrderGoodsAdapter(mContext, 0, mGoodsBeanList);
        mLvGoodsList.setAdapter(mOrderGoodsAdapter);
    }


    @OnClick({R.id.btn_comment, R.id.btn_buy_again})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_comment:
                ActivityUtils.gotoIntent(mContext, AddCommentActivity.class);
                break;
            case R.id.btn_buy_again:
                ActivityUtils.gotoIntent(mContext, MallHomeActivity.class);
                break;
        }
    }


    class OrderGoodsAdapter extends ArrayAdapter<GoodsBean> {

        public OrderGoodsAdapter(Context context, int resource, List<GoodsBean> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_mall_order_confirm, parent, false);
            }
            return convertView;
        }
    }
}
