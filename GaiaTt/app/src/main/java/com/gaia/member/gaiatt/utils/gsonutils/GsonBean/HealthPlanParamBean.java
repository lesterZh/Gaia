package com.gaia.member.gaiatt.utils.gsonutils.GsonBean;

/**
 * Created by WangHaohan on 2016/5/12.
 */
public class HealthPlanParamBean extends BaseBean{
    private HealthPlanParamListBean param;
    private String returnValue;

    public HealthPlanParamListBean getParam() {
        return param;
    }

    public void setParam(HealthPlanParamListBean param) {
        this.param = param;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
