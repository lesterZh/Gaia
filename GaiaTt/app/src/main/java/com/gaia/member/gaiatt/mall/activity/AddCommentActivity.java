package com.gaia.member.gaiatt.mall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaia.com.componentlibrary.custom.CustomCommentStar;
/**
 * @Title: AddCommentActivity
 * @Package com.gaia.member.gaiatt.mall
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/6/16 15:03
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: AddCommentActivity
 * Description: 添加评论
 * @date 2016/6/16 15:03
 */
public class AddCommentActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView mTvTitleTitleBar;
    @Bind(R.id.rating_bar)
    CustomCommentStar mRatingBar;
    @Bind(R.id.et_comment)
    EditText mEtComment;
    @Bind(R.id.cb_anonymity)
    CheckBox mCbAnonymity;
    @Bind(R.id.btn_ok)
    Button mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        ButterKnife.bind(this);

        mTvTitleTitleBar.setText("评价");
    }

    @OnClick(R.id.btn_ok)
    public void onClick() {
    }
}
