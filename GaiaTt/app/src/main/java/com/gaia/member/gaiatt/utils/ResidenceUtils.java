package com.gaia.member.gaiatt.utils;

import android.content.Context;

import com.gaia.member.gaiatt.GaiaCustomApplication;
import com.gaia.member.gaiatt.utils.plistparserutils.PListXMLHandler;
import com.gaia.member.gaiatt.utils.plistparserutils.PListXMLParser;
import com.gaia.member.gaiatt.utils.plistparserutils.PString;
import com.gaia.member.gaiatt.utils.plistparserutils.domain.Array;
import com.gaia.member.gaiatt.utils.plistparserutils.domain.Dict;
import com.gaia.member.gaiatt.utils.plistparserutils.domain.PList;
import com.gaia.member.gaiatt.utils.plistparserutils.domain.PListObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangHaohan on 2016/5/27.
 *
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/27 15:35
 */
public class ResidenceUtils {

    private static Map<String,PListObject> province;
    private static Map<String,Dict> citys=new HashMap<>();
    private static Map<String,Map> pros=new HashMap<>();
    //获取省份
    public  static ArrayList<String> getProData(){

        List<String> proList=new ArrayList<>();
        PList actualPList = ((PListXMLHandler) GaiaCustomApplication.parser.getHandler()).getPlist();
        Dict root = (Dict) actualPList.getRootElement();

        Map<String,PListObject> provinceCities = root.getConfigMap();

        pros.clear();
        for(int i=0; i<provinceCities.keySet().size();i++) {
            // 因为省份的key是从小到大排序的
            Dict provinceRoot = (Dict) provinceCities.get(String.valueOf(i));
            province = provinceRoot.getConfigMap();
            String provinceName = province.keySet().iterator().next();
            pros.put(provinceName,province);
            proList.add(provinceName);						//省份
        }
        return (ArrayList<String>) proList;
    }
    /**
     * 获取城市
     * @param proName 省份名称
     * */
    public  static ArrayList<String> getCityData( String proName){
        List<String>  cityList=new ArrayList<>();

        //通过省获取城市
        Dict cityRoot = (Dict) pros.get(proName).get(proName);

        Map<String,PListObject> cities = cityRoot.getConfigMap();
        citys.clear();
        for(int j=0;j<cities.keySet().size();j++) {
            Dict city = (Dict) cities.get(String.valueOf(j));
            String cityName = city.getConfigMap().keySet().iterator().next();
            citys.put(cityName,city);
            cityList.add(cityName)	;					//城市
        }
        return (ArrayList<String>) cityList;
    }
    /**
     * 获取城市
     * @param cityName 城市名称
     * */
    public  static ArrayList<String> getAreaData( String cityName){
        List<String>  areaList=new ArrayList<>();
        Array districts = citys.get(cityName).getConfigurationArray(cityName);
        for(int k=0;k<districts.size();k++) {
            PString district = (PString) districts.get(k);
            areaList.add(district.getValue());// 地区
        }
        return (ArrayList<String>) areaList;
    }
}
