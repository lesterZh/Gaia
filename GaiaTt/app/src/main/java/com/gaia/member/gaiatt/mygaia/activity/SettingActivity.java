package com.gaia.member.gaiatt.mygaia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.login.activity.RegistDetailActivity;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * @Title: SettingActivity
 * @Package com.gaia.member.gaiatt.makeorder.activity
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/18 18:44
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: SettingActivity
 * Description: 我的盖亚——设置
 * @date 2016/5/18 18:44
 */
public class SettingActivity extends BaseActivity {


    @Bind(R.id.iv_back_title_bar)
    ImageView ivBackTitleBar;
    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @Bind(R.id.iv_user_photo)
    ImageView ivUserPhoto;
    @Bind(R.id.rl_user_photo)
    RelativeLayout rlUserPhoto;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.et_phone)
    TextView etPhone;
    @Bind(R.id.ll_select_base)
    LinearLayout llSelectBase;
    @Bind(R.id.btn_cancel)
    Button btnCancel;
    @Bind(R.id.btn_camara)
    Button btnCamara;
    @Bind(R.id.btn_select_from_photo_album)
    Button btnSelectFromPhotoAlbum;
    @Bind(R.id.rl_set_photo)
    RelativeLayout rlSetPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvTitleTitleBar.setText("设置");

        //设定点击头像事件
        /*ivUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlSetPhoto.setVisibility(View.VISIBLE);
            }
        });*/

       /* //设定年龄
        llSelectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> ageList = new ArrayList<String>();
                for (int i = 1; i <= 120; i++) {
                    ageList.add(i + "");
                }
                PickerDialogUtils pickerDialog = new PickerDialogUtils();
                pickerDialog.setSinglePickerDialog(mContext, "选择年龄", ageList, 25, new PickerDialogUtils
                        .OnDialogClickListener() {
                    @Override
                    public void okDialogClick(String text1, String text2) {
                        tvAge.setText(text1);
                    }
                });
            }
        });*/
    }

    @OnClick({R.id.btn_cancel, R.id.btn_camara, R.id.btn_select_from_photo_album})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                rlSetPhoto.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_camara:
                break;
            case R.id.btn_select_from_photo_album:
                break;
        }
    }

    //设定点击头像事件
    @OnClick({R.id.rl_user_photo, R.id.ll_select_base})
    public void onClickNewAdd(View view) {
        switch (view.getId()) {
            case R.id.rl_user_photo://设置头像
                rlSetPhoto.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_select_base://设置基本资料
                ActivityUtils.gotoIntent(SettingActivity.this, RegistDetailActivity.class);
                break;
        }
    }
}
