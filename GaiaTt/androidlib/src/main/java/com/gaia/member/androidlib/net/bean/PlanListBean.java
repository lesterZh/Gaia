package com.gaia.member.androidlib.net.bean;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/13 10:55
 * @version V1.0
 */

import java.util.List;

/**
 * @ClassName: PlanListBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/13 10:55
 */
public class PlanListBean {
    private ParamBean param;
    /**
     * param : {"list":[{"title":"轻度健康干预计划","detail":"轻度健康干预计划描述","status":1,"date":"2015-02-13","category":1,
     * "id":100001}]}
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
         * title : 轻度健康干预计划
         * detail : 轻度健康干预计划描述
         * status : 1
         * date : 2015-02-13
         * category : 1
         * id : 100001
         */
        //计划列表
        private List<ListBean> list;

        //
        public List<ListBean> getList() {
            return list;
        }

        //
        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            //标题
            private String title;
            //内容详情
            private String detail;
            //状态
            private int status;
            //日期
            private String date;
            //计划类型
            private int category;
            //计划id
            private int id;
            //获取计划名称
            public String getTitle() {
                return title;
            }
            //设置计划名称
            public void setTitle(String title) {
                this.title = title;
            }
            //获取内容
            public String getDetail() {
                return detail;
            }
            //设置内容
            public void setDetail(String detail) {
                this.detail = detail;
            }
            //获取感知强度
            public int getStatus() {
                return status;
            }
            //设置状态
            public void setStatus(int status) {
                this.status = status;
            }
            //获取日期
            public String getDate() {
                return date;
            }
            //设置日期
            public void setDate(String date) {
                this.date = date;
            }
            //获取类型
            public int getCategory() {
                return category;
            }
            //设置类型
            public void setCategory(int category) {
                this.category = category;
            }
            //获取计划id
            public int getId() {
                return id;
            }
            //设置计划id
            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
