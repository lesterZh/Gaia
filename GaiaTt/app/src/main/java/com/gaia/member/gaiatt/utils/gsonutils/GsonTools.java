package com.gaia.member.gaiatt.utils.gsonutils;

import com.gaia.member.androidlib.net.bean.ConsultingParamBean;
import com.gaia.member.androidlib.net.bean.HealthDoctorParamBean;
import com.gaia.member.androidlib.net.bean.HealthToolsListBean;
import com.gaia.member.androidlib.net.bean.PersonArchivesBean;
import com.gaia.member.androidlib.net.bean.PlanListBean;
import com.gaia.member.androidlib.net.bean.SleepRecordBean;
import com.gaia.member.androidlib.net.bean.SportPlanBean;
import com.gaia.member.androidlib.net.bean.SportRecordBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.AroundClinicParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.BaseBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthServiceParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthServiceVipParam;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.MealParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.ProcessEvaluationBean;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/3/7.
 */
public class GsonTools {
    private static Gson gson = new Gson();


    /**
     * 返回类型为BaseBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static BaseBean getBaseReqBean(String jsonStr) {
        return gson.fromJson(jsonStr, BaseBean.class);
    }
    /**
     * 返回类型为MealParamBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static MealParamBean getMealParamBean(String jsonStr) {
        return gson.fromJson(jsonStr, MealParamBean.class);
    }

    /**
     * 返回类型为SportPlanBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static SportPlanBean getSportPlanBean(String jsonStr) {
        return gson.fromJson(jsonStr, SportPlanBean.class);
    }
    /**
     * 返回类型为HealthPlanParamBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static HealthPlanParamBean getHealthPlanParamBean(String jsonStr) {
        return gson.fromJson(jsonStr, HealthPlanParamBean.class);
    }
    /**
     * 返回类型为ProcessEvaluationBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static ProcessEvaluationBean getProcessEvaluationBean(String jsonStr) {
        return gson.fromJson(jsonStr, ProcessEvaluationBean.class);
    }

    /**
     * 返回类型为ConsultingParamBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static ConsultingParamBean getConsultingParamBean(String jsonStr) {
        return gson.fromJson(jsonStr, ConsultingParamBean.class);
    }

    /**
     * 返回类型为HealthServiceParamBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static HealthServiceParamBean getHealthServiceParamBean(String jsonStr) {
        return gson.fromJson(jsonStr, HealthServiceParamBean.class);
    }
    /**
     * 返回类型为HealthServiceVipParam时调用
     *
     * @param jsonStr
     * @return
     */
    public static HealthServiceVipParam getHealthServiceVipParam(String jsonStr) {
        return gson.fromJson(jsonStr, HealthServiceVipParam.class);
    }
    /**
     * 返回类型为HealthToolsParamBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static HealthToolsListBean getHealthToolsParamBean(String jsonStr) {
        return gson.fromJson(jsonStr, HealthToolsListBean.class);
    }
    /**
     * 返回类型为SleepRecordBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static SleepRecordBean getSleepRecordBean(String jsonStr) {
        return gson.fromJson(jsonStr, SleepRecordBean.class);
    }
    /**
     * 返回类型为SportRecordBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static SportRecordBean getSportRecordBean(String jsonStr) {
        return gson.fromJson(jsonStr, SportRecordBean.class);
    }
    /**
     * 返回类型为HealthDoctorParamBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static HealthDoctorParamBean getHealthDoctorParamBean(String jsonStr) {
        return gson.fromJson(jsonStr, HealthDoctorParamBean.class);
    }
    /**
     * 返回类型为AroundClinicParamBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static AroundClinicParamBean getAroundClinicParamBean(String jsonStr) {
        return gson.fromJson(jsonStr, AroundClinicParamBean.class);
    }

    /**
     * 返回类型为PersonArchivesBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static PersonArchivesBean getPersonArchivesBean(String jsonStr) {
        return gson.fromJson(jsonStr, PersonArchivesBean.class);
    }


    /**
     * 返回类型为PlanListBean时调用
     *
     * @param jsonStr
     * @return
     */
    public static PlanListBean getPlanListBean(String jsonStr) {
        return gson.fromJson(jsonStr, PlanListBean.class);
    }




}
