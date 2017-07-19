package com.gaia.member.androidlib.net.bean;/**
 * @Title: ClinicListFromServiceBean
 * @Package com.gaia.member.androidlib.net.bean
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 14:39
 * @version V1.0
 */

import java.util.List;

/**
 *
 *@ClassName: ClinicListFromServiceBean
 * Description: 根据服务返回诊所列表
 * @author Android客户端组-ZhangHaiTao
 * @date 2016/6/22 14:39
 *
 */
public class ClinicListFromServiceBean {

    private ParamBean param;
    /**
     * param : {"clinicList":[{"clinicLocation":"天府大道中段188号","clinicID":124,"clinicName":"盖亚诊所（高新店）","clinicPhoneNum":"028-87777777","distance":800,"clinicImageUrl":"325423.png","latitude":123.45,"longitude":12.34,"currentService":{"serviceid":"231","serviceName":"陪诊服务","serviceIconUrl":"afdap.png","content":"服务内容"},"appointOptions":{"price":12,"districtRequirement":"不限","serviceDates":["2016/06/06","2016/09/09"],"minimumDate":"2015/01/01","maximumDate":"2016/12/12"}}]}
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
         * clinicLocation : 天府大道中段188号
         * clinicID : 124
         * clinicName : 盖亚诊所（高新店）
         * clinicPhoneNum : 028-87777777
         * distance : 800
         * clinicImageUrl : 325423.png
         * latitude : 123.45
         * longitude : 12.34
         * currentService : {"serviceid":"231","serviceName":"陪诊服务","serviceIconUrl":"afdap.png","content":"服务内容"}
         * appointOptions : {"price":12,"districtRequirement":"不限","serviceDates":["2016/06/06","2016/09/09"],"minimumDate":"2015/01/01","maximumDate":"2016/12/12"}
         */

        private List<ClinicListBean> clinicList;

        public List<ClinicListBean> getClinicList() {
            return clinicList;
        }

        public void setClinicList(List<ClinicListBean> clinicList) {
            this.clinicList = clinicList;
        }

        public static class ClinicListBean {
            private String clinicLocation;
            private int clinicID;
            private String clinicName;
            private String clinicPhoneNum;
            private int distance;
            private String clinicImageUrl;
            private double latitude;
            private double longitude;
            /**
             * serviceid : 231
             * serviceName : 陪诊服务
             * serviceIconUrl : afdap.png
             * content : 服务内容
             */

            private CurrentServiceBean currentService;
            /**
             * price : 12
             * districtRequirement : 不限
             * serviceDates : ["2016/06/06","2016/09/09"]
             * minimumDate : 2015/01/01
             * maximumDate : 2016/12/12
             */

            private AppointOptionsBean appointOptions;

            public String getClinicLocation() {
                return clinicLocation;
            }

            public void setClinicLocation(String clinicLocation) {
                this.clinicLocation = clinicLocation;
            }

            public int getClinicID() {
                return clinicID;
            }

            public void setClinicID(int clinicID) {
                this.clinicID = clinicID;
            }

            public String getClinicName() {
                return clinicName;
            }

            public void setClinicName(String clinicName) {
                this.clinicName = clinicName;
            }

            public String getClinicPhoneNum() {
                return clinicPhoneNum;
            }

            public void setClinicPhoneNum(String clinicPhoneNum) {
                this.clinicPhoneNum = clinicPhoneNum;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getClinicImageUrl() {
                return clinicImageUrl;
            }

            public void setClinicImageUrl(String clinicImageUrl) {
                this.clinicImageUrl = clinicImageUrl;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public CurrentServiceBean getCurrentService() {
                return currentService;
            }

            public void setCurrentService(CurrentServiceBean currentService) {
                this.currentService = currentService;
            }

            public AppointOptionsBean getAppointOptions() {
                return appointOptions;
            }

            public void setAppointOptions(AppointOptionsBean appointOptions) {
                this.appointOptions = appointOptions;
            }

            public static class CurrentServiceBean {
                private String serviceid;
                private String serviceName;
                private String serviceIconUrl;
                private String content;

                public String getServiceid() {
                    return serviceid;
                }

                public void setServiceid(String serviceid) {
                    this.serviceid = serviceid;
                }

                public String getServiceName() {
                    return serviceName;
                }

                public void setServiceName(String serviceName) {
                    this.serviceName = serviceName;
                }

                public String getServiceIconUrl() {
                    return serviceIconUrl;
                }

                public void setServiceIconUrl(String serviceIconUrl) {
                    this.serviceIconUrl = serviceIconUrl;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }

            public static class AppointOptionsBean {
                private int price;
                private String districtRequirement;
                private String minimumDate;
                private String maximumDate;
                private List<String> serviceDates;

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
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

                public List<String> getServiceDates() {
                    return serviceDates;
                }

                public void setServiceDates(List<String> serviceDates) {
                    this.serviceDates = serviceDates;
                }
            }
        }
    }
}
