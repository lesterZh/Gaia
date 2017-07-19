/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: AroundGaiaListFragment
 * @Package com.gaia.member.gaiatt.gaiaclinic.fragment
 * @Description: 身边盖亚列表fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.AroundGaiaListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.activity.GaiaDetailActivity;
import com.gaia.member.gaiatt.gaiaclinic.adapter.SearchListViewAdapter;
import com.gaia.member.gaiatt.makeorder.ui.WheelView;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.ResidenceUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.AroundClinicParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.AroundClinicParamBean.ParamBean.ClinicListBean;
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
 * @ClassName: AroundGaiaListFragment
 *Description: 身边盖亚列表fragment
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class AroundGaiaListFragment extends Fragment {

    //选择文本省
    @Bind(R.id.tv_selected_province_map)
    TextView tvSelectedProvinceMap;
    //城市
    @Bind(R.id.tv_selected_city_map)
    TextView tvSelectedCityMap;
    //地区
    @Bind(R.id.tv_selected_county_map)
    TextView tvSelectedCountyMap;
    //选择器 省市区
    @Bind(R.id.wheel_provinces_map)
    WheelView wheelProvincesMap;
    @Bind(R.id.wheel_citys_map)
    WheelView wheelCitysMap;
    @Bind(R.id.wheel_countys_map)
    WheelView wheelCountysMap;
    //搜索按钮
    @Bind(R.id.btn_query_hospital_map)
    Button btnQueryHospitalMap;
    //省市区TextView图组
    @Bind(R.id.ll_city_select_map)
    LinearLayout llCitySelectMap;
    //列表
    @Bind(R.id.gaia_search_list_view)
    PullOrLoadListView gaiaSearchListView;
    //下拉上拉空间
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;
    //视图
    private View view;
    //列表适配器
    private SearchListViewAdapter adapter;
    //数据源
    private ArrayList<ClinicListBean> clinicList;

    //初始页
    private int INITCURRENTPAGE = 1;
    //当前页
    private int currentPage = 1;
    //一页数量
    private final int PAGESIZE = 20;
    Activity mContext;//上下文

    //.............................省市区选择器
    private String selectedProvince;
    private String selectedCity;
    private String selectedCounty;

    private ArrayList<String> provincesList = new ArrayList<>();
    private ArrayList<String> citysList = new ArrayList<>();
    private ArrayList<String> countysList = new ArrayList<>();

    private static final int DEFAULT_PRIVINCE_INDEX = 0;
    private static final int DEFAULT_CITY_INDEX = 0;
    private static final int DEFAULT_COUNTY_INDEX = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = (Activity) getContext();
        view = inflater.inflate(R.layout.list_view_layout, container, false);
        ButterKnife.bind(this, view);
        initWheels();
        initData();//初始化数据
        return view;
    }

    /***
     * 初始化数据
     */
    private void initData() {

        clinicList = (ArrayList<ClinicListBean>) getLocalAroundGaiaListData();
        adapter = new SearchListViewAdapter(getActivity(), clinicList);
        gaiaSearchListView.setAdapter(adapter);

        mVisibleSwitchListener = new VisibleSwitchListener();
        tvSelectedCountyMap.setOnClickListener(mVisibleSwitchListener);
        tvSelectedProvinceMap.setOnClickListener(mVisibleSwitchListener);
        tvSelectedCityMap.setOnClickListener(mVisibleSwitchListener);
        llCitySelectMap.setVisibility(View.INVISIBLE);
        gaiaSearchListView.setVisibility(View.VISIBLE);
        gaiaSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GaiaDetailActivity.class);
                startActivity(intent);
            }
        });
        //initServiceData(INITCURRENTPAGE);//获取服务器诊所列表
        setUpdata();//更新和加载更多
    }

    /**
     * 更新和加载更多
     */
    private void setUpdata() {

        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                //更新
                //initServiceData(INITCURRENTPAGE);
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
                //加载更多
                //initServiceData(currentPage++);
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //TODO

                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }
        });
    }

    /**
     * 获取服务器诊所列表
     * @param currentPage
     */
    private void initServiceData(int currentPage) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("currentPage").value(currentPage)//当前页
                    .key("pageSize").value(PAGESIZE)//一页数量
                    .key("myselfLongitude").value(23.24)//定位经度
                    .key("myselfLatitude").value(123.43)//定位纬度
                    .key("range").value(1000)//搜索范围
                            //.key("area").value(id)//搜索区域可以不填
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetAroundGaiaList(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AroundGaiaListBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(AroundGaiaListBean aroundGaiaListBean) {
                            if (aroundGaiaListBean.isSuccess()) {
                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<ClinicListBean> getLocalAroundGaiaListData() {
        String aroundGaiaJsonString = AssetsFileUtil.getJsonStr(getActivity(), "aroundgaialist.txt");
        AroundClinicParamBean aroundClinicParamBean = null;
        if (aroundGaiaJsonString != null) {
            aroundClinicParamBean = GsonTools.getAroundClinicParamBean(aroundGaiaJsonString);
        }
        return aroundClinicParamBean.getParam().getClinicList();
    }

    //选择器设置
    private void initWheels() {
        provincesList = getProvinces();
        wheelProvincesMap.setData(provincesList);
        wheelProvincesMap.setDefault(DEFAULT_PRIVINCE_INDEX);
        selectedProvince = provincesList.get(DEFAULT_PRIVINCE_INDEX);
        tvSelectedProvinceMap.setText(selectedProvince);

        citysList = getCitys(selectedProvince);
        wheelCitysMap.setData(citysList);
        wheelCitysMap.setDefault(DEFAULT_CITY_INDEX);
        selectedCity = citysList.get(DEFAULT_CITY_INDEX);
        tvSelectedCityMap.setText(selectedCity);

        countysList = getCountys(selectedCity);
        wheelCountysMap.setData(countysList);
        wheelCountysMap.setDefault(DEFAULT_COUNTY_INDEX);
        selectedCounty = countysList.get(DEFAULT_COUNTY_INDEX);
        tvSelectedCountyMap.setText(selectedCounty);


        wheelProvincesMap.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedProvince = text;
                wheelCitysMap.setData(getCitys(selectedProvince));
                wheelCitysMap.setDefault(DEFAULT_CITY_INDEX);

                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedProvinceMap.setText(selectedProvince);

                        // 同步更新县区 和 顶部市、县文本
                        selectedCity = wheelCitysMap.getSelectedText();
                        wheelCountysMap.setData(getCountys(selectedCity));
                        wheelCountysMap.setDefault(DEFAULT_COUNTY_INDEX);

                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvSelectedCityMap.setText(selectedCity);

                                selectedCounty = wheelCountysMap.getSelectedText();
                                tvSelectedCountyMap.setText(selectedCounty);
                            }
                        });

                    }
                });
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        wheelCitysMap.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedCity = text;
                wheelCountysMap.setData(getCountys(selectedCity));
                wheelCountysMap.setDefault(DEFAULT_COUNTY_INDEX);

                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedCityMap.setText(selectedCity);
                        selectedCounty = wheelCountysMap.getSelectedText();
                        tvSelectedCountyMap.setText(selectedCounty);
                    }
                });

            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        wheelCountysMap.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedCounty = text;

                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedCountyMap.setText(selectedCounty);
                    }
                });

            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }


    @OnClick(R.id.btn_query_hospital_map)
    void btnQueryHospitalMap() {
        llCitySelectMap.setVisibility(View.INVISIBLE);
        gaiaSearchListView.setVisibility(View.VISIBLE);
    }

    //用于切换城市三级联动选择器的是否可见,添加动画效果
    private VisibleSwitchListener mVisibleSwitchListener;

    class VisibleSwitchListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (llCitySelectMap.getVisibility() == View.INVISIBLE) {
                llCitySelectMap.setVisibility(View.VISIBLE);
                ObjectAnimator showAnim = ObjectAnimator.ofFloat(llCitySelectMap, "scaleY", 0, 1);
                llCitySelectMap.setPivotY(0);
                showAnim.setDuration(300);
                showAnim.start();

                gaiaSearchListView.setVisibility(View.GONE);
            } else {

                ObjectAnimator hideAnim = ObjectAnimator.ofFloat(llCitySelectMap, "scaleY", 1, 0);
                llCitySelectMap.setPivotY(0);
                hideAnim.setDuration(300);
                hideAnim.start();
                hideAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        llCitySelectMap.setVisibility(View.INVISIBLE);//为if判断创造条件
                    }
                });
                gaiaSearchListView.setVisibility(View.VISIBLE);
            }
        }
    }


    private ArrayList<String> getProvinces() {
        return ResidenceUtils.getProData();
    }

    private ArrayList<String> getCitys(String province) {
        return ResidenceUtils.getCityData(province);
    }

    private ArrayList<String> getCountys(String city) {

        return ResidenceUtils.getAreaData(city);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
