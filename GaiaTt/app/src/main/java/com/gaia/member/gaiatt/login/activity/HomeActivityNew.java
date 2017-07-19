package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.fragment.GaiaClinicFragment;
import com.gaia.member.gaiatt.healthmanage.fragment.HealthManagementFragment;
import com.gaia.member.gaiatt.message.fragment.MessageHomeFragement;
import com.gaia.member.gaiatt.mygaia.fragment.MyGaiaHomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: HomeActivityNew
 * @Package com.gaia.member.gaiatt.login.activity
 * @Description: 登录进入的主界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/12.
 */
public class HomeActivityNew extends AppCompatActivity {

    @Bind(R.id.iv_index_clinic)
    ImageView ivIndexClinic;
    @Bind(R.id.tv_index_clinic)
    TextView tvIndexClinic;
    @Bind(R.id.ll_index_clinic)
    LinearLayout llIndexClinic;
    @Bind(R.id.iv_index_health_manage)
    ImageView ivIndexHealthManage;
    @Bind(R.id.tv_index_health_manage)
    TextView tvIndexHealthManage;
    @Bind(R.id.ll_index_health_manage)
    LinearLayout llIndexHealthManage;
    @Bind(R.id.iv_index_message)
    ImageView ivIndexMessage;
    @Bind(R.id.tv_index_message)
    TextView tvIndexMessage;
    @Bind(R.id.ll_index_message)
    LinearLayout llIndexMessage;
    @Bind(R.id.view_center)
    View viewCenter;
    @Bind(R.id.tv_index_message_notify)
    TextView tvIndexMessageNotify;
    @Bind(R.id.iv_index_mygaia)
    ImageView ivIndexMygaia;
    @Bind(R.id.tv_index_mygaia)
    TextView tvIndexMygaia;
    @Bind(R.id.ll_index_mygaia)
    LinearLayout llIndexMygaia;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;


    boolean selectGy = false;
    MyIndexListener myIndexListener;
    TextView[] indexTextViews;
    ImageView[] indexImageViews;


    private Activity mContext;


    private GaiaClinicFragment gaiaClinicFragment;
    private HealthManagementFragment healthManagementFragment;
    private MyGaiaHomeFragment myGaiaHomeFragment;
    private MessageHomeFragement messageHomeFragement;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        indexTextViews = new TextView[]{tvIndexClinic, tvIndexHealthManage, tvIndexMessage, tvIndexMygaia};
        indexImageViews = new ImageView[]{ivIndexClinic, ivIndexHealthManage, ivIndexMessage, ivIndexMygaia};

        myIndexListener = new MyIndexListener();
        llIndexClinic.setOnClickListener(myIndexListener);
        llIndexHealthManage.setOnClickListener(myIndexListener);
        llIndexMessage.setOnClickListener(myIndexListener);
        llIndexMygaia.setOnClickListener(myIndexListener);

        fragmentManager = getFragmentManager();

        selectIndexImageAndText(0);
        selectIndexFragment(2);//预读通知消息
        selectIndexFragment(0);
    }

    class MyIndexListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_index_clinic:
                    selectIndexImageAndText(0);
                    selectIndexFragment(0);
                    break;

                case R.id.ll_index_health_manage:
                    selectIndexImageAndText(1);
                    selectIndexFragment(1);
                    break;

                case R.id.ll_index_message:
                    selectIndexImageAndText(2);
                    selectIndexFragment(2);
                    break;

                case R.id.ll_index_mygaia:
                    selectIndexImageAndText(3);
                    selectIndexFragment(3);
                    break;

            }
        }
    }

    /**
     * 设置下面指示tab的图片和文字的显示状态
     *
     * @param index
     */
    private void selectIndexImageAndText(int index) {
        for (int i = 0; i < 4; i++) {
            if (i == index) {
                indexTextViews[i].setSelected(true);
                indexImageViews[i].setSelected(true);
                continue;
            }
            indexTextViews[i].setSelected(false);
            indexImageViews[i].setSelected(false);
        }
    }

    /**
     * 设置选中的Fragment
     *
     * @param index
     */
    private void selectIndexFragment(int index) {
        Fragment selectedFragment = null;
        switch (index) {
            case 0:
                if (gaiaClinicFragment == null) {
                    gaiaClinicFragment = new GaiaClinicFragment();
                    addFragment(gaiaClinicFragment);
                }
                selectedFragment = gaiaClinicFragment;
                break;

            case 1:
                if (healthManagementFragment == null) {
                    healthManagementFragment = new HealthManagementFragment();
                    addFragment(healthManagementFragment);
                }
                selectedFragment = healthManagementFragment;
                break;
            case 2:
                if (messageHomeFragement == null) {
                    messageHomeFragement = new MessageHomeFragement();
                    addFragment(messageHomeFragement);
                }
                selectedFragment = messageHomeFragement;
                break;
            case 3:
                if (myGaiaHomeFragment == null) {
                    myGaiaHomeFragment = new MyGaiaHomeFragment();
                    addFragment(myGaiaHomeFragment);
                }
                selectedFragment = myGaiaHomeFragment;
                break;
        }
        showSelectedFragment(selectedFragment);
    }

    /**
     * 添加fragment
     *
     * @param fragment
     */
    private void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    /**
     * 显示指定的fragment
     *
     * @param showTarget
     */
    private void showSelectedFragment(Fragment showTarget) {
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment tempFragment;
        for (int i = 0; i < fragmentList.size(); i++) {
            tempFragment = fragmentList.get(i);
            if (tempFragment != null) {
                if (tempFragment == showTarget) {
                    fragmentTransaction.show(tempFragment);
                    continue;
                } else {
                    fragmentTransaction.hide(tempFragment);
                }
            }
        }
//        for (int i = 0; i < fragmentList.size(); i++) {
//            tempFragment = fragmentList.get(i);
//            if (tempFragment != null) {
//                fragmentTransaction.hide(tempFragment);
//            }
//        }
//        fragmentTransaction.show(showTarget);
        fragmentTransaction.commit();
    }

    /**
     * 设定未读消息
     * @param unread
     */
    public void setUnreadMsgNum(int unread) {
        if (unread == 0) {//消息数为0 隐藏
            tvIndexMessageNotify.setVisibility(View.INVISIBLE);
        } else if (unread > 99){//显示未读消息数
            tvIndexMessageNotify.setText("99+");
        } else {//显示未读消息数
            tvIndexMessageNotify.setText(unread + "");
        }
    }


//    @Override
//    public void onAttachFragment(Fragment fragment) {
//        if (fragment != null) {
//            if (fragment instanceof  MyGaiaHomeFragment) {
//                myGaiaHomeFragment = (MyGaiaHomeFragment) fragment;
//            } else if (fragment instanceof  HealthManagementFragment) {
//                healthManagementFragment = (HealthManagementFragment) fragment;
//            } else if (fragment instanceof  GaiaClinicFragment) {
//                gaiaClinicFragment = (GaiaClinicFragment) fragment;
//            } else if (fragment instanceof  MessageHomeFragement) {
//                messageHomeFragement = (MessageHomeFragement) fragment;
//            }
//        }
//        super.onAttachFragment(fragment);
//    }


    /**
     * 防止fragment重叠
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }
}
