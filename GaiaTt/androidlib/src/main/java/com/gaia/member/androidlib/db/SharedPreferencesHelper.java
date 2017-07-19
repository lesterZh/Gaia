package com.gaia.member.androidlib.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.gaia.member.androidlib.CustomApplication;
import com.gaia.member.androidlib.constant.CommonConstant;

/**
 * Created by baiyuanwei on 16/3/15.
 */
public class SharedPreferencesHelper {

    private static SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static SharedPreferencesHelper getSharedPreHelperInstance(Context context){
        if (sharedPreferencesHelper == null){
            sharedPreferencesHelper = new SharedPreferencesHelper();

            sharedPreferencesHelper.sharedPreferences = ((CustomApplication)context.getApplicationContext()).getSharedPreferences();
            sharedPreferencesHelper.editor = sharedPreferencesHelper.sharedPreferences.edit();
        }

        return sharedPreferencesHelper;
    }
    /**
     * 保存String型的数据
     * @param key
     * @param value
     */
    public void saveString(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * 保存Int型的数据
     * @param key
     * @param value
     */
    public void saveInt(String key,int value){
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 保存Float型的数据
     * @param key
     * @param value
     */
    public void saveFloat(String key,float value){
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 保存Long型的数据
     * @param key
     * @param value
     */
    public void saveLong(String key,Long value){
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 保存Boolean型的数据
     * @param key
     * @param value
     */
    public void saveBoolean(String key,Boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 从SharedPreferences中获得数据
     * @param key
     * @return
     */
    public String getString(String key){
       return sharedPreferences.getString(key, CommonConstant.DEFAULT_STR);
    }

    public int getInt(String key){
        return sharedPreferences.getInt(key, CommonConstant.DEFAULT_INT);
    }

    public float getFloat(String key){
        return sharedPreferences.getFloat(key, CommonConstant.DEFAULT_FLOAT);
    }

    public long getLong(String key){
        return sharedPreferences.getLong(key, CommonConstant.DEFAULT_LONG);
    }

    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key, CommonConstant.DEFAULT_BOOLEAN);
    }
}
