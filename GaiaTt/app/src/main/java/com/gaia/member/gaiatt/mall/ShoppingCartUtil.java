package com.gaia.member.gaiatt.mall;

import android.content.Context;

import com.gaia.member.androidlib.utils.LogUtil;
import com.gaia.member.gaiatt.GaiaCustomApplication;
import com.gaia.member.gaiatt.mall.bean.GoodsBean;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.SharedPreferencesUtil;
import com.gaia.member.gaiatt.utils.StreamUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Android客户端开发组-zhangHaiTao
 * @version V1.0
 * @Title: ShoppingCartUtil
 * @Package com.gaia.member.gaiatt.mall
 * @Description: 购物车的处理类
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/7 16:03
 */
public class ShoppingCartUtil {

    //key是商品id
    private static Map<String, GoodsBean> shoppingCart = new HashMap<>();
    private static Context mContext = GaiaCustomApplication.getAppContext();

    /**
     * 向购物车中添加商品，需要保存在本地
     *
     * @param goodsBean
     */
    public static void addGoods(GoodsBean goodsBean) {
        if (shoppingCart.containsKey(goodsBean.getGoodId())) {
            shoppingCart.get(goodsBean.getGoodId()).addNum(1);
        } else {
            goodsBean.addNum(1);
            shoppingCart.put(goodsBean.getGoodId(), goodsBean);
        }
        int num = SharedPreferencesUtil.getInteger(mContext,
                CommonConstants.shoppingCartGoodsNum, 0);
//        num++;
//        SharedPreferencesUtil.putInteger(mContext, CommonConstants.shoppingCartGoodsNum, num);
        saveShoppingCart();
    }

    /**
     * 结算的时候选择商品
     *
     * @param goodsBean
     */
    public static void selectGoods(GoodsBean goodsBean) {
        if (shoppingCart.containsKey(goodsBean.getGoodId())) {
            shoppingCart.get(goodsBean.getGoodId()).setIsSelected(true);
        }
        saveShoppingCart();
    }


    /**
     * 获取当前购物车中的商品数量
     *
     * @return
     */
    public static int getGoodsNum() {
        int num = 0;
        for (GoodsBean bean : shoppingCart.values()) {
            num += bean.getNum();
        }
        return num;
//        return SharedPreferencesUtil.getInteger(mContext,
//                CommonConstants.shoppingCartGoodsNum, 0);
    }

    /**
     * 获取当前购物车中的商品列表
     *
     * @return
     */
    public static List<GoodsBean> getGoods() {
        List<GoodsBean> list = new ArrayList<>();
        for (GoodsBean bean : shoppingCart.values()) {
            if (bean.getNum() > 0)
                list.add(bean);
        }
        return list;
    }

    /**
     * 获取当前购物车中选中的（要购买的）商品列表
     *
     * @return
     */
    public static List<GoodsBean> getSelectedGoods() {
        List<GoodsBean> list = new ArrayList<>();
        for (GoodsBean bean : shoppingCart.values()) {
            if (bean.getNum() > 0 && bean.isSelected())
                list.add(bean);
        }
        return list;
    }

    /**
     * 获取购物车中选中商品的总价格
     *
     * @return
     */
    public static double getTotalPrice() {
        double totalPrice = 0;
        for (GoodsBean bean : shoppingCart.values()) {
            LogUtil.i(bean.toString());

            if (bean.isSelected())
                totalPrice += bean.getPrice() * bean.getNum();
        }

        LogUtil.w("totalPrice " + totalPrice);
        return totalPrice;
    }

    /**
     * 将购物车中的数据保存到本地
     */
    public static void saveShoppingCart() {
        String saveStr = StreamUtils.objectToString(shoppingCart);
        SharedPreferencesUtil.putString(mContext, CommonConstants.shoppingCart, saveStr);
    }

    //恢复保存的购物车数据
    static {
        String saveStr = SharedPreferencesUtil.getString(mContext,
                CommonConstants.shoppingCart, "");
        if (saveStr != "") {
            shoppingCart = (Map<String, GoodsBean>) StreamUtils.stringToObject(saveStr);
        }
    }
}
