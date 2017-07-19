package com.gaia.member.gaiatt.leancloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avoscloud.leanchatlib.activity.AVChatActivity;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.utils.Constants;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.utils.UiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatDemoActivity extends BaseActivity {

    @Bind(R.id.et_from)
    EditText etFrom;
    @Bind(R.id.et_to)
    EditText etTo;
    @Bind(R.id.btn_chat)
    Button btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_demo);
        ButterKnife.bind(this);

//        ChatUtil.initLeanCloud(this);
    }

    String from = "from";
    String to = "to";

    @OnClick(R.id.btn_chat)
    public void btnChat() {
        gotoChatActivity();
    }

    private void gotoChatActivity() {
        from = etFrom.getText().toString().trim();
        to = etTo.getText().toString().trim();
        ChatManager.getInstance().openClient(this, from, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    Intent intent = new Intent(mContext, AVChatActivity.class);
                    intent.putExtra(Constants.MEMBER_ID, to);
                    startActivity(intent);
                } else {
                    UiUtils.showToast(mContext, e.toString());
                }
            }
        });
    }


}
