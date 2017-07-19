package com.gaia.member.androidlib.net.bean;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/15 16:15
 * @version V1.0
 */

import java.util.List;

/**
 * @ClassName: AroundGaiaDetailBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/15 16:15
 */
public class AroundGaiaDetailBean {
    /**
     * gaiaDetail : {"clinicId":123,"clinicPic":"234.png","clinicName":"盖亚诊所（高新店）","adress":"天府大道中段188号",
     * "contactPhone":"028-87777777","couponList":[{"couponValue":3,"couponTitle":"无门槛即可食用"}],"areaCode":"100001"}
     */

    private ParamBean param;
    /**
     * param : {"gaiaDetail":{"clinicId":123,"clinicPic":"234.png","clinicName":"盖亚诊所（高新店）","adress":"天府大道中段188号",
     * "contactPhone":"028-87777777","couponList":[{"couponValue":3,"couponTitle":"无门槛即可食用"}],"areaCode":"100001"}}
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
        /**
         * clinicId : 123
         * clinicPic : 234.png
         * clinicName : 盖亚诊所（高新店）
         * adress : 天府大道中段188号
         * contactPhone : 028-87777777
         * couponList : [{"couponValue":3,"couponTitle":"无门槛即可食用"}]
         * areaCode : 100001
         */

        private GaiaDetailBean gaiaDetail;

        public GaiaDetailBean getGaiaDetail() {
            return gaiaDetail;
        }

        public void setGaiaDetail(GaiaDetailBean gaiaDetail) {
            this.gaiaDetail = gaiaDetail;
        }

        public static class GaiaDetailBean {
            private int clinicId;
            private String clinicPic;
            private String clinicName;
            private String adress;
            private String contactPhone;
            private String areaCode;
            /**
             * couponValue : 3
             * couponTitle : 无门槛即可食用
             */

            private List<CouponListBean> couponList;

            public int getClinicId() {
                return clinicId;
            }

            public void setClinicId(int clinicId) {
                this.clinicId = clinicId;
            }

            public String getClinicPic() {
                return clinicPic;
            }

            public void setClinicPic(String clinicPic) {
                this.clinicPic = clinicPic;
            }

            public String getClinicName() {
                return clinicName;
            }

            public void setClinicName(String clinicName) {
                this.clinicName = clinicName;
            }

            public String getAdress() {
                return adress;
            }

            public void setAdress(String adress) {
                this.adress = adress;
            }

            public String getContactPhone() {
                return contactPhone;
            }

            public void setContactPhone(String contactPhone) {
                this.contactPhone = contactPhone;
            }

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public List<CouponListBean> getCouponList() {
                return couponList;
            }

            public void setCouponList(List<CouponListBean> couponList) {
                this.couponList = couponList;
            }

            public static class CouponListBean {
                private int couponValue;
                private String couponTitle;

                public int getCouponValue() {
                    return couponValue;
                }

                public void setCouponValue(int couponValue) {
                    this.couponValue = couponValue;
                }

                public String getCouponTitle() {
                    return couponTitle;
                }

                public void setCouponTitle(String couponTitle) {
                    this.couponTitle = couponTitle;
                }
            }
        }
    }
}
