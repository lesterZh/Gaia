/**
 * @Description: 身边盖亚列表
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/15 12:00
 * @version V1.0
 */
package com.gaia.member.androidlib.net.bean;

import java.util.List;

/**
 * @ClassName: AroundGaiaListBean
 *Description: 身边盖亚列表
 *@author android移动客户端-王浩韩
 * @date 2016/6/15 12:00
 */
public class AroundGaiaListBean {
    private ParamBean param;
    /**
     * param : {"clinicList":[{"clinicId":124,"clinicName":"盖亚诊所（高新店）","headPic":"325423.png","distance":800,
     * "address":"天府大道中段188号","contactPhone":"028-87777777","longitude":12.34,"latitude":123.45}]}
     * error : null
     * returnValue : null
     * success : true
     */

    private Object error;
    private Object returnValue;
    private boolean success;

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

    public static class ParamBean {

        private List<ClinicListBean> clinicList;

        public List<ClinicListBean> getClinicList() {
            return clinicList;
        }

        public void setClinicList(List<ClinicListBean> clinicList) {
            this.clinicList = clinicList;
        }

        /**
         * clinicId : 124
         * clinicName : 盖亚诊所（高新店）
         * headPic : 325423.png
         * distance : 800
         * address : 天府大道中段188号
         * contactPhone : 028-87777777
         * longitude : 12.34
         * latitude : 123.45
         */
        public static class ClinicListBean {
            private int clinicId;
            private String clinicName;
            private String headPic;
            private int distance;
            private String address;
            private String contactPhone;
            private double longitude;
            private double latitude;

            public int getClinicId() {
                return clinicId;
            }

            public void setClinicId(int clinicId) {
                this.clinicId = clinicId;
            }

            public String getClinicName() {
                return clinicName;
            }

            public void setClinicName(String clinicName) {
                this.clinicName = clinicName;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getContactPhone() {
                return contactPhone;
            }

            public void setContactPhone(String contactPhone) {
                this.contactPhone = contactPhone;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }
        }
    }
}
