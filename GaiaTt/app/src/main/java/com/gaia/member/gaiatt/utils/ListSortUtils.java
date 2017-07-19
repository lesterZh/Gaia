/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: ListSortUtils
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 干预计划排序工具
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.utils;

import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanBean;

import java.util.List;

/**
 * @ClassName: ListSortUtils
 *Description: 干预计划排序工具
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class ListSortUtils {

    //数据源
    private static List<HealthPlanBean> planBeans;

    /**
     * 设置排序数据
     * @param  planBeanslist 数据源
     * */
    public static  List<HealthPlanBean> SetPlanListSort( List<HealthPlanBean> planBeanslist){
        planBeans=planBeanslist;
        //开始排序
        new Thread(new Runnable() {
            @Override
            public void run() {
                quick(planBeans);
            }
        }).start();
      return planBeanslist;
    }

    /**
     * 快速排序
     * @param  planBeanslist 数据源
     * */
    private static void quick(List<HealthPlanBean> planBeanslist) {
        if (planBeanslist.size() > 0) {    //查看列表是否为空

            _quickSort(planBeanslist);

        }

    }
    /**
     * 冒泡实现排序
     * @param  list 列表
     * */
    private static void _quickSort(List<HealthPlanBean> list) {
        HealthPlanBean temp=new HealthPlanBean();
        for (int i = 0; i <list.size()-1 ; i++) {
            for (int j= 0; j <list.size()-i-1 ; j++) {
                //比较大小
                if (isLessThan(list.get(j).getDate(),list.get(j+1).getDate())) {
                    //替换位置
                    replaceListItem(temp,list.get(j));
                    replaceListItem(list.get(j),list.get(j+1));
                    replaceListItem(list.get(j + 1),temp);
                }
            }
        }


    }

    /**
     * 替换位置实现方法
     * @param  healthPlanBean 计划对象
     * @param  healthPlanBean1 计划对象
     * */
    private static void replaceListItem(HealthPlanBean healthPlanBean, HealthPlanBean healthPlanBean1) {
        healthPlanBean.setTitle(healthPlanBean1.getTitle());
        healthPlanBean.setCategory(healthPlanBean1.getCategory());
        healthPlanBean.setDate(healthPlanBean1.getDate());
        healthPlanBean.setDetail(healthPlanBean1.getDetail());
        healthPlanBean.setId(healthPlanBean1.getId());
        healthPlanBean.setStatus(healthPlanBean1.getStatus());
    }

    /**
     * 比较大小
     * @param  dateOne 通过两个日期比较
     * @param  dateTwo
     * */
    private static boolean isLessThan(String dateOne,String dateTwo){
        int dateIntOne=getIntegetDate(dateOne);
        int dateIntTwo=getIntegetDate(dateTwo);
        if (dateIntOne <dateIntTwo) {
            return true;
        }
        return false;
    }

    /**
     * String转化为int
     * */
    private static int getIntegetDate(String dateOne) {
        String dateString=dateOne.replace("-","");
        int date=Integer.parseInt(dateString);
        return date;
    }

}
