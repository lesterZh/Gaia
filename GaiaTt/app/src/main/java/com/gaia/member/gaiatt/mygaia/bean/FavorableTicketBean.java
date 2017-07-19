package com.gaia.member.gaiatt.mygaia.bean;

/**
 * Created by zhangHaiTao on 2016/5/6.
 */
public class FavorableTicketBean {
    String name;
    long deadlineTime;
    String discount;
    String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(long deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
