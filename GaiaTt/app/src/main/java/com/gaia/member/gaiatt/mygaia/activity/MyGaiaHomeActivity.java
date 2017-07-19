package com.gaia.member.gaiatt.mygaia.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Title: CouponActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 测试我的盖亚的入口activity  之后不用
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/6. 11:22
 * @version V1.0
 */
public class MyGaiaHomeActivity extends AppCompatActivity {

    @Bind(R.id.fl_content)
    FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gaia_home);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.fl_content, new MyGaiaHomeFragment());
        transaction.commit();
    }
}
