package com.gaia.member.gaiatt.healthmanage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.HealthListViewAdapter;
import com.gaia.member.gaiatt.healthmanage.adapter.HealthQuestionaireAdapter;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: QuestionaireListActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 问卷调查列表活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class QuestionaireListActivity extends AppBaseActivity {


    @Bind(R.id.titlebar_back_img)
    ImageView titlebarBackImg;
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    @Bind(R.id.titlebar_search_img)
    ImageView titlebarSearchImg;
    @Bind(R.id.titlebar_search_ll)
    LinearLayout titlebarSearchLl;
    @Bind(R.id.pull_icon)
    ImageView pullIcon;
    @Bind(R.id.refreshing_icon)
    ImageView refreshingIcon;
    @Bind(R.id.state_tv)
    TextView stateTv;
    @Bind(R.id.state_iv)
    ImageView stateIv;
    @Bind(R.id.head_view)
    RelativeLayout headView;
    @Bind(R.id.list_view_questionaire)
    PullOrLoadListView listViewQuestionaire;
    private ArrayList<HealthPlanBean> planBeanslist;//数据源
    private HealthQuestionaireAdapter adapter;//适配器

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_questionaire_list);
        ButterKnife.bind(this);
        titlebarTv.setText(GetResourcesUtils.getString(this,R.string.questionaire_list_title));
        titlebarSearchLl.setVisibility(View.GONE);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        planBeanslist=new ArrayList<>();
    }

    @Override
    protected void loadData() {
        //设置问卷列表信息
        adapter = new HealthQuestionaireAdapter(this, planBeanslist);
        listViewQuestionaire.setAdapter(adapter);
        listViewQuestionaire.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(QuestionaireListActivity.this,QuestionnaireActivity.class);
                startActivity(intent);
            }
        });

    }
    //返回
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl(){
        onBackPressed();
    }
}
