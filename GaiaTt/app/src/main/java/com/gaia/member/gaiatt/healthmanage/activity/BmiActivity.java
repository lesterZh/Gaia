package com.gaia.member.gaiatt.healthmanage.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.fragment.ShareFragment;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: BmiActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 健康工具5活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class BmiActivity extends AppBaseActivity {


    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    @Bind(R.id.titlebar_search_ll)
    LinearLayout titlebarSearchLl;
    @Bind(R.id.desp_dialog_layout)
    LinearLayout despDialogLayout;
    @Bind(R.id.wv_bmi)
    WebView wvBmi;
    private ShareFragment shareFragment;//健康工具分享Fragment

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_bmi);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        titlebarTv.setText(GetResourcesUtils.getString(this,R.string.bmi_title));
        titlebarSearchLl.setVisibility(View.GONE);

    }

    @Override
    protected void loadData() {

        //设置WebView属性，能够执行Javascript脚本
        wvBmi.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        wvBmi.loadUrl("http://baidu.com");
        wvBmi.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    //返回
    @OnClick(R.id.titlebar_back_ll)
    void bmiBack() {
        onBackPressed();
    }

    //分享
    @OnClick(R.id.tv_bmi_share)
    void shareBtn() {
        if (shareFragment == null) {
            shareFragment = new ShareFragment();
        }
        despDialogLayout.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.bottom_in, 0);
        transaction.replace(R.id.share_dialog_layout, shareFragment);
        transaction.commit();
    }


    //取消分享
    @OnClick(R.id.dialog_button)
    void cancelFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction translaction = manager.beginTransaction();
        translaction.remove(shareFragment);
        translaction.commit();
        despDialogLayout.setVisibility(View.GONE);
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvBmi.canGoBack()) {
            wvBmi.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }else {
            onBackPressed();
        }
        return false;
    }
}
