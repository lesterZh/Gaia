package com.gaia.member.androidlib.net.bean;

/**
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/1 14:52
 */
public class PersonArchivesBean {

    /**
     * required : {"name":"王丽","sex":"女","birthday":"1995-04-23","id":"51220119950422300000",
     * "phoneNumber":"028-7777777","contact":"13898985644","contactPhoneName":"王爸爸",
     * "contactPhoneNumber":"13658928687","householdType":"户籍","nativePlace":"四川省|成都市|武侯区|大源|xxx","ethnicity":"汉族",
     * "ABObloodType":"不详","RHbloodType":"不详","education":"文盲","profession ":"学生","maritalStatus":"未婚",
     * "medicalPayment":"城镇职工基本医疗保险"}
     * optional : {"address":"四川省|成都市|武侯区|大源|xxx","wrokPlace":"成都壹柒互动科技有限公司","drugAllergyHistory":"无",
     * "exposureHistory":"无","pastMedicalHistory":{"disease":"无","surgery":"无","trauma":"无","transfusion":"无",
     * "familyMedicalHistory":"无","geneticHistory":"无","disabilityStatus":"无"},
     * "environment":{"kitchenExhaustSetting":"油烟机","fuelType":"天然气","water":"经净化过滤的水","toilet":"卫生厕所","corral":"单设"}}
     */

    private ParamBean param;

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public static class ParamBean{
        private RequiredBean required;

        private OptionalBean optional;

        public RequiredBean getRequired() {
            return required;
        }

        public void setRequired(RequiredBean required) {
            this.required = required;
        }


        public OptionalBean getOptional() {
            return optional;
        }

        public void setOptional(OptionalBean optional) {
            this.optional = optional;
        }

        public static class RequiredBean{
            /**
             * name : 王丽
             * sex : 女
             * birthday : 1995-04-23
             * id : 51220119950422300000
             * phoneNumber : 028-7777777
             * contact : 13898985644
             * contactPhoneName : 王爸爸
             * contactPhoneNumber : 13658928687
             * householdType : 户籍
             * nativePlace : 四川省|成都市|武侯区|大源|xxx
             * ethnicity : 汉族
             * ABObloodType : 不详
             * RHbloodType : 不详
             * education : 文盲
             * profession  : 学生
             * maritalStatus : 未婚
             * medicalPayment : 城镇职工基本医疗保险
             */

            private String name;
            private String sex;
            private String birthday;
            private String id;
            private String phoneNumber;
            private String contact;
            private String contactPhoneNumber;
            private String householdType;
            private String nativePlace;
            private String ethnicity;
            private String ABObloodType;
            private String RHbloodType;
            private String education;
            private String profession;
            private String maritalStatus;
            private String medicalPayment;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }


            public String getContactPhoneNumber() {
                return contactPhoneNumber;
            }

            public void setContactPhoneNumber(String contactPhoneNumber) {
                this.contactPhoneNumber = contactPhoneNumber;
            }

            public String getHouseholdType() {
                return householdType;
            }

            public void setHouseholdType(String householdType) {
                this.householdType = householdType;
            }

            public String getNativePlace() {
                return nativePlace;
            }

            public void setNativePlace(String nativePlace) {
                this.nativePlace = nativePlace;
            }

            public String getEthnicity() {
                return ethnicity;
            }

            public void setEthnicity(String ethnicity) {
                this.ethnicity = ethnicity;
            }

            public String getABObloodType() {
                return ABObloodType;
            }

            public void setABObloodType(String ABObloodType) {
                this.ABObloodType = ABObloodType;
            }

            public String getRHbloodType() {
                return RHbloodType;
            }

            public void setRHbloodType(String RHbloodType) {
                this.RHbloodType = RHbloodType;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }

            public String getProfession() {
                return profession;
            }

            public void setProfession(String profession) {
                this.profession = profession;
            }

            public String getMaritalStatus() {
                return maritalStatus;
            }

            public void setMaritalStatus(String maritalStatus) {
                this.maritalStatus = maritalStatus;
            }

            public String getMedicalPayment() {
                return medicalPayment;
            }

            public void setMedicalPayment(String medicalPayment) {
                this.medicalPayment = medicalPayment;
            }
        }
        public static class OptionalBean {
            private String address;
            private String wrokPlace;
            private String drugAllergyHistory;
            private String exposureHistory;
            /**
             * disease : 无
             * surgery : 无
             * trauma : 无
             * transfusion : 无
             * familyMedicalHistory : 无
             * geneticHistory : 无
             * disabilityStatus : 无
             */

