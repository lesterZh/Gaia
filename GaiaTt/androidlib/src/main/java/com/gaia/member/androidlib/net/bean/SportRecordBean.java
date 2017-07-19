package com.gaia.member.androidlib.net.bean;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/23 18:30
 * @version V1.0
 */

import java.util.List;

/**
 * @ClassName: SportRecordBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/23 18:30
 */
public class SportRecordBean {
    //参数
    private ParamBean param;
    /**
     * param : {"sportList":[{"steps":5454,"kilometre":4.02,"minute":18,"calorie":209,"date":"2016-6-20"}]}
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
         * steps : 5454
         * kilometre : 4.02
         * minute : 18
         * calorie : 209
         * date : 2016-6-20
         */

        //运动记录列表
        private List<SportListBean> sportList;

        //获取运动记录
        public List<SportListBean> getSportList() {
            return sportList;
        }

        //设置运动记录
        public void setSportList(List<SportListBean> sportList) {
            this.sportList = sportList;
        }

        public static class SportListBean {
            //步数
            private int steps;
            //距离
            private double kilometre;
            //活动时长
            private int minute;
            //活动消耗
            private int calorie;
            //日期
            private String date;

            //获取步数
            public int getSteps() {
                return steps;
            }

            //设置步数
            public void setSteps(int steps) {
                this.steps = steps;
            }

            //获取距离
            public double getKilometre() {
                return kilometre;
            }

            //设置距离
            public void setKilometre(double kilometre) {
                this.kilometre = kilometre;
            }

            //获取时长
            public int getMinute() {
                return minute;
            }

            //设置时长
            public void setMinute(int minute) {
                this.minute = minute;
            }

            //获取消耗
            public int getCalorie() {
                return calorie;
            }

            //设置消耗
            public void setCalorie(int calorie) {
                this.calorie = calorie;
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
