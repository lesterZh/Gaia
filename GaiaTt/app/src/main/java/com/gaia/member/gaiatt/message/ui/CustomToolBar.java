package com.gaia.member.gaiatt.message.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.gaia.member.androidlib.utils.LogUtil;
/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: CustomToolBar
 * @Package com.gaia.member.gaiatt.message.acitivity
 * @Description:  自定义标题栏，供全局使
 *
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/16.
 */
public class CustomToolBar extends RelativeLayout {
    Context mContext;
    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //返回图标的功能设定
        getChildAt(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof Activity) {
                    ((Activity)mContext).onBackPressed();
                } else {
                    LogUtil.w("zht", "not activity");
                }
            }
        });
    }
}
