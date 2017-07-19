package com.gaia.member.gaiatt.mygaia.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.tv_service)
    TextView tvService;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    @Bind(R.id.tv_order_address)
    TextView tvOrderAddress;
    @Bind(R.id.tv_contact_phone)
    TextView tvContactPhone;
    @Bind(R.id.tv_order_date)
    TextView tvOrderDate;
    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;
    @Bind(R.id.tv_order_name)
    TextView tvOrderName;
    @Bind(R.id.tv_order_phone)
    TextView tvOrderPhone;
    @Bind(R.id.tv_recommend_name)
    TextView tvRecommendName;
    @Bind(R.id.tv_recommend_code)
    TextView tvRecommendCode;
    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        tvTitleTitleBar.setText("预约详情");
        initView();
    }

    /**
     * 初始化视图
     * */
    private void initView() {
        tvService.setText("挂号服务");
        tvPay.setText("112元");
        tvOrderAddress.setText("天府大道中段186号");
        tvContactPhone.setText("028-07777777");
        tvOrderDate.setText("2016-06-13");
        tvOrderTime.setText("8:00-10:00");
        tvOrderName.setText("赵日天");
        tvOrderPhone.setText("028-12865942582");
        tvRecommendName.setText("叶良辰");
        tvRecommendCode.setText("141412");
    }

    //返回
    @OnClick(R.id.iv_back_title_bar)
    public void onClick() {
        onBackPressed();
    }
}
