package com.gaia.member.gaiatt.utils.gsonutils.GsonBean;

/**
 * Created by WangHaohan on 2016/5/5.
 */
public class MealParamBean extends BaseBean{
    private MealParamListBean param;
    private String returnValue;

    public MealParamListBean getParam() {
        return param;
    }

    public void setParam(MealParamListBean param) {
        this.param = param;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
