package com.gaia.member.gaiatt.login.bean;

import java.io.Serializable;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: GurdianInfoBean
 * @Package com.gaia.member.gaiatt.login.bea
 * @Description:  引导人信息的
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/27.
 */
public class GurdianInfoBean implements Serializable {
    String relationship;
    String gurdianName;
    String gurdianPhone;
    String gurdianAdress;

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getGurdianName() {
        return gurdianName;
    }

    public void setGurdianName(String gurdianName) {
        this.gurdianName = gurdianName;
    }

    public String getGurdianPhone() {
        return gurdianPhone;
    }

    public void setGurdianPhone(String gurdianPhone) {
        this.gurdianPhone = gurdianPhone;
    }

    public String getGurdianAdress() {
        return gurdianAdress;
    }

    public void setGurdianAdress(String gurdianAdress) {
        this.gurdianAdress = gurdianAdress;
    }
}
