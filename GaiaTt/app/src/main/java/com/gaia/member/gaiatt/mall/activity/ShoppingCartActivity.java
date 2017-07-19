package com.gaia.member.gaiatt.mall.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.mall.ShoppingCartUtil;
import com.gaia.member.gaiatt.mall.bean.GoodsBean;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingCartActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.lv_goods_list)
    ListView lvGoodsList;
    @Bind(R.id.cb_shopping_cart_select_all)
    CheckBox cbShoppingCartSelectAll;
    @Bind(R.id.tv_shopping_cart_all_payment)
    TextView tvShoppingCartAllPayment;
    @Bind(R.id.tv_shopping_cart_freight)
    TextView tvShoppingCartFreight;
    @Bind(R.id.btn_shopping_cart_pay)
    Button btnShoppingCartPay;

    private ShoppingCartAdapter shoppingCartAdapter;
    volatile boolean ignoreSelectAll = false; //是否按下全选
    private MyOnCheckedChangeListener myOnCheckedChangeListener;

    List<GoodsBean> goodsBeanList = new ArrayList<>();
    private double freight = 12;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onPause() {
        ShoppingCartUtil.saveShoppingCart();//本地保存购物车的数据
        super.onPause();
    }


    private void initView() {
        tvTitleTitleBar.setText("购物车");
        goodsBeanList = ShoppingCartUtil.getGoods();
        shoppingCartAdapter = new ShoppingCartAdapter(mContext, 0, goodsBeanList);
        lvGoodsList.setAdapter(shoppingCartAdapter);


        myOnCheckedChangeListener = new MyOnCheckedChangeListener();
        //全选操作
        cbShoppingCartSelectAll.setOnCheckedChangeListener(myOnCheckedChangeListener);

        showPriceInfo();
    }

    /**
     * 显示价格信息
     */
    private void showPriceInfo() {
        tvShoppingCartFreight.setText("¥" + freight);//运费
        double totalPrice = ShoppingCartUtil.getTotalPrice() + freight;
        tvShoppingCartAllPayment.setText("¥" + totalPrice + "");//总价格
    }

    /**
     * 支付,去结算
     */
    @OnClick(R.id.btn_shopping_cart_pay)
    public void btnShoppingCartPay() {
        ActivityUtils.gotoIntent(mContext, OrderConfirmActivity.class);
    }

    /**
     * 全选操作的事件监听器
     */
    class MyOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                for (GoodsBean bean : goodsBeanList) {
                    bean.setIsSelected(true);
                }
            } else {
                for (GoodsBean bean : goodsBeanList) {
                    bean.setIsSelected(false);
                }
            }



            shoppingCartAdapter.notifyDataSetChanged();
            showPriceInfo();
        }
    }

    class ShoppingCartAdapter extends ArrayAdapter<GoodsBean> {

        public ShoppingCartAdapter(Context context, int resource, List<GoodsBean> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckBox mCbIsSelected;
            ImageView mIvGoodsPicture;
            TextView mTvGoodsName;
            TextView mTvGoodsDesc;
            ImageView mIvMinusNum;
            final TextView mTvGoodsAddNum;
            ImageView mIvPlusNum;

            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_mall_shopping_cart_list, parent, false);

            mTvGoodsName = (TextView) convertView.findViewById(R.id.tv_goods_name);
            mTvGoodsDesc = (TextView) convertView.findViewById(R.id.tv_goods_desc);
            mTvGoodsAddNum = (TextView) convertView.findViewById(R.id.tv_goods_add_num);
            mCbIsSelected = (CheckBox) convertView.findViewById(R.id.cb_is_selected);

            mIvMinusNum = (ImageView) convertView.findViewById(R.id.iv_minus_num);
            mIvPlusNum = (ImageView) convertView.findViewById(R.id.iv_plus_num);
            mIvGoodsPicture = (ImageView) convertView.findViewById(R.id.iv_goods_picture);

            final GoodsBean bean = getItem(position);

            mTvGoodsName.setText(bean.getGoodsName());
            mTvGoodsDesc.setText(bean.getGoodsDesc());
            mTvGoodsAddNum.setText(bean.getNum() + "");
            mCbIsSelected.setChecked(bean.isSelected());

            //如果使用viewholder,出现bug: checkbox点击一个，所有的商品都被选中
            mCbIsSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    UiUtils.showToast(mContext, getPosition(bean)+"");
                    bean.setIsSelected(isChecked);
                    //任意一个商品被取消选中是时候，全选取消
                    if (!isChecked) {
                        if (cbShoppingCartSelectAll.isChecked()) {
                            cbShoppingCartSelectAll.setOnCheckedChangeListener(null);
                            cbShoppingCartSelectAll.setChecked(false);
                            cbShoppingCartSelectAll.setOnCheckedChangeListener(myOnCheckedChangeListener);
                        }
                    }

                    showPriceInfo();
                }
            });

            mIvMinusNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = bean.getNum();
                    n--;
                    if (n <= 0) {
                        mTvGoodsAddNum.setText(0 + "");
                        bean.setNum(0);
                    } else {
                        mTvGoodsAddNum.setText(n + "");
                        bean.setNum(n);
                    }

                    showPriceInfo();
                }
            });

            mIvPlusNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = bean.getNum();
                    n++;
                    mTvGoodsAddNum.setText(n + "");
                    ShoppingCartUtil.addGoods(bean);

                    showPriceInfo();
                }
            });
            return convertView;
        }

        /*
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_mall_shopping_cart_list, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final GoodsBean bean = getItem(position);
            bind(viewHolder, bean);

            return convertView;
        }



        private void bind(final ViewHolder holder, final GoodsBean bean) {
            holder.tvGoodsName.setText(bean.getGoodsName());
            holder.tvGoodsDesc.setText(bean.getGoodsDesc());
            holder.tvGoodsAddNum.setText(bean.getNum() + "");
            holder.cbIsSelected.setSelected(bean.isSelected());

            holder.cbIsSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean selected = bean.isSelected();
                    holder.cbIsSelected.setSelected(!selected);
                }
            });
//            holder.cbIsSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    bean.setIsSelected(isChecked);
//
//
//                    LogUtil.i("cbIsSelected"+bean.toString());
//                    //任意一个商品被取消选中是时候，全选取消
////                    if (!isChecked) {
////                        if (cbShoppingCartSelectAll.isChecked()) {
////                            cbShoppingCartSelectAll.setOnCheckedChangeListener(null);
////                            cbShoppingCartSelectAll.setChecked(false);
////                            cbShoppingCartSelectAll.setOnCheckedChangeListener(myOnCheckedChangeListener);
////                        }
////                    }
//
//                    showPriceInfo();
//                }
//            });
        }

        class ViewHolder {
            @Bind(R.id.cb_is_selected)
            ImageView cbIsSelected;
            @Bind(R.id.iv_goods_picture)
            ImageView ivGoodsPicture;
            @Bind(R.id.tv_goods_name)
            TextView tvGoodsName;
            @Bind(R.id.tv_goods_desc)
            TextView tvGoodsDesc;
            @Bind(R.id.iv_minus_num)
            ImageView ivMinusNum;
            @Bind(R.id.tv_goods_add_num)
            TextView tvGoodsAddNum;
            @Bind(R.id.iv_plus_num)
            ImageView ivPlusNum;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

*/

    }
}
