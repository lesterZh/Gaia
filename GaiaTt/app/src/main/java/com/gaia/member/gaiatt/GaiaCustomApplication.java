package com.gaia.member.gaiatt;

import com.amap.api.location.AMapLocationClient;
import com.gaia.member.androidlib.CustomApplication;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.plistparserutils.PListXMLHandler;
import com.gaia.member.gaiatt.utils.plistparserutils.PListXMLParser;
import com.gaia.member.gaiatt.utils.SharedPreferencesUtil;

import java.io.IOException;

/**
 * Created by WangHaohan on 2016/5/30.
 *
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/30 12:50
 */
public class GaiaCustomApplication extends CustomApplication{


    //解析plist
    public static PListXMLParser parser;
    private  PListXMLHandler handler;
    //声明AMapLocationClient类对象
    public static AMapLocationClient mLocationClient = null;
    @Override
    public void onCreate() {
        super.onCreate();
        setupArea();
        initPreference();
        setLocationOption();//设置定位
    }
    /**
     * 设置定位
     * */
    private void setLocationOption() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());

    }
    /**
     * 地区三级联动数据解析
     * */
    private void setupArea() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                parser = new PListXMLParser(); // 基于SAX的实现
                handler = new PListXMLHandler();
                parser.setHandler(handler);
                try {
                    parser.parse(getAssets().open("area.plist"));// area.plist是你要解析的文件，该文件需放在assets文件夹下
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void  initPreference() {
        SharedPreferencesUtil.putInteger(this.getApplicationContext(), CommonConstants.shoppingCartGoodsNum, 0);
    }

}
