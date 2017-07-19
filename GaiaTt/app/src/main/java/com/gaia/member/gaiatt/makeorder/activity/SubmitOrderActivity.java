package com.gaia.member.gaiatt.makeorder.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.ui.CustomPicker;
import com.gaia.member.gaiatt.makeorder.bean.OrderDoctorInfoBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: SubmitOrderActivity
 * @Package com.gaia.member.gaiatt.makeorder.activity
 * @Description:  预约医生、服务的订单信息
 *
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/16.
 */

public class SubmitOrderActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.tv_order_service)
    TextView tvOrderService;
    @Bind(R.id.tv_order_price)
    TextView tvOrderPrice;
    @Bind(R.id.tv_order_address)
    TextView tvOrderAddress;
    @Bind(R.id.tv_order_tel)
    TextView tvOrderTel;
    @Bind(R.id.et_order_date)
    EditText etOrderDate;
    @Bind(R.id.et_order_time)
    EditText etOrderTime;
    @Bind(R.id.et_order_user_name)
    EditText etOrderUserName;
    @Bind(R.id.et_order_user_phone)
    EditText etOrderUserPhone;
    @Bind(R.id.et_order_recommand_name)
    EditText etOrderRecommandName;
    @Bind(R.id.et_order_recommand_code)
    EditText etOrderRecommandCode;
    @Bind(R.id.btn_pay_fee)
    Button btnPayFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        etOrderDate.setText(intent.getStringExtra("orderDate"));

        tvTitleTitleBar.setText("提交预约");


        final CustomPicker timePicker = new CustomPicker(mContext);
        timePicker.setTitle("选择时间");
        timePicker.setSubTitles("小时", "分钟");
        timePicker.setWheelDataIndex(1, timePicker.getHourList(), 11);
        timePicker.setWheelDataIndex(2, timePicker.getMinuteList(), 29);
        timePicker.setSaveButtonClickListener(new CustomPicker.OnSaveButtonClickListener() {
            @Override
            public void okClick(String text1, String text2) {
                etOrderTime.setText(text1+":"+text2);
            }
        });
        etOrderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show(true);
            }
        });

    }

    public static void actionStart(Context context,  String date, OrderDoctorInfoBean infoBean) {
        Intent intent = new Intent(context, SubmitOrderActivity.class);
        intent.putExtra("orderDate" , date);
        intent.putExtra("orderInfo" , infoBean);
        context.startActivity(intent);
    }


    @OnClick(R.id.btn_pay_fee)
    public void btnPayFee() {

    }
}
