package com.gaia.member.gaiatt.healthmanage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.HealthEvaluationAdapter;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.ProcessEvaluationBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.ProcessEvaluationBean.ParamBean.ListBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: ProcessEvaluationActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 过程评价列表活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class ProcessEvaluationActivity extends AppBaseActivity {


    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    @Bind(R.id.titlebar_search_img)
    ImageView titlebarSearchImg;
    @Bind(R.id.listview_peocess_evat)
    PullOrLoadListView listviewPeocessEvat;
    private HealthEvaluationAdapter healthEvaluationAdapter;
    private ArrayList<ListBean> datas;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_process_evaluation);
        ButterKnife.bind(this);
        titlebarTv.setText(GetResourcesUtils.getString(this,R.string.process_evaluation_title));
        titlebarSearchImg.setVisibility(View.GONE);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        datas = new ArrayList<>();
        datas = (ArrayList<ListBean>) getLocalEvaluationData();
    }

    @Override
    protected void loadData() {
        healthEvaluationAdapter = new HealthEvaluationAdapter(this, datas);
        listviewPeocessEvat.setAdapter(healthEvaluationAdapter);
    }

    //获取本地数据
    private List getLocalEvaluationData() {
        String evaluationJsonString = AssetsFileUtil.getJsonStr(this,"healthevaluationt.txt");
        List<ListBean> mList = new ArrayList<>();
        if (evaluationJsonString != null) {
            ProcessEvaluationBean processEvaluationBean = GsonTools.getProcessEvaluationBean(evaluationJsonString);
            mList = processEvaluationBean.getParam().getList();
        }
        return mList;
    }

    @OnClick(R.id.titlebar_back_ll)
    void  titlebarBackLl(){
        onBackPressed();
    }
}
