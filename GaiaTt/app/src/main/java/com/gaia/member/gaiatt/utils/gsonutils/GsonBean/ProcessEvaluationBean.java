package com.gaia.member.gaiatt.utils.gsonutils.GsonBean;

import java.util.List;

/**
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/31 18:06
 */
public class ProcessEvaluationBean {
    private ParamBean param;
    /**
     * param : {"list":[{"id":100001,"title":"SCL90症状自评星标","detail":"从感觉，健康，思维，健康，思维，意识直至生活......","degree":50,
     * "date":"2015-01-11","category":1},{"id":100002,"title":"SCL90症状自评星标","detail":"从感觉，健康，思维，健康，思维，意识直至生活......",
     * "degree":60,"date":"2015-02-15","category":2},{"id":100003,"title":"SCL90症状自评星标",
     * "detail":"从感觉，健康，思维，健康，思维，意识直至生活......","degree":72,"date":"2015-03-23","category":3},{"id":100004,
     * "title":"SCL90症状自评星标","detail":"从感觉，健康，思维，健康，思维，意识直至生活......","degree":80,"date":"2015-04-03","category":4},
     * {"id":100005,"title":"SCL90症状自评星标","detail":"从感觉，健康，思维，健康，思维，意识直至生活......","degree":30,"date":"2015-05-10",
     * "category":5},{"id":100006,"title":"SCL90症状自评星标","detail":"从感觉，健康，思维，健康，思维，意识直至生活......","degree":10,
     * "date":"2015-06-11","category":1},{"id":100007,"title":"SCL90症状自评星标","detail":"从感觉，健康，思维，健康，思维，意识直至生活......",
     * "degree":90,"date":"2015-07-15","category":2},{"id":100008,"title":"SCL90症状自评星标",
     * "detail":"从感觉，健康，思维，健康，思维，意识直至生活......","degree":100,"date":"2015-08-23","category":3},{"id":100009,
     * "title":"SCL90症状自评星标","detail":"从感觉，健康，思维，健康，思维，意识直至生活......","degree":100,"date":"2016-09-03","category":4},
     * {"id":1000010,"title":"SCL90症状自评星标","detail":"从感觉，健康，思维，健康，思维，意识直至生活......","degree":40,"date":"2016-10-10",
     * "category":5}]}
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
         * id : 100001
         * title : SCL90症状自评星标
         * detail : 从感觉，健康，思维，健康，思维，意识直至生活......
         * degree : 50
         * date : 2015-01-11
         * category : 1
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String title;
            private String detail;
            private int degree;
            private String date;
            private int category;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public int getDegree() {
                return degree;
            }

            public void setDegree(int degree) {
                this.degree = degree;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }
        }
    }
}
