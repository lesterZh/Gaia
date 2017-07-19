package com.gaia.member.androidlib.net.bean;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/13 15:54
 * @version V1.0
 */

import java.util.List;

/**
 * @ClassName: SportPlanBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 2016/6/13 15:54
 */
public class SportPlanBean {
    /**
     * list : [{"title":"俯卧撑","detail":"每组5个，共10组，1个小时","status":1,"date":"2015-02-13","level":1,"missionId":100001,
     * "attrs":"","repeat":0,"times":10,"spendTime":60,"feelLevel":1}]
     * title : 轻度健康干预计划
     * detail : 轻度健康干预计划描述
     * noitce : 注意事项：xxxx
     * repeatDetail : 30天
     * fitGroupDetail : 适用年龄 20岁-30岁
     * levelDetail : 第一阶段：养成运动习惯
     * startDate : 2015-02-03
     * endDate : 2015-03-04
     */

    private ParamBean param;
    /**
     * param : {"list":[{"title":"俯卧撑","detail":"每组5个，共10组，1个小时","status":1,"date":"2015-02-13","level":1,
     * "missionId":100001,"attrs":"","repeat":0,"times":10,"spendTime":60,"feelLevel":1}],"title":"轻度健康干预计划",
     * "detail":"轻度健康干预计划描述","noitce":"注意事项：xxxx","repeatDetail":"30天","fitGroupDetail":"适用年龄 20岁-30岁",
     * "levelDetail":"第一阶段：养成运动习惯","startDate":"2015-02-03","endDate":"2015-03-04"}
     * error : null
     * returnValue : null
     * success : true
     */

    //错误
    private Object error;
    //返回值
    private Object returnValue;
    //成功
    private boolean success;

    //获取参数
    public ParamBean getParam() {
        return param;
    }

    //设置参数
    public void setParam(ParamBean param) {
        this.param = param;
    }

    //获取错误信息
    public Object getError() {
        return error;
    }

    //设置错误
    public void setError(Object error) {
        this.error = error;
    }

    //获取返回值
    public Object getReturnValue() {
        return returnValue;
    }
    //设置返回值
    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
    //获取成功
    public boolean isSuccess() {
        return success;
    }
    //设置成功
    public void setSuccess(boolean success) {
        this.success = success;
    }


    public static class ParamBean {
        //标题
        private String title;
        //详情
        private String detail;
        //注意事项
        private String noitce;
        //重复周期描述
        private String repeatDetail;
        //适用范围描述
        private String fitGroupDetail;
        //等级描述
        private String levelDetail;
        //开始日期
        private String startDate;
        //结束日期
        private String endDate;
        //详情列表
        private List<ListBean> list;
        //获取标题
        public String getTitle() {
            return title;
        }
        //设置标题
        public void setTitle(String title) {
            this.title = title;
        }
        //获取详情
        public String getDetail() {
            return detail;
        }
        //设置详情
        public void setDetail(String detail) {
            this.detail = detail;
        }
        //获取注意事项
        public String getNoitce() {
            return noitce;
        }
        //设置注意事项
        public void setNoitce(String noitce) {
            this.noitce = noitce;
        }
        //获取重复周期描述
        public String getRepeatDetail() {
            return repeatDetail;
        }
        //设置重复周期描述
        public void setRepeatDetail(String repeatDetail) {
            this.repeatDetail = repeatDetail;
        }
        //获取适用范围描述
        public String getFitGroupDetail() {
            return fitGroupDetail;
        }
        //设置适用范围描述
        public void setFitGroupDetail(String fitGroupDetail) {
            this.fitGroupDetail = fitGroupDetail;
        }
        //获取等级描述
        public String getLevelDetail() {
            return levelDetail;
        }
        //设置等级描述
        public void setLevelDetail(String levelDetail) {
            this.levelDetail = levelDetail;
        }
        //获取开始日期
        public String getStartDate() {
            return startDate;
        }
        //设置开始日期
        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
        //获取结束日期
        public String getEndDate() {
            return endDate;
        }
        //设置结束日期
        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
        //获取选项列表
        public List<ListBean> getList() {
            return list;
        }
        //设置选项列表
        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            //子项目名称
            private String title;
            //子选项描述
            private String detail;
            //状态
            private int status;
            //执行日期
            private String date;
            //运动强度
            private int level;
            //子项id
            private int missionId;
            //属性
            private String attrs;
            //重复
            private int repeat;
            //每组次数
            private int times;
            //总共花费时间
            private int spendTime;
            //自我感知强度
            private int feelLevel;
            /**
             * title : 俯卧撑
             * detail : 每组5个，共10组，1个小时
             * status : 1
             * date : 2015-02-13
             * level : 1
             * missionId : 100001
             * attrs :
             * repeat : 0
             * times : 10
             * spendTime : 60
             * feelLevel : 1
             */
            //获取子项目名称
            public String getTitle() {
                return title;
            }
            //设置子项目名称
            public void setTitle(String title) {
                this.title = title;
            }
            //获取子项目描述
            public String getDetail() {
                return detail;
            }
            //设置子项目描述
            public void setDetail(String detail) {
                this.detail = detail;
            }
            //获取状态
            public int getStatus() {
                return status;
            }
            //设置状态
            public void setStatus(int status) {
                this.status = status;
            }
            //获取执行时间
            public String getDate() {
                return date;
            }
            //设置执行时间
            public void setDate(String date) {
                this.date = date;
            }
            //获取运动强度
            public int getLevel() {
                return level;
            }
            //设置运动强度
            public void setLevel(int level) {
                this.level = level;
            }
            //获取子项id
            public int getMissionId() {
                return missionId;
            }
            //设置子项id
            public void setMissionId(int missionId) {
                this.missionId = missionId;
            }
            //获取属性
            public String getAttrs() {
                return attrs;
            }
            //设置属性
            public void setAttrs(String attrs) {
                this.attrs = attrs;
            }
            //获取重复
            public int getRepeat() {
                return repeat;
            }
            //设置重复
            public void setRepeat(int repeat) {
                this.repeat = repeat;
            }
            //获取每组次数
            public int getTimes() {
                return times;
            }
            //设置次数
            public void setTimes(int times) {
                this.times = times;
            }
            //获取花费时间
            public int getSpendTime() {
                return spendTime;
            }
            //设置花费时间
            public void setSpendTime(int spendTime) {
                this.spendTime = spendTime;
            }
            //获取感知强度
            public int getFeelLevel() {
                return feelLevel;
            }
            //设置自我感知强度
            public void setFeelLevel(int feelLevel) {
                this.feelLevel = feelLevel;
            }
        }
    }
}
