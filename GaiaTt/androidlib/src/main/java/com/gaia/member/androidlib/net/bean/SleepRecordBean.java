package com.gaia.member.androidlib.net.bean;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/23 14:34
 * @version V1.0
 */

import java.util.List;

/**
 * @ClassName: SleepRecordBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/23 14:34
 */
public class SleepRecordBean {
    //参数
    private ParamBean param;
    /**
     * param : {"sleepList":[{"sleepTime":480,"fallAsleep":"22:00","wake":"06:00","date":"2016-6-20"}]}
     * error : null
     * returnValue : null
     * success : true
     */
    //错误
    private Object error;
    //返回值
    private Object returnValue;
    //成功
    private boolean success;

    //获取参数
    public ParamBean getParam() {
        return param;
    }

    //设置参数
    public void setParam(ParamBean param) {
        this.param = param;
    }

    //获取错误信息
    public Object getError() {
        return error;
    }

    //设置错误
    public void setError(Object error) {
        this.error = error;
    }

    //获取返回值
    public Object getReturnValue() {
        return returnValue;
    }
    //设置返回值
    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
    //获取成功
    public boolean isSuccess() {
        return success;
    }
    //设置成功
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ParamBean {
        /**
         * sleepTime : 480
         * fallAsleep : 22:00
         * wake : 06:00
         * date : 2016-6-20
         */

        //睡眠记录列表
        private List<SleepListBean> sleepList;

        public List<SleepListBean> getSleepList() {
            return sleepList;
        }

        public void setSleepList(List<SleepListBean> sleepList) {
            this.sleepList = sleepList;
        }

        public static class SleepListBean {
            //时长
            private int sleepTime;
            //入睡时间
            private String fallAsleep;
            //醒来时间
            private String wake;
            //日期
            private String date;

            //获取睡眠时长
            public int getSleepTime() {
                return sleepTime;
            }

            //设置时长
            public void setSleepTime(int sleepTime) {
                this.sleepTime = sleepTime;
            }

            //获取入睡时间
            public String getFallAsleep() {
                return fallAsleep;
            }

            //设置入睡时间
            public void setFallAsleep(String fallAsleep) {
                this.fallAsleep = fallAsleep;
            }

            //获取醒来时间
            public String getWake() {
                return wake;
            }

            //设置醒来时间
            public void setWake(String wake) {
                this.wake = wake;
            }

            //获取日期
            public String getDate() {
                return date;
            }

            //设置日期
            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
