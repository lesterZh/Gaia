package com.gaia.member.gaiatt.utils.gsonutils.GsonBean;

import java.util.List;

/**
 * Created by WangHaohan on 2016/5/5.
 */
public class MealParamListBean {

    private List<MealBean> commonList;
    private List<MealBean> stapleFoodList;

    public List<MealBean> getStapleFoodList() {
        return stapleFoodList;
    }

    public void setStapleFoodList(List<MealBean> stapleFoodList) {
        this.stapleFoodList = stapleFoodList;
    }

    public List<MealBean> getCommonList() {
        return commonList;
    }

    public void setCommonList(List<MealBean> commonList) {
        this.commonList = commonList;
    }
}
