/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthManagementFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 健康管理Home 主界面Fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.EvaluationBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.activity.HealthManagerActivity;
import com.gaia.member.gaiatt.healthmanage.activity.HealthPlanActivity;
import com.gaia.member.gaiatt.healthmanage.activity.HealthRecordActivity;
import com.gaia.member.gaiatt.healthmanage.activity.HealthToolsActivity;
import com.gaia.member.gaiatt.healthmanage.activity.ProcessEvaluationActivity;
import com.gaia.member.gaiatt.healthmanage.activity.QuestionnaireActivity;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.UserUtils;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;
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
 * @ClassName: HealthManagementFragment
 *Description: 健康管理Home 主界面Fragment
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthManagementFragment extends Fragment {
    //用户健康状态显示
    @Bind(R.id.userhealth_state_tv)
    TextView userhealthStateTv;
    //尚未评估视图组
    @Bind(R.id.immediate_assessment_ll)
    LinearLayout immediateAssessmentLl;
    //刷新动画
    @Bind(R.id.img_anim_health)
    ImageView imgAnimHealth;
    //评估百分比
    @Bind(R.id.ing_assessment_percent)
    TextView ingAssessmentPercent;
    //正在评估视图组
    @Bind(R.id.ing_assessment_ll)
    RelativeLayout ingAssessmentLl;
    //头部颜色
    @Bind(R.id.health_color_ll)
    RelativeLayout healthColorLl;
    //头部用户视图组
    @Bind(R.id.userhead_ll)
    LinearLayout userheadLl;
    //fragment布局视图
    private View view;
    //动画
    private Animation animation;
    //动画实现资源
    int animId = R.anim.translating;
    //上下文
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.healthmanage_fragment, container, false);
        mContext=getActivity();
        //绑定控件
        ButterKnife.bind(this, view);
        return view;
    }

    //设置评估数据
    private int progressNum = 1;

    /**
     * 立即评估
     * */
    private void getAssessmentDate() {
        //隐藏立即评估
        immediateAssessmentLl.setVisibility(View.GONE);
        //显示正在评估
        ingAssessmentLl.setVisibility(View.VISIBLE);
        //设置评估状态
        userhealthStateTv.setText(GetResourcesUtils.getString(getActivity(), R.string.health_manage_status2));
        //开始动画
        startAnimation();
        //闹钟控制
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startAnimation();
                //进度小于100
                while (progressNum <= CommonConstants.MAXPROGRESS) {
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //控制百分比显示
                                ingAssessmentPercent.setText(GetResourcesUtils.getString(getActivity(), R.string
                                        .health_manage_ing)
                                        + progressNum + GetResourcesUtils.getString(getActivity(), R.string
                                        .percent_sign));
                            }
                        });
                        //睡眠
                        Thread.sleep(20);
                        //进度加
                        progressNum++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //加载百分百
                if (progressNum > CommonConstants.MAXPROGRESS) {
                    //发空消息完成
                    handler.sendEmptyMessage(CommonConstants.MANAGERESULTHANDLER);
                }
            }
        };
        timer.schedule(timerTask, 0);

        //获取接口评估数据
        //getServiceData();
    }

    /**
     * 获取服务器评估结果
     * */
    private void getServiceData() {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("userId").value(UserUtils.getUserId())//用户ID
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetEvaluationStatus(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<EvaluationBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(EvaluationBean evaluationBean) {
                            if (evaluationBean.isSuccess()) {
                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
                                //当前健康状态
                                int currentStatus=evaluationBean.getParam().getHealthStatus();
                            } else {
                                Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取评估结果
     * */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //评估完成
                case CommonConstants.MANAGERESULTHANDLER:
                    //关闭动画
                    closeAnimation();
                    //隐藏正在评估
                    ingAssessmentLl.setVisibility(View.GONE);
                    //获取健康状态(-1、0、1、2)
                    int type = (int) ((Math.random() * 4))+(-1);
                    //健康1
                    if (type == CommonConstants.HEALTH) {
                        //头部用户控件居中
                        userheadLl.setGravity(Gravity.CENTER);
                        userhealthStateTv.setText(GetResourcesUtils.getString(getActivity(), R.string.health_health));
                        healthColorLl.setBackgroundResource(R.color.green);
                     //亚健康2
                    } else if (type == CommonConstants.SUBHEALTH) {
                        //头部用户控件居中
                        userheadLl.setGravity(Gravity.CENTER);
                        userhealthStateTv.setText(GetResourcesUtils.getString(getActivity(), R.string
                                .health_sub_health));
                        healthColorLl.setBackgroundResource(R.color.orange);
                    //未评估-1
                    } else if (type == CommonConstants.NOEVALUATE) {
                        immediateAssessmentLl.setVisibility(View.VISIBLE);
                        ToastUtil.show(mContext,"未评估");
                    //需要补全资料0
                    } else if (type == CommonConstants.NOCOMPLETEDATA) {
                        immediateAssessmentLl.setVisibility(View.VISIBLE);
                        ToastUtil.show(mContext, "需要补全资料");
                        Intent intent = new Intent(getActivity(), QuestionnaireActivity.class);
                        startActivity(intent);
                    }
                    break;
                default:
                    break;
            }
        }
    };


    //开始加载动画
    private void startAnimation() {
        //判断动画对象是否为空
        if (animation == null) {
            animation = AnimationUtils.loadAnimation(getActivity(), animId);
            //开始动画
            imgAnimHealth.startAnimation(animation);
        }
    }

    //关闭加载动画
    private void closeAnimation() {
        //判断动画对象是否为空
        if (animation != null) {
            //关闭动画
            imgAnimHealth.clearAnimation();
            //置空
            animation=null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 健康管理底部六个按钮
     * */
    @OnClick({R.id.health_plan, R.id.health_assessment, R.id.health_tools, R.id
            .health_record, R.id.health_managementdivision, R.id.rocess_evaluation})
    public void onClick(View view) {
        Class cl = null;
        switch (view.getId()) {
            //干预计划
            case R.id.health_plan:
                cl = HealthPlanActivity.class;
                break;
            //健康评估
            case R.id.health_assessment:
                cl = QuestionnaireActivity.class;
                break;
            //健康工具
            case R.id.health_tools:
                cl = HealthToolsActivity.class;
                break;
            //健康记录
            case R.id.health_record:
                cl = HealthRecordActivity.class;
                break;
            //健康管理师
            case R.id.health_managementdivision:
                cl = HealthManagerActivity.class;
                break;
            //过程评价
            case R.id.rocess_evaluation:
                cl = ProcessEvaluationActivity.class;
                break;
        }
        //界面跳转
        Intent intent = new Intent(getActivity(), cl);
        startActivity(intent);
    }

    /**
     * 一键评估
     * */
    @OnClick(R.id.immediate_assessment_tv)
    public void onClick() {
        //初始化进度
        progressNum=1;
        //获取服务器评估数据
        getAssessmentDate();
    }
}
