/**
 * @Title: LoginBean
 * @Package com.gaia.member.gaiatt.login.net.bean
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author 移动开发组-王浩韩
 * @date 2016/6/7 0007 下午 11:11
 * @version V1.0
 */
package com.gaia.member.androidlib.net.bean;

/**
 *
 *@ClassName: LoginBean
 * Description: 登录接口模型
 * @author 移动开发组-王浩韩
 * @date 2016/6/7 0007 下午 11:11
 *
 */
public class LoginBean {
    /**
     * userinfo : {"masterTel":null,"userId":"80","authToken":"TLFK8Z-8R7BQJWMOJIQTYMOYHYM1-E48I55PI-5"}
     */

    private ParamBean param;
    /**
     * param : {"userinfo":{"masterTel":null,"userId":"80","authToken":"TLFK8Z-8R7BQJWMOJIQTYMOYHYM1-E48I55PI-5"}}
     * returnValue : null
     * errMsg : null
     * resultCode : null
     * success : true
     */

    private Object returnValue;//返回值
    private Object errMsg;//返回错误
    private Object resultCode;//返回验证码
    private boolean success;//成功与否

    /**
     * 获取返回参数
     * */
    public ParamBean getParam() {
        return param;
    }

    /**
     * 设置参数
     * @param param 参数
     * */
    public void setParam(ParamBean param) {
        this.param = param;
    }

    /**
     * 获取返回值
     * */
    public Object getReturnValue() {
        return returnValue;
    }

    /**
     * 设置返回值
     * @param  returnValue 返回值
     * */
    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    /**
     * 获取错误数据
     * */
    public Object getErrMsg() {
        return errMsg;
    }

    /**
     * 设置错误数据
     * @param errMsg 错误数据
     * */
    public void setErrMsg(Object errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 获取返回验证码
     * */
    public Object getResultCode() {
        return resultCode;
    }

    /**
     * 设置验证码
     * @param resultCode 验证码
     * */
    public void setResultCode(Object resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 判断是否成功
     * */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置是否成功
     * @param  success 是否成功
     * */
    public void setSuccess(boolean success) {
        this.success = success;
    }


    public static class ParamBean {
        /**
         * masterTel : null
         * userId : 80
         * authToken : TLFK8Z-8R7BQJWMOJIQTYMOYHYM1-E48I55PI-5
         */

        private UserinfoBean userinfo;

        /**
         * 获取用户信息
         * */
        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        /**
         *设置用户信息
         * @param userinfo 用户信息
         * */
        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            private Object masterTel;//用户电话号码
            private String userId;//用户Id
            private String authToken;//个人Token

            /**
             * 获取电话号码
             * */
            public Object getMasterTel() {
                return masterTel;
            }

            /**
             * 设置电话号码
             * @param  masterTel
             * */
            public void setMasterTel(Object masterTel) {
                this.masterTel = masterTel;
            }

            /**
             * 获取用户Id
             * */
            public String getUserId() {
                return userId;
            }

            /**
             * 设置用户Id
             * @param  userId
             * */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             * 获取Token
             * */
            public String getAuthToken() {
                return authToken;
            }

            /**
             * 设置Token
             * @param authToken
             * */
            public void setAuthToken(String authToken) {
                this.authToken = authToken;
            }
        }
    }
}
