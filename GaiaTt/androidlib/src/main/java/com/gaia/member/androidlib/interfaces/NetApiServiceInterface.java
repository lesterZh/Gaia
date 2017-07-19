package com.gaia.member.androidlib.interfaces;

import com.gaia.member.androidlib.net.ApiConstant;
import com.gaia.member.androidlib.net.bean.AllDoctorsFromClinicIDBean;
import com.gaia.member.androidlib.net.bean.AroundGaiaDetailBean;
import com.gaia.member.androidlib.net.bean.AroundGaiaListBean;
import com.gaia.member.androidlib.net.bean.BannerBean;
import com.gaia.member.androidlib.net.bean.BaseBean;
import com.gaia.member.androidlib.net.bean.ClinicListFromServiceBean;
import com.gaia.member.androidlib.net.bean.ClinicOrderBean;
import com.gaia.member.androidlib.net.bean.CommitOrderServiceBean;
import com.gaia.member.androidlib.net.bean.ConsultingParamBean;
import com.gaia.member.androidlib.net.bean.CouponsListBean;
import com.gaia.member.androidlib.net.bean.DietPlanBean;
import com.gaia.member.androidlib.net.bean.DoctorsFromClinicIDBean;
import com.gaia.member.androidlib.net.bean.DrinkPlanBean;
import com.gaia.member.androidlib.net.bean.EvaluationBean;
import com.gaia.member.androidlib.net.bean.HealthDoctorParamBean;
import com.gaia.member.androidlib.net.bean.HealthToolsListBean;
import com.gaia.member.androidlib.net.bean.LoginBean;
import com.gaia.member.androidlib.net.bean.MedicinePlanBean;
import com.gaia.member.androidlib.net.bean.MessageHomeListBean;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean;
import com.gaia.member.androidlib.net.bean.PlanListBean;
import com.gaia.member.androidlib.net.bean.ServiceOrderBean;
import com.gaia.member.androidlib.net.bean.SleepRecordBean;
import com.gaia.member.androidlib.net.bean.SmokePlanBean;
import com.gaia.member.androidlib.net.bean.SmsBean;
import com.gaia.member.androidlib.net.bean.SportPlanBean;
import com.gaia.member.androidlib.net.bean.TestBean;
import com.gaia.member.androidlib.net.bean.VersionBean;
import com.squareup.okhttp.RequestBody;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by baiyuanwei on 16/3/16.
 */
public interface NetApiServiceInterface {

    /**
     * Get请求方式
     *
     * xyzBean是一个自定义的实体类
     * key 是请求时,参数对应的key
     * 类型 是这个参数value的类型
     */
    @GET("name")
    Observable<TestBean> getTest(@Query("key") String value);

    /**
     * Post 请求方式
     *
     * TestBean 是自己定义的一个实体类
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST("api/auth/login")
    Observable<TestBean> postTest(@Body RequestBody requestBody);
    /**
     * 上传文件
     *
     * @param requestBody
     * @return
     */
    @POST("api/img/uplaod")
    Observable<TestBean> uploadFile(@Body RequestBody requestBody);
    //===================================================================================================以上用于测试

