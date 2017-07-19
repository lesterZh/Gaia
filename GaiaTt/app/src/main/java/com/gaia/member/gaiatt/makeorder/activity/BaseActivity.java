package com.gaia.member.gaiatt.makeorder.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: BaseActivity
 * @Package com.gaia.member.gaiatt.makeorder.activity
 * @Description:   基础acitvity
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/13.
 */
public class BaseActivity extends AppCompatActivity {
    protected Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
}
