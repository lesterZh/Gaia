package com.gaia.member.gaiatt.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.ui.ResidencePicker;
import com.gaia.member.gaiatt.healthmanage.ui.CustomDrinkPicker;
import com.gaia.member.gaiatt.healthmanage.ui.CustomSmokePicker;
import com.gaia.member.gaiatt.healthmanage.ui.CustomSportPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by WangHaohan on 2016/5/27.
 *
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/27 10:09
 */
public class PickerDialogUtils {


    private OnDialogClickListener ls;

    /**
     * 单选择器
     *
     * @param context     上下文
     * @param title       选择器标题
     * @param list        选择器数据源
     * @param selectIndex 当前默认选择位置
     * @param ls          接口
     */
    public void setSmokePickerDialog(Context context, String title, ArrayList<String> list, int selectIndex, final
    OnDialogClickListener ls) {
        this.ls = ls;
        CustomSmokePicker customSmokePicker = new CustomSmokePicker(context);
        customSmokePicker.setTitle(title);
        customSmokePicker.setSubTitles("", "");
        //1、2代表第几个选中器
        customSmokePicker.setWheelDataIndex(1, list, selectIndex);//5当前选中位置
        customSmokePicker.setSaveButtonClickListener(new CustomSmokePicker.OnSaveButtonClickListener() {
            @Override
            public void okClick(String text1, String text2) {
                ls.okDialogClick(text1, text2);
            }
        });
        customSmokePicker.show(true);
    }

    /**
     * 单选择器
     *
     * @param context     上下文
     * @param title       选择器标题
     * @param list        选择器数据源
     * @param selectIndex 当前默认选择位置
     * @param ls          接口
     */
    public void setSinglePickerDialog(Context context, String title, ArrayList<String> list, int selectIndex, final
    OnDialogClickListener ls) {
        this.ls = ls;
        CustomSportPicker customSportPicker = new CustomSportPicker(context);
        customSportPicker.setTitle(title);
        customSportPicker.setSubTitles("", "");
        //1、2代表第几个选中器
        customSportPicker.setWheelDataIndex(1, list, selectIndex);//1当前选中位置
        customSportPicker.setSaveButtonClickListener(new CustomSportPicker.OnSaveButtonClickListener() {
            @Override
            public void okClick(String text1, String text2) {
                ls.okDialogClick(text1, text2);
            }
        });
        customSportPicker.show(true);
    }

    /**
     * 单选择器
     *
     * @param context     上下文
     * @param title       选择器标题
     * @param SubTitle1   选择器1标题
     * @param SubTitle2   选择器2标题
     * @param Onelist     选择器1数据源
     * @param twolist     选择器2数据源
     * @param selectIndex 当前默认选择位置
     * @param ls          接口
     */
    public void setDoublePickerDialog(Context context, String title, String SubTitle1, String SubTitle2,
                                      ArrayList<String> Onelist, ArrayList<String> twolist, int selectIndex, final
    OnDialogClickListener ls) {
        this.ls = ls;
        CustomDrinkPicker customDrinkPicker = new CustomDrinkPicker(context);
        customDrinkPicker.setTitle(title);
        customDrinkPicker.setSubTitles(SubTitle1, SubTitle2);
        //1、2代表第几个选中器
        customDrinkPicker.setWheelDataIndex(1, Onelist, selectIndex);//3当前选中位置
        customDrinkPicker.setWheelDataIndex(2, twolist, selectIndex);
        customDrinkPicker.setSaveButtonClickListener(new CustomDrinkPicker.OnSaveButtonClickListener() {
            @Override
            public void okClick(String text1, String text2) {
                ls.okDialogClick(text1, text2);
            }
        });
        customDrinkPicker.show(true);
    }

    /**
     *
     * */
    public void  setProAndCityDialog(Context context, String title, ArrayList<String> Onelist, ArrayList<String> twolist, final
                                             OnDialogClickListener ls) {
        this.ls = ls;
        ResidencePicker residencePicker = new ResidencePicker(context);
        residencePicker.setTitle(title);
        residencePicker.setSubTitles("", "");
        //1、2代表第几个选中器
        residencePicker.setWheelDataIndex(1, Onelist, 0);//0当前选中位置
        residencePicker.setWheelDataIndex(2, twolist, 0);
        residencePicker.setSaveButtonClickListener(new ResidencePicker.OnSaveButtonClickListener() {
            @Override
            public void okClick(String text1, String text2) {
                ls.okDialogClick(text1, text2);
            }
        });
        residencePicker.show(true);
    }







    public interface OnDialogClickListener {
        void okDialogClick(String text1, String text2);
    }
}
