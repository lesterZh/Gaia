package com.gaia.member.androidlib.net.bean;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/14 14:15
 * @version V1.0
 */

import java.util.List;

/**
 * @ClassName: HealthToolsListBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 14:15
 */
public class HealthToolsListBean {
    private ParamBean param;
    /**
     * param : {"list":[{"title":"体重指数BMI","detail":"想知道你是否体重超标么？","icon":"bmi.png","stauts":true,
     * "linkUrl":"http://xxx/test/BMI.html","vip":"flase"},{"title":"腰臀比计算","detail":"此工具可以帮你了解你是什么样子的体型","icon":"xx
     * .png","stauts":true,"linkUrl":"http://xxx/test/yaotun.html","vip":false}]}
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

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        /**
         * title : 体重指数BMI
         * detail : 想知道你是否体重超标么？
         * icon : bmi.png
         * stauts : true
         * linkUrl : http://xxx/test/BMI.html
         * vip : flase
         */
        public static class ListBean {
            private String title;
            private String detail;
            private String icon;
            private boolean stauts;
            private String linkUrl;
            private String vip;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public boolean isStauts() {
                return stauts;
            }

            public void setStauts(boolean stauts) {
                this.stauts = stauts;
            }

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getVip() {
                return vip;
            }

            public void setVip(String vip) {
                this.vip = vip;
            }
        }
    }
}
