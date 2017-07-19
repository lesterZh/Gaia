package com.gaia.member.androidlib.net.bean;/**
 * @Title: DoctorsFromClinicIDBean
 * @Package com.gaia.member.androidlib.net.bean
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 15:07
 * @version V1.0
 */

import java.util.List;

/**
 *
 *@ClassName: DoctorsFromClinicIDBean
 * Description: 根据诊所ID来查询所有的可以预约的医生
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 15:07
 *
 */
public class DoctorsFromClinicIDBean {

    private ParamBean param;
    /**
     * param : {"doctors":[{"userId":"123","username":"徐晓晨","jobTitle":"副教授","clinicName":"盖亚高新店","departmentName":"小儿外科","conditions":["小儿创伤","骨科","矫形外科"],"price":12,"phone":"028-999999","address":"高新区华府大道XXX号","appointOptions":{"diseaseRequirement":"疾病要求，疾病要求","districtRequirement":"不限","canOrderDate":["2016-02-01","2016-03-02"],"minimumDate":"2016-02-01","maximumDate":"2016-04-30"}}]}
     * error : null
     * returnValue : null
     * success : true
     * errMsg : 错误描述
     */

    private Object error;
    private Object returnValue;
    private boolean success;
    private String errMsg;

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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static class ParamBean {
        /**
         * userId : 123
         * username : 徐晓晨
         * jobTitle : 副教授
         * clinicName : 盖亚高新店
         * departmentName : 小儿外科
         * conditions : ["小儿创伤","骨科","矫形外科"]
         * price : 12
         * phone : 028-999999
         * address : 高新区华府大道XXX号
         * appointOptions : {"diseaseRequirement":"疾病要求，疾病要求","districtRequirement":"不限","canOrderDate":["2016-02-01","2016-03-02"],"minimumDate":"2016-02-01","maximumDate":"2016-04-30"}
         */

        private List<DoctorsBean> doctors;

        public List<DoctorsBean> getDoctors() {
            return doctors;
        }

        public void setDoctors(List<DoctorsBean> doctors) {
            this.doctors = doctors;
        }

        public static class DoctorsBean {
            private String userId;
            private String username;
            private String jobTitle;
            private String clinicName;
            private String departmentName;
            private int price;
            private String phone;
            private String address;
            /**
             * diseaseRequirement : 疾病要求，疾病要求
             * districtRequirement : 不限
             * canOrderDate : ["2016-02-01","2016-03-02"]
             * minimumDate : 2016-02-01
             * maximumDate : 2016-04-30
             */

            private AppointOptionsBean appointOptions;
            private List<String> conditions;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getJobTitle() {
                return jobTitle;
            }

            public void setJobTitle(String jobTitle) {
                this.jobTitle = jobTitle;
            }

            public String getClinicName() {
                return clinicName;
            }

            public void setClinicName(String clinicName) {
                this.clinicName = clinicName;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public AppointOptionsBean getAppointOptions() {
                return appointOptions;
            }

            public void setAppointOptions(AppointOptionsBean appointOptions) {
                this.appointOptions = appointOptions;
            }

            public List<String> getConditions() {
                return conditions;
            }

            public void setConditions(List<String> conditions) {
                this.conditions = conditions;
            }

            public static class AppointOptionsBean {
                private String diseaseRequirement;
                private String districtRequirement;
                private String minimumDate;
                private String maximumDate;
                private List<String> canOrderDate;

                public String getDiseaseRequirement() {
                    return diseaseRequirement;
                }

                public void setDiseaseRequirement(String diseaseRequirement) {
                    this.diseaseRequirement = diseaseRequirement;
                }

                public String getDistrictRequirement() {
                    return districtRequirement;
                }

                public void setDistrictRequirement(String districtRequirement) {
                    this.districtRequirement = districtRequirement;
                }

                public String getMinimumDate() {
                    return minimumDate;
                }

                public void setMinimumDate(String minimumDate) {
                    this.minimumDate = minimumDate;
                }

                public String getMaximumDate() {
                    return maximumDate;
                }

                public void setMaximumDate(String maximumDate) {
                    this.maximumDate = maximumDate;
                }

                public List<String> getCanOrderDate() {
                    return canOrderDate;
                }

                public void setCanOrderDate(List<String> canOrderDate) {
                    this.canOrderDate = canOrderDate;
                }
            }
        }
    }
}
