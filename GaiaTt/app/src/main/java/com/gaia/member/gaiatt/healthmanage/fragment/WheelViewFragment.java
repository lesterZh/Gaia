package com.gaia.member.gaiatt.healthmanage.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.activity.DietPlanDetailActivity;
import com.gaia.member.gaiatt.healthmanage.activity.HealthRecordActivity;
import com.gaia.member.gaiatt.ui.DegreeScaleView;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.MealBean;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WangHaohan on 2016/5/18.
 *
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/18 18:21
 */
public class WheelViewFragment extends Fragment {


    @Bind(R.id.degree_title_text_view)
    TextView degreeTitleTextView;
    @Bind(R.id.degree_value_text_view)
    TextView degreeValueTextView;
    @Bind(R.id.degree_scale_view)
    DegreeScaleView degreeScaleView;

    private View view;//WheelViewFragment依附视图
    private Handler mealHandler;
    private String wheelData;

    public void setMealHandler(Handler mealHandler) {
        this.mealHandler = mealHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wheelview_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        degreeScaleView.setOnValueChangeListener(new DegreeScaleView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                degreeValueTextView.setText(value + "kg");
                wheelData=value + "kg";
            }
        });
    }

    //保存数据
    @OnClick(R.id.tv_wheel_save)
    void tvWheelSave(){
        if (wheelData != null&&!wheelData.equals("kg")) {
            Message msg=Message.obtain();
            MealBean mealBean=new MealBean();
            Bundle bundle=getArguments();
            String mealName=bundle.getString("mealName","");
            mealBean.setMealName(mealName);
            mealBean.setUnit(wheelData);
            msg.obj=mealBean;
            mealHandler.sendMessage(msg);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction translaction = manager.beginTransaction();
            translaction.remove(this);
            translaction.commit();
            wheelData=null;
            try{
                ((HealthRecordActivity) getActivity()).getDietDialogLayout().setVisibility(View.GONE);
            }catch (ClassCastException e){
                ((DietPlanDetailActivity) getActivity()).getDietDialogLayout().setVisibility(View.GONE);
            }
        }else {
            ToastUtil.show(getActivity(),"请选择质量");
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