    /**
     * Post 请求方式
     *
     * SmsBean 验证码
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.SMSGET)
    Observable<SmsBean> postAmsGet(@Body RequestBody requestBody);

    /**
     * Post 请求方式
     *
     * LoginBean 登录
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.LOGIN)
    Observable<LoginBean> postLogin(@Body RequestBody requestBody);


    /**
     * 通过userId和token登录
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.LOGIN)
    Observable<LoginBean> postLoginWithToken(@Body RequestBody requestBody);

    /**
     * Post 请求方式
     *
     * CouponsListBean 获取优惠券
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.COUPONLIST)
    Observable<CouponsListBean> postGetCouponsList(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * BannerBean 获取盖亚诊所顶部横幅
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETBANNER)
    Observable<BannerBean> postGetBanner(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * ConsultingParamBean 获取我要咨询地区列表
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETCONSULTING)
    Observable<ConsultingParamBean> postConsultingParam(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * ConsultingParamBean 获取我要咨询地区列表
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETCONSULTINGBYCODE)
    Observable<ConsultingParamBean> postConsultingParamByCode(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * SleepRecordBean 睡眠记录列表
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETSLEEPLIST)
    Observable<SleepRecordBean> postSleepRecordn(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * PlanListBean 干预计划列表
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.PLANLIST)
    Observable<PlanListBean> postGetPlanList(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * PlanListBean 干预计划列表通过日历
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETPLANLISTBYDATE)
    Observable<PlanListBean> postGetPlanListByDate(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * SmokePlanBean 吸烟计划
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.SMOKEPLAN)
    Observable<SmokePlanBean> postGetSmokePlan(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * SportPlanBean 运动计划
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.SPORTPLAN)
    Observable<SportPlanBean> postGetSportPlan(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * DrinkPlanBean 饮酒计划
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.DRINKPLAN)
    Observable<DrinkPlanBean> postGetDrinkPlan(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * DietPlanBean 饮食计划
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.DIETPLAN)
    Observable<DietPlanBean> postGetDietPlan(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * MedicinePlanBean 中医计划
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.MEDICINEPLAN)
    Observable<MedicinePlanBean> postGetMedicinePlan(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * BaseBean 基类（用于提交吸烟计划数据获取返回状态）
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.SUBMITSMOKEPLAN)
    Observable<BaseBean> postPutSmokePlan(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * BaseBean 基类（用于提交运动感知计划数据获取返回状态）
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.SUBMITSPORTPLAN)
    Observable<BaseBean> postPutSportPlan(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * BaseBean 基类（用于提交个人档案数据获取返回状态）
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.SUBMITARCHIVES)
    Observable<BaseBean> postPutArchives(@Body RequestBody requestBody);

    /**
     * Post 请求方式
     *
     * BaseBean 基类（用于提交请求替换个人医师获取返回状态）
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.APPLYREPLACE)
    Observable<BaseBean> postPutApplyReplace(@Body RequestBody requestBody);

    /**
     * Post 请求方式
     *
     * BaseBean 基类（用于提交投诉个人医师获取返回状态）
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.PUTCOMPLAIN)
    Observable<BaseBean> postPutComplaint(@Body RequestBody requestBody);

    /**
     * Post 请求方式
     *
     * HealthToolsListBean 健康工具
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETHEALTHTOOLS)
    Observable<HealthToolsListBean> postGetHealthToolsList(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * PersonArchivesBean 个人档案
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETARCHIVES)
    Observable<PersonArchivesBean> postGetPersonArchives(@Body RequestBody requestBody);

    /**
     * Post 请求方式
     *
     * HealthDoctorParamBean 健康管理医师
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETDOCTORINFO)
    Observable<HealthDoctorParamBean> postGetHealthDoctorInfo(@Body RequestBody requestBody);
    /**
     * Post 请求方式
     *
     * AroundGaiaListBean 周边盖亚诊所列表
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETCLINICLIST)
    Observable<AroundGaiaListBean> postGetAroundGaiaList(@Body RequestBody requestBody);

    /**
     * Post 请求方式
     *
     * AroundGaiaDetailBean 周边盖亚诊所详情
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETCLINICDETAIL)
    Observable<AroundGaiaDetailBean> postGetAroundGaiaDetail(@Body RequestBody requestBody);

    /**
     * Post 请求方式
     *
     * EvaluationBean 一键评估接口
     * @param requestBody 请求体: 通过RequestBody.create(MediaType,String)来创建
     * @return
     */
    @POST(ApiConstant.GETEVALUATIONSTATUS)
    Observable<EvaluationBean> postGetEvaluationStatus(@Body RequestBody requestBody);


    /**
     * 获取最新版本号
     * @return
     */
    @GET(ApiConstant.VERSIONGET)
    Observable<VersionBean> getVersion();

    /**
     * 根据省市区来获取诊所列表
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.GETBYREGION)
    Observable<ClinicOrderBean> postGetClinicsFromArea(@Body RequestBody requestBody);


    /**
     * 根据用户的位置来获取诊所列表
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.GET_BY_LOCATION)
    Observable<ClinicOrderBean> postGetClinicsFromuserLocation(@Body RequestBody requestBody);


    /**
     * 查询用户可以预约的服务列表，查询默认服务列表
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.SERVICESGET)
    Observable<ServiceOrderBean> postGetServes(@Body RequestBody requestBody);

    /**
     * 根据服务返回诊所列表
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.GET_BY_SERVICE)
    Observable<ClinicListFromServiceBean> postGetClinicListFromService(@Body RequestBody requestBody);



   /**
     * 根据诊所ID来查询所有的可以预约的医生
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.GET_BY_CLINICID)
    Observable<DoctorsFromClinicIDBean> postGetOrderDoctorsFromClinicID(@Body RequestBody requestBody);


    /**
     * 根据诊所返回所有医生列表
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.GET_DOCTOR_BY_CLINICID)
    Observable<AllDoctorsFromClinicIDBean> postGetAllDoctorsFromClinicID(@Body RequestBody requestBody);

    /**
     * 预约服务提交接口
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.POST_ORDER_SERVICE)
    Observable<CommitOrderServiceBean> postCommitOrderService(@Body RequestBody requestBody);


    /**
     * 获取我的消息主界面列表
     * @param requestBody
     * @return
     */
    @POST(ApiConstant.POST_ORDER_SERVICE)
    Observable<MessageHomeListBean> postGetMessageHomeList(@Body RequestBody requestBody);


}
