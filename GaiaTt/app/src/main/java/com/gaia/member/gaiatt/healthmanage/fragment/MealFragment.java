package com.gaia.member.gaiatt.healthmanage.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.activity.DietPlanDetailActivity;
import com.gaia.member.gaiatt.healthmanage.activity.HealthRecordActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.MealGridViewAdapter;
import com.gaia.member.gaiatt.healthmanage.adapter.MealViewPagerAdapter;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.MealBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.MealParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: MealFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 饮食选择器Fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class MealFragment extends Fragment implements Serializable {

    @Bind(R.id.tv_meal_commont)
    TextView tvMealCommont;
    @Bind(R.id.tv_meal_main)
    TextView tvMealMain;
    @Bind(R.id.tv_meal_nai)
    TextView tvMealNai;
    @Bind(R.id.tv_meal_rou)
    TextView tvMealRou;
    @Bind(R.id.tv_meal_next)
    TextView tvMealNext;
    @Bind(R.id.tv_line_commont)
    TextView tvLineCommont;
    @Bind(R.id.tv_line_main)
    TextView tvLineMain;
    @Bind(R.id.tv_line_nai)
    TextView tvLineNai;
    @Bind(R.id.tv_line_rou)
    TextView tvLineRou;
    @Bind(R.id.vp_meal_container)
    ViewPager vpMealContainer;
    private View view;
    private WheelViewFragment wheelViewFragment;//刻度Fragment
    private TextView[] tvMeals;
    private TextView[] tvLine;
    private static List<MealBean> commonList = new ArrayList<>();
    private static List<MealBean> stapleFoodList = new ArrayList<>();
    private MealGridViewAdapter mealGridViewAdapter;

    public String dietData;//选择器获取结果
    private Handler dietHandler;//消息传递
    private List<View> vpList;//消息传递

    public void setDietHandler(Handler dietHandler) {
        this.dietHandler = dietHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meal_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    //初始化数据
    private void initData() {
        tvMeals = new TextView[]{tvMealCommont, tvMealMain, tvMealNai, tvMealRou};
        tvLine = new TextView[]{tvLineCommont, tvLineMain, tvLineNai, tvLineRou};
        vpList=new ArrayList<>();
        setGridViewItem();//ViewPager设置数据
    }

    //下一步
    @OnClick(R.id.tv_meal_next)
    void tvMealNext() {
        if (wheelViewFragment == null) {
            wheelViewFragment = new WheelViewFragment();
            wheelViewFragment.setMealHandler(mHandler);
        }
        if (dietData != null ) {
            Bundle bundle = new Bundle();
            bundle.putString("mealName", dietData);
            wheelViewFragment.setArguments(bundle);
            //选择器
            try {
                ((HealthRecordActivity) getActivity()).getDietDialogLayout().setVisibility(View.VISIBLE);
            } catch (ClassCastException e) {
                ((DietPlanDetailActivity) getActivity()).getDietDialogLayout().setVisibility(View.VISIBLE);
            }
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.bottom_in, 0);
            transaction.replace(R.id.ll_diet_dialog, wheelViewFragment);
            transaction.commit();
        } else {
            ToastUtil.show(getActivity(), "请选择饮食");
        }
        dietData=null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //常用
    @OnClick(R.id.tv_meal_commont)
    void tvMealCommon() {
        vpMealContainer.setCurrentItem(CommonConstants.MEALCOMMON);
        setView(CommonConstants.MEALCOMMON);
    }

    //主食
    @OnClick(R.id.tv_meal_main)
    void tvMealMain() {
        vpMealContainer.setCurrentItem(CommonConstants.MEALMAIN);
        setView(CommonConstants.MEALMAIN);
    }

    //奶类
    @OnClick(R.id.tv_meal_nai)
    void tvMealNai() {
        vpMealContainer.setCurrentItem(CommonConstants.MEALDAIRY);
        setView(CommonConstants.MEALDAIRY);
    }

    //肉类
    @OnClick(R.id.tv_meal_rou)
    void tvMealRou() {
        vpMealContainer.setCurrentItem(CommonConstants.MEALMEAT);
        setView(CommonConstants.MEALMEAT);
    }


    //设置Bar切换
    private void setView(int type) {
        if (tvMeals != null) {
            for (int i = 0; i < tvMeals.length; i++) {
                if (i == type) {
                    setItemView(tvMeals[i], getViewColor(R.color.blue));
                    tvLine[i].setVisibility(View.VISIBLE);
                } else {
                    setItemView(tvMeals[i], getViewColor(R.color.text_index));
                    tvLine[i].setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void setItemView(TextView tvMeal, int viewColor) {
        tvMeal.setTextColor(viewColor);
    }


    private int getViewColor(int color) {
        return getActivity().getResources().getColor(color);
    }

    //设置网格子视图
    private void setGridViewItem() {
        for (int i = 0; i <tvMeals.length ; i++) {//ViewPagetr数据源
            View gridView = getActivity().getLayoutInflater().inflate(R.layout.mealgrid_fragment_layout, null);
            GridView gvMealdata = (GridView) gridView.findViewById(R.id.gv_mealdata);
            List<MealBean> dataList= getLocalJsonData(i);//数据源
            mealGridViewAdapter = new MealGridViewAdapter(getActivity(), (ArrayList<MealBean>) dataList);
            gvMealdata.setAdapter(mealGridViewAdapter);
            mealGridViewAdapter.setReGetClickListener(mReGetClickListener);
            vpList.add(gridView);
        }
        MealViewPagerAdapter mealViewPagerAdapter=new MealViewPagerAdapter(getActivity(), (ArrayList<View>) vpList);
        vpMealContainer.setAdapter(mealViewPagerAdapter);
        vpMealContainer.setOnPageChangeListener(mOnPageChangeListener);
    }

    /**
     * ViewPager滑动改变监听
     * */
    ViewPager.OnPageChangeListener mOnPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setView(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    MealGridViewAdapter.ReGetClickListener mReGetClickListener = new MealGridViewAdapter.ReGetClickListener() {
        @Override
        public void getTextString(String result) {
            dietData = result;
        }
    };



    //得到本地json数据
    private List<MealBean> getLocalJsonData(int type) {
        String mealJsonString = AssetsFileUtil.getJsonStr(getActivity(), "meal.txt");
        if (mealJsonString != null) {
            MealParamBean mealParamBean = GsonTools.getMealParamBean(mealJsonString);
            commonList = mealParamBean.getParam().getCommonList();
            stapleFoodList = mealParamBean.getParam().getStapleFoodList();
            switch (type) {
                case CommonConstants.COMMON:
                    return commonList;//常用
                case CommonConstants.STAPLEFOOD:
                    return stapleFoodList;//主食
                case CommonConstants.DAIRY:
                    return commonList;//常用
                case CommonConstants.MEAT:
                    return stapleFoodList;//主食
                default:
                    break;
            }
        }
        return null;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MealBean mealBean = (MealBean) msg.obj;
            Message message = Message.obtain();
            message.obj = mealBean;
            dietHandler.sendMessage(message);
        }
    };
}

