package com.gaia.member.androidlib.constant;

/**
 * Created by baiyuanwei on 16/3/15.
 * 有关缓存的一些常量
 */
public class CacheConstant {

    //图片在sd卡中的缓存路径
    public final static String CACHE_PATH = "imageloader/Cache";

    //ImageLoader图片缓存的大小
    public final static int CACHE_MEMORY_SIZE = 2 * 1024 * 1024;

    //sd的图片缓存大小
    public final static int DISK_CACHE_SIZE = 50 * 1024 * 1024;

    //缓存图片的最大宽度
    public final static int CACHE_IMAGE_MAX_WIDTH = 480;

    //缓存图片的最大高度
    public final static int CACHE_IMAGE_MAX_HEIGHT = 480;
}
