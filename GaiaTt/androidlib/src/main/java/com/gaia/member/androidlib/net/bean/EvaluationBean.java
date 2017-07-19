/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/22 11:45
 * @version V1.0
 */
package com.gaia.member.androidlib.net.bean;


/**
 * @ClassName: EvaluationBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/22 11:45
 */
public class EvaluationBean {

    private ParamBean param;
    /**
     * param : {"healthStatus":0}
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
         * healthStatus : 健康状态
         */
        private int healthStatus;

        public int getHealthStatus() {
            return healthStatus;
        }

        public void setHealthStatus(int healthStatus) {
            this.healthStatus = healthStatus;
        }
    }
}
