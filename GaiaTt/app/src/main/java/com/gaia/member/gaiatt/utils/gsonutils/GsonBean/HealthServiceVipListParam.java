package com.gaia.member.gaiatt.utils.gsonutils.GsonBean;

import java.util.List;

/**
 * Created by WangHaohan on 2016/5/17.
 */
public class HealthServiceVipListParam {
    private List<HealthServiceBean> vip1List;
    private List<HealthServiceBean> vip2List;

    public List<HealthServiceBean> getVip1List() {
        return vip1List;
    }

    public void setVip1List(List<HealthServiceBean> vip1List) {
        this.vip1List = vip1List;
    }

    public List<HealthServiceBean> getVip2List() {
        return vip2List;
    }

    public void setVip2List(List<HealthServiceBean> vip2List) {
        this.vip2List = vip2List;
    }
}
