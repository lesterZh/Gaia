package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.UiUtils;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 欢迎注册界面，客户需要改变，注册仅需要手机和验证码
 * created by zhangHaiTao at 2016/4/25
 */
public class RegistWelcomeActivity extends Activity {

    private static final int TIME_COUNT = 0;
    private static final int COUNT_END = 1;

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
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;

    private Activity context;

    private int time = 60;//倒计时
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIME_COUNT:
                    time--;
                    btnSendVerificationCode.setText(time + "S 重新发送");
                    break;
                case COUNT_END:
                    btnSendVerificationCode.setText("发送验证码");
                    btnSendVerificationCode.setEnabled(true);//恢复点击响应
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_regist_welcome);
        ButterKnife.bind(this);

        initView();


    }

    private void initView() {
        tvTitleToolBar.setText(R.string.regist_welcome_acitivity_title);
        //发送验证码
        btnSendVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    UiUtils.showToast(context, getString(R.string.regist_welcome_acitivity_enter_right_phone));
                    UiUtils.shake(etPhone, 2, 300);
                    return;
                }

                if (phone.matches("^1[3578]\\d{9}")) {
                    //手机号码正确，发生验证码
                    UiUtils.showToast(context, getString(R.string.regist_welcome_acitivity_verification_code_sended));
                    sendVerificationCode();

                    time = 60;
                    btnSendVerificationCode.setText(time + getString(R.string.regist_welcome_acitivity_wait));
                    btnSendVerificationCode.setEnabled(false);//禁止点击
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (time <= 0) {
                                handler.removeCallbacks(this);
                                handler.sendEmptyMessage(COUNT_END);
                                return;
                            }
                            handler.postDelayed(this, 1000);
                            handler.sendEmptyMessage(TIME_COUNT);
                        }
                    };
                    handler.postDelayed(runnable, 1000);

                } else {
                    UiUtils.showToast(context, "请输入正确的手机号码");
                    UiUtils.shake(etPhone, 2, 300);
                }
            }
        });

        //下一步
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVerificationCode();
            }
        });
    }

    /**
     * 判定验证码是否正确
     */
    private void checkVerificationCode() {
        String code = editVerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            UiUtils.showToast(context, "请输入验证码");
        } else {
            if (!code.equals("1234")) {
                UiUtils.showToast(context, "验证码不正确");
            } else {
                //验证码正确
                Intent intent = new Intent(context, RegistDetailActivity.class);
                startActivity(intent);

                time = 0;//恢复发送验证码按钮
                btnSendVerificationCode.setEnabled(true);
            }
        }

        //测试跳转
        ActivityUtils.gotoIntent(this, RegistDetailActivity.class);
    }

    /**
     * 判定输入密码是否合法
     */
    private void checkPasswordValid() {
        String pw = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(pw)) {
            UiUtils.showToast(context, "密码输入不能为空");
        } else {
            if (!pw.matches("^[a-zA-Z0-9]\\w{5,17}$")) {
                //输入密码不合法
                UiUtils.showToast(context, "请输入6~18位密码");
            } else {
//                        if () 密码2次确认
                Intent intent = new Intent(context, RegistDetailActivity.class);
                startActivity(intent);
                time = 0;//恢复发送验证码按钮
            }
        }
    }


    /**
     * 发送手机验证码
     */
    private void sendVerificationCode() {

    }

    /**
     * @return 从网络中获取验证码，用于用户输入比较
     */
    private String getVerificationCode() {
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
