package com.gaia.member.gaiatt.utils;/**
 * @Description: 获取本地存储工具
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/14 18:04
 * @version V1.0
 */

import android.text.TextUtils;

import com.gaia.member.gaiatt.GaiaCustomApplication;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;

/**
 * @ClassName: UserUtils
 *Description: 获取本地存储工具
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 18:04
 */
public class UserUtils {

    /**
     * 获取用户的uid
     *
     * @return
     */
    public static String getUserId() {

        String uid = GaiaCustomApplication.mApplication.getSharedPreferences().getString(CommonConstants.USERID, "");
        if (TextUtils.isEmpty(uid)) {
            ToastUtil.show(GaiaCustomApplication.mApplication, "用户uid出错");
            return "";
        }
        return String.valueOf(uid);
    }

    /**
     * 获取用户的token
     *
     * @return
     */
    public static String getUserToken() {
        String token = GaiaCustomApplication.mApplication.getSharedPreferences().getString(CommonConstants.USERTOKEN,
                "");
        if (TextUtils.isEmpty(token)) {
            ToastUtil.show(GaiaCustomApplication.mApplication, "用户token出错");
            return "";
        }
        return token;
    }
}
