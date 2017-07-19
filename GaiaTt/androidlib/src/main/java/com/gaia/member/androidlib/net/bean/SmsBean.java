package com.gaia.member.androidlib.net.bean;

/**
 * @author android客户端-王浩韩
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/7 15:32
 */
public class SmsBean {

    private SmsParam param;
    private String errMsg;
    private Integer returnValue;
    private Integer returnCode;
    private Boolean success;

    public SmsParam getParam() {
        return param;
    }

    public void setParam(SmsParam param) {
        this.param = param;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Integer returnValue) {
        this.returnValue = returnValue;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
