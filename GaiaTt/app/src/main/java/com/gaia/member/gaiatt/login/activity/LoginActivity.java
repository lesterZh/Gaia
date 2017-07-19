package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.LoginBean;
import com.gaia.member.androidlib.net.bean.SmsBean;
import com.gaia.member.gaiatt.GaiaCustomApplication;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.ActivityUtils;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.SharedPreferencesUtil;
import com.gaia.member.gaiatt.utils.UiUtils;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: LoginActivity
 * @Package com.gaia.member.gaiatt.login.activity
 * @Description: 用户登录界面，客户需求改变，登录只需要手机和验证码
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/25.
 */
public class LoginActivity extends AppCompatActivity {


    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_regist_new)
    TextView tvRegistNew;
    @Bind(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @Bind(R.id.btn_send_verification_code)
    Button btnSendVerificationCode;
    @Bind(R.id.et_phone)
    EditText etPhone;//电话号码编辑
    @Bind(R.id.et_password)
    EditText etPassword;//密码编辑
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;//标题栏
    @Bind(R.id.edit_verification_code)
    EditText editVerificationCode;//验证码编辑
    private Activity mContext;
    private String phone;//电话号码
    private String password;//密码
    private String verificationCode;//验证码
    private int geohashLength = 1;//登录参数
    private int limitKm = 1; //限制距离


    private static final int TIME_COUNT = 0;
    private static final int COUNT_END = 1;
    private static final int COUNT_NUM = 30;

    private int time = COUNT_NUM;//倒计时
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
        setContentView(R.layout.activity_login);
        mContext = this;
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvTitleToolBar.setText("欢迎登录");

        //发送验证码按键
        btnSendVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    UiUtils.showToast(mContext, getString(R.string.regist_welcome_acitivity_enter_right_phone));
                    UiUtils.shake(etPhone, 2, 300);
                    return;
                }

                if (phone.matches("^1[3578]\\d{9}")) {//判断电话号码
                    //手机号码正确，发生验证码
                    UiUtils.showToast(mContext, getString(R.string.regist_welcome_acitivity_verification_code_sended));
                    sendVerificationCode();

                    //倒计时
                    time = COUNT_NUM;
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
                    UiUtils.showToast(mContext, "手机号格式不正确，请重新输入正确手机号");
                    UiUtils.shake(etPhone, 2, 300);
                }
            }
        });

        //登录操作按键
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = etPhone.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                verificationCode = editVerificationCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    UiUtils.showToast(mContext, "请输入手机号");
                } else if (!phone.matches("^1[3578]\\d{9}")) {
                    UiUtils.showToast(mContext, "手机号格式不正确，请重新输入正确手机号");
                }else if (TextUtils.isEmpty(verificationCode)) {
                    UiUtils.showToast(mContext, "请输入验证码");
                } else if (verificationCode.length()!=6) {
                    UiUtils.showToast(mContext, "校验码是6位数字");
                }else {//登录验证
                    ActivityUtils.gotoIntent(mContext, HomeActivityNew.class);
                    setLoginVerification(phone, verificationCode);
                }
            }
        });

        tvRegistNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.gotoIntent(mContext, RegistWelcomeActivity.class);
            }
        });

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.gotoIntent(mContext, FindPasswordActivity.class);
            }
        });
    }


    //登录验证API接口
    private void setLoginVerification(String phone, String verificationCode) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("phone").value(phone)
                    .key("smsCode").value(verificationCode)
                    .key("ulon").value(99)//经度
                    .key("ulat").value(99)//纬度
                    .key("geohashLength").value(geohashLength)
                    .key("limitKm").value(limitKm)
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postLogin(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<LoginBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            Log.e("error", "" + e.getMessage());
                        }

                        @Override
                        public void onNext(LoginBean loginBean) {
                            if (loginBean.isSuccess()) {
                                //保存用户Id和Token
                                saveIdAndToken(loginBean.getParam().getUserinfo());
                                Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_LONG).show();
                                ActivityUtils.gotoIntent(mContext, HomeActivityNew.class);
                            } else {
                                Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存用户Id和Token
     *
     * @param userinfo 用户个人信息
     */
    private void saveIdAndToken(LoginBean.ParamBean.UserinfoBean userinfo) {
        SharedPreferences.Editor editor = GaiaCustomApplication.mApplication.getSharedPreferences().edit();
        editor.putString(CommonConstants.USERID, userinfo.getUserId());
        editor.putString(CommonConstants.USERTOKEN, userinfo.getAuthToken());
        editor.commit();

    }

    /**
     * 验证输入的用户名和密码是否有效
     *
     * @param phone
     * @param password
     */
    private void isPhonePasswordValid(String phone, String password) {
        UiUtils.showToast(mContext, "正在登录……");
        ActivityUtils.gotoIntent(mContext, HomeActivityNew.class);
    }


    //发送验证码
    public void sendVerificationCode() {
        phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {//如果电话号码等于空
            UiUtils.showToast(mContext, "请输入手机号");
        } else {
            try {
                //构造请求体
                JSONStringer jsonStringer = new JSONStringer().object()
                        .key("phone").value(phone)
                        .endObject();
                String jsonStr = jsonStringer.toString();
                RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
                //发起请求
                NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postAmsGet(body)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SmsBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("error", "" + e.getMessage());
                            }

                            @Override
                            public void onNext(SmsBean smsBean) {
                                if (smsBean.getSuccess()) {
                                    Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_LONG).show();
                                    editVerificationCode.setText(smsBean.getReturnCode() + "");
                                } else {
                                    Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
