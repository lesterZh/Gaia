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
import com.gaia.member.androidlib.net.bean.BaseBean;
import com.gaia.member.androidlib.net.bean.SmokePlanBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.SmokeListViewAdapter;
import com.gaia.member.gaiatt.healthmanage.utils.LocalDataUtils;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.PickerDialogUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.SportListBean;
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
 * @Title: SmokePlanDetailActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 吸烟干预计划详情活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class SmokePlanDetailActivity extends AppBaseActivity {

    //计划详情列表
    @Bind(R.id.smoke_plan_list)
    PullOrLoadListView smokePlanList;
    //标题TextView
    @Bind(R.id.smoke_detail_title_tv)
    TextView smokeDetailTitleTv;
    //返回按钮
    @Bind(R.id.smokeplan_back_re)
    RelativeLayout smokeplanBackRe;
    //返回按钮图标
    @Bind(R.id.smoke_detail_back_img)
    ImageView smokeDetailBackImg;
    //标题分割线
    @Bind(R.id.plan_line)
    View planLine;
    //刷新加载
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;


    //适配器
    private SmokeListViewAdapter adapter;
    //数据源
    private List<SportListBean> listSport;
    //头部返回按钮
    private LinearLayout headSmokeDetailBackLl;
    //头部背景
    private LinearLayout headSmokeDetailBackgroundLl;
    //头部返回按钮
    private RelativeLayout headSmokeplanBackRe;
    //头部标题//头部标题
    private TextView headSmokeHeadDetailTitleTv;
    //头部内容
    private TextView headSmokeDetailContentTv;
    //头部提醒
    private TextView headSmokeDetailMindTv;
    //头部视图
    private View headView;
    //计划ID
    private int id;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_smoke_plan_detail);
        ButterKnife.bind(this);
        //获取计划Id
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        listSport = new ArrayList<SportListBean>();
    }

    @Override
    protected void loadData() {
        initHeadView();
        adapter = new SmokeListViewAdapter(SmokePlanDetailActivity.this, (ArrayList<SportListBean>) listSport);
        smokePlanList.setAdapter(adapter);
        smokePlanList.setOnScrollListener(mOnScrollListener);
        headSmokeDetailBackLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initData();
    }

    /**
     * 设置ListView 头部视图
     */
    private void initHeadView() {
        headView = getLayoutInflater().inflate(R.layout.sport_plan_detail_headview, null);
        headSmokeDetailBackLl = (LinearLayout) headView.findViewById(R.id.sport_detail_back_ll_head);
        headSmokeplanBackRe = (RelativeLayout) headView.findViewById(R.id.sportplan_back_re_head);
        headSmokeDetailBackgroundLl = (LinearLayout) headView.findViewById(R.id.sport_detail_background_ll_head);
        headSmokeHeadDetailTitleTv = (TextView) headView.findViewById(R.id.sport_detail_title_tv_head);
        headSmokeDetailContentTv = (TextView) headView.findViewById(R.id.sport_detail_content_tv_head);
        headSmokeDetailMindTv = (TextView) headView.findViewById(R.id.sport_detail_mind_tv_head);
        setHeadViewData();
        smokePlanList.addHeaderView(headView, null, false);
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
     * 初始化数据网络请求
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
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetSmokePlan(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<SmokePlanBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(SmokePlanBean smokePlanBean) {
                            if (smokePlanBean.isSuccess()) {
                                Toast.makeText(SmokePlanDetailActivity.this, "成功", Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(SmokePlanDetailActivity.this, "失败", Toast.LENGTH_LONG).show();
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
     * 设置标题栏的滑动渐变
     *
     * @param firstVisibleItem
     */
    private void setTitielOffSet(int firstVisibleItem) {
        int distanceY = -(headView.getTop()) - smokeplanBackRe.getHeight();//y的距离
        //是否是listView的第一个子视图
        if (firstVisibleItem == 0 && smokePlanList.getChildAt(0) != null) {
            if (distanceY >= 0) {
                smokeDetailBackImg.setBackgroundResource(R.drawable.selector_icon_nav_back);
                smokeDetailTitleTv.setTextColor(getResources().getColor(R.color.blue));
                int alpha = distanceY;//根据Y的距离控制透明度的变化
                if (alpha > 250) {
                    alpha = 250;
                    planLine.setVisibility(View.VISIBLE);
                } else {
                    smokeplanBackRe.setVisibility(View.VISIBLE);
                    planLine.setVisibility(View.GONE);
                }
                smokeplanBackRe.setBackgroundColor(Color.argb(alpha, 250, 250, 250));
            } else {
                smokeplanBackRe.setVisibility(View.GONE);
                planLine.setVisibility(View.GONE);
                smokeDetailBackImg.setBackgroundResource(R.drawable.selector_icon_plan_back);
                smokeDetailTitleTv.setTextColor(getResources().getColor(R.color.white));
                smokeplanBackRe.setBackgroundColor(Color.argb(0, 250, 250, 250));
            }

        }
    }

    //设置详情头部文案
    private void setHeadViewData() {
        headSmokeHeadDetailTitleTv.setText(GetResourcesUtils.getString(this, R.string.smoke_plan_title));
        headSmokeDetailContentTv.setText(GetResourcesUtils.getString(this, R.string.smoke_plan_content));
        headSmokeDetailMindTv.setText(GetResourcesUtils.getString(this, R.string.smoke_plan_mind));
        headSmokeDetailBackgroundLl.setBackgroundResource(R.drawable.banner_plan_top_smoking);
    }

    /**
     * 返回按钮
     * */
    @OnClick(R.id.smoke_detail_back_ll)
    void smokeDetailBackLl() {
        onBackPressed();
    }



    /**
     * 吸烟选择器
     * */
    @OnClick(R.id.smoke_detail_edit)
    public void onClick() {
        PickerDialogUtils pickerDialogUtils=new PickerDialogUtils();
        pickerDialogUtils.setSmokePickerDialog(this, GetResourcesUtils.getString(this,R.string.smoke_dialog_title), LocalDataUtils.getSmokeNumArrayList(), 5, new
                PickerDialogUtils.OnDialogClickListener() {

                    @Override
                    public void okDialogClick(String text1, String text2) {
                        uploadData(text1);//当日吸烟数据更新
                    }
                });
    }

    /**
     * 上传数据
     * @param text1 吸烟根数
     * */
    private void uploadData(String text1) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("id").value(id)//计划Id
                    .key("missionId").value(id)//子项目任务id
                    .key("count").value("")//数量（根/ml）
                    .key("level").value("")//自我感知等级
                    .key("attrs").value("")//其他参数（如 黄酒的id/10ml）
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postPutSmokePlan(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseBean>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(BaseBean baseBean) {
                            if (baseBean.isSuccess()) {
                                Toast.makeText(SmokePlanDetailActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SmokePlanDetailActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
