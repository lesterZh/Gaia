package com.gaia.member.gaiatt.base;

import com.gaia.member.androidlib.base.BaseActivity;
import com.gaia.member.androidlib.utils.ActivityCollector;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by baiyuanwei on 16/3/15.
 *
 * 此基类处理一些与业务相关的公用逻辑
 */
public abstract class AppBaseActivity extends BaseActivity {

    //图片加载器
    public ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onDestroy() {
        //activity销毁之前,回收该页面缓存在内存中的图片
        imageLoader.clearMemoryCache();

        super.onDestroy();

        //销毁此activity后,从activity收集器中移除此activity
        ActivityCollector.removeActivity(this);
    }
}
