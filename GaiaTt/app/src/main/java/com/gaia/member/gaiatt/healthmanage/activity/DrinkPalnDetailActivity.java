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
import com.gaia.member.androidlib.net.bean.DrinkPlanBean;
import com.gaia.member.androidlib.net.bean.DrinkPlanBean.ParamBean.ListBean.AttrsBean;
import com.gaia.member.androidlib.utils.LogUtil;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.DrinkListViewAdapter;
import com.gaia.member.gaiatt.healthmanage.utils.LocalDataUtils;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.PickerDialogUtils;
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

;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: DrinkPalnDetailActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 饮酒计划干预活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class DrinkPalnDetailActivity extends AppBaseActivity {

    //计划详情列表
    @Bind(R.id.drink_plan_listview)
    PullOrLoadListView drinkPlanListview;
    //返回按钮图标
    @Bind(R.id.drink_detail_back_img)
    ImageView drinkDetailBackImg;
    //标题TextView
    @Bind(R.id.drink_detail_title_tv)
    TextView drinkDetailTitleTv;
    //返回按钮
    @Bind(R.id.drinkplan_back_re)
    RelativeLayout drinkplanBackRe;
    //标题分割线
    @Bind(R.id.plan_line)
    View planLine;
    //刷新加载
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;


    //适配器
    private DrinkListViewAdapter drinkListViewAdapter;//列表适配器
    private List<AttrsBean> drinkList;//数据源
    //头部返回按钮
    private LinearLayout headDrinkDetailBackLl;
    //头部背景
    private LinearLayout headDrinkDetailBackgroundLl;
    //头部标题
    private TextView headDrinkDetailTitleTv;
    //头部内容
    private TextView headDrinkDetailContentTv;
    //头部提醒
    private TextView headDrinkDetailMindTv;
    //头部视图
    private View headView;
    //计划ID
    private int id;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_drink_paln_detail);
        ButterKnife.bind(this);
        //获取计划Id
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

        //设置头部控件视图
        initHeadView();
        drinkListViewAdapter = new DrinkListViewAdapter(DrinkPalnDetailActivity.this, (ArrayList<AttrsBean>)
                drinkList);
        drinkPlanListview.setAdapter(drinkListViewAdapter);
        drinkPlanListview.setOnScrollListener(mOnScrollListener);
        headDrinkDetailBackLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initData();
    }

    /**
     * 设置头部控件视图
     */
    private void initHeadView() {
        headView = getLayoutInflater().inflate(R.layout.sport_plan_detail_headview, null);
        headDrinkDetailBackLl = (LinearLayout) headView.findViewById(R.id.sport_detail_back_ll_head);
        headDrinkDetailBackgroundLl = (LinearLayout) headView.findViewById(R.id.sport_detail_background_ll_head);
        headDrinkDetailTitleTv = (TextView) headView.findViewById(R.id.sport_detail_title_tv_head);
        headDrinkDetailContentTv = (TextView) headView.findViewById(R.id.sport_detail_content_tv_head);
        headDrinkDetailMindTv = (TextView) headView.findViewById(R.id.sport_detail_mind_tv_head);
        setHeadViewData();
        drinkPlanListview.addHeaderView(headView, null, false);
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
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetDrinkPlan(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DrinkPlanBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(DrinkPlanBean drinkPlanBean) {
                            if (drinkPlanBean.isSuccess()) {
                                Toast.makeText(DrinkPalnDetailActivity.this, "成功", Toast.LENGTH_LONG).show();
                                drinkListViewAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(DrinkPalnDetailActivity.this, "失败", Toast.LENGTH_LONG).show();
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

    private void setTitielOffSet(int firstVisibleItem) {
        int distanceY = -(headView.getTop()) - drinkplanBackRe.getHeight();//y的距离
        //是否是listView的第一个子视图
        if (firstVisibleItem == 0 && drinkPlanListview.getChildAt(0) != null) {
            if (distanceY >= 0) {
                LogUtil.d("wz", distanceY + "");
                drinkDetailBackImg.setBackgroundResource(R.drawable.selector_icon_nav_back);
                drinkDetailTitleTv.setTextColor(getResources().getColor(R.color.blue));
                int alpha = distanceY;//根据Y的距离控制透明度的变化
                if (alpha > 250) {
                    alpha = 250;
                    planLine.setVisibility(View.VISIBLE);
                } else {
                    drinkplanBackRe.setVisibility(View.VISIBLE);
                    planLine.setVisibility(View.GONE);
                }
                drinkplanBackRe.setBackgroundColor(Color.argb(alpha, 250, 250, 250));
            } else {
                drinkplanBackRe.setVisibility(View.GONE);
                planLine.setVisibility(View.GONE);
                drinkDetailBackImg.setBackgroundResource(R.drawable.selector_icon_plan_back);
                drinkDetailTitleTv.setTextColor(getResources().getColor(R.color.white));
                drinkplanBackRe.setBackgroundColor(Color.argb(0, 250, 250, 250));
            }

        }
    }

    /**
     * 头部文案设置
     */
    private void setHeadViewData() {
        headDrinkDetailTitleTv.setText(GetResourcesUtils.getString(this, R.string.drink_plan_title));
        headDrinkDetailContentTv.setText(GetResourcesUtils.getString(this, R.string.drink_plan_content));
        headDrinkDetailMindTv.setText(GetResourcesUtils.getString(this, R.string.drink_plan_mind));
        headDrinkDetailBackgroundLl.setBackgroundResource(R.drawable.banner_plan_top_drink);
    }

    /**
     * 返回按钮
     * */
    @OnClick(R.id.drink_detail_back_ll)
    void drinkDetailBackLl() {
        onBackPressed();
    }
    /**
     * 饮酒选择器
     * */
    @OnClick(R.id.drink_detail_edit)
    public void onClick() {
        PickerDialogUtils pickerDialogUtils=new PickerDialogUtils();
        pickerDialogUtils.setDoublePickerDialog(this, getString( R.string.drink_dialog_title),
                getString(R.string.drink_dialog_content1),
                getString(R.string.drink_dialog_content2),
                LocalDataUtils.getDrinkTypeArrayList(this), LocalDataUtils.getDrinkNumArrayList(), 3,
                new PickerDialogUtils.OnDialogClickListener() {
                    @Override
                    public void okDialogClick(String text1, String text2) {

                    }
                });
    }
}
