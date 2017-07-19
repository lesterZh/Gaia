/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: SportPlanDetailActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 运动干预计划详情活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
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
import com.gaia.member.androidlib.net.bean.SportPlanBean;
import com.gaia.member.androidlib.net.bean.SportPlanBean.ParamBean;
import com.gaia.member.androidlib.net.bean.SportPlanBean.ParamBean.ListBean;
import com.gaia.member.androidlib.utils.LogUtil;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.SportListViewAdapter;
import com.gaia.member.gaiatt.healthmanage.utils.LocalDataUtils;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.PickerDialogUtils;
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
 * @ClassName: SportPlanDetailActivity
 *Description: 运动干预计划详情活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class SportPlanDetailActivity extends AppBaseActivity {


    //计划详情列表
    @Bind(R.id.sport_plan_list)
    PullOrLoadListView sportPlanList;
    //返回按钮图标
    @Bind(R.id.sport_detail_back_img)
    ImageView sportDetailBackImg;
    //标题TextView
    @Bind(R.id.sport_detail_title_tv)
    TextView sportDetailTitleTv;
    //返回按钮
    @Bind(R.id.sportplan_back_re)
    RelativeLayout sportplanBackRe;
    //标题分割线
    @Bind(R.id.plan_line)
    View planLine;
    //刷新加载
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;

    //适配器
    private SportListViewAdapter adapter;
    //数据源
    private List<ListBean> listSport;
    private ParamBean paramBean;
    //头部返回按钮
    private LinearLayout headSportDetailBackLl;
    //头部背景
    private LinearLayout headSportDetailBackgroundLl;
    //头部标题
    private TextView headSportDetailTitleTv;
    //头部提醒
    private TextView headSportDetailMindTv;
    //头部内容
    private TextView headSportDetailContentTv;
    //头部视图
    private View headView;
    //计划ID
    private int id;
    private  SportPlanBean sportPlanBean;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_sport_plan_detail);
        ButterKnife.bind(this);
        //获取计划Id
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void loadData() {
        //初始化数据
        initData();
        //设置标题
        sportDetailTitleTv.setText(paramBean.getTitle());
        //设置头部视图
        initHeadView();
        //适配数据
        adapter = new SportListViewAdapter(SportPlanDetailActivity.this, (ArrayList<ListBean>) listSport,paramBean);
        sportPlanList.setAdapter(adapter);
        //滑动监听
        sportPlanList.setOnScrollListener(mOnScrollListener);
        //返回按钮
        headSportDetailBackLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 设置头部视图
     */
    private void initHeadView() {
        //头部视图
        headView = getLayoutInflater().inflate(R.layout.sport_plan_detail_headview, null);
        //返回按钮
        headSportDetailBackLl = (LinearLayout) headView.findViewById(R.id.sport_detail_back_ll_head);
        //背景图片
        headSportDetailBackgroundLl = (LinearLayout) headView.findViewById(R.id.sport_detail_background_ll_head);
        //头部标题
        headSportDetailTitleTv = (TextView) headView.findViewById(R.id.sport_detail_title_tv_head);
        //头部内容
        headSportDetailContentTv = (TextView) headView.findViewById(R.id.sport_detail_content_tv_head);
        //头部注意事项
        headSportDetailMindTv = (TextView) headView.findViewById(R.id.sport_detail_mind_tv_head);
        //设置头部数据
        setHeadViewData();
        //添加头部
        sportPlanList.addHeaderView(headView, null, false);
    }

    /**
     * 网络请求获得数据
     */
    private void initData() {
        //获取本地数据
        sportPlanBean=getLocalplanData();
        paramBean=sportPlanBean.getParam();
        listSport=sportPlanBean.getParam().getList();
        //initNetData(id);
        initUpData();
    }

    /**
     * 刷新加载数据
     */
    private void initUpData() {
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                //刷新数据
                //initNetData(id);
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
                //加载数据
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
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetSportPlan(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<SportPlanBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(SportPlanBean sportPlanBean) {
                            if (sportPlanBean.isSuccess()) {
                                Toast.makeText(SportPlanDetailActivity.this, "成功", Toast.LENGTH_LONG).show();
                                setServiceData(sportPlanBean);//设置数据到控件
                            } else {
                                Toast.makeText(SportPlanDetailActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置获取数据
     * @param  sportPlanBean  运动详情
     * */
    private void setServiceData(SportPlanBean sportPlanBean) {
        paramBean=sportPlanBean.getParam();
        //获取列表数据
        List<ListBean> sportList=paramBean.getList();
        for (int i = 0; i <sportList.size() ; i++) {
            listSport.add(sportList.get(i));
        }
        //通知适配器更新数据
        adapter.notifyDataSetChanged();
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

    /***
     * 设置标题栏随listView滚动
     *   的变化状态
     *   @param  firstVisibleItem  看见的第一个子视图
     * */
    private void setTitielOffSet(int firstVisibleItem) {
        int distanceY = -(headView.getTop()) - sportplanBackRe.getHeight();//y的距离
        //是否是listView的第一个子视图
        if (firstVisibleItem == 0 && sportPlanList.getChildAt(0) != null) {
            //顶部距离判断
            if (distanceY >= 0) {
                LogUtil.d("wz", distanceY + "");
                sportDetailBackImg.setBackgroundResource(R.drawable.selector_icon_nav_back);
                sportDetailTitleTv.setTextColor(getResources().getColor(R.color.blue));
                int alpha = distanceY;//根据Y的距离控制透明度的变化
                if (alpha > 250) {//最高色值250
                    alpha = 250;
                    planLine.setVisibility(View.VISIBLE);//分割线显示
                } else {
                    sportplanBackRe.setVisibility(View.VISIBLE);//返回按钮显示
                    planLine.setVisibility(View.GONE);//分割线隐藏
                }
                sportplanBackRe.setBackgroundColor(Color.argb(alpha, 250, 250, 250));//设置透明度
            } else {
                sportplanBackRe.setVisibility(View.GONE);//隐藏返回按钮
                planLine.setVisibility(View.GONE);//隐藏标题栏分割线
                sportDetailBackImg.setBackgroundResource(R.drawable.selector_icon_plan_back);//设置标题栏返回图标
                sportDetailTitleTv.setTextColor(getResources().getColor(R.color.white));//设置标题字体颜色
                sportplanBackRe.setBackgroundColor(Color.argb(0, 250, 250, 250));//设置标题栏背景透明度变化
            }

        }
    }

    //设置详情头部文案
    private void setHeadViewData() {
        ParamBean paramBean=sportPlanBean.getParam();
        //设置标题
        headSportDetailTitleTv.setText(paramBean.getTitle());
        //设置内容
        headSportDetailContentTv.setText(paramBean.getDetail());
        //设置注意
        headSportDetailMindTv.setText(paramBean.getNoitce());
        //设置背景图片
        headSportDetailBackgroundLl.setBackgroundResource(R.drawable.banner_plan_top_sports);
    }

    /**
     * 获取assets文件路径下的本地文件
     */
    private SportPlanBean getLocalplanData() {
        //获取本地assets文件目录json数据
        String sportJsonString = AssetsFileUtil.getJsonStr(this, "sportplandetail.txt");
        SportPlanBean sportPlanBean = null;
        if (sportJsonString != null) {
            //解析json数据
            sportPlanBean = GsonTools.getSportPlanBean(sportJsonString);
        }
        return sportPlanBean;
    }
    /**
     * 返回
     * */
    @OnClick(R.id.sport_detail_back_ll)
    void sportDetailBackLl() {
        onBackPressed();
    }

    /**
     * 点击调用运动选择器
     * */
    @OnClick(R.id.sport_detail_edit)
    public void onClick() {
        //初始化运动感知弹窗
        PickerDialogUtils pickerDialogUtils=new PickerDialogUtils();
        //设置回调参数
        pickerDialogUtils.setSinglePickerDialog(this, GetResourcesUtils.getString(this,R.string.sport_dialog_title), LocalDataUtils.getSportArrayList(this), 1, new
                PickerDialogUtils.OnDialogClickListener() {
                    @Override
                    public void okDialogClick(String text1, String text2) {
                        int level=0;
                        switch (text1){
                            case CommonConstants.RELAXE://轻松
                                level=1;
                                break;
                            case CommonConstants.MODERATE://适中
                                level=2;
                                break;
                            case CommonConstants.SWEATY://吃力
                                level=3;
                                break;
                        }
                        //submitSportLevel(level);//提交感知强度
                    }
                });
    }

    /**
     * 提交运动感知强度
     *
     * @param level*/
    private void submitSportLevel(int level) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("id").value(id)//计划Id
                    .key("date").value("日期")//计划日期
                    .key("level").value(level)//感知强度
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postPutSportPlan(body)
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
                                Toast.makeText(SportPlanDetailActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SportPlanDetailActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
