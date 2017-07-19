package com.gaia.member.androidlib.net.bean;
/**
 * @Title: MessageHomeListBean
 * @Package com.gaia.member.androidlib.net.bean
 * @Description: 根据诊所返回所有医生列表
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 15:43
 * @version V1.0
 */

import java.io.Serializable;
import java.util.List;

/**
 *
 *@ClassName: MessageHomeListBean
 * Description: 获取我的消息主界面列表
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 15:43
 *
 */
public class MessageHomeListBean implements Serializable {

    private ParamBean param;
    /**
     * param : {"list":[{"messageType":"1111","name":"消息1","icon":"www.jkfjdl.com/22.jpg","desc":"dfdfsdfdfs","unread":5}]}
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
         * messageType : 1111
         * name : 消息1
         * icon : www.jkfjdl.com/22.jpg
         * desc : dfdfsdfdfs
         * unread : 5
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String messageType;
            private String name;
            private String icon;
            private String desc;
            private int unread;

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getUnread() {
                return unread;
            }

            public void setUnread(int unread) {
                this.unread = unread;
            }
        }
    }
}
