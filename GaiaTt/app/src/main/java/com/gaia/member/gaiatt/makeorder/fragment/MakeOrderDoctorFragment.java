package com.gaia.member.gaiatt.makeorder.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.activity.ShowDoctorsListActivity;
import com.gaia.member.gaiatt.makeorder.bean.HospitalBean;
import com.gaia.member.gaiatt.makeorder.ui.WheelView;
import com.gaia.member.gaiatt.makeorder.ui.ZhtCustomProgressDialog;
import com.gaia.member.gaiatt.utils.ActivityUtils;
import com.gaia.member.gaiatt.utils.ResidenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 预约医生的初始入口fragment
 * Created by zhangHaiTao on 2016/5/13.
 */
public class MakeOrderDoctorFragment extends Fragment {

    Activity mContext;
    @Bind(R.id.wheel_provinces)
    WheelView wheelProvinces;
    @Bind(R.id.wheel_citys)
    WheelView wheelCitys;
    @Bind(R.id.wheel_countys)
    WheelView wheelCountys;
    @Bind(R.id.tv_selected_province)
    TextView tvSelectedProvince;
    @Bind(R.id.tv_selected_city)
    TextView tvSelectedCity;
    @Bind(R.id.tv_selected_county)
    TextView tvSelectedCounty;
    @Bind(R.id.btn_query_hospital)
    Button btnQueryHospital;
    @Bind(R.id.ll_city_select)
    LinearLayout llCitySelect;
    @Bind(R.id.lv_hospitals)
    ListView lvHospitals;

    private String selectedProvince;
    private String selectedCity;
    private String selectedCounty;

    private ArrayList<String> provincesList = new ArrayList<>();
    private ArrayList<String> citysList = new ArrayList<>();
    private ArrayList<String> countysList = new ArrayList<>();

    private static final int DEFAULT_PRIVINCE_INDEX = 0;
    private static final int DEFAULT_CITY_INDEX = 0;
    private static final int DEFAULT_COUNTY_INDEX = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = (Activity) getContext();
        View view = View.inflate(mContext, R.layout.fragment_make_order_doctor, null);
        ButterKnife.bind(this, view);


        initWheels();

        initView();

