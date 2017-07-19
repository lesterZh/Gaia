package com.gaia.member.androidlib.net.bean;/**
 * @Title: ServiceOrderBean
 * @Package com.gaia.member.androidlib.net.bean
 * @Description: 查询用户可以预约的服务列表
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/13 15:04
 * @version V1.0
 */

import java.util.List;

/**
 *
 *@ClassName: ServiceOrderBean
 * Description: 查询用户可以预约的服务列表
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/13 15:04
 *
 */
public class ServiceOrderBean {

    private ParamBean param;
    /**
     * param : {"services":[{"serviceid":"1231","name":"陪诊服务","iconUrl":"http://asdfa.png","content":"病人可以带1名家属陪同诊断"}]}
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
         * serviceid : 1231
         * name : 陪诊服务
         * iconUrl : http://asdfa.png
         * content : 病人可以带1名家属陪同诊断
         */

        private List<ServicesBean> services;

        public List<ServicesBean> getServices() {
            return services;
        }

        public void setServices(List<ServicesBean> services) {
            this.services = services;
        }

        public static class ServicesBean {
            private String serviceid;
            private String name;
            private String iconUrl;
            private String content;

            public String getServiceid() {
                return serviceid;
            }

            public void setServiceid(String serviceid) {
                this.serviceid = serviceid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
