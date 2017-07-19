package com.gaia.member.gaiatt.healthmanage.utils;

import android.content.Context;

import com.gaia.member.gaiatt.R;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: LocalDataUtils
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 获取保存在本地的数据类
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class LocalDataUtils {
    /**
     * 获取睡眠时间选择器数据源
     * */
    public static ArrayList<String> getTimeArrayList(){
        ArrayList<String> timeList=new ArrayList<>();
        for (int i = 0; i <24 ; i++) {
            String hString=null;
            if (i <10) {
                hString="0"+i;
            }else {
                hString=i+"";
            }
            for (int j = 0; j <12 ; j++) {
                String mString=null;
                if (j < 2) {
                    mString="0"+j*5;
                }else {
                    mString=j*5+"";
                }
                timeList.add(hString+":"+mString);
            }
        }
        return timeList;
    }
    /**
     * 获取饮酒种类选择器数据源
     * @param context 上下文
     * */
    public static ArrayList<String> getDrinkTypeArrayList(Context context){
        return getArrayList(context,R.array.health_record_wine_type);
    }
    /**
     * 获取饮酒量选择器数据源
     * */
    public static ArrayList<String> getDrinkNumArrayList(){
        ArrayList<String> drinkNumList=new ArrayList<>();
        String numString=null;
        for (int i = 1; i <20; i++) {
            numString=50*i+"";
            drinkNumList.add(numString);
        }
        return drinkNumList;
    }
    /**
     * 获取吸烟数选择器数据源
     * */
    public static ArrayList<String> getSmokeNumArrayList(){
        ArrayList<String> smokeNumList=new ArrayList<>();
        String numString=null;
        for (int i = 1; i <21; i++) {
            numString=i+"";
            smokeNumList.add(numString);
        }
        return smokeNumList;
    }
    /**
     * 获取运动感知强度选择器数据源
     * @param context 上下文
     * */
    public static ArrayList<String> getSportArrayList(Context context){
        return getArrayList(context,R.array.health_plan_sport_feel);
    }
    /**
     * 获取民族选择器数据源
     * @param context 上下文
     * */
    public static ArrayList<String> getNativeArrayList(Context context){
        return getArrayList(context,R.array.picker_native);
    }
    /**
     * 获取文化程度选择器数据源
     * @param context 上下文
     * */
    public static ArrayList<String> getCultrueArrayList(Context context){
        return getArrayList(context,R.array.picker_cultrue);
    }
    /**
     * 获取职业选择器数据源
     * @param context 上下文
     * */
    public static ArrayList<String> getprofessionArrayList(Context context){
        return getArrayList(context,R.array.picker_profession);
    }


    /**
     * @param context 上下文
     * @param id 数组ID
     * */
    private static ArrayList<String> getArrayList(Context context,int id){
        ArrayList<String> list=new ArrayList<>();
        String[] string=context.getResources().getStringArray(id);
        for (int i = 0; i <string.length ; i++) {
            list.add(string[i]);
        }
        return list;
    }
}
