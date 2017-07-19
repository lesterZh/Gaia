package com.gaia.member.gaiatt.message.acitivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: MessageHomeActivity
 * @Package com.gaia.member.gaiatt.message.acitivity
 * @Description:  用来测试加载fragment 可删除
 *
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/16.
 */
public class MessageHomeActivity extends AppCompatActivity {

    @Bind(R.id.fl_content)
    FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_home);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.fl_content, new MessageHomeFragement());
        transaction.commit();
    }
}