        return view;
    }

    private void initView() {
        mVisibleSwitchListener = new VisibleSwitchListener();
        tvSelectedCounty.setOnClickListener(mVisibleSwitchListener);
        tvSelectedProvince.setOnClickListener(mVisibleSwitchListener);
        tvSelectedCity.setOnClickListener(mVisibleSwitchListener);

        llCitySelect.setVisibility(View.INVISIBLE);
        lvHospitals.setVisibility(View.VISIBLE);

        //测试数据
        for (int i=0; i<10; i++) {
            HospitalBean hospital = new HospitalBean();
            hospitalDefaultList.add(hospital);
        }

        showHospitalList(hospitalDefaultList);
        lvHospitals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //需要向下一个activity传递医院的数据
                ActivityUtils.gotoIntent(mContext, ShowDoctorsListActivity.class);
            }
        });
    }

    private void showHospitalList(List<HospitalBean> list) {
        hospitalListAdapter = new MyHospitalListAdapter(mContext, 0, list);
        lvHospitals.setAdapter(hospitalListAdapter);
    }



    private void initWheels() {
        provincesList = getProvinces();
        wheelProvinces.setData(provincesList);
        wheelProvinces.setDefault(DEFAULT_PRIVINCE_INDEX);
        selectedProvince = provincesList.get(DEFAULT_PRIVINCE_INDEX);
        tvSelectedProvince.setText(selectedProvince);

        citysList = getCitys(selectedProvince);
        wheelCitys.setData(citysList);
        wheelCitys.setDefault(DEFAULT_CITY_INDEX);
        selectedCity = citysList.get(DEFAULT_CITY_INDEX);
        tvSelectedCity.setText(selectedCity);

        countysList = getCountys(selectedCity);
        wheelCountys.setData(countysList);
        wheelCountys.setDefault(DEFAULT_COUNTY_INDEX);
        selectedCounty = countysList.get(DEFAULT_COUNTY_INDEX);
        tvSelectedCounty.setText(selectedCounty);


        wheelProvinces.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedProvince = text;
                wheelCitys.setData(getCitys(selectedProvince));
                wheelCitys.setDefault(DEFAULT_CITY_INDEX);

                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedProvince.setText(selectedProvince);

                        // 同步更新县区 和 顶部市、县文本
                        selectedCity = wheelCitys.getSelectedText();
                        wheelCountys.setData(getCountys(selectedCity));
                        wheelCountys.setDefault(DEFAULT_COUNTY_INDEX);

                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvSelectedCity.setText(selectedCity);

                                selectedCounty = wheelCountys.getSelectedText();
                                tvSelectedCounty.setText(selectedCounty);
                            }
                        });

                    }
                });
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        wheelCitys.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedCity = text;
                wheelCountys.setData(getCountys(selectedCity));
                wheelCountys.setDefault(DEFAULT_COUNTY_INDEX);

                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedCity.setText(selectedCity);
                    }
                });

            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        wheelCountys.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedCounty = text;

                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSelectedCounty.setText(selectedCounty);
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

    private ArrayList<String> getProvinces() {
       return ResidenceUtils.getProData();
//        ArrayList<String> provinces = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            provinces.add("省份" + (i + 1));
//        }
//        return provinces;
    }

    private ArrayList<String> getCitys(String province) {
        return ResidenceUtils.getCityData(province);
//        ArrayList<String> provinces = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            provinces.add(province + "-市" + (i + 1));
//        }
//        return provinces;
    }

    private ArrayList<String> getCountys(String city) {
        return ResidenceUtils.getAreaData(city);
//        ArrayList<String> provinces = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            provinces.add(city + "-县" + (i + 1));
//        }
//        return provinces;
    }

    ZhtCustomProgressDialog progressDialog;
    @OnClick(R.id.btn_query_hospital)
    public void btnQueryHospital() {
        llCitySelect.setVisibility(View.INVISIBLE);
        lvHospitals.setVisibility(View.VISIBLE);

        progressDialog = ZhtCustomProgressDialog.show(mContext, "查询中", true, null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 500);


        //测试数据
        for (int i=0; i<30; i++) {
            HospitalBean hospital = new HospitalBean();
            hospitalQureyList.add(hospital);
        }
        showHospitalList(hospitalQureyList);

    }

    List<HospitalBean> hospitalQureyList = new ArrayList<>();
    List<HospitalBean> hospitalDefaultList = new ArrayList<>();

    MyHospitalListAdapter hospitalListAdapter;

    class MyHospitalListAdapter extends ArrayAdapter<HospitalBean> {

        public MyHospitalListAdapter(Context context, int resource, List<HospitalBean> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHoder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_list_make_order_hospital, null);

            }
            return convertView;
        }

         class ViewHolder {
            @Bind(R.id.tv_hospital_name)
            TextView tvHospitalName;
            @Bind(R.id.tv_hospital_level)
            TextView tvHospitalLevel;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    //用于切换城市三级联动选择器的是否可见,添加动画效果
    private  VisibleSwitchListener mVisibleSwitchListener;
    class VisibleSwitchListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (llCitySelect.getVisibility() == View.INVISIBLE) {
                llCitySelect.setVisibility(View.VISIBLE);
                ObjectAnimator showAnim = ObjectAnimator.ofFloat(llCitySelect, "scaleY", 0, 1);
                llCitySelect.setPivotY(0);
                showAnim.setDuration(300);
                showAnim.start();

                lvHospitals.setVisibility(View.INVISIBLE);
            } else {

                ObjectAnimator hideAnim = ObjectAnimator.ofFloat(llCitySelect,"scaleY",1,0);
                llCitySelect.setPivotY(0);
                hideAnim.setDuration(300);
                hideAnim.start();
                hideAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        llCitySelect.setVisibility(View.INVISIBLE);//为if判断创造条件
                    }
                });
//                lvHospitals.setVisibility(View.VISIBLE);
            }
        }
    }


}
