package com.gaia.member.gaiatt.healthmanage.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import com.gaia.member.androidlib.net.bean.DietPlanBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.DietListViewAdapter;
import com.gaia.member.gaiatt.healthmanage.fragment.MealFragment;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.DietListBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.MealBean;
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
 * @Title: DietPlanDetailActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 饮食计划干预活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class DietPlanDetailActivity extends AppBaseActivity {


    //计划详情列表
    @Bind(R.id.diet_plan_listview)
    PullOrLoadListView dietPlanListview;

    //选择器布局
    @Bind(R.id.diet_dialog_layout)
    LinearLayout dietDialogLayout;
    //返回按钮图标
    @Bind(R.id.diet_detail_back_img)
    ImageView dietDetailBackImg;
    //标题TextView
    @Bind(R.id.diet_detail_title_tv)
    TextView dietDetailTitleTv;
    //返回按钮
    @Bind(R.id.dietplan_back_re)
    RelativeLayout dietplanBackRe;
    //标题分割线
    @Bind(R.id.plan_line)
    View planLine;
    //刷新加载
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    //适配器
    private DietListViewAdapter dietListViewAdapter;
    //列表数据源
    private List<DietListBean> dietList;
    //头部返回按钮
    private LinearLayout headDietDetailBackLl;
    //头部背景
    private LinearLayout headDietDetailBackgroundLl;
    //头部标题
    private TextView headDietDetailTitleTv;
    //头部内容
    private TextView headDietDetailContentTv;
    //头部提醒
    private TextView headDietDetailMindTv;
    //头部视图
    private View headView;
    //计划ID
    private int id;


    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_diet_plan_detail);
        ButterKnife.bind(this);
        //获取计划Id
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        dietList = new ArrayList<DietListBean>();
    }

    @Override
    protected void loadData() {
        //头部视图控件
        initHeadView();
        dietListViewAdapter = new DietListViewAdapter(DietPlanDetailActivity.this, (ArrayList<DietListBean>) dietList);
        dietPlanListview.setAdapter(dietListViewAdapter);
        dietPlanListview.setOnScrollListener(mOnScrollListener);
        headDietDetailBackLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initData();
    }

    /**
     * dietDialogLayout  Get方法
     */
    public LinearLayout getDietDialogLayout() {
        return dietDialogLayout;
    }

    /**
     * 初始化头部视图
     */
    private void initHeadView() {
        headView = getLayoutInflater().inflate(R.layout.sport_plan_detail_headview, null);
        headDietDetailBackLl = (LinearLayout) headView.findViewById(R.id.sport_detail_back_ll_head);
        headDietDetailBackgroundLl = (LinearLayout) headView.findViewById(R.id.sport_detail_background_ll_head);
        headDietDetailTitleTv = (TextView) headView.findViewById(R.id.sport_detail_title_tv_head);
        headDietDetailContentTv = (TextView) headView.findViewById(R.id.sport_detail_content_tv_head);
        headDietDetailMindTv = (TextView) headView.findViewById(R.id.sport_detail_mind_tv_head);
        setHeadViewData();
        dietPlanListview.addHeaderView(headView, null, false);
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
     *
     * @param id
     */
    private void initNetData(int id) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("id").value(id)//计划Id
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetDietPlan(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DietPlanBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(DietPlanBean dietPlanBean) {
                            if (dietPlanBean.isSuccess()) {
                                Toast.makeText(DietPlanDetailActivity.this, "成功", Toast.LENGTH_LONG).show();
                                dietListViewAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(DietPlanDetailActivity.this, "失败", Toast.LENGTH_LONG).show();
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
     * @param firstVisibleItem
     */
    private void setTitielOffSet(int firstVisibleItem) {
        int distanceY = -(headView.getTop()) - dietplanBackRe.getHeight();//y的距离
        //是否是listView的第一个子视图
        if (firstVisibleItem == 0 && dietPlanListview.getChildAt(0) != null) {
            if (distanceY >= 0) {
                dietDetailBackImg.setBackgroundResource(R.drawable.selector_icon_nav_back);
                dietDetailTitleTv.setTextColor(getResources().getColor(R.color.blue));
                int alpha = distanceY;//根据Y的距离控制透明度的变化
                if (alpha > 250) {
                    alpha = 250;
                    planLine.setVisibility(View.VISIBLE);
                } else {
                    dietplanBackRe.setVisibility(View.VISIBLE);
                    planLine.setVisibility(View.GONE);
                }
                dietplanBackRe.setBackgroundColor(Color.argb(alpha, 250, 250, 250));
            } else {
                dietplanBackRe.setVisibility(View.GONE);
                planLine.setVisibility(View.GONE);
                dietDetailBackImg.setBackgroundResource(R.drawable.selector_icon_plan_back);
                dietDetailTitleTv.setTextColor(getResources().getColor(R.color.white));
                dietplanBackRe.setBackgroundColor(Color.argb(0, 250, 250, 250));
            }

        }
    }

    //设置头部视图View
    private void setHeadViewData() {
        headDietDetailTitleTv.setText(GetResourcesUtils.getString(this, R.string.diet_plan_title));
        headDietDetailContentTv.setText(GetResourcesUtils.getString(this, R.string.diet_plan_content));
        headDietDetailMindTv.setText(GetResourcesUtils.getString(this, R.string.diet_plan_mind));
        headDietDetailBackgroundLl.setBackgroundResource(R.drawable.banner_plan_top_diabetes);
    }

    /**
     * 返回
     */
    @OnClick(R.id.diet_detail_back_ll)
    void dietDetailBackLl() {
        onBackPressed();
    }

    /**
     * 饮食选择器
     **/
    private MealFragment mealFragment;
    @OnClick(R.id.diet_detail_edit)
    public void onClick() {
        if (mealFragment == null) {//判断对象是否为空
            mealFragment = new MealFragment();
            mealFragment.setDietHandler(mHandler);
        }
        dietDialogLayout.setVisibility(View.VISIBLE);//显示选择器
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.bottom_in, 0);//显示动画
        transaction.replace(R.id.ll_diet_dialog, mealFragment);
        transaction.commit();
    }

    /**
     * 获取选择器数据，通过消息发送
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MealBean mealBean = (MealBean) msg.obj;

        }
    };


}
