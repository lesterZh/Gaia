package com.gaia.member.gaiatt.login;/**
 * @Title: NetApi
 * @Package com.gaia.member.gaiatt.login
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/13 16:18
 * @version V1.0
 */

import android.content.Context;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.AllDoctorsFromClinicIDBean;
import com.gaia.member.androidlib.net.bean.ClinicListFromServiceBean;
import com.gaia.member.androidlib.net.bean.ClinicOrderBean;
import com.gaia.member.androidlib.net.bean.CommitOrderServiceBean;
import com.gaia.member.androidlib.net.bean.DoctorsFromClinicIDBean;
import com.gaia.member.androidlib.net.bean.MessageHomeListBean;
import com.gaia.member.androidlib.net.bean.ServiceOrderBean;
import com.gaia.member.androidlib.utils.LogUtil;
import com.gaia.member.gaiatt.makeorder.bean.AppointServiceBean;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.UserUtils;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 *@ClassName: NetApi
 * Description: TODO
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/13 16:18
 *
 */
public class NetApi {

    static private NetApi netApi;

    private NetApi() {};

    public static NetApi getIntance() {
        if (netApi == null) {
            netApi = new NetApi();
        }
        return netApi;
    }

    private static RequestBody getRequestBody(JSONStringer jsonStringer) {
        String jsonStr = jsonStringer.toString();
        return RequestBody.create(NetConstant.JSON, jsonStr);
    }

    /**
     * 根据省市区来获取诊所列表
     * @param mContext
     * @param selectedProvince 省份
     * @param selectedCity 市
     * @param selectedCounty 县、区
     */
    private void postGetClinicsFromArea(Context mContext, String selectedProvince,String selectedCity,
                            String selectedCounty) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("provice").value(selectedProvince)
                    .key("city").value(selectedCity)
                    .key("region").value(selectedCounty)
                    .key("token").value(UserUtils.getUserToken())
                    .key("currentPage").value(1)
                    .key("pageSize").value(10)
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);//构造请求体
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetClinicsFromArea(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ClinicOrderBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e("error", "" + e.getMessage());
                        }
                        @Override
                        public void onNext(ClinicOrderBean bean) {
                            if (bean.isSuccess()) {
//                                hospitalQureyList = bean.getParam().getClinics();
                            } else {

                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据用户位置来获取诊所列表
     * @param mContext
     * @param longitude
     * @param latitude
     */
    private void postGetClinicsFromuserLocation(Context mContext, double longitude,double latitude) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("longitude").value(longitude)
                    .key("latitude").value(latitude)
                    .key("token").value(UserUtils.getUserToken())
                    .key("currentPage").value(1)
                    .key("pageSize").value(10)
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);//构造请求体
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetClinicsFromuserLocation(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ClinicOrderBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e("error", "" + e.getMessage());
                        }
                        @Override
                        public void onNext(ClinicOrderBean bean) {
                            if (bean.isSuccess()) {
//                                hospitalQureyList = bean.getParam().getClinics();
                            } else {

                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询用户可以预约的服务列表,查询默认服务列表
     * @param mContext
     * @param currentPage
     * @param pageSize
     */
    private void postGetServiceList(Context mContext, int currentPage, int pageSize) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("token").value(UserUtils.getUserToken())
                    .key("currentPage").value(currentPage)
                    .key("pageSize").value(pageSize)
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);//构造请求体
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetServes(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ServiceOrderBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e("error", "" + e.getMessage());
                        }
                        @Override
                        public void onNext(ServiceOrderBean bean) {
                            if (bean.isSuccess()) {
//                                hospitalQureyList = bean.getParam().getClinics();
                            } else {

                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 根据服务返回诊所列表
     * @param mContext
     * @param serviceId 服务id
     * @param currentPage
     * @param pageSize
     */
    private void postGetClinicListFromService(Context mContext,String serviceId, int currentPage, int pageSize) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("token").value(UserUtils.getUserToken())
                    .key("serviceid").value(serviceId)
                    .key("userLongitude").value(CommonConstants.LONGITUDE)
                    .key("userLatitude").value(CommonConstants.LATIYUDE)
                    .key("currentPage").value(currentPage)
                    .key("pageSize").value(pageSize)
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);//构造请求体
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetClinicListFromService(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ClinicListFromServiceBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e("error", "" + e.getMessage());
                        }
                        @Override
                        public void onNext(ClinicListFromServiceBean bean) {
                            if (bean.isSuccess()) {

                            } else {

                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 根据诊所ID来查询所有的可以预约的医生
     * @param mContext
     * @param clinicId 诊所id
     * @param currentPage
     * @param pageSize
     */
    private void postGetOrderDoctorsFromClinicID(Context mContext,String clinicId, int currentPage, int pageSize) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("token").value(UserUtils.getUserToken())
                    .key("clinicID").value(clinicId)
                    .key("currentPage").value(currentPage)
                    .key("pageSize").value(pageSize)
                    .endObject();

            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);//构造请求体
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetOrderDoctorsFromClinicID(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DoctorsFromClinicIDBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e("error", "" + e.getMessage());
                        }
                        @Override
                        public void onNext(DoctorsFromClinicIDBean bean) {
                            if (bean.isSuccess()) {

                            } else {

                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 根据诊所返回所有医生列表
     * @param mContext
     * @param clinicId 诊所id
     */
    private void postGetAllDoctorsFromClinicID(Context mContext,String clinicId) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
//                    .key("token").value(UserUtils.getUserToken())
                    .key("clinicID").value(clinicId)
//                    .key("currentPage").value(currentPage)
//                    .key("pageSize").value(pageSize)
                    .endObject();

            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);//构造请求体
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetAllDoctorsFromClinicID(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AllDoctorsFromClinicIDBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e("error", "" + e.getMessage());
                        }
                        @Override
                        public void onNext(AllDoctorsFromClinicIDBean bean) {
                            if (bean.isSuccess()) {

                            } else {

                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 预约服务提交接口
     * @param mContext
     * @param appointService 预约信息
     */
    private void postCommitOrderService(Context mContext,AppointServiceBean appointService) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("token").value(UserUtils.getUserToken())
                    .key("appointService").value(appointService)

                    .endObject();

            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);//构造请求体
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postCommitOrderService(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CommitOrderServiceBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtil.e("error", "" + e.getMessage());
                        }
                        @Override
                        public void onNext(CommitOrderServiceBean bean) {
                            if (bean.isSuccess()) {

                            } else {

                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
