/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: GaiaClinicFragment
 * @Package com.gaia.member.gaiatt.gaiaclinic.fragment
 * @Description: 盖亚诊所home主界面fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.BannerBean;
import com.gaia.member.androidlib.net.bean.BannerBean.ParamBean.BinnersBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.activity.AroundGaiaActivity;
import com.gaia.member.gaiatt.gaiaclinic.activity.ConsultingActivity;
import com.gaia.member.gaiatt.gaiaclinic.activity.HealthSeviceActivity;
import com.gaia.member.gaiatt.gaiaclinic.activity.PersonalArchivesActivity;
import com.gaia.member.gaiatt.gaiaclinic.adapter.AutoPagerAdapter;
import com.gaia.member.gaiatt.gaiaclinic.adapter.CustomViewPagerAdapter;
import com.gaia.member.gaiatt.healthmanage.activity.HealthManagerActivity;
import com.gaia.member.gaiatt.healthmanage.activity.HealthRecordActivity;
import com.gaia.member.gaiatt.healthmanage.activity.QuestionnaireActivity;
import com.gaia.member.gaiatt.makeorder.activity.MakeOrderActivity;
import com.gaia.member.gaiatt.mygaia.activity.PointsActivity;
import com.gaia.member.gaiatt.ui.AutoSlidePager;
import com.gaia.member.gaiatt.ui.IndicatorView;
import com.gaia.member.gaiatt.utils.CommonConstants;
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
 * @ClassName: GaiaClinicFragment
 *Description: 盖亚诊所home主界面fragment
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class GaiaClinicFragment extends Fragment {

    //盖亚诊所底部滑动
    @Bind(R.id.vp_gaiaclinic_bottom)
    ViewPager vpGaiaclinicBottom;
    //盖亚诊所顶部滑动
    @Bind(R.id.vp_gaiaclinic_banner)
    AutoSlidePager vpGaiaclinicBanner;
    //盖亚诊所顶部滑动远点
    @Bind(R.id.iv_gaiaclinic_indicatorview)
    IndicatorView ivGaiaclinicIndicatorview;
    //滑动子视图
    private ImageView itemClinicBanner;
    //fragment加载视图
    private View view;
    //顶部banner列表
    private List<View> bannerList;
    //顶部banner列表
    private List<View> bottomList;
    private int[] banners = {R.drawable.top1, R.drawable.top2, R.drawable.top3, R.drawable.top4, R.drawable.top1, R
            .drawable.top2, R.drawable.top3, R.drawable.top4};//banner假数据
    //底部title
    private String[] bottomsTitle;
    //底部内容
    private String[] bottomsContent;
    //自定义滑动页面适配器
    private AutoPagerAdapter autpPagerAdapter;
    //自定义滑动页面适配器
    private CustomViewPagerAdapter myViewPagerAdapter;
    //上下文
    private Context mContext;
    private List<String>  bannerUrlList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gaiaclinic_fragment, container, false);
        mContext=getActivity();
        ButterKnife.bind(this, view);
        initFragment();
        return view;
    }

    private void initFragment() {
        initBanner();//顶部滑动设置
        initBottom();//底部滑动设置
    }


    /**
     * 顶部滑动设置
     */
    private void initBanner() {
        getBannerData();//获取顶部横幅数据
        //初始化列表
        bannerList = new ArrayList<View>();
        //获取顶部子视图
        for (int i = 0; i < banners.length; i++) {
            View bannerView = getActivity().getLayoutInflater().inflate(R.layout.item_clinic_banner_layout, null);
            itemClinicBanner = (ImageView) bannerView.findViewById(R.id.item_clinic_banner);
            itemClinicBanner.setImageResource(banners[i]);
            //添加子视图
            bannerList.add(bannerView);
        }
        //非零
        if (bannerList.size() != 0) {
            //设置适配器
            autpPagerAdapter = new AutoPagerAdapter(bannerList);
            //设置小圆点个数
            ivGaiaclinicIndicatorview.setIndicatorsCount(bannerList.size() / 2);
            vpGaiaclinicBanner.setAutoPagerAdapter(autpPagerAdapter);
            //监听滑动改变
            vpGaiaclinicBanner.setOnSlidingChangedListener(mOnSlidingChangedListener);
            //监听触摸事件
            vpGaiaclinicBanner.setOnTouchListener(mOnTouchListener);
            //设置当前item
            vpGaiaclinicBanner.setCurrentItem(6000);
            //开始滑动
            vpGaiaclinicBanner.startSliding();
        }
    }

    /**
     * 获取顶部横幅数据
     * */
    private void getBannerData() {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object().endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetBanner(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BannerBean>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(BannerBean bannerBean) {
                            if (bannerBean.isSuccess()) {
                                //保存用户Id和Token
                                Toast.makeText(mContext, "成功", Toast.LENGTH_LONG).show();
                                setInitData(bannerBean);//设置网络初始数据
                            } else {
                                Toast.makeText(mContext, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置网络初始数据
     * */
    private void setInitData(BannerBean bannerBean) {
        //获取列表
        List<BinnersBean> binnersList=bannerBean.getParam().getBinners();
        for (int i = 0; i <binnersList.size() ; i++) {
            //获取横幅Url
            bannerUrlList.add(binnersList.get(i).getUrl());
        }
    }

    /**
     * 底部滑动设置
     */
    private void initBottom() {
        //获取资源文件配置数据
        bottomsTitle = getBottomStringArray(R.array.clinic_bottomstitle);
        bottomsContent = getBottomStringArray(R.array.clinic_bottomscontent);
        bottomList = new ArrayList<>();
        if (bottomsTitle != null) {
            //获取数据源
            for (int i = 0; i < bottomsTitle.length; i++) {
                View bottomView = getActivity().getLayoutInflater().inflate(R.layout.item_clinic_bottom_layout, null);
                TextView tvTitle = (TextView) bottomView.findViewById(R.id.tv_item_clinic_title);
                TextView tvContent = (TextView) bottomView.findViewById(R.id.tv_item_clinic_content);
                ImageView imgClinic = (ImageView) bottomView.findViewById(R.id.img_item_clinic);
                tvTitle.setText(bottomsTitle[i]);
                tvContent.setText(bottomsContent[i]);
                bottomList.add(bottomView);
            }
        }
        //适配数据
        myViewPagerAdapter = new CustomViewPagerAdapter(getActivity(), (ArrayList<View>) bottomList);
        vpGaiaclinicBottom.setAdapter(myViewPagerAdapter);
        // pageCount设置宏缓存的页面数
        vpGaiaclinicBottom.setOffscreenPageLimit(3);
        // 设置2张图之前的间距。
        vpGaiaclinicBottom.setPageMargin(40);
        //滑动改变监听
        vpGaiaclinicBottom.setOnPageChangeListener(mOnPageChangeListener);
        //设置当前颜色
        vpGaiaclinicBottom.setCurrentItem(6000);
    }


    /**
     * 获取strings数据资源
     */
    private String[] getBottomStringArray(int id) {
        return getActivity().getResources().getStringArray(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    //我要咨询按钮
    @OnClick(R.id.ll_consulting)
    void llConsulting() {
        setStartActivity(ConsultingActivity.class);
    }

    //健康服务按钮
    @OnClick(R.id.ll_healthsevice)
    void llHealthsevice() {
        setStartActivity(HealthSeviceActivity.class);
    }

    //预约按钮
    @OnClick(R.id.tv_subscribe)
    void tvSubscribe() {
        setStartActivity(MakeOrderActivity.class);
    }

    //评估
    @OnClick(R.id.tv_assess)
    void tvAssess() {
        setStartActivity(QuestionnaireActivity.class);
    }

    //记录
    @OnClick(R.id.tv_record)
    void tvRecord() {
        setStartActivity(HealthRecordActivity.class);
    }

    //档案
    @OnClick(R.id.tv_archives)
    void tvArchives() {
        setStartActivity(PersonalArchivesActivity.class);
    }

    /**
     * 盖亚诊所底部滑动入口
     */

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            setItemViewPageListener(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
    /**
     * 盖亚诊所顶部滑动监听
     */

    AutoSlidePager.OnSlidingChangedListener mOnSlidingChangedListener = new AutoSlidePager.OnSlidingChangedListener() {
        @Override
        public void onScrollChanged(int pos) {
            ivGaiaclinicIndicatorview.move(pos, vpGaiaclinicBanner.getWidth());
        }
    };


    /**
     * 底部滑动单点击监听
     */
    private void setItemViewPageListener(int position) {
        //获取当前点击item
        int index = position % bottomsTitle.length;
        View v = bottomList.get(index);
        final int indexNormal = index % 3;

        //处理滑动异常的算法
        RelativeLayout reItemClinic = (RelativeLayout) v.findViewById(R.id.re_item_clinic);
        reItemClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (indexNormal) {
                    case CommonConstants.HOMEDOCYOR://家庭医生
                        setStartActivity(HealthManagerActivity.class);
                        break;
                    case CommonConstants.AROUNDGAIA://身边盖亚
                        setStartActivity(AroundGaiaActivity.class);
                        break;
                    case CommonConstants.POINTSGIFT://积分好礼
                        setStartActivity(PointsActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * intent封装方法
     *
     * @param cls
     */
    private void setStartActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    /**
     * 定时滑动和触摸滑动时间冲突解决
     */
    View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                //触摸移动和按下
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_DOWN:
                    vpGaiaclinicBanner.stopSliding();//停止滑动
                    break;
                //抬起
                case MotionEvent.ACTION_UP:
                    vpGaiaclinicBanner.startSliding();//开始滑动
                    break;
            }
            return false;
        }
    };
}
