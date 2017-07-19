package com.gaia.member.androidlib.net.bean;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/13 16:13
 * @version V1.0
 */

import java.util.List;

/**
 * @ClassName: DietPlanBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/13 16:13
 */
public class DietPlanBean {
    /**
     * list : [{"title":"第一天","detail":"","status":1,"date":"2015-02-13","missionId":100001,"attrs":[{"title":"上午",
     * "count":"100ml"}],"count":0}]
     * title : 减肥计划
     * detail : 减轻体重
     * noitce : 注意事项：xxxx
     * repeatDetail : 30天
     * fitGroupDetail : 适用年龄 20岁-30岁
     * levelDetail :
     * startDate : 2015-02-03
     * endDate : 2015-03-04
     * limitCount : 0
     */

    private ParamBean param;
    /**
     * param : {"list":[{"title":"第一天","detail":"","status":1,"date":"2015-02-13","missionId":100001,
     * "attrs":[{"title":"上午","count":"100ml"}],"count":0}],"title":"减肥计划","detail":"减轻体重","noitce":"注意事项：xxxx",
     * "repeatDetail":"30天","fitGroupDetail":"适用年龄 20岁-30岁","levelDetail":"","startDate":"2015-02-03",
     * "endDate":"2015-03-04","limitCount":0}
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
        private String title;
        private String detail;
        private String noitce;
        private String repeatDetail;
        private String fitGroupDetail;
        private String levelDetail;
        private String startDate;
        private String endDate;
        private int limitCount;
        /**
         * title : 第一天
         * detail :
         * status : 1
         * date : 2015-02-13
         * missionId : 100001
         * attrs : [{"title":"上午","count":"100ml"}]
         * count : 0
         */

        private List<ListBean> list;

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

        public String getNoitce() {
            return noitce;
        }

        public void setNoitce(String noitce) {
            this.noitce = noitce;
        }

        public String getRepeatDetail() {
            return repeatDetail;
        }

        public void setRepeatDetail(String repeatDetail) {
            this.repeatDetail = repeatDetail;
        }

        public String getFitGroupDetail() {
            return fitGroupDetail;
        }

        public void setFitGroupDetail(String fitGroupDetail) {
            this.fitGroupDetail = fitGroupDetail;
        }

        public String getLevelDetail() {
            return levelDetail;
        }

        public void setLevelDetail(String levelDetail) {
            this.levelDetail = levelDetail;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getLimitCount() {
            return limitCount;
        }

        public void setLimitCount(int limitCount) {
            this.limitCount = limitCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String title;
            private String detail;
            private int status;
            private String date;
            private int missionId;
            private int count;
            /**
             * title : 上午
             * count : 100ml
             */

            private List<AttrsBean> attrs;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getMissionId() {
                return missionId;
            }

            public void setMissionId(int missionId) {
                this.missionId = missionId;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<AttrsBean> getAttrs() {
                return attrs;
            }

            public void setAttrs(List<AttrsBean> attrs) {
                this.attrs = attrs;
            }

            public static class AttrsBean {
                private String title;
                private String count;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }
            }
        }
    }
}
