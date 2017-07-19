/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: ApplyReplaceActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 健康管理申请替换医师活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.BaseBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * @ClassName: ApplyReplaceActivity
 *Description: 健康管理申请替换医师活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class ApplyReplaceActivity extends AppBaseActivity {

    //标题
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    //标题栏右边图标
    @Bind(R.id.titlebar_search_ll)
    LinearLayout titlebarSearchLl;
    //申请更换内容
    @Bind(R.id.edt_repalce_content)
    EditText edtRepalceContent;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_apply_replace);
        //绑定控件
        ButterKnife.bind(this);
        //设置标题
        titlebarTv.setText(GetResourcesUtils.getString(this,R.string.apply_replace_title));
        //隐藏右边图标
        titlebarSearchLl.setVisibility(View.GONE);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }

    //申请替换
    @OnClick(R.id.tv_manager_replace)
    void tvDoctorRepalce(){
        //检查更换内容是否为空
        if (checkReplaceData()) {
            //提交申请替换
            applyReplace(edtRepalceContent.getText()+"");
            onBackPressed();
            ToastUtil.show(this,getString(R.string.commit_success));
        }
    }

    /**
     * //提交申请替换
     * @param reason  替换原因
     * */
    private void applyReplace(String reason) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("userid").value("")//用户id
                    .key("reason").value(reason)//更换理由
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postPutApplyReplace(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(BaseBean baseBean) {
                            if (baseBean.isSuccess()) {
                                Toast.makeText(ApplyReplaceActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ApplyReplaceActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查更换医师数据
    private boolean checkReplaceData(){
        String errorMessage = null;
        //更换内容是否为空
        if (TextUtils.isEmpty(edtRepalceContent.getText())) {
            errorMessage=getString(R.string.please_input_reason);
        }else {
            return true;
        }
        //土司弹窗
        ToastUtil.show(this,errorMessage);
        return false;
    }
    //返回
    @OnClick(R.id.titlebar_back_ll)
    void applyBack(){
        onBackPressed();
    }

}
