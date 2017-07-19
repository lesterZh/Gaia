package com.gaia.member.gaiatt.utils;

import android.content.Context;

import com.gaia.member.androidlib.utils.LogUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by WangHaohan on 2016/5/5.
 */
public class AssetsFileUtil {

    public static String getJsonStr(Context context,String assetsFile) {
        String str = "";
        InputStream in = null;
        try {
            in = context.getAssets().open(assetsFile);
            byte[] buffer = new byte[in.available()];
            int num = in.read(buffer);
            str = new String(buffer, 0, num);
            return str;
        } catch (Exception e) {
            LogUtil.d("wz", "获取字符串过程出现异常:" + e.toString());
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
