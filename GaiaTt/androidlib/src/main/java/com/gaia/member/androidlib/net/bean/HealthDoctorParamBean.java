package com.gaia.member.androidlib.net.bean;

/**
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/31 15:38
 */
public class HealthDoctorParamBean {
    /**
     * id : 1000002
     * name : 徐星辰
     * icon : xxx.png
     * detail : 联康管理医师介绍，健康管理医师介绍，健康管理医师介绍,健康管理医师介绍.
     * degree : 3
     * storeId : 3
     * storeName : 盖亚高新店
     * professionalTitle : 副教授|华西医院|小儿外科|从业20年，有丰富的临床经验
     */

    private ParamBean param;
    /**
     * param : {"id":1000002,"name":"徐星辰","icon":"xxx.png","detail":"联康管理医师介绍，健康管理医师介绍，健康管理医师介绍,健康管理医师介绍.",
     * "degree":3,"storeId":3,"storeName":"盖亚高新店","professionalTitle":"副教授|华西医院|小儿外科|从业20年，有丰富的临床经验"}
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
        private int id;
        private String name;
        private String icon;
        private String detail;
        private int degree;
        private int storeId;
        private String storeName;
        private String professionalTitle;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getProfessionalTitle() {
            return professionalTitle;
        }

        public void setProfessionalTitle(String professionalTitle) {
            this.professionalTitle = professionalTitle;
        }
    }
}
