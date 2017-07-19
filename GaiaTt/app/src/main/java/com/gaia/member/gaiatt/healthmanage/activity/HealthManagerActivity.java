/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthManagerActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 健康管理师活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.HealthDoctorParamBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.gaiaclinic.activity.GaiaDetailActivity;
import com.gaia.member.gaiatt.leancloud.ChatUtil;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * @ClassName: HealthManagerActivity
 *Description: 健康管理师活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthManagerActivity extends AppBaseActivity {

    //标题
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    //标题栏右边图标
    @Bind(R.id.titlebar_search_ll)
    LinearLayout titlebarSearchLl;
    //健康管理师头像
    @Bind(R.id.img_doctor_headpic)
    ImageView imgDoctorHeadpic;
    //健康管理师姓名
    @Bind(R.id.tv_doctor_name)
    TextView tvDoctorName;
    //健康管理师级别
    @Bind(R.id.tv_doctor_education)
    TextView tvDoctorEducation;
    //健康管理师所属医院
    @Bind(R.id.tv_doctor_hospital)
    TextView tvDoctorHospital;
    //健康管理师科别
    @Bind(R.id.tv_doctor_type)
    TextView tvDoctorType;
    //健康管理师经验
    @Bind(R.id.tv_doctor_experience)
    TextView tvDoctorExperience;
    //健康管理师详情布局
    @Bind(R.id.ll_manager_info)
    LinearLayout llManagerInfo;
    //健康管理师介绍
    @Bind(R.id.tv_doctor_info)
    TextView tvDoctorInfo;
    //健康管理师所在门店名
    @Bind(R.id.tv_doctor_hospitalname)
    TextView tvDoctorHospitalname;
    //获取服务器对象
    private  HealthDoctorParamBean.ParamBean  doctorBean;
    //btn是否能点击
    private boolean isClick=true;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_health_manager);
        //绑定控件
        ButterKnife.bind(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //设置标题
        titlebarTv.setText(GetResourcesUtils.getString(this, R.string.health_manager_title));
        //隐藏标题栏右面按钮
        titlebarSearchLl.setVisibility(View.GONE);
    }

    @Override
    protected void loadData() {
        //获取本地数据
        doctorBean=getLocalhealthDoctorData();
        //获取健康管理医师个人信息
        //getServicehealthDoctorData();
        //数据初始化
        setDoctorInfo(doctorBean);
    }

    private void setDoctorInfo(HealthDoctorParamBean.ParamBean doctorBean) {
        if (doctorBean != null) {
            //医师姓名
            tvDoctorName.setText(doctorBean.getName());
            String[] professionalTitl= doctorBean.getProfessionalTitle().split("\\|");
            //级别
            tvDoctorEducation.setText(professionalTitl[0]);
            //所在医院
            tvDoctorHospital.setText(professionalTitl[1]);
            //专业类型
            tvDoctorType.setText(professionalTitl[2]);
            //经验介绍
            tvDoctorExperience.setText(professionalTitl[3]);
            //医师介绍
            tvDoctorInfo.setText(doctorBean.getDetail());
            //所在诊所名称
            tvDoctorHospitalname.setText(doctorBean.getStoreName());
            //医师头像
           // ImageLoaderUtils.loadImageView(imgDoctorHeadpic,doctorBean.getIcon());
        }
    }

    /**
     * 获取健康管理医师个人信息
     * */
    private void getServicehealthDoctorData() {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("userid").value("")//用户Id
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetHealthDoctorInfo(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<HealthDoctorParamBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(HealthDoctorParamBean healthDoctorParamBean) {
                            if (healthDoctorParamBean.isSuccess()) {
                                Toast.makeText(HealthManagerActivity.this, "成功", Toast.LENGTH_LONG).show();
                                //设置健康医师数据
                                setDoctorInfo(healthDoctorParamBean.getParam());
                            } else {
                                Toast.makeText(HealthManagerActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //身边盖亚
    @OnClick(R.id.ll_doctor_in)
    void llDoctorIn(){
        Intent intent=new Intent(HealthManagerActivity.this,GaiaDetailActivity.class);
        intent.putExtra("storeId", doctorBean.getStoreId());//诊所ID
        startActivity(intent);
    }
    //投诉
    @OnClick(R.id.tv_manager_complaint)
    void tvManagerComplaint(){

        Intent intent=new Intent(HealthManagerActivity.this,HealthAddNewComplaintActivity.class);
        startActivity(intent);
    }
    //替换医师
    @OnClick(R.id.tv_manager_replace)
    void tvManagerReplace(){
        Intent intent=new Intent(HealthManagerActivity.this,ApplyReplaceActivity.class);
        startActivity(intent);
    }
    //咨询
    @OnClick(R.id.tv_manager_consultation)
    void tvManagerConsultation(){
        if (isClick) {
            ChatUtil.gotoChatActivity(this, "1", "5");//LeanCloud入口
            isClick = false;
            handleBtnMultipleClick();
        }
    }
    //返回
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl(){
        onBackPressed();
    }

    /**
     * 获取本地医师数据
     * */
    private HealthDoctorParamBean.ParamBean getLocalhealthDoctorData() {
        //读取assets文件目录下文件
        String doctorJsonString= AssetsFileUtil.getJsonStr(this, "healthmanager.txt");
        HealthDoctorParamBean healthDoctorParamBean = null;
        if (doctorJsonString != null) {
            //解析json数据
            healthDoctorParamBean= GsonTools.getHealthDoctorParamBean(doctorJsonString);
        }
        return healthDoctorParamBean.getParam();
    }
    /**
     * 处理btn短期内多次点击事件
     * */
    private void handleBtnMultipleClick(){
        if (isClick == false) {
            Timer timer=new Timer();
            TimerTask timerTask=new TimerTask() {
                @Override
                public void run() {
                    isClick=true;
                }
            };
            timer.schedule(timerTask,5*1000);
        }
    }
}
