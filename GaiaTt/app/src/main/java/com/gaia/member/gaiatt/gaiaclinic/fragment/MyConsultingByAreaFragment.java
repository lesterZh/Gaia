/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: MyConsultingByAreaFragment
 * @Package com.gaia.member.gaiatt.gaiaclinic.fragment
 * @Description: 健康咨询咨询地区选择列表fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.ConsultingBean;
import com.gaia.member.androidlib.net.bean.ConsultingParamBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.adapter.ConsultingListViewAdapter;
import com.gaia.member.gaiatt.makeorder.ui.WheelView;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.ResidenceUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;
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
 * @ClassName: MyConsultingByAreaFragment
 * Description: 健康咨询咨询地区选择列表fragment
 * @date 2016/6/5 0005 下午 11:30
 */
public class MyConsultingByAreaFragment extends Fragment {
    //咨询地区列表视图
    @Bind(R.id.consulting_area_list_view)
    PullOrLoadListView consultingAreaListView;
    //显示省
    @Bind(R.id.tv_selected_province_area)
    TextView tvSelectedProvinceArea;
    //显示市
    @Bind(R.id.tv_selected_city_area)
    TextView tvSelectedCityArea;
    //显示区
    @Bind(R.id.tv_selected_county_area)
    TextView tvSelectedCountyArea;
    //选择器省
    @Bind(R.id.wheel_provinces_area)
    WheelView wheelProvincesArea;
    //选择器市
    @Bind(R.id.wheel_citys_area)
    WheelView wheelCitysArea;
    //选择器区
    @Bind(R.id.wheel_countys_area)
    WheelView wheelCountysArea;
    //选择器弹窗布局
    @Bind(R.id.ll_city_select_area)
    LinearLayout llCitySelectArea;
    //上拉 、下拉
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    //列表适配器
    private ConsultingListViewAdapter consultingListViewAdapter;
    //列表数据源
    private List<ConsultingBean> datasList;
    //fragment视图
    private View view;
    //上下文
    Activity mContext;
    //省市区字符串
    private String selectedProvince;
    private String selectedCity;
    private String selectedCounty;
    //省市区列表
    private ArrayList<String> provincesList = new ArrayList<>();
    private ArrayList<String> citysList = new ArrayList<>();
    private ArrayList<String> countysList = new ArrayList<>();

    //省市区下标
    private static final int DEFAULT_PRIVINCE_INDEX = 0;
    private static final int DEFAULT_CITY_INDEX = 0;
    private static final int DEFAULT_COUNTY_INDEX = 0;

