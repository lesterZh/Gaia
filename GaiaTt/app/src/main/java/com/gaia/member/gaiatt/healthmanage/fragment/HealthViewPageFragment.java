package com.gaia.member.gaiatt.healthmanage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.PlanListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.activity.CnMedicinePlanDetailActivity;
import com.gaia.member.gaiatt.healthmanage.activity.DietPlanDetailActivity;
import com.gaia.member.gaiatt.healthmanage.activity.DrinkPalnDetailActivity;
import com.gaia.member.gaiatt.healthmanage.activity.SmokePlanDetailActivity;
import com.gaia.member.gaiatt.healthmanage.activity.SportPlanDetailActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.HealthListViewAdapter;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.ListSortUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthViewPageFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 干预计划导航栏Fragmengt
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthViewPageFragment extends Fragment implements AdapterView.OnItemClickListener{

    //fragment 视图
    View view;
    //列表视图
    @Bind(R.id.health_fragment_listview)
    PullOrLoadListView healthFragmentListview;
    //上拉、下拉控件
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    //数据源
    private ArrayList<HealthPlanBean> planBeanslist;
    //数据类型
    private int category;
    //适配器
    private HealthListViewAdapter healthListViewAdapter;
    //当前页
    private int CURRENTPAGE=1;
    //当前页
    private int mCurrentPage=1;
    //当前页大小
    private int pageSize=20;

    //当前日期
    private  String currentDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.healthfragment_list_layout, container, false);
        //绑定控件
        ButterKnife.bind(this, view);
        initView();//初始化视图
        initData();//获取列表初始化数据
        return view;
    }

    //初始化数据视图
    void initView() {
        //获取列表数据源
        planBeanslist = (ArrayList<HealthPlanBean>) getLocalplanData();
        if (currentDate != null) {
            //获取列表数据源
            if (planBeanslist!= null) {
                //测试数据
                for (int i = 0; i < planBeanslist.size(); i++) {
                    planBeanslist.get(i).setDate(currentDate);
                }
            }
        }
        //列表排序
        if (planBeanslist.size() >1) {
            ListSortUtils.SetPlanListSort(planBeanslist);
        }
        //列表适配
        healthListViewAdapter = new HealthListViewAdapter(getActivity(), planBeanslist);
        healthFragmentListview.setAdapter(healthListViewAdapter);
        //点击事件监听
        healthFragmentListview.setOnItemClickListener(this);
    }
    /**
     * 计划列表数据初始化
     */
    private void initData() {
        if (currentDate == null) {
            //获取全部列表数据
            // getListData(CURRENTPAGE);
        }else {
            getListDataByDate(CURRENTPAGE,currentDate);
        }
        PullOrLoad();//刷新和加载更多
    }
    /**
     * 获取全部列表数据
     * @param currentPage 访问当前页
     * */
    private void getListData(int currentPage) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("category").value(category)//类型
                    .key("currentPage").value(currentPage)//访问当前页
                    .key("pageSize").value(pageSize)//该页数量
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetPlanList(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PlanListBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(PlanListBean planListBean) {
                            if (planListBean.isSuccess()) {
                                mCurrentPage++;
                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
                                //planBeanslist=planListBean.getParam().getList();
                                healthListViewAdapter.notifyDataSetChanged();
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
     * 刷新和加载更多
     */
    private void PullOrLoad() {
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {//下拉刷新
                //getListData(CURRENTPAGE);
                //测试
                planBeanslist = (ArrayList<HealthPlanBean>) getLocalplanData();
                healthListViewAdapter.notifyDataSetChanged();
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //TODO

                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {//上拉加载更多
                //getListData(mCurrentPage);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 设置数据源
     * @param category 数据类型
     * */
    public void setExtraInfo(int category) {
        this.category = category;
    }

    /**
     * 设置数据源
     * @param currentDate 当前日期
     * */
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * 获取全部列表数据
     * @param
     * */
    private void getListDataByDate(int currentPage,String currentDate) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("category").value(category)//类型
                    .key("currentPage").value(currentPage)//访问当前页
                    .key("pageSize").value(pageSize)//该页数量
                    .key("currentDate").value(currentDate)//当前日期20160603
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetPlanListByDate(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PlanListBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(PlanListBean planListBean) {
                            if (planListBean.isSuccess()) {
                                mCurrentPage++;
                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
                                //planBeanslist=planListBean.getParam().getList();
                                healthListViewAdapter.notifyDataSetChanged();
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
     * 获取assets文件路径下的本地文件
     */
    private List getLocalplanData() {
        //获取本地assets文件目录json数据
        String planJsonString = AssetsFileUtil.getJsonStr(getActivity(), "healthplanlist.txt");
        List<HealthPlanBean> mList = new ArrayList<>();
        if (planJsonString != null) {
            //解析json数据
            HealthPlanParamBean healthPlanParamBean = GsonTools.getHealthPlanParamBean(planJsonString);
            mList = healthPlanParamBean.getParam().getList();
        }
        return mList;
    }



    /***
     * 点击事件监听
     * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HealthPlanBean bean = (HealthPlanBean) parent.getItemAtPosition(position);
        //获取点击的数据类型
        int type = bean.getCategory();
        Class<?> cls = null;
        switch (type) {
            case CommonConstants.SPORTPLAN:
                cls = SportPlanDetailActivity.class;//运动计划详情
                break;
            case CommonConstants.CNMEDICINEPLAN:
                cls = CnMedicinePlanDetailActivity.class;//中医计划详情
                break;
            case CommonConstants.DIETPLAN:
                cls = DietPlanDetailActivity.class;//饮食计划详情
                break;
            case CommonConstants.DRINKPLAN:
                cls = DrinkPalnDetailActivity.class;//饮酒计划详情
                break;
            case CommonConstants.SMOKEPLAN:
                cls = SmokePlanDetailActivity.class;//吸烟计划详情
                break;
            default:
                break;
        }
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }
}
