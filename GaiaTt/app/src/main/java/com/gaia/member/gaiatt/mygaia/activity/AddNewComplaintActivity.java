package com.gaia.member.gaiatt.mygaia.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @Title: AddNewComplaintActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: 添加新的投诉
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/20 11:22
 * @version V1.0
 */
public class AddNewComplaintActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.tv_complaint_type)
    TextView tvComplaintType;
    @Bind(R.id.ll_type_selected)
    LinearLayout llTypeSelected;
    @Bind(R.id.cb_anonymity)
    CheckBox cbAnonymity;
    @Bind(R.id.btn_submit_complaint)
    Button btnSubmitComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_complaint);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvTitleTitleBar.setText("新增投诉");
    }

    @OnClick({R.id.ll_type_selected, R.id.btn_submit_complaint})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_type_selected:
                llTypeSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog dialog;
                        final String[] choice = {"服务", "医生", "其他"};

                        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewComplaintActivity.this);
                        builder.setTitle("选择投诉类型");
                        builder.setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvComplaintType.setText(choice[which]);
                                dialog.dismiss();
                            }
                        });
                        dialog = builder.show();
                    }
                });
                break;
            case R.id.btn_submit_complaint:
                break;
        }
    }
}
