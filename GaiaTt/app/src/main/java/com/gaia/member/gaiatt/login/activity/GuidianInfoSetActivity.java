package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.login.bean.GurdianInfoBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: GuidianInfoSetActivity
 * @Package com.gaia.member.gaiatt.login.activity
 * @Description: 监护人信息设置界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/25
 */
public class GuidianInfoSetActivity extends AppCompatActivity {

    @Bind(R.id.tv_relationship_hint)
    TextView tvRelationshipHint;
    @Bind(R.id.tv_relationship)
    TextView tvRelationship;
    @Bind(R.id.ll_relationship)
    LinearLayout llRelationship;
    @Bind(R.id.et_gurdian_name)
    EditText etGurdianName;
    @Bind(R.id.et_gurdian_phone)
    EditText etGurdianPhone;
    @Bind(R.id.et_gurdian_address)
    EditText etGurdianAddress;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    private Activity mContext;
    private GurdianInfoBean gurdianInfo = new GurdianInfoBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_guidian_info_set);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        llRelationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                final String[] choice = {"父亲", "母亲", "姑姑"};

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(tvRelationshipHint.getText().toString());
                builder.setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvRelationship.setText(choice[which]);
                        dialog.dismiss();
                    }
                });
                dialog = builder.show();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromUi();
                Intent intent = getIntent();
                intent.putExtra("gurdianInfo", gurdianInfo);
                setResult(0, intent);
                onBackPressed();
            }
        });
    }

    private void getDataFromUi() {
        gurdianInfo.setRelationship(tvRelationship.getText().toString().trim());
        gurdianInfo.setGurdianName(etGurdianName.getText().toString().trim());
        gurdianInfo.setGurdianPhone(etGurdianPhone.getText().toString().trim());
        gurdianInfo.setGurdianAdress(etGurdianAddress.getText().toString().trim());
    }

}
