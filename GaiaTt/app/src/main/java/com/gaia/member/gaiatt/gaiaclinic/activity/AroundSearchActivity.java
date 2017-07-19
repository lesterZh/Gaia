/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: AroundSearchActivity
 * @Package com.gaia.member.gaiatt.gaiaclinic.activity
 * @Description: 身边盖亚搜寻活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.gaiaclinic.adapter.SearchListViewAdapter;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.AroundClinicParamBean.ParamBean.ClinicListBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * @ClassName: AroundSearchActivity
 *Description: 身边盖亚搜寻活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class AroundSearchActivity extends AppBaseActivity {

    //搜索文本框
    @Bind(R.id.searchbar_search_edt)
    EditText searchbarSearchEdt;
    //清除搜索内容
    @Bind(R.id.searchbar_search_clear)
    ImageView searchbarSearchClear;
    //搜索条数
    @Bind(R.id.search_number)
    TextView searchNumber;
    //搜索列表
    @Bind(R.id.gaia_search_list_view)
    PullOrLoadListView gaiaSearchListView;
    //适配器
    private SearchListViewAdapter searchListViewAdapter;
    //数据源
    private ArrayList<ClinicListBean> searchList;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_around_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
    @Override
    protected void loadData() {
        searchList=new ArrayList();
        searchEdt();
    }


    /***
     * 搜索
     * */
    @OnClick(R.id.searchbar_search_ll)
    void searchbarSearchLl(){
        //显示搜索条数
        searchNumber.setVisibility(View.VISIBLE);
        //清空搜索列表
        searchList.clear();
        //获取搜索条件
        String clinicName=searchbarSearchEdt.getText()+"";
        //搜寻内容判空
        if (clinicName!=null&&!clinicName.equals("")) {
            for (int i = 0; i <8; i++) {
                ClinicListBean listBean=new ClinicListBean();
                listBean.setClinicName("盖亚诊所(" + clinicName + "店)");
                listBean.setAddress("武侯区天府一街80" + i + "号");
                listBean.setContactPhone("028-8777777" + i);
                listBean.setDistance(800 + i);
                searchList.add(listBean);
            }
            //设置搜索数目
            searchNumber.setText(GetResourcesUtils.getString(this,R.string.search_gaia)
                    +searchList.size()+GetResourcesUtils.getString(this, R.string.search_clinic_gaia));
            //适配搜索列表
            searchListViewAdapter=new SearchListViewAdapter(this,searchList);
            gaiaSearchListView.setAdapter(searchListViewAdapter);

            //监听子视图点击
            gaiaSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent =new Intent(AroundSearchActivity.this, GaiaDetailActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            searchNumber.setText(GetResourcesUtils.getString(this,R.string.no_search_gaia));
            searchListViewAdapter.notifyDataSetChanged();
        }


    }
    //取消
    @OnClick(R.id.searchbar_cancel_ll)
    void searchbarCancelLl(){
        onBackPressed();
    }
    @OnClick(R.id.searchbar_search_clear)
    void searchbarSearchClear(){
        clearEditText();// 清除文本框数据
    }


    /**
     * 监听文本框的改变，对清除按钮进行显示和隐藏
     */
    private void searchEdt() {
        // TODO Auto-generated method stub
        searchbarSearchEdt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
                //判断文本框是否为空
                if (arg0 == null || arg0.equals("")) {
                    searchbarSearchClear.setVisibility(View.INVISIBLE);//显示文本清理按钮
                } else {
                    searchbarSearchClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                //判断文本框是否为空
                if (searchbarSearchEdt.getText() == null
                        || (searchbarSearchEdt.getText() + "").equals("")) {
                    searchbarSearchClear.setVisibility(View.INVISIBLE);
                }
            }
        });

        searchbarSearchEdt.getText();
    }

    /**
     * 清除文本框内容 隐藏自己
     */
    private void clearEditText() {
        // TODO Auto-generated method stub
        searchbarSearchEdt.setText("");
        searchbarSearchClear.setVisibility(View.INVISIBLE);
    }


}
