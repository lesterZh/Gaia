package com.gaia.member.gaiatt.healthmanage.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.activity.HealthRecordActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.HorizontalListViewAdapter;
import com.gaia.member.gaiatt.healthmanage.ui.HorizontalListView;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.MealBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: DietFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 饮食记录界面Fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class DietFragment extends Fragment {




    //早餐
    @Bind(R.id.hlv_diet_breakfirst)
    HorizontalListView hlvDietBreakfirst;
    private boolean isAddBreakfast = false;
    ArrayList<String> datasLunch;//保存早餐数据
    private HorizontalListViewAdapter breakfirstAdapter;
    //午餐
    @Bind(R.id.hlv_diet_lunch)
    HorizontalListView hlvDietLunch;
    private boolean isAddLunch = false;
    ArrayList<String> datasDinner;//保存午餐数据
    private HorizontalListViewAdapter lunchAdapter;
    //晚餐
    @Bind(R.id.hlv_diet_dinner)
    HorizontalListView hlvDietDinner;
    private boolean isAddDinner = false;
    ArrayList<String> datasBreakfirst;//保存晚餐数据
    private HorizontalListViewAdapter dinnerAdapter;


    private MealFragment mealFragment;//饮食选择器

    private View view;//fragment视图


    @Nullable
    @Override
    public View getView() {
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diet_fragment, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }


    //初始化三餐分组数据
    private void initData() {
        datasBreakfirst = new ArrayList<>();
        datasLunch = new ArrayList<>();
        datasDinner = new ArrayList<>();

    }

    //初始化视图
    private void initView() {
        breakfirstAdapter = new HorizontalListViewAdapter(getActivity(), datasBreakfirst);
        hlvDietBreakfirst.setAdapter(breakfirstAdapter);//设置早餐数据
        lunchAdapter = new HorizontalListViewAdapter(getActivity(), datasLunch);
        hlvDietLunch.setAdapter(lunchAdapter);//设置午餐数据
        dinnerAdapter = new HorizontalListViewAdapter(getActivity(), datasDinner);
        hlvDietDinner.setAdapter(dinnerAdapter);//设置晚餐数据
        //饮食记录早餐删除
        breakfirstAdapter.setOnHorzontalLongClicklistener(new HorizontalListViewAdapter.OnHorzontalLongClicklistener() {
            @Override
            public void onLongClick(View view, int position) {
                datasBreakfirst.remove(position);
                breakfirstAdapter.notifyDataSetChanged();
            }
        });
        //饮食记录午餐删除
        lunchAdapter.setOnHorzontalLongClicklistener(new HorizontalListViewAdapter.OnHorzontalLongClicklistener() {
            @Override
            public void onLongClick(View view, int position) {
                datasLunch.remove(position);//移除选择数据
                lunchAdapter.notifyDataSetChanged();
            }
        });
        //饮食记录晚餐删除
        dinnerAdapter.setOnHorzontalLongClicklistener(new HorizontalListViewAdapter.OnHorzontalLongClicklistener() {
            @Override
            public void onLongClick(View view, int position) {
                datasDinner.remove(position);
                dinnerAdapter.notifyDataSetChanged();
            }
        });
    }
    /**
     * 销毁View
     * */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 早餐选择
     * */
    @OnClick(R.id.img_item_breakfast)
    void imgItemBreakfast() {
        isAddBreakfast = true;
        showMealFragment();
    }

    /**
     * 午餐选择
     * */
    @OnClick(R.id.img_item_lunch)
    void imgItemLunch() {
        isAddLunch = true;
        showMealFragment();
    }

    /**
     * 晚餐选择
     * */
    @OnClick(R.id.img_item_dinner)
    void imgItemDinner() {
        isAddDinner = true;
        showMealFragment();
    }

    /**
     * 启动饮食选择器
     * */
    private void showMealFragment() {
        if (mealFragment == null) {
            mealFragment = new MealFragment();
            mealFragment.setDietHandler(mHandler);
        }
        ((HealthRecordActivity) getActivity()).getDietDialogLayout().setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.bottom_in, 0);
        transaction.replace(R.id.ll_diet_dialog, mealFragment);
        transaction.commit();
    }


    /**
     * 获取饮食选择器的消息数据
     * */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MealBean mealBean = (MealBean) msg.obj;
            String strData = mealBean.getMealName() + "(" + mealBean.getUnit() + ")";
            if (isAddBreakfast == true) {//判断是添加早餐
                isAddBreakfast = false;
                datasBreakfirst.add(strData);
                breakfirstAdapter.notifyDataSetChanged();//通知更新适配器
            } else if (isAddLunch == true) {//添加午餐
                isAddLunch = false;
                datasLunch.add(strData);
                lunchAdapter.notifyDataSetChanged();
            } else if (isAddDinner == true) {
                isAddDinner = false;
                datasDinner.add(strData);
                dinnerAdapter.notifyDataSetChanged();
            }
        }
    };

}
