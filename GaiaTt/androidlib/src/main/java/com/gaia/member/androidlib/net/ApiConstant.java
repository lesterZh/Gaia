package com.gaia.member.androidlib.net;

/**
 * @author android客户端-王浩韩
 * @version V1.0
 * @Description:接口类
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/7 14:57
 */
public class ApiConstant {

    //获取验证码
    public static final String SMSGET="api/sms/get";
    //电话号码+验证码登录
    public static final String LOGIN="api/auth/login/sms";
    //用户电子券列表
    public static final String COUPONLIST="api/user/coupons/list";
    //获取最新版本号
    public static final String VERSIONGET="/version/get";
    //根据省市区来获取诊所列表
    public static final String GETBYREGION="/clinics/getByRegion";

    //根据用户的位置来获取诊所列表
    public static final String GET_BY_LOCATION="/clinics/getByUserLocation";

    //根据诊所ID来查询所有的可以预约的医生
    public static final String GET_BY_CLINICID="/doctor/getByClinicID";

    //根据诊所返回所有医生列表
    public static final String GET_DOCTOR_BY_CLINICID="/services/Service?appointmentList/get";

    //根据服务返回诊所列表
    public static final String GET_BY_SERVICE="/clinics/getByService";

    //预约服务提交接口
    public static final String POST_ORDER_SERVICE="/appoint/service/put";

    //获取我的消息主界面列表
    public static final String POST_GET_HOME_MSG="/MyMessage/get";

    //获取盖亚诊所顶部横幅
    public static final String GETBANNER=" /binner/get";
    //根据位置查询我要咨询列表
    public static final String GETCONSULTING="/consulting/get";
    //根据城市码查询我要咨询列表
    public static final String GETCONSULTINGBYCODE="/consulting/get";

    //查询用户可以预约的服务列表
    public static final String SERVICESGET="/services/get";
    //提供周边盖亚诊所列表接口
    public static final String GETCLINICLIST="/services/Service?clinicList/get";

    //提供身边诊所详情信息
    public static final String GETCLINICDETAIL="/services/Service?clinicDetail/get";

    //一键评估会员健康状态
    public static final String GETEVALUATIONSTATUS="地址/evaluation/get";

    //计划干预列表
    public static final String PLANLIST="需要更改";
    //通过日期获取计划干预列表
    public static final String GETPLANLISTBYDATE="需要更改";
    //吸烟计划详情
    public static final String SMOKEPLAN="需要更改";
    //运动计划详情
    public static final String SPORTPLAN="需要更改";
    //饮酒计划详情
    public static final String DRINKPLAN="需要更改";
    //中医计划详情
    public static final String MEDICINEPLAN="需要更改";
    //饮食计划详情
    public static final String DIETPLAN="需要更改";
    //提交吸烟计划详情
    public static final String SUBMITSMOKEPLAN="需要更改";
    //提交运动感知强度计划详情
    public static final String SUBMITSPORTPLAN="需要更改";

    //获取健康工具list的列表
    public static final String GETHEALTHTOOLS="需要更改";

    //获得个人档案详细内容
    public static final String GETARCHIVES="需要更改";

    //提交个人档案详细内容
    public static final String SUBMITARCHIVES="需要更改";

    //请求更换获取健康管理师信息
    public static final String APPLYREPLACE="需要更改";

    //请求获取健康管理师信息
    public static final String GETDOCTORINFO="需要更改";

    //提供设置新增投诉
    public static final String PUTCOMPLAIN="需要更改";


    //获取睡眠记录列表
    public static final String GETSLEEPLIST="需要更改";



}
