/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthToolsActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 健康工具列表活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.HealthToolsListBean;
import com.gaia.member.androidlib.net.bean.HealthToolsListBean.ParamBean.ListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.healthmanage.adapter.HealthToolsListViewAdapter;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.ui.PullToRefreshLayout;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
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
 * @ClassName: HealthToolsActivity
 *Description: 健康工具列表活动界面
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthToolsActivity extends AppBaseActivity {

    //标题
    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    //工具列表
    @Bind(R.id.health_tools_listview)
    PullOrLoadListView healthToolsListview;
    //标题栏右边图标
    @Bind(R.id.titlebar_search_img)
    ImageView titlebarSearchImg;
    //上拉、下拉控件
    @Bind(R.id.pull_to_refresh_layout)
    PullToRefreshLayout pullToRefreshLayout;
    //工具适配器
    private HealthToolsListViewAdapter healthToolsListViewAdapter;
    //初始页
    private final int INITCURRENTPAGE = 1;
    //当前页
    private int currentPage = 1;
    //当前页的数量
    private int PageSize = 20;
    List<ListBean> toolsList;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_health_tools);
        //绑定控件
        ButterKnife.bind(this);

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //设置标题
        titlebarTv.setText(GetResourcesUtils.getString(this, R.string.health_tools_title));
        //隐藏标题栏右边图标
        titlebarSearchImg.setVisibility(View.GONE);
    }

    @Override
    protected void loadData() {
        //获取本地数据源
        toolsList = getLocalToolsData();
        //适配数据
        healthToolsListViewAdapter = new HealthToolsListViewAdapter(this, (ArrayList<ListBean>) toolsList);
        healthToolsListview.setAdapter(healthToolsListViewAdapter);
        //工具子选项点击事件监听
        healthToolsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //界面跳转传递URL
                HealthToolsListBean.ParamBean.ListBean dataBean = (ListBean) parent.getItemAtPosition(position);
                Intent intent = new Intent(HealthToolsActivity.this, BmiActivity.class);
                intent.putExtra("linkUrl", dataBean.getLinkUrl());
                startActivity(intent);
            }
        });
        //获取网络健康工具列表信息
        //initData(INITCURRENTPAGE);
        //更新工具列表
        upData();
    }

    /**
     * 刷新工具列表
     * */
    private void upData() {
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                //下拉刷新
                //initData(INITCURRENTPAGE);
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
                //上拉加载
                //initData(currentPage);
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
     * 获取网络健康工具列表信息
     *
     * @param page
     */
    private void initData(int page) {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("currentPage").value(page)//当前页
                    .key("pageSize").value(PageSize)//当前页的数量
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postGetHealthToolsList(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<HealthToolsListBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(HealthToolsListBean healthToolsListBean) {
                            if (healthToolsListBean.isSuccess()) {
                                currentPage++;
                                //列表
                                List<ListBean> dataList=healthToolsListBean.getParam().getList();
                                //获取服务器数据更新数据
                                if (dataList != null) {
                                    for (int i = 0; i <dataList.size() ; i++) {
                                        toolsList.add(dataList.get(i));
                                    }
                                }
                                //刷新适配器更新数据
                                healthToolsListViewAdapter.notifyDataSetChanged();
                                Toast.makeText(HealthToolsActivity.this, "成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(HealthToolsActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //返回
    @OnClick(R.id.titlebar_back_ll)
    void toolsBack() {
        onBackPressed();
    }

    /**
     * 获取assets文件路径下的本地文件
     */
    private List getLocalToolsData() {
        //获取assets文件目录下的String数据
        String toolsJsonString = AssetsFileUtil.getJsonStr(this, "healthtools.txt");
        List<ListBean> mList = new ArrayList<>();
        if (toolsJsonString != null) {
            //解析json数据
            HealthToolsListBean healthToolsParamBean = GsonTools.getHealthToolsParamBean(toolsJsonString);
            mList = healthToolsParamBean.getParam().getList();
        }
        return mList;
    }

}
