/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/14 9:56
 * @version V1.0
 */

package com.gaia.member.androidlib.net.bean;
/**
 * @ClassName: BaseBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class BaseBean {
    /**
     * error : null
     * returnValue : null
     * success : true
     */
    private Object returnValue;//返回值
    private Object error;//返回错误
    private boolean success;//成功与否
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
}
