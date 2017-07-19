package com.gaia.member.gaiatt.mall.bean;

import java.io.Serializable;

/**
 * @author ZhangHaiTao
 * @ClassName: GoodsBean
 * Description: 商城主界面中商品fragment中显示的商品类定义
 * @date 2016/6/7 16:06
 */
public class GoodsBean implements Serializable{
    private static final long serialVersionUID = 100L;
    String goodId;
    String goodsName;
    String goodsDesc;
    float price;
    String detailUrl;

    //和购物车相关的属性
    int num ;//购买商品数量
    boolean isSelected;//是否选中

    @Override
    public String toString() {
        return "GoodsBean{" +
                "goodId='" + goodId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", price=" + price +
                ", detailUrl='" + detailUrl + '\'' +
                ", num=" + num +
                ", isSelected=" + isSelected +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void addNum(int num) {
        this.num += num;
    }

    public void deleteNum(int num) {
        this.num -= num;
        if (this.num < 0) this.num = 0;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
