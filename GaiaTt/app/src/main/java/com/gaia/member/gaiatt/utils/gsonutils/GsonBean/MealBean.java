package com.gaia.member.gaiatt.utils.gsonutils.GsonBean;

import java.io.Serializable;

/**
 * Created by WangHaohan on 2016/5/5.
 */
public class MealBean implements Serializable{
    private int mealId;
    private int group;
    private String mealName;
    private String unit;
    private int sort;
    private String calorie;

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
