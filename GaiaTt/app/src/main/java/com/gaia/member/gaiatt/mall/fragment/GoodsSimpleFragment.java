package com.gaia.member.gaiatt.mall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端开发组-zhangHaiTao
 * @version V1.0
 * @Title: GoodsSimpleFragment
 * @Package com.gaia.member.gaiatt.mall
 * @Description: 商品概览，位于商城首页点击单个商品后的跳转的activity的一个fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/19 12:03
 */
public class GoodsSimpleFragment extends Fragment {
    @Bind(R.id.wv_content)
    WebView wvContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mall_goods_simple, container, false);
        ButterKnife.bind(this, view);

        wvContent.getSettings().setJavaScriptEnabled(false);
        wvContent.getSettings().setSupportZoom(true);
        wvContent.loadUrl("http://www.baidu.com/");
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
