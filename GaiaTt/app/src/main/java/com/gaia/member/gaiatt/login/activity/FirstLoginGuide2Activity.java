package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.login.ui.DegreeScaleView;
import com.gaia.member.gaiatt.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: FindPasswordActivity
 * @Package com.gaia.member.gaiatt.login.activity
 * @Description: 用户第一次登录引导界面，设置体重
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/25
 */
public class FirstLoginGuide2Activity extends AppCompatActivity {


    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.degree_value_text_view)
    TextView degreeValueTextView;
    @Bind(R.id.degree_scale_view)
    DegreeScaleView degreeScaleView;
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;
    private Activity mContext;
    private float curWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_first_login_guide2);
        ButterKnife.bind(this);

        tvTitleToolBar.setText("基本资料填写");
        degreeScaleView.setOnValueChangeListener(new DegreeScaleView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                degreeValueTextView.setText(value + "kg");
                curWeight = value;
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.showToast(mContext, "进入主界面");
            }
        });
    }
}
