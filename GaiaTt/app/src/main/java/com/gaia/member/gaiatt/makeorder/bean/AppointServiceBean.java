package com.gaia.member.gaiatt.makeorder.bean;/**
 * @Title: AppointServiceBean
 * @Package com.gaia.member.gaiatt.makeorder.bean
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 16:39
 * @version V1.0
 */

import java.io.Serializable;

/**
 *
 *@ClassName: AppointServiceBean
 * Description: 向服务器提交预约服务的bean
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 16:39
 *
 */
public class AppointServiceBean implements Serializable{

    /**
     * beginDetailDate : 2016-01-06 10:00:00
     * endDetailDate : 2016-01-06 11:00:00
     * appointorName : 张三
     * appointorPhone : 187868686589
     * recommenderName : 小王
     * recommendCode : adasf
     * serviceId : 12321
     * price : 12
     * clinicId : 1312
     */

    private String beginDetailDate;
    private String endDetailDate;
    private String appointorName;
    private String appointorPhone;
    private String recommenderName;
    private String recommendCode;
    private String serviceId;
    private String price;
    private String clinicId;

    public String getBeginDetailDate() {
        return beginDetailDate;
    }

    public void setBeginDetailDate(String beginDetailDate) {
        this.beginDetailDate = beginDetailDate;
    }

    public String getEndDetailDate() {
        return endDetailDate;
    }

    public void setEndDetailDate(String endDetailDate) {
        this.endDetailDate = endDetailDate;
    }

    public String getAppointorName() {
        return appointorName;
    }

    public void setAppointorName(String appointorName) {
        this.appointorName = appointorName;
    }

    public String getAppointorPhone() {
        return appointorPhone;
    }

    public void setAppointorPhone(String appointorPhone) {
        this.appointorPhone = appointorPhone;
    }

    public String getRecommenderName() {
        return recommenderName;
    }

    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommenderName;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }
}
