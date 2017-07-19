package com.gaia.member.gaiatt.healthmanage.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaia.member.gaiatt.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: ShareFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 分享fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class ShareFragment extends Fragment{



    View view;

    private OnDialogListener dialogListener;

    public void setOnDialogListener(OnDialogListener listener) {
        dialogListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.share_dialog, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    //微信
    @OnClick(R.id.share_weixin)
    public void shareweixinListener(){
        ((OnDialogListener)getActivity()).shareListener(1);
    }

    //朋友圈
    @OnClick(R.id.share_friends_circle_layout)
    public void sharefriendsListener(){
        ((OnDialogListener)getActivity()).shareListener(2);
    }
//QQ
    @OnClick(R.id.share_qq_layout)
    public void shareqqListener(){
        ((OnDialogListener)getActivity()).shareListener(3);
    }
    //email
    @OnClick(R.id.share_email_layout)
    public void sharemailListener(){
        ((OnDialogListener)getActivity()).shareListener(4);
    }
    //短信
    @OnClick(R.id.share_message_layout)
    public void sharemessageListener(){
        ((OnDialogListener)getActivity()).shareListener(5);
    }

    //复制
    @OnClick(R.id.share_copy_layout)
    public void copyListener(){
        ((OnDialogListener)getActivity()).shareListener(6);
    }

    //弹窗接口
    public interface OnDialogListener {
        public void onCancelListener();

        public void shareListener(int type);
    }
}
