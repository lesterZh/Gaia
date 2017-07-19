package com.gaia.member.androidlib;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDexApplication;

import com.gaia.member.androidlib.constant.CacheConstant;
import com.gaia.member.androidlib.constant.DbConstant;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by baiyuanwei on 16/3/14.
 *
 * 配置数据库相关信息,如数据库的名称
 * 配置SharedPreferences
 * 配置图片加载器ImageLoader
 *
 */
public class CustomApplication extends MultiDexApplication {

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqLiteDatabase;
    private SharedPreferences sharedPreferences;
    public static CustomApplication mApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        setupDataBase();

        setupSharedPreferences();

        configImageLoader();

    }

    public static CustomApplication getAppContext() {
        return mApplication;
    }

    /**
     * 初始化数据库相关变量
     */
    private void setupDataBase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DbConstant.DB_NAME, null);
        sqLiteDatabase = helper.getWritableDatabase();
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
    }

    /**
     * 给外部提供获得DaoSession的方法
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 初始化SharedPreferences
     */
    private void setupSharedPreferences() {

        //MODE_PRIVATE 表示只有当前的应用程序才可以对这个SharedPreferences文件进行读写
        sharedPreferences = getSharedPreferences(DbConstant.SP_NAME, MODE_PRIVATE);

    }

    /**
     * 给外部提供获得SharedPreferences的方法
     *
     * @return
     */
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    /**
     * 配置ImageLoader
     */
    private void configImageLoader() {

        //自定义缓存文件
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), CacheConstant.CACHE_PATH);

        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(
                        getApplicationContext())
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .memoryCacheExtraOptions(CacheConstant.CACHE_IMAGE_MAX_WIDTH, CacheConstant.CACHE_IMAGE_MAX_HEIGHT)
                        .memoryCacheSize(CacheConstant.CACHE_MEMORY_SIZE)
                        .diskCacheSize(CacheConstant.DISK_CACHE_SIZE)

                        //自定义缓存路径
                        .diskCache(new UnlimitedDiskCache(cacheDir))

                        .denyCacheImageMultipleSizesInMemory()
                        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .memoryCache(new WeakMemoryCache()).build();

        //全局初始化此配置
        ImageLoader.getInstance().init(config);
    }




}
