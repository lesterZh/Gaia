package com.gaia.member.gaiatt.utils;/**
 * @Description: 图片加载工具
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/22 16:13
 * @version V1.0
 */

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * @ClassName: ImageLoaderUtils
 *Description: 图片加载工具
 *@author android移动客户端-王浩韩
 * @date 2016/6/22 16:13
 */
public class ImageLoaderUtils {

    //图片加载工具
    private  static ImageLoader imageLoader;

    /**
     * 设置图片
     * @param imageView 控件
     * @param  urlStr Url
     * */
    public static  void loadImageView(ImageView imageView,String urlStr){
        if (imageLoader == null) {
            imageLoader = ImageLoader.getInstance();
        }
        DisplayImageOptions options=setDisplayImageOptions();

        ImageLoader.getInstance().displayImage("域名"+urlStr, imageView,options);
        // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件 ， options代表DisplayImageOptions配置文件
    }

    /**
     *进行显示的图片的各种格式DisplayImageOptions 的设置
     * 如果经常出现OOM:
     * ①减少配置之中线程池的大小，(.threadPoolSize).推荐1-5；
     * ②使用.bitmapConfig(Bitmap.config.RGB_565)代替ARGB_8888;
     * ③使用.imageScaleType(ImageScaleType.IN_SAMPLE_INT)或者 try.imageScaleType(ImageScaleType.EXACTLY)；
     * ④避免使用RoundedBitmapDisplayer.他会创建新的ARGB_8888格式的Bitmap对象；
     * ⑤使用.memoryCache(new WeakMemoryCache())，不要使用.cacheInMemory();
     * */
    private static DisplayImageOptions setDisplayImageOptions() {
        DisplayImageOptions options = null;
        if (options == null) {
            options = new DisplayImageOptions.Builder()
                    //.showImageOnLoading(R.drawable.ic_launcher) //设置图片在下载期间显示的图片
                    //.showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                    // .showImageOnFail(R.drawable.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                            /**
                             * imageScaleType(ImageScaleType imageScaleType)  是设置 图片的缩放方式
                             *  缩放类型mageScaleType:
                             * EXACTLY :图像将完全按比例缩小的目标大小
                             * EXACTLY_STRETCHED:图片会缩放到目标大小完全
                             * IN_SAMPLE_INT:图像将被二次采样的整数倍
                             * IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
                             * NONE:图片不会调整
                             * */
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                            //.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
                            //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                            //设置图片加入缓存前，对bitmap进行设置
                            //.preProcessor(BitmapProcessor preProcessor)
                    .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                    //.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                   // .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                            /**
                             *displayer(BitmapDisplayer displayer)   是设置 图片的显示方式
                             * 显示方式displayer：
                             * RoundedBitmapDisplayer（int roundPixels）设置圆角图片
                             * FakeBitmapDisplayer（）这个类什么都没做
                             * FadeInBitmapDisplayer（int durationMillis）设置图片渐显的时间
                             * SimpleBitmapDisplayer()正常显示一张图片
                             * */
                    .build();//构建完成
        }
        return options;
    }
}
