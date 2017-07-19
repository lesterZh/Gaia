package com.gaia.member.androidlib.net.bean;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: VersionBean
 * @Package com.gaia.member.androidlib.net.bean
 * @Description: 网络获取软件版本号
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/13 12:01
 */


public class VersionBean {

    /**
     * iosVersion : 1.0.1
     * androidVersion : 10.1.1
     */

    private ParamBean param;
    /**
     * param : {"iosVersion":"1.0.1","androidVersion":"10.1.1"}
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
        private String iosVersion;
        private String androidVersion;

        public String getIosVersion() {
            return iosVersion;
        }

        public void setIosVersion(String iosVersion) {
            this.iosVersion = iosVersion;
        }

        public String getAndroidVersion() {
            return androidVersion;
        }

        public void setAndroidVersion(String androidVersion) {
            this.androidVersion = androidVersion;
        }
    }
}
