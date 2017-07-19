package com.gaia.member.gaiatt.mygaia.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Title: PayResultActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/19 12:03
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: PayResultActivity
 * Description: 显示支付结果
 * @date 2016/5/19 12:03
 */
public class PayResultActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;//标题
    @Bind(R.id.iv_result)
    ImageView ivResult;//显示图标
    @Bind(R.id.tv_result)
    TextView tvResult;//显示内容
    @Bind(R.id.btn_ok)
    Button btnOk;//确定按键

    private boolean isSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        ButterKnife.bind(this);


        //动态设置标题、内容和图片
        if (getIntent() != null) {
            tvTitleTitleBar.setText(getIntent().getStringExtra("title"));
            tvResult.setText(getIntent().getStringExtra("text"));
            isSuccess = getIntent().getBooleanExtra("isSuccess", false);
            setResultView(isSuccess);
        }

    }


    /**
     * 进入该activity的入口调用函数  传入是否支付成功的标志位
     * @param context
     * @param isSuccess
     */
    public static void actionStart(Context context, boolean isSuccess, String title, String text) {
        Intent intent = new Intent(context, PayResultActivity.class);
        intent.putExtra("isSuccess", isSuccess);
        intent.putExtra("text", text);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }


    /**
     * 设置界面图标
     * @param isSuccess
     */
    private void setResultView(boolean isSuccess) {
        if (isSuccess) {//成功
            ivResult.setImageResource(R.drawable.icon_return_seccess);
//            tvResult.setText("充值成功");
        } else {//失败
            ivResult.setImageResource(R.drawable.icon_return_faild);
//            tvResult.setText("充值失败");
        }
    }


    /**
     * 确定按键定义
     */
    @OnClick(R.id.btn_ok)
    public void onClick() {
        onBackPressed();//返回到上一个activity
        finish();//关闭自身
    }
}