            private PastMedicalHistoryBean pastMedicalHistory;
            /**
             * kitchenExhaustSetting : 油烟机
             * fuelType : 天然气
             * water : 经净化过滤的水
             * toilet : 卫生厕所
             * corral : 单设
             */

            private EnvironmentBean environment;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getWrokPlace() {
                return wrokPlace;
            }

            public void setWrokPlace(String wrokPlace) {
                this.wrokPlace = wrokPlace;
            }

            public String getDrugAllergyHistory() {
                return drugAllergyHistory;
            }

            public void setDrugAllergyHistory(String drugAllergyHistory) {
                this.drugAllergyHistory = drugAllergyHistory;
            }

            public String getExposureHistory() {
                return exposureHistory;
            }

            public void setExposureHistory(String exposureHistory) {
                this.exposureHistory = exposureHistory;
            }

            public PastMedicalHistoryBean getPastMedicalHistory() {
                return pastMedicalHistory;
            }

            public void setPastMedicalHistory(PastMedicalHistoryBean pastMedicalHistory) {
                this.pastMedicalHistory = pastMedicalHistory;
            }

            public EnvironmentBean getEnvironment() {
                return environment;
            }

            public void setEnvironment(EnvironmentBean environment) {
                this.environment = environment;
            }

            public static class PastMedicalHistoryBean {
                private String disease;
                private String surgery;
                private String trauma;
                private String transfusion;
                private String geneticHistory;
                private String disabilityStatus;
                private FamilyMedicalHistory familyMedicalHistory;
                public static class FamilyMedicalHistory{
                    private String father;
                    private String mother;
                    private String brothersAndSisters;
                    private String children;

                    public String getFather() {
                        return father;
                    }

                    public void setFather(String father) {
                        this.father = father;
                    }

                    public String getMother() {
                        return mother;
                    }

                    public void setMother(String mother) {
                        this.mother = mother;
                    }

                    public String getChildren() {
                        return children;
                    }

                    public void setChildren(String children) {
                        this.children = children;
                    }

                    public String getBrothersAndSisters() {
                        return brothersAndSisters;
                    }

                    public void setBrothersAndSisters(String brothersAndSisters) {
                        this.brothersAndSisters = brothersAndSisters;
                    }
                }

                public FamilyMedicalHistory getFamilyMedicalHistory() {
                    return familyMedicalHistory;
                }

                public void setFamilyMedicalHistory(FamilyMedicalHistory familyMedicalHistory) {
                    this.familyMedicalHistory = familyMedicalHistory;
                }

                public String getDisease() {
                    return disease;
                }

                public void setDisease(String disease) {
                    this.disease = disease;
                }

                public String getSurgery() {
                    return surgery;
                }

                public void setSurgery(String surgery) {
                    this.surgery = surgery;
                }

                public String getTrauma() {
                    return trauma;
                }

                public void setTrauma(String trauma) {
                    this.trauma = trauma;
                }

                public String getTransfusion() {
                    return transfusion;
                }

                public void setTransfusion(String transfusion) {
                    this.transfusion = transfusion;
                }


                public String getGeneticHistory() {
                    return geneticHistory;
                }

                public void setGeneticHistory(String geneticHistory) {
                    this.geneticHistory = geneticHistory;
                }

                public String getDisabilityStatus() {
                    return disabilityStatus;
                }

                public void setDisabilityStatus(String disabilityStatus) {
                    this.disabilityStatus = disabilityStatus;
                }
            }

            public static class EnvironmentBean {
                private String kitchenExhaustSetting;
                private String fuelType;
                private String water;
                private String toilet;
                private String corral;

                public String getKitchenExhaustSetting() {
                    return kitchenExhaustSetting;
                }

                public void setKitchenExhaustSetting(String kitchenExhaustSetting) {
                    this.kitchenExhaustSetting = kitchenExhaustSetting;
                }

                public String getFuelType() {
                    return fuelType;
                }

                public void setFuelType(String fuelType) {
                    this.fuelType = fuelType;
                }

                public String getWater() {
                    return water;
                }

                public void setWater(String water) {
                    this.water = water;
                }

                public String getToilet() {
                    return toilet;
                }

                public void setToilet(String toilet) {
                    this.toilet = toilet;
                }

                public String getCorral() {
                    return corral;
                }

                public void setCorral(String corral) {
                    this.corral = corral;
                }
            }
        }
    }
    /**
     * error : null
     * returnValue : null
     * success : true
     */

    private Object error;
    private Object returnValue;
    private boolean success;

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


}
