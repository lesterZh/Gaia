/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: GaiaDetailActivity
 * @Package com.gaia.member.gaiatt.gaiaclinic.activity
 * @Description: 身边盖亚详情活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.AroundGaiaDetailBean;
import com.gaia.member.androidlib.net.bean.AroundGaiaDetailBean.ParamBean.GaiaDetailBean;
import com.gaia.member.androidlib.net.bean.AroundGaiaDetailBean.ParamBean.GaiaDetailBean.CouponListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.GaiaGridViewAdapter;
import com.gaia.member.gaiatt.makeorder.activity.ShowServiceInfoActivity;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.ImageLoaderUtils;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;
import com.gaia.member.gaiatt.utils.permissiongen.PermissionFail;
import com.gaia.member.gaiatt.utils.permissiongen.PermissionGen;
import com.gaia.member.gaiatt.utils.permissiongen.PermissionSuccess;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author android移动客户端-王浩韩
 * @ClassName: GaiaDetailActivity
 * Description: 身边盖亚详情活动界面
 * @date 2016/6/14 9:56
 */
public class GaiaDetailActivity extends AppBaseActivity {

    //标题
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    //详情内容
    @Bind(R.id.detail_content)
    TextView detailContent;
    //地址
    @Bind(R.id.detail_address)
    TextView detailAddress;
    //电话
    @Bind(R.id.detail_phone)
    TextView detailPhone;

    //优惠券网格视图
    @Bind(R.id.detail_gridview)
    GridView detailGridview;
    //详情图片
    @Bind(R.id.img_clinic)
    ImageView imgClinic;
    //适配器
    private GaiaGridViewAdapter couponAdapter;
    //身边盖亚详情优惠券图片Id
    private ArrayList<CouponListBean> PicList;
    //统计所有子项的总高度
    private int totalHeight;
    //诊所Id
    private int clinicId;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_gaia_detail);
        //绑定控件
        ButterKnife.bind(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //初始化及获取诊所Id
        PicList = new ArrayList<CouponListBean>();
        clinicId = getIntent().getIntExtra("clinicId", 0);
    }

    @Override
    protected void loadData() {
        //获取诊所详情数据
        //initServiceData();
        for (int i = 0; i < 6; i++) {
            CouponListBean  couponListBean=new CouponListBean();
            PicList.add(couponListBean);
        }

        //优惠券gridView适配
        couponAdapter = new GaiaGridViewAdapter(this, PicList);
        detailGridview.setAdapter(couponAdapter);
        titlebarTv.setText("盖亚诊所（高新店）");
        //设置GridView的总高度
        setListViewHeightBasedOnChildren(detailGridview);
    }

    /**
     * 获取诊所详情数据
     */
    private void initServiceData() {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("clinicId").value(clinicId)//诊所Id
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetAroundGaiaDetail(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AroundGaiaDetailBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(AroundGaiaDetailBean aroundGaiaDetailBean) {
                            if (aroundGaiaDetailBean.isSuccess()) {
                                Toast.makeText(GaiaDetailActivity.this, "成功", Toast.LENGTH_LONG).show();
                                //设置诊所详情控件数据
                                setClinicData(aroundGaiaDetailBean.getParam().getGaiaDetail());
                            } else {
                                Toast.makeText(GaiaDetailActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置诊所详情控件数据
     *
     * @param gaiaDetail 诊所详情
     */
    private void setClinicData(GaiaDetailBean gaiaDetail) {
        //设置诊所详情头部图片
        ImageLoaderUtils.loadImageView(imgClinic, gaiaDetail.getClinicPic());
        //设置诊所名
        detailContent.setText(gaiaDetail.getClinicName());
        //设置诊所地址
        detailAddress.setText(gaiaDetail.getAdress());
        //设置诊所电话
        detailPhone.setText(gaiaDetail.getContactPhone());

        //获取优惠券列表
        List<CouponListBean>  dataList=gaiaDetail.getCouponList();
        for (int i = 0; i <dataList.size() ; i++) {
            PicList.add(dataList.get(i));
        }
        //通知适配器更新数据
        couponAdapter.notifyDataSetChanged();
    }


    //电话联系.....//解决android 6.0及其以上权限问题
    @OnClick(R.id.phone_contact)
    void phoneContact() {
        PermissionGen.with(GaiaDetailActivity.this)
                .addRequestCode(CommonConstants.TELEPHONEPERMISSION)
                .permissions(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.WRITE_CONTACTS)
                .request();

    }

    //解决android 6.0及其以上权限问题
    @PermissionSuccess(requestCode = CommonConstants.TELEPHONEPERMISSION)
    public void callPhoneSu() {
        Intent intentPhone = new Intent();
        intentPhone.setAction(Intent.ACTION_CALL);
        intentPhone.setData(Uri.parse("tel:" + detailPhone.getText()));
        startActivity(intentPhone);
    }

    //获取权限失败
    @PermissionFail(requestCode = CommonConstants.TELEPHONEPERMISSION)
    private void callPhoneFa() {
        ToastUtil.show(this, getString(R.string.contact_fail));
    }

    //权限获取结果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    //预约服务
    @OnClick(R.id.reservation_service)
    void reservationService() {
        Intent intent = new Intent(GaiaDetailActivity.this, ShowServiceInfoActivity.class);
        startActivity(intent);
    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl() {
        onBackPressed();
    }

    //进入搜索页
    @OnClick(R.id.titlebar_search_ll)
    void titlebarSearchLl() {
        Intent intent = new Intent(GaiaDetailActivity.this, AroundSearchActivity.class);
        startActivity(intent);
    }


    /**
     * 计算gridView的高度
     *
     * @param detailGridview
     */
    private void setListViewHeightBasedOnChildren(GridView detailGridview) {
        // 获取detail_gridview对应的Adapter
        ListAdapter listAdapter = detailGridview.getAdapter();
        for (int i = 0; i < PicList.size(); i++) {
            if (listAdapter != null) {
                View listItem = listAdapter.getView(i, null, detailGridview);
                // 计算子项View 的宽高
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }
        ViewGroup.LayoutParams params = detailGridview.getLayoutParams();
        params.height = (totalHeight / 2) + (detailGridview.getVerticalSpacing()
                * (listAdapter.getCount() - 1) / 2);
        // detail_gridview.getVerticalSpacing()获取子项间分隔符占用的高度
        // params.height最后得到整个detail_gridview完整显示需要的高度
        detailGridview.setLayoutParams(params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
