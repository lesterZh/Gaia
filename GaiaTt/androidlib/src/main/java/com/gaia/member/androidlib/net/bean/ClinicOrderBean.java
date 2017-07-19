package com.gaia.member.androidlib.net.bean;/**
 * @Title: ClinicOrderBean
 * @Package com.gaia.member.androidlib.net.bean
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/13 14:40
 * @version V1.0
 */

import java.util.List;

/**
 *
 *@ClassName: ClinicOrderBean
 * Description: 根据省市区来获取诊所列表 /根据用户的位置来获取诊所列表
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/13 14:40
 *
 */
public class ClinicOrderBean {

    private ParamBean param;
    /**
     * param : {"Clinics":[{"clinicID":"123","clinicSmallImageUrl":"http://afasdfa.png","clinicImageUrls":["http:afasfad1.png","http:afasfad2.png"],"clinicName":"盖亚诊所（双流店）","level":"二级甲等","clinicLocation":"天府大道中天府大道西天府大道东天府大道北","clinicPhoneNum":"028-8888888","latitude":103.12,"longitude":104.11}]}
     * error : null
     * returnValue : null
     * success : true
     * errMsg : 错误描述
     */

    private Object error;
    private Object returnValue;
    private boolean success;
    private String errMsg;

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static class ParamBean {
        /**
         * clinicID : 123
         * clinicSmallImageUrl : http://afasdfa.png
         * clinicImageUrls : ["http:afasfad1.png","http:afasfad2.png"]
         * clinicName : 盖亚诊所（双流店）
         * level : 二级甲等
         * clinicLocation : 天府大道中天府大道西天府大道东天府大道北
         * clinicPhoneNum : 028-8888888
         * latitude : 103.12
         * longitude : 104.11
         */

        private List<ClinicsBean> Clinics;

        public List<ClinicsBean> getClinics() {
            return Clinics;
        }

        public void setClinics(List<ClinicsBean> Clinics) {
            this.Clinics = Clinics;
        }

        public static class ClinicsBean {
            private String clinicID;
            private String clinicSmallImageUrl;
            private String clinicName;
            private String level;
            private String clinicLocation;
            private String clinicPhoneNum;
            private double latitude;
            private double longitude;
            private List<String> clinicImageUrls;

            public String getClinicID() {
                return clinicID;
            }

            public void setClinicID(String clinicID) {
                this.clinicID = clinicID;
            }

            public String getClinicSmallImageUrl() {
                return clinicSmallImageUrl;
            }

            public void setClinicSmallImageUrl(String clinicSmallImageUrl) {
                this.clinicSmallImageUrl = clinicSmallImageUrl;
            }

            public String getClinicName() {
                return clinicName;
            }

            public void setClinicName(String clinicName) {
                this.clinicName = clinicName;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getClinicLocation() {
                return clinicLocation;
            }

            public void setClinicLocation(String clinicLocation) {
                this.clinicLocation = clinicLocation;
            }

            public String getClinicPhoneNum() {
                return clinicPhoneNum;
            }

            public void setClinicPhoneNum(String clinicPhoneNum) {
                this.clinicPhoneNum = clinicPhoneNum;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public List<String> getClinicImageUrls() {
                return clinicImageUrls;
            }

            public void setClinicImageUrls(List<String> clinicImageUrls) {
                this.clinicImageUrls = clinicImageUrls;
            }
        }
    }
}
