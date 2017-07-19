package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.login.bean.GurdianInfoBean;
import com.gaia.member.gaiatt.login.bean.RegistInfoBean;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: RegistDetailActivity
 * @Package com.gaia.member.gaiatt.login.activity
 * @Description:  注册详情信息填写界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/4/25.
 */
public class RegistDetailActivity extends AppCompatActivity {

    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.et_company)
    EditText etCompany;
    @Bind(R.id.et_contact_name)
    EditText etContactName;
    @Bind(R.id.et_contact_phone)
    EditText etContactPhone;
    @Bind(R.id.tv_marriage_state_hint)
    TextView tvMarriageStateHint;
    @Bind(R.id.tv_marriage_state)
    TextView tvMarriageState;
    @Bind(R.id.ll_marriage_state)
    LinearLayout llMarriageState;
    @Bind(R.id.tv_profession_hint)
    TextView tvProfessionHint;
    @Bind(R.id.tv_profession)
    TextView tvProfession;
    @Bind(R.id.ll_profession)
    LinearLayout llProfession;
    @Bind(R.id.tv_gurdian_info_hint)
    TextView tvGurdianInfoHint;
    @Bind(R.id.tv_gurdian_info)
    TextView tvGurdianInfo;
    @Bind(R.id.ll_gurdian_info)
    LinearLayout llGurdianInfo;
    @Bind(R.id.tv_wear_device_hint)
    TextView tvWearDeviceHint;
    @Bind(R.id.tv_wear_device)
    TextView tvWearDevice;
    @Bind(R.id.ll_wear_device)
    LinearLayout llWearDevice;
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleToolBar;


    private RegistInfoBean registInfo = new RegistInfoBean();
    private GurdianInfoBean gurdianInfo = new GurdianInfoBean();

    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_regist_detail);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvTitleToolBar.setText("基本资料填写");
        llMarriageState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                final String[] choice = {"已婚", "未婚"};

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(tvMarriageStateHint.getText().toString());
                builder.setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvMarriageState.setText(choice[which]);
                        dialog.dismiss();
                    }
                });
                dialog = builder.show();
            }
        });

        llProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                final String[] choice = {"银行", "服务行业", "工程师", "计算机软件开发"};

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(tvProfessionHint.getText().toString());
                builder.setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvProfession.setText(choice[which]);
                        dialog.dismiss();
                    }
                });
                dialog = builder.show();
            }
        });

        llWearDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                final String[] choice = {"心跳记录仪", "智能手环", "血压测量仪"};

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(tvWearDeviceHint.getText().toString());
                builder.setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvWearDevice.setText(choice[which]);
                        dialog.dismiss();
                    }
                });
                dialog = builder.show();
            }
        });

        llGurdianInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GuidianInfoSetActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("保存");
                builder.setMessage("尊敬的客户，您修改的信息在健康管理师核实之后有效。");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDataFromUi();//获取用户输入，保存在类 RegistInfo
                        sendRegistInfoToServer();

                        //进入第一次引导界面，设置性别 身高
                        ActivityUtils.gotoIntent(mContext, FirstLoginGuide1Activity.class);
                    }
                });
                builder.show();
            }
        });

    }

    /**
     * 将获取的注册信息发送给服务器
     */
    private void sendRegistInfoToServer() {
    }

    /**
     * 将用户输入信息保存在类 RegistInfo
     */
    private void getDataFromUi() {
        registInfo.setAddress(etAddress.getText().toString().trim());
        registInfo.setCompany(etCompany.getText().toString().trim());
        registInfo.setContactName(etContactName.getText().toString().trim());
        registInfo.setContactPhone(etContactPhone.getText().toString().trim());
        registInfo.setMarriageState(tvMarriageState.getText().toString().trim());
        registInfo.setProfession(tvProfession.getText().toString().trim());
        registInfo.setWearDeviceStr(tvWearDevice.getText().toString().trim());

        //监护人信息
        registInfo.setGurdianInfo(gurdianInfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == 0) {
            if (data != null) {
                gurdianInfo = (GurdianInfoBean) data.getSerializableExtra("gurdianInfo");
            }

//            LogUtil.w(gurdianInfo.getRelationship());
//            LogUtil.w(gurdianInfo.getGurdianName());
//            LogUtil.w(gurdianInfo.getGurdianPhone());
//            LogUtil.w(gurdianInfo.getGurdianAdress());
        }
    }
}
