package com.gaia.member.gaiatt.login.bean;

import java.io.Serializable;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: GurdianInfoBean
 * @Package com.gaia.member.gaiatt.login.bea
 * @Description:   注册人信息
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/27.
 */
public class RegistInfoBean implements Serializable{
    String registPhone;
    String address;
    String company;
    String contactName;
    String contactPhone;
    String marriageState;
    String profession;

    GurdianInfoBean gurdianInfo;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMarriageState() {
        return marriageState;
    }

    public void setMarriageState(String marriageState) {
        this.marriageState = marriageState;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public GurdianInfoBean getGurdianInfo() {
        return gurdianInfo;
    }

    public void setGurdianInfo(GurdianInfoBean gurdianInfo) {
        this.gurdianInfo = gurdianInfo;
    }

    public String getWearDeviceStr() {
        return wearDeviceStr;
    }

    public void setWearDeviceStr(String wearDeviceStr) {
        this.wearDeviceStr = wearDeviceStr;
    }

    String wearDeviceStr;

    public String getRegistPhone() {
        return registPhone;
    }

    public void setRegistPhone(String registPhone) {
        this.registPhone = registPhone;
    }
}
