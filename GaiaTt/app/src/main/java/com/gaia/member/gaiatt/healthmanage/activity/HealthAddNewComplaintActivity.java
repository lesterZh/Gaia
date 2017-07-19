/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthAddNewComplaintActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 增加新的投诉活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.BaseBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.UserUtils;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 *@ClassName: HealthAddNewComplaintActivity
 * Description: 增加新的投诉活动界面
 * @author 移动开发组-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 *
 */
public class HealthAddNewComplaintActivity extends AppBaseActivity {

    //标题
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    //右边按钮
    @Bind(R.id.titlebar_search_ll)
    LinearLayout titlebarSearchLl;
    //投诉内容
    @Bind(R.id.edt_complaint_content)
    EditText edtComplaintContent;
    //匿名
    @Bind(R.id.cb_anonymous_complaint)
    CheckBox cbAnonymousComplaint;
    //投诉人姓名
    @Bind(R.id.tv_complaint_name)
    TextView tvComplaintName;
    //投诉日期
    @Bind(R.id.tv_complaint_date)
    TextView tvComplaintDate;
    //投诉类型
    @Bind(R.id.tv_complaint_type)
    TextView tvComplaintType;
    private PopupWindow popupWindow;//投诉类别选择弹窗
    private List<String> typeList;//投诉类别列表

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_health_complaint);
        ButterKnife.bind(this);//插件绑定
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //设置界面标题
        titlebarTv.setText(GetResourcesUtils.getString(this,R.string.complaint_title));
        //隐藏标题栏右边图标
        titlebarSearchLl.setVisibility(View.GONE);
        //投诉类别列表初始化
        typeList = new ArrayList<String>();
        //设置投诉日期
        tvComplaintDate.setText(GetResourcesUtils.getString(this, R.string.complaint_date) + getComplaintDate());
    }

    @Override
    protected void loadData() {
        //获取投诉类别数据源
        String[] complaintType=GetResourcesUtils.getStringArrary(this,R.array.complaint_type);
        for (int i = 0; i <complaintType.length; i++) {
            typeList.add(complaintType[i]);
        }
    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl() {
        onBackPressed();
    }


    //投诉
    @OnClick(R.id.tv_manager_complaint)
    void tvManagerComplaint() {
        if (checkComplaintData()) {
            //submitComplaint();//提交投诉
            onBackPressed();
            ToastUtil.show(this, getString(R.string.complaint_success));
        }
    }

    /**
     * 提交新增投诉
     * */
    private void submitComplaint() {
        String complaintType=tvComplaintType.getText()+"";
        String complainContent=edtComplaintContent.getText()+"";
        String userName=tvComplaintName.getText()+"";
        String time=tvComplaintDate.getText()+"";
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("userid").value(UserUtils.getUserId())//用户id
                    .key("complaintType").value(complaintType)//投诉类型
                    .key("complainContent").value(complainContent)//投诉内容
                    .key("userName").value(userName)//用户姓名
                    .key("time").value(time)//投诉时间
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postPutComplaint(body)
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
                                Toast.makeText(HealthAddNewComplaintActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(HealthAddNewComplaintActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查投诉
     * */
    private boolean checkComplaintData(){
        String errorMessage = null;
        //检查投诉类别是否为空
        if (TextUtils.isEmpty(tvComplaintType.getText())) {
            errorMessage=getString(R.string.please_select_type);
            //检查投诉内容是否为空
        }else  if(TextUtils.isEmpty(edtComplaintContent.getText())){
            errorMessage=getString(R.string.please_input_content);
        }else{
            return true;
        }
        ToastUtil.show(this,errorMessage);//为空提示
        return false;
    }

    /**
     * 获取当前日期
     * */
    private String getComplaintDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    //选择投诉类别弹窗
    @OnClick(R.id.re_complaint_type)
    void ReComplaintType() {
        //加载布局视图
        View v = LayoutInflater.from(HealthAddNewComplaintActivity.this).inflate(R.layout.item_popuwindow_complaint_type,
                null);
        ListView lvComplaintPopu = (ListView) v.findViewById(R.id.lv_complaint_popu);
        popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//设置弹窗宽高属性
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));//设置弹窗背景图片
        popupWindow.setFocusable(true);//设置焦点
        popupWindow.setOutsideTouchable(true);//设置触摸
        popupWindow.showAsDropDown(tvComplaintType);//设置依附布局
        popupWindow.update();//更新开启弹窗
        //弹窗子视图列表适配数据
        ArrayAdapter arrayAdapter = new ArrayAdapter(HealthAddNewComplaintActivity.this, R.layout
                .item_complainttype_layout, R.id.tv_item_complaint, typeList);
        lvComplaintPopu.setAdapter(arrayAdapter);
        //监听选择器选择数据
        lvComplaintPopu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemString = (String) parent.getItemAtPosition(position);
                tvComplaintType.setText(itemString);//设置投诉类型
                popupWindow.dismiss();
            }
        });
    }


}
