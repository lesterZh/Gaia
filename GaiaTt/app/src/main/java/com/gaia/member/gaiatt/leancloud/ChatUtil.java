package com.gaia.member.gaiatt.leancloud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avoscloud.leanchatlib.activity.AVChatActivity;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.utils.Constants;
import com.avoscloud.leanchatlib.utils.ThirdPartUserUtils;
import com.gaia.member.androidlib.constant.CommonConstant;
import com.gaia.member.gaiatt.leancloud.base.CustomUserProvider;
import com.gaia.member.gaiatt.utils.UiUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by ZhangHaiTao on 2016/5/28.
 */
public class ChatUtil {
    static String userId = "1";//代表本机用户的leancloud实时通信ID。
    /**
     * 进入聊天界面
     * @param mContext
     * @param from 消息发送者
     * @param to 消息接收者
     */
    static public void gotoChatActivity(final Context mContext, final String from, final String to) {

        ChatManager.getInstance().openClient(mContext, from, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    Intent intent = new Intent(mContext, AVChatActivity.class);
                    intent.putExtra(Constants.MEMBER_ID, to);
                    mContext.startActivity(intent);
                } else {
                    UiUtils.showToast((Activity) mContext, e.toString());
                }
            }
        });
    }

    static public void gotoChatActivity(final Context mContext, final String to) {
        gotoChatActivity(mContext, userId, to);
    }


    /**
     * 初始化leanCloud实时通信
     * 后续需要修改AppID、AppKey为公司的leanCloud的相关配置
     */
    static public void initLeanCloud(Context context) {
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(context, CommonConstant.LEAN_CLOUD_APP_ID, CommonConstant.LEAN_CLOUD_APP_KEY);
        ChatManager.setDebugEnabled(true);// tag leanchatlib
        AVOSCloud.setDebugLogEnabled(true);  // set false when release
        initImageLoader(context);
        ChatManager.getInstance().init(context);
        ThirdPartUserUtils.setThirdPartUserProvider(new CustomUserProvider());
//        loginleanCloudChat(context, userId); //登录，和服务器建立连接
    }

    private static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
                        //.memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private static void loginleanCloudChat(final Context context, String userId) {
        ChatManager.getInstance().openClient(context, userId, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (e != null) {
                    UiUtils.showToast((Activity) context, "无法连接实时通信服务器");
                }
            }
        });
    }
}
