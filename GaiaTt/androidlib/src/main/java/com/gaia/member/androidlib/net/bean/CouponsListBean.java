package com.gaia.member.androidlib.net.bean;

import java.util.List;

/**
 * @author android客户端-王浩韩
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/7 16:20
 */
public class CouponsListBean {

    private ParamBean param;
    /**
     * param : {"list":[{"couponCode":"1111","name":"优惠券1","validDate":"2016-09-01","discount":8,"lowestValue":"7",
     * "vip":"flase"}]}
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
         * couponCode : 1111
         * name : 优惠券1
         * validDate : 2016-09-01
         * discount : 8
         * lowestValue : 7
         * vip : flase
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String couponCode;
            private String name;
            private String validDate;
            private int discount;
            private String lowestValue;
            private String vip;

            public String getCouponCode() {
                return couponCode;
            }

            public void setCouponCode(String couponCode) {
                this.couponCode = couponCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValidDate() {
                return validDate;
            }

            public void setValidDate(String validDate) {
                this.validDate = validDate;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getLowestValue() {
                return lowestValue;
            }

            public void setLowestValue(String lowestValue) {
                this.lowestValue = lowestValue;
            }

            public String getVip() {
                return vip;
            }

            public void setVip(String vip) {
                this.vip = vip;
            }
        }
    }
}