    private final int CURRENTPAGE = 1;//.当前页数
    private int currentPage = 1;//.当前页数
    private final int PAGESIZE = 20; //一页数量
    //是否查询过
    private  boolean isCheck=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //初始化上下文
        mContext = (Activity) getContext();
        //加载视图
        view = inflater.inflate(R.layout.consulting_area_layout, container, false);
        //控件绑定
        ButterKnife.bind(this, view);
        //选择器设置
        initWheels();
        //初始化选择器数据
        initData();
        return view;
    }

    /**
     * 初始化选择器数据
     */
    private void initData() {
        //获取本地数据
        datasList = getLocalconsultingData();
        //适配器
        consultingListViewAdapter = new ConsultingListViewAdapter(getActivity(), (ArrayList<ConsultingBean>) datasList);
        consultingAreaListView.setAdapter(consultingListViewAdapter);
        //选择器监听
        mVisibleSwitchListener = new VisibleSwitchListener();
        tvSelectedCountyArea.setOnClickListener(mVisibleSwitchListener);
        tvSelectedProvinceArea.setOnClickListener(mVisibleSwitchListener);
        tvSelectedCityArea.setOnClickListener(mVisibleSwitchListener);
        //隐藏选择器
        llCitySelectArea.setVisibility(View.INVISIBLE);
        //宣誓地区列表
        consultingAreaListView.setVisibility(View.VISIBLE);

        //获取服务器列表数据
        //getConsultingListData(CURRENTPAGE);
        //刷新和加载
        upDataOrMore();
    }

    /**
     * 刷新和加载更多
     * */
    private void upDataOrMore() {
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                //下拉刷新
                //根据定位刷新
                if (isCheck) {
                    //getConsultingListData(CURRENTPAGE);
                }else{
                    //根据区域码
                    //getConsultingListDataByCode(CURRENTPAGE);
                }
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //TODO
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                //上拉加载更多
                //根据定位刷新
                if (isCheck) {
                    //getConsultingListData(currentPage);
                }else{
                    //根据区域码
                    //getConsultingListDataByCode(currentPage);
                }
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //TODO
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }
        });
    }


    /**
     * 获取服务器类列表数据
     *
     * @param currentPage
     */
    private void getConsultingListData(int currentPage) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("currentPage").value(currentPage)//当前页
                    .key("pageSize").value(PAGESIZE)//一页数量
                    .key("myselfLongitude").value(23.24)//定位经度
                    .key("myselfLatitude").value(123.43)//定位纬度
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postConsultingParam(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ConsultingParamBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(ConsultingParamBean consultingParamBean) {
                            if (consultingParamBean.isSuccess()) {
                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
                                setViewData(consultingParamBean);//设置视图数据
                            } else {
                                Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取服务器类列表数据
     *
     * @param currentPage
     */
    private void getConsultingListDataByCode(int currentPage) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("currentPage").value(currentPage)//当前页
                    .key("pageSize").value(PAGESIZE)//一页数量
                    .key("county").value(254)//区域码
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postConsultingParamByCode(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ConsultingParamBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(ConsultingParamBean consultingParamBean) {
                            if (consultingParamBean.isSuccess()) {
                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
                                setViewData(consultingParamBean);//设置视图数据
                            } else {
                                Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 为视图设置数据
     * @param consultingParamBean 我要问诊参数
     * */
    private void setViewData(ConsultingParamBean consultingParamBean) {
        //当前页加一
        currentPage++;
        //获取地区列表
        List<ConsultingBean> consultingBeanList=consultingParamBean.getParam().getList();
        for (int i = 0; i <consultingBeanList.size() ; i++) {
            datasList.add(consultingBeanList.get(i));
        }
        //刷新适配器
        consultingListViewAdapter.notifyDataSetChanged();
    }


    /**
     * 选择器设置
     */
    private void initWheels() {
        //获取省级数据设置省级选择器数据
        provincesList = getProvinces();
        wheelProvincesArea.setData(provincesList);
        wheelProvincesArea.setDefault(DEFAULT_PRIVINCE_INDEX);
        selectedProvince = provincesList.get(DEFAULT_PRIVINCE_INDEX);
        tvSelectedProvinceArea.setText(selectedProvince);

        //获取市级数据设置市级选择器数据
        citysList = getCitys(selectedProvince);
        wheelCitysArea.setData(citysList);
        wheelCitysArea.setDefault(DEFAULT_CITY_INDEX);
        selectedCity = citysList.get(DEFAULT_CITY_INDEX);
        tvSelectedCityArea.setText(selectedCity);

        //获取区级数据设置区级选择器数据
        countysList = getCountys(selectedCity);
        wheelCountysArea.setData(countysList);
        wheelCountysArea.setDefault(DEFAULT_COUNTY_INDEX);
        selectedCounty = countysList.get(DEFAULT_COUNTY_INDEX);
        tvSelectedCountyArea.setText(selectedCounty);


        //监听省级选择滑动
        wheelProvincesArea.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedProvince = text;
                wheelCitysArea.setData(getCitys(selectedProvince));
                wheelCitysArea.setDefault(DEFAULT_CITY_INDEX);
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedProvinceArea.setText(selectedProvince);
                        // 同步更新县区 和 顶部市、县文本
                        selectedCity = wheelCitysArea.getSelectedText();
                        wheelCountysArea.setData(getCountys(selectedCity));
                        wheelCountysArea.setDefault(DEFAULT_COUNTY_INDEX);
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvSelectedCityArea.setText(selectedCity);
                                selectedCounty = wheelCountysArea.getSelectedText();
                                tvSelectedCountyArea.setText(selectedCounty);
                            }
                        });

                    }
                });
            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        //监听市级选择滑动
        wheelCitysArea.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedCity = text;
                wheelCountysArea.setData(getCountys(selectedCity));
                wheelCountysArea.setDefault(DEFAULT_COUNTY_INDEX);
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedCityArea.setText(selectedCity);
                        selectedCounty = wheelCountysArea.getSelectedText();
                        tvSelectedCountyArea.setText(selectedCounty);
                    }
                });

            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        //监听区级选择滑动
        wheelCountysArea.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedCounty = text;
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedCountyArea.setText(selectedCounty);
                    }
                });
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 获取本地数据
     */
    private List getLocalconsultingData() {
        //获取assets数据
        String jsonString = AssetsFileUtil.getJsonStr(getActivity(), "consultinglist.txt");
        List<ConsultingBean> mList = new ArrayList<ConsultingBean>();
        if (jsonString != null) {
            ConsultingParamBean consultingParamBean = GsonTools.getConsultingParamBean(jsonString);
            mList = consultingParamBean.getParam().getList();
        }
        return mList;
    }


    /**
     * 开始查找按钮
     */
    @OnClick(R.id.btn_query_hospital_area)
    public void btnQueryHospitalArea() {
        llCitySelectArea.setVisibility(View.INVISIBLE);
        consultingAreaListView.setVisibility(View.VISIBLE);
        //调用城市码查询接口
        isCheck=true;
        //getConsultingListDataByCode(CURRENTPAGE);//地区查询
    }

    //用于切换城市三级联动选择器的是否可见,添加动画效果
    private VisibleSwitchListener mVisibleSwitchListener;

    class VisibleSwitchListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //如果选择器隐藏
            if (llCitySelectArea.getVisibility() == View.INVISIBLE) {
                llCitySelectArea.setVisibility(View.VISIBLE);
                ObjectAnimator showAnim = ObjectAnimator.ofFloat(llCitySelectArea, "scaleY", 0, 1);
                llCitySelectArea.setPivotY(0);
                showAnim.setDuration(300);
                showAnim.start();
                consultingAreaListView.setVisibility(View.GONE);
                //如果选择器实现
            } else {
                ObjectAnimator hideAnim = ObjectAnimator.ofFloat(llCitySelectArea, "scaleY", 1, 0);
                llCitySelectArea.setPivotY(0);
                hideAnim.setDuration(300);
                hideAnim.start();
                hideAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        llCitySelectArea.setVisibility(View.INVISIBLE);//为if判断创造条件
                    }
                });
                consultingAreaListView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        consultingListViewAdapter.setIsClick(true);
    }

    /**
     * 获取plist省数据
     */
    private ArrayList<String> getProvinces() {
        return ResidenceUtils.getProData();
    }

    /**
     * 获取plist市数据
     */
    private ArrayList<String> getCitys(String province) {
        return ResidenceUtils.getCityData(province);
    }

    /**
     * 获取plist区数据
     */
    private ArrayList<String> getCountys(String city) {

        return ResidenceUtils.getAreaData(city);
    }
}
