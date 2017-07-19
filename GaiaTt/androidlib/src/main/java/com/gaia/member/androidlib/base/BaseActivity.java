package com.gaia.member.androidlib.base;

import android.app.Activity;
import android.os.Bundle;

import com.gaia.member.androidlib.utils.ActivityCollector;

/**
 * Created by baiyuanwei on 16/3/15.
 *
 * 此基类不得与界面交互,只处理与业务无关的公用逻辑
 *
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);

        initVariables();
        initView(savedInstanceState);
        loadData();
    }

    /**
     * 处理从上一个activity传过来的数据
     */
    protected abstract void initVariables();

    /**
     * 控件的初始化
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 加载数据,添加到控件
     */
    protected abstract void loadData();
}
