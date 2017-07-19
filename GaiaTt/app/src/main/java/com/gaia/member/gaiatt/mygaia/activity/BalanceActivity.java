package com.gaia.member.gaiatt.mygaia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Title: BalanceActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 充值   入口为：余额
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/6. 11:22
 * @version V1.0
 */
public class BalanceActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;
    @Bind(R.id.et_input_money)
    EditText etInputMoney;
    @Bind(R.id.iv_ali_selected)
    ImageView ivAiliSelected;
    @Bind(R.id.ll_ali_pay)
    LinearLayout llAiliPay;
    @Bind(R.id.iv_weixin_selected)
    ImageView ivWeixinSelected;
    @Bind(R.id.ll_weixin_pay)
    LinearLayout llWeixinPay;
    @Bind(R.id.btn_confirm_pay)
    Button btnConfirmPay;

    private boolean isAliPayMethod = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);

        tvTitleToolBar.setText("充值");
        etInputMoney.setHint("10");
    }

    @OnClick({R.id.ll_ali_pay, R.id.ll_weixin_pay, R.id.btn_confirm_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_ali_pay://选择支付宝支付
                isAliPayMethod = true;
                changePayMethodSelectedState(isAliPayMethod);
                break;

            case R.id.ll_weixin_pay://选择微信支付
                isAliPayMethod = false;
                changePayMethodSelectedState(isAliPayMethod);

                break;
            case R.id.btn_confirm_pay://确认支付

                PayResultActivity.actionStart(mContext, true, "充值结果", "充值成功");
                break;
        }
    }

    /**
     * 设置支付方式
     * @param isAliPay
     */
    private void changePayMethodSelectedState(boolean isAliPay) {
        if (isAliPay) {
            ivAiliSelected.setVisibility(View.VISIBLE);
            ivWeixinSelected.setVisibility(View.INVISIBLE);
        } else {
            ivAiliSelected.setVisibility(View.INVISIBLE);
            ivWeixinSelected.setVisibility(View.VISIBLE);
        }
    }
}
