package com.gaia.member.gaiatt.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.BaseFragmentPagerAdapter;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.mall.ShoppingCartUtil;
import com.gaia.member.gaiatt.mall.bean.GoodsBean;
import com.gaia.member.gaiatt.mall.fragment.CommentHomeFragment;
import com.gaia.member.gaiatt.mall.fragment.GoodsSimpleFragment;
import com.gaia.member.gaiatt.mygaia.ui.CustomIndicator;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsDetailActivity extends BaseActivity {

    List<Fragment> fragmentList;
    FragmentManager fragmentManager;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.indicator_goods_detail)
    CustomIndicator mIndicatorGoodsDetail;
    @Bind(R.id.iv_share)
    ImageView mIvShare;
    @Bind(R.id.vp_goods_detail)
    ViewPager mVpGoodsDetail;
    @Bind(R.id.btn_add_shopping_cart)
    Button btnAddShoppingCart;
    @Bind(R.id.tv_add_num_notify)
    TextView tvAddNumNotify;
    @Bind(R.id.rl_shopping_cart_entry)
    RelativeLayout rlShoppingCartEntry;
    @Bind(R.id.rl_bottom_function)
    RelativeLayout mRlBottomFunction;

    private GoodsBean mGoodsBean;

    int cartGoodsNum; //购物车中的商品数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            mGoodsBean = (GoodsBean) getIntent().getSerializableExtra("goodsBean");
//            LogUtil.w("ZHT", mGoodsBean.getGoodsName());
        }
        initDate();
        initView();

        refreshShoppingCartNum();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshShoppingCartNum();//更新购物车的数量
    }

    /**
     * 更新购物车的显示数量
     */
    private void refreshShoppingCartNum() {
        cartGoodsNum = ShoppingCartUtil.getGoodsNum();
        if (cartGoodsNum == 0) {
            tvAddNumNotify.setVisibility(View.INVISIBLE);
        } else {
            tvAddNumNotify.setText(cartGoodsNum + "");
            tvAddNumNotify.setVisibility(View.VISIBLE);
        }
    }

    private void initDate() {
    }

    private void initView() {
        //初始化viewPager
        fragmentList = new ArrayList<>();
        fragmentList.add(new GoodsSimpleFragment());
        fragmentList.add(new GoodsSimpleFragment());
        fragmentList.add(new CommentHomeFragment());

        fragmentManager = getSupportFragmentManager();
        BaseFragmentPagerAdapter fragmentPagerAdapter = new BaseFragmentPagerAdapter(fragmentManager, fragmentList);
        mVpGoodsDetail.setAdapter(fragmentPagerAdapter);
        mVpGoodsDetail.setCurrentItem(0);

        //初始化indicator
        List<String> titles = new ArrayList<>();
        titles.add("商品");
        titles.add("详情");
        titles.add("评论");
        mIndicatorGoodsDetail.setTitles(titles);
        mIndicatorGoodsDetail.bindViewPager(mVpGoodsDetail);

        mVpGoodsDetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    mRlBottomFunction.setVisibility(View.INVISIBLE);
                } else {
                    mRlBottomFunction.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        refreshShoppingCartNum();
    }


    /**
     * activity入口调用函数，需要传递一个商品类的参数
     *
     * @param context
     * @param goodsBean
     */
    public static void actionStart(Context context, GoodsBean goodsBean) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra("goodsBean", goodsBean);
        context.startActivity(intent);
    }

    @OnClick(R.id.btn_add_shopping_cart)
    public void btnAddShoppingCart() {
        ShoppingCartUtil.addGoods(mGoodsBean);
        refreshShoppingCartNum();
    }

    /**
     * 点击进入购物车界面
     */
    @OnClick(R.id.rl_shopping_cart_entry)
    public void rlShoppingCartEntry() {
        ActivityUtils.gotoIntent(mContext, ShoppingCartActivity.class);
    }
}
