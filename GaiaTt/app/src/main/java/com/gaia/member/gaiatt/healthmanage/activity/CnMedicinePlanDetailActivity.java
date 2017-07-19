package com.gaia.member.gaiatt.healthmanage.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.MedicinePlanBean;
import com.gaia.member.androidlib.net.bean.MedicinePlanBean.ParamBean.ListBean;
import com.gaia.member.androidlib.utils.LogUtil;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.MedicineListViewAdapter;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
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
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: CnMedicinePlanDetailActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 中医计划干预活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class CnMedicinePlanDetailActivity extends AppBaseActivity {


    //计划详情列表
    @Bind(R.id.medicine_plan_list)
    PullOrLoadListView medicinePlanList;
    //返回按钮图标
    @Bind(R.id.medicine_detail_back_img)
    ImageView medicineDetailBackImg;
    //标题TextView
    @Bind(R.id.medicine_detail_title_tv)
    TextView medicineDetailTitleTv;
    //返回按钮
    @Bind(R.id.meidicineplan_back_re)
    RelativeLayout meidicineplanBackRe;
    //标题分割线
    @Bind(R.id.plan_line)
    View planLine;
    //刷新加载
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    //适配器
    private MedicineListViewAdapter adapter;
    //列表数据源
    private List<ListBean> listSport;
    //头部返回按钮
    private LinearLayout headMedicineDetailBackLl;
    //头部背景
    private LinearLayout headMedicineDetailBackgroundLl;
    //头部标题
    private TextView headMedicineDetailTitleTv;
    //头部内容
    private TextView headMedicineDetailContentTv;
    //头部提醒
    private TextView headMedicineDetailMindTv;

    //头部视图
    private View headView;
    //计划ID
    private int id;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_cn_medicine_plan_detail);
        ButterKnife.bind(this);
        //获取健康计划id
        id=getIntent().getIntExtra("id",0);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

        //头部控件
        initHeadView();
        adapter = new MedicineListViewAdapter(CnMedicinePlanDetailActivity.this, (ArrayList<ListBean>) listSport);
        medicinePlanList.setAdapter(adapter);
        medicinePlanList.setOnScrollListener(mOnScrollListener);
        headMedicineDetailBackLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initData();
    }

    /**
     * 设置头部视图
     * */
    private void initHeadView() {
        headView = getLayoutInflater().inflate(R.layout.sport_plan_detail_headview, null);
        headMedicineDetailBackLl = (LinearLayout) headView.findViewById(R.id.sport_detail_back_ll_head);
        headMedicineDetailBackgroundLl = (LinearLayout) headView.findViewById(R.id.sport_detail_background_ll_head);
        headMedicineDetailTitleTv = (TextView) headView.findViewById(R.id.sport_detail_title_tv_head);
        headMedicineDetailContentTv = (TextView) headView.findViewById(R.id.sport_detail_content_tv_head);
        headMedicineDetailMindTv = (TextView) headView.findViewById(R.id.sport_detail_mind_tv_head);
        //
        setHeadViewData();
        //添加头部视图
        medicinePlanList.addHeaderView(headView, null, false);
    }

    /**
     * 网络请求获得数据
     */
    private void initData() {
        //initNetData(id);
        // initUpData();
    }
    /**
     * 刷新加载数据
     */
    private void initUpData() {
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                //刷新数据
                initNetData(id);
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //TODO

                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                //加载数据
            }
        });
    }


    /**
     * 通过计划id请求网络数据
     * @param  id
     * */
    private void initNetData(int id) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("id").value(id)//计划Id
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetMedicinePlan(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<MedicinePlanBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(MedicinePlanBean medicinePlanBean) {

                            if (medicinePlanBean.isSuccess()) {
                                Toast.makeText(CnMedicinePlanDetailActivity.this, "成功", Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(CnMedicinePlanDetailActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //listView滑动监听
    public AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            setTitielOffSet(firstVisibleItem);
        }
    };

   /**
    * 设置头部标题栏随ListView滑动的改变状态
    * */
    private void setTitielOffSet(int firstVisibleItem) {
        int distanceY = -(headView.getTop()) - meidicineplanBackRe.getHeight();//y的距离
        //是否是listView的第一个子视图
        if (firstVisibleItem == 0 && medicinePlanList.getChildAt(0) != null) {
            //标题栏和头部视图距离
            if (distanceY >= 0) {
                LogUtil.d("wz", distanceY + "");
                medicineDetailBackImg.setBackgroundResource(R.drawable.selector_icon_nav_back);
                medicineDetailTitleTv.setTextColor(getResources().getColor(R.color.blue));
                int alpha = distanceY;//根据Y的距离控制透明度的变化
                if (alpha > 250) {
                    alpha = 250;
                    planLine.setVisibility(View.VISIBLE);
                } else {
                    meidicineplanBackRe.setVisibility(View.VISIBLE);
                    planLine.setVisibility(View.GONE);
                }
                meidicineplanBackRe.setBackgroundColor(Color.argb(alpha, 250, 250, 250));
            } else {
                meidicineplanBackRe.setVisibility(View.GONE);
                planLine.setVisibility(View.GONE);
                medicineDetailBackImg.setBackgroundResource(R.drawable.selector_icon_plan_back);
                medicineDetailTitleTv.setTextColor(getResources().getColor(R.color.white));
                meidicineplanBackRe.setBackgroundColor(Color.argb(0, 250, 250, 250));
            }

        }
    }

    /**
     * 设置listview头部视图
     * */
    private void setHeadViewData() {
        headMedicineDetailTitleTv.setText(GetResourcesUtils.getString(this,R.string.medicine_plan_title));
        headMedicineDetailContentTv.setText(GetResourcesUtils.getString(this,R.string.medicine_plan_content));
        headMedicineDetailMindTv.setText(GetResourcesUtils.getString(this,R.string.medicine_plan_mind));
        headMedicineDetailBackgroundLl.setBackgroundResource(R.drawable.banner_plan_top_cm);
    }
    /**
     * 返回按钮
     * */
    @OnClick(R.id.medicine_detail_back_ll)
    void medicineDetailBackLl() {
        onBackPressed();
    }


}