package com.gaia.member.androidlib.net.bean;/**
 * @Title: CommitOrderServiceBean
 * @Package com.gaia.member.androidlib.net.bean
 * @Description: 预约服务提交接口
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 16:05
 * @version V1.0
 */

/**
 *
 *@ClassName: CommitOrderServiceBean
 * Description: 预约服务提交接口
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 16:05
 *
 */
public class CommitOrderServiceBean {
    private ParamBean param;
    /**
     * param : {}
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
    }
}
