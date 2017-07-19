package com.gaia.member.gaiatt.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gaia.member.gaiatt.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.InputStream;

/**
 * Created by baiyuanwei on 16/3/15.
 */
public class ImageUtils {

    /**
     * 获取ImageLoader的配置参数
     *
     * @return
     */
    public static DisplayImageOptions getDisplayImageOptions() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.test1) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.test2)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.test3) //下载失败后显示此图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成

        return options;
    }


    /**
     * 将drawable下的图片转换成bitmap
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap resIdToBitmap(Context context, int resId) {

        BitmapFactory.Options opt = new BitmapFactory.Options();

        //最低位的配置
        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        //drawable下的图片构造流的方法
        InputStream is = context.getResources().openRawResource(resId);

        return BitmapFactory.decodeStream(is, null, opt);
    }
}
