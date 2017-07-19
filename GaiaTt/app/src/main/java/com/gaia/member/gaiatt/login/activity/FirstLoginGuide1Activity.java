package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.UiUtils;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: FindPasswordActivity
 * @Package com.gaia.member.gaiatt.login.activity
 * @Description: 用户第一次登录引导界面，设置性别
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/25
 */
public class FirstLoginGuide1Activity extends AppCompatActivity {


    @Bind(R.id.ll_female_select)
    LinearLayout llFemaleSelect;
    @Bind(R.id.ll_male_select)
    LinearLayout llMaleSelect;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;
    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_first_login_guide1);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvTitleToolBar.setText("基本资料填写");
        llFemaleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.showToast(mContext, "你选择了女");
            }
        });

        llMaleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.showToast(mContext, "你选择了男");
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.gotoIntent(mContext, FirstLoginGuide2Activity.class);
            }
        });
    }
}
