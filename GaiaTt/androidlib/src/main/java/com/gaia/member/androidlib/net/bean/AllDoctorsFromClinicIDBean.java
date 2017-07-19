package com.gaia.member.androidlib.net.bean;
/**
 * @Title: AllDoctorsFromClinicIDBean
 * @Package com.gaia.member.androidlib.net.bean
 * @Description: 根据诊所返回所有医生列表
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 15:43
 * @version V1.0
 */

import java.util.List;

/**
 *
 *@ClassName: AllDoctorsFromClinicIDBean
 * Description: 根据诊所返回所有医生列表
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 15:43
 *
 */
public class AllDoctorsFromClinicIDBean {

    private ParamBean param;
    /**
     * param : {"doctorList":[{"doctorId":124,"doctorName":"徐心澄","headPic":"2343.png","doctorLevel":"副教授","medicalCategory":"华西医院 小儿外科","appointmentPrice":10,"diseaseRequirements":"新生儿肛肠畸形 小儿肿瘤","areaRequirements":"不限","allowTime":[{"time":"20160410"},{"time":"20160415"},{"time":"20160417"}],"doctorPhone":"028-2564654"}]}
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
         * doctorId : 124
         * doctorName : 徐心澄
         * headPic : 2343.png
         * doctorLevel : 副教授
         * medicalCategory : 华西医院 小儿外科
         * appointmentPrice : 10
         * diseaseRequirements : 新生儿肛肠畸形 小儿肿瘤
         * areaRequirements : 不限
         * allowTime : [{"time":"20160410"},{"time":"20160415"},{"time":"20160417"}]
         * doctorPhone : 028-2564654
         */

        private List<DoctorListBean> doctorList;

        public List<DoctorListBean> getDoctorList() {
            return doctorList;
        }

        public void setDoctorList(List<DoctorListBean> doctorList) {
            this.doctorList = doctorList;
        }

        public static class DoctorListBean {
            private int doctorId;
            private String doctorName;
            private String headPic;
            private String doctorLevel;
            private String medicalCategory;
            private int appointmentPrice;
            private String diseaseRequirements;
            private String areaRequirements;
            private String doctorPhone;
            /**
             * time : 20160410
             */

            private List<AllowTimeBean> allowTime;

            public int getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(int doctorId) {
                this.doctorId = doctorId;
            }

            public String getDoctorName() {
                return doctorName;
            }

            public void setDoctorName(String doctorName) {
                this.doctorName = doctorName;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public String getDoctorLevel() {
                return doctorLevel;
            }

            public void setDoctorLevel(String doctorLevel) {
                this.doctorLevel = doctorLevel;
            }

            public String getMedicalCategory() {
                return medicalCategory;
            }

            public void setMedicalCategory(String medicalCategory) {
                this.medicalCategory = medicalCategory;
            }

            public int getAppointmentPrice() {
                return appointmentPrice;
            }

            public void setAppointmentPrice(int appointmentPrice) {
                this.appointmentPrice = appointmentPrice;
            }

            public String getDiseaseRequirements() {
                return diseaseRequirements;
            }

            public void setDiseaseRequirements(String diseaseRequirements) {
                this.diseaseRequirements = diseaseRequirements;
            }

            public String getAreaRequirements() {
                return areaRequirements;
            }

            public void setAreaRequirements(String areaRequirements) {
                this.areaRequirements = areaRequirements;
            }

            public String getDoctorPhone() {
                return doctorPhone;
            }

            public void setDoctorPhone(String doctorPhone) {
                this.doctorPhone = doctorPhone;
            }

            public List<AllowTimeBean> getAllowTime() {
                return allowTime;
            }

            public void setAllowTime(List<AllowTimeBean> allowTime) {
                this.allowTime = allowTime;
            }

            public static class AllowTimeBean {
                private String time;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }
            }
        }
    }
}
