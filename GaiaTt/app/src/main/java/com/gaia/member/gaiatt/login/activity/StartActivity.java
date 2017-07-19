package com.gaia.member.gaiatt.login.activity;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.LoginBean;
import com.gaia.member.androidlib.net.bean.VersionBean;
import com.gaia.member.androidlib.utils.LogUtil;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.leancloud.ChatUtil;
import com.gaia.member.gaiatt.utils.AMapUtils;
import com.gaia.member.gaiatt.utils.ActivityUtils;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.UserUtils;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;
import com.gaia.member.gaiatt.utils.permissiongen.PermissionFail;
import com.gaia.member.gaiatt.utils.permissiongen.PermissionGen;
import com.gaia.member.gaiatt.utils.permissiongen.PermissionSuccess;
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
 * @Title: StartActivity
 * @Package com.gaia.member.gaiatt.login.activity
 * @Description:  初始登录界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/25.
 */
public class StartActivity extends Activity {
    @Bind(R.id.btn_test)
    Button btnTest;//测试button


    private Button btn_regist, btn_login;//不需要的
    private Activity mContext;//上下文

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        initView();
        //初始化leanCloud
        ChatUtil.initLeanCloud(this);
        //获取权限设置定位
        initLocationOption();

        curVersion = getAppVersionName(this);


//        getLastedVersion();
        //登录流程
        //1.最新版本获取，提示更新
        //2.确定更新，进入新版本下载网页
        //3.取消更新，判断是否存在userID和token
        //4.存在，通过userid、token登录
        //5.不存在，进入登录界面
    }
    /**
     * 设置定位
     * */
    private void initLocationOption() {
        //定位权限
        PermissionGen.with(StartActivity.this)
                .addRequestCode(CommonConstants.MAPLOCATION)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE
                )
                .request();
    }
    //定位成功
    @PermissionSuccess(requestCode = CommonConstants.MAPLOCATION)
    public void openLocation(){
        AMapUtils.setLocation();//获取定位权限后设置定位
    }

    //定位失败
    @PermissionFail(requestCode = CommonConstants.MAPLOCATION)
    public void failOpenLocation(){
        ToastUtil.show(this, getString(R.string.location_fail));
    }

    /**
     * 定位权限结果
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                     int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    /**
     * 初始化控件，设置监听事件
     */
    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_regist = (Button) findViewById(R.id.btn_regist);

        //登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });

        //注册
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RegistWelcomeActivity.class);
                startActivity(intent);
//                finish();
            }
        });


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityUtils.gotoIntent(mContext, HomeActivityNew.class);
                testFun();
            }
        });


    }

    String lastedVersion = null;//最新版本号
    String curVersion = null;//当前版本号

    /**
     * 获取最新的版本,和当前版本比较
     */
    private void getLastedVersion() {
        NetUtils.getNetUtilsInstance().getNetApiServiceInterface().getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VersionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {
                        if (versionBean.isSuccess()) {
                            //成功
                            lastedVersion = versionBean.getParam().getAndroidVersion();

                            //和当前版本进行比较判断
                            if (curVersion.equals(lastedVersion)) {//已经是最新版本了
                                autoLogin();//登录

                            } else {//提示更新
                                upDate();
                            }
                        } else {
                            //失败

                        }
                    }
                });

    }


    /**
     * 自动登录，如果存在userId和token则自动登录，否则进入登录界面
     */
    private void autoLogin() {
        if (UserUtils.getUserId() == "" || UserUtils.getUserToken() == "") {
            //不存在，进入登录界面
            ActivityUtils.gotoIntent(mContext, LoginActivity.class);
        } else {
            //将本地的userId和token发给服务器进行登录
            postLoginWithToken();
        }
    }

    /**
     * testButton 的测试代码
     */
    private void testFun() {
        ChatUtil.gotoChatActivity(mContext, "tom", "jack");
    }


    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";//版本
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            LogUtil.e("VersionInfo", "Exception");
        }
        return versionName;
    }

    /**
     * 使用系统浏览器打开指定网页
     * @param url 网址
     */
    private void openBrowser(String url) {
        Intent intent= new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(intent);
    }


    /**
     * 版本更新
     */
    private void upDate() {
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("版本更新提示")
                .setMessage("您确定要下载最新版本吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openBrowser("www.baidu.com");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        autoLogin();//不更新 登录
                    }
                });
        dialog = builder.create();
        dialog.show();

    }


    //通过userID和token登录, 返回bean可能需要修改
    private void postLoginWithToken() {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("useId").value(UserUtils.getUserId())
                    .key("token").value(UserUtils.getUserToken())
//                    .key("ulon").value(99)//经度
//                    .key("ulat").value(99)//纬度
//                    .key("geohashLength").value(geohashLength)
//                    .key("limitKm").value(limitKm)
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postLoginWithToken(body)
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
                                //登录成功
                                Toast.makeText(mContext, "成功", Toast.LENGTH_LONG).show();
                                ActivityUtils.gotoIntent(mContext, HomeActivityNew.class);
                            } else {
                                Toast.makeText(mContext, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}