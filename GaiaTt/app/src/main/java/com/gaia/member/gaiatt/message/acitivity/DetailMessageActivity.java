package com.gaia.member.gaiatt.message.acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: DetailMessageActivity
 * @Package com.gaia.member.gaiatt.message.acitivity
 * @Description: 显示消息详情，此处加载H5页面
 * <p/>
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/16.
 */
public class DetailMessageActivity extends AppCompatActivity {

    @Bind(R.id.iv_back_title_bar)
    ImageView ivBackToolBar;
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;
    @Bind(R.id.wv_message_detail)
    WebView wvMessageDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);
        ButterKnife.bind(this);

        tvTitleToolBar.setText("消息详情");

        //设置WebView属性，能够执行Javascript脚本
        wvMessageDetail.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        wvMessageDetail.loadUrl("http://baidu.com");
        wvMessageDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
