package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: FindPasswordActivity
 * @Package com.gaia.member.gaiatt.login.activity
 * @Description: 找回密码界面，客户需求改变，只需要手机和验证码，该功能不再需要
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/25
 */
public class FindPasswordActivity extends AppCompatActivity {


    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.edit_verification_code)
    EditText editVerificationCode;
    @Bind(R.id.btn_send_verification_code)
    Button btnSendVerificationCode;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_next)
    Button btnNext;
    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);

        tvTitleToolBar.setText("找回密码");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.gotoIntent(mContext, LoginActivity.class);
            }
        });


    }
}
