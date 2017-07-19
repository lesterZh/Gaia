package com.gaia.member.androidlib.net.bean;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/21 14:46
 * @version V1.0
 */

import java.util.List;

/**
 * @ClassName: BannerBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/21 14:46
 */
public class BannerBean {
    private ParamBean param;
    /**
     * param : {"binners":[{"url":"imageUrl1"},{"url":"imageUrl2"}]}
     * returnCode : null
     * returnValue : null
     * success : true
     * errMsg : null
     */

    private Object returnCode;
    private Object returnValue;
    private boolean success;
    private Object errMsg;

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public Object getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Object returnCode) {
        this.returnCode = returnCode;
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

    public Object getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(Object errMsg) {
        this.errMsg = errMsg;
    }

    public static class ParamBean {
        private List<BinnersBean> binners;

        public List<BinnersBean> getBinners() {
            return binners;
        }

        public void setBinners(List<BinnersBean> binners) {
            this.binners = binners;
        }

        public static class BinnersBean {
            /**
             * url : imageUrl1
             */
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
