package com.gaia.member.gaiatt.mygaia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: ShowVipInfoActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 显示Vip用户信息
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/7. 11:22
 * @version V1.0
 */
public class ShowVipInfoActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_vip_info);
        ButterKnife.bind(this);

        tvTitleTitleBar.setText("我的特权");
    }
}
