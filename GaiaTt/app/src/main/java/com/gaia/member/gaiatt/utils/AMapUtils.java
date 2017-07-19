/**
 * @Description: 获取定位信息工具
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/15 10:04
 * @version V1.0
 */
package com.gaia.member.gaiatt.utils;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.gaia.member.gaiatt.GaiaCustomApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: AMapUtils
 *Description: 获取定位信息工具
 *@author android移动客户端-王浩韩
 * @date 2016/6/15 10:04
 */
public class AMapUtils {


    //声明mLocationOption对象
    private static AMapLocationClientOption mLocationOption = null;

    /**设置定位监听
     * @param mLocationClient
     * @param ls
     * */
    public static void setOnAMapLocationListener(AMapLocationClient mLocationClient,OnAMapLocationListener ls){
        AMapUtils.ls=ls;
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
    }



    //声明定位回调监听器
    private static AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());
                    df.format(date);//定位时间
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    String strProvince = amapLocation.getProvince();//省信息
                    String strCity = amapLocation.getCity();//城市信息
                    String strDistrict = amapLocation.getDistrict();//城区信息
                    String strStreet = amapLocation.getStreet();//街道信息
                    String strStreetNum = amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    String strAoiName = amapLocation.getAoiName();//获取当前定位点的AOI信息
                    ls.onLocationChanged(amapLocation);
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    /**
     * 设置定位数据、打开定位
     * */
    public static void setLocation() {
        //初始化定位参数
        if (mLocationOption == null) {
            mLocationOption = new AMapLocationClientOption();
        }
        //设置定位模式Hight_Accuracy为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        GaiaCustomApplication.mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        GaiaCustomApplication.mLocationClient.startLocation();
    }
    private static OnAMapLocationListener ls;
    /**
     * 定位监听接口
     * */
    public  interface OnAMapLocationListener{
        void onLocationChanged(AMapLocation amapLocation);
    }
}
