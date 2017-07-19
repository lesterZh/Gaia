package com.gaia.member.gaiatt.utils.gsonutils.GsonBean;

/**
 * Created by WangHaohan on 2016/5/12.
 */
public class HealthServiceParamBean extends BaseBean{
    private HealthServiceParamListBean param;
    private String returnValue;

    public HealthServiceParamListBean getParam() {
        return param;
    }

    public void setParam(HealthServiceParamListBean param) {
        this.param = param;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
