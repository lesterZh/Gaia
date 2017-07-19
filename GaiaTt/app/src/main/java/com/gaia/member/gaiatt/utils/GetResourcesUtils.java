package com.gaia.member.gaiatt.utils;

import android.content.Context;

/**
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/31 9:47
 */
public class GetResourcesUtils {

    /**
     * 获取资源配置文件中的字符数组
     * @param context
     * @param id
     * */
    public static String[] getStringArrary(Context context,int id){
        String[] resources=context.getResources().getStringArray(id);
        return  resources;
    }
    /**
     * 获取资源配置文件中的字符
     * @param context
     * @param id
     * */
    public static String getString(Context context,int id){
        String resources=context.getResources().getString(id);
        return  resources;
    }
}
