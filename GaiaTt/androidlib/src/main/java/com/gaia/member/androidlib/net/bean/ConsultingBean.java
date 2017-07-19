/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 016/5/1614:46
 * @version V1.0
 */
package com.gaia.member.androidlib.net.bean;

/**
 * @ClassName: ConsultingBean
 *Description: TODO
 *@author android移动客户端-王浩韩
 * @date 016/5/169:56
 */
public class ConsultingBean {
    private int id;
    private String headpic;//头像
    private int status;//状态
    private int demand;//需求
    private String name;//姓名
    private String level;//级别
    private String skilful;//擅长
    private String belongs;//所属医院

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSkilful() {
        return skilful;
    }

    public void setSkilful(String skilful) {
        this.skilful = skilful;
    }

    public String getBelongs() {
        return belongs;
    }

    public void setBelongs(String belongs) {
        this.belongs = belongs;
    }
}
