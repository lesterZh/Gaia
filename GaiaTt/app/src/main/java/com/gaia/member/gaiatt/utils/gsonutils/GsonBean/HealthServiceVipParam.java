package com.gaia.member.gaiatt.utils.gsonutils.GsonBean;

/**
 * Created by WangHaohan on 2016/5/17.
 */
public class HealthServiceVipParam extends BaseBean{
    private HealthServiceVipListParam param;
    private String returnValue;

    public HealthServiceVipListParam getParam() {
        return param;
    }

    public void setParam(HealthServiceVipListParam param) {
        this.param = param;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
