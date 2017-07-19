package com.gaia.member.androidlib.net.bean;

/**
 * Created by baiyuanwei on 16/3/16.
 */
public class TestBean {

    private TestParam param;

    private String error;

    private Integer returnValue;

    private Boolean success;

    public TestParam getParam() {
        return param;
    }

    public void setParam(TestParam param) {
        this.param = param;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Integer returnValue) {
        this.returnValue = returnValue;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
