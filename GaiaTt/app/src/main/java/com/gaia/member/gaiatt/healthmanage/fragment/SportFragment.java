package com.gaia.member.gaiatt.healthmanage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gaia.member.androidlib.net.bean.SportRecordBean;
import com.gaia.member.androidlib.net.bean.SportRecordBean.ParamBean.SportListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.MpChartUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: SportFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 运动折现图fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class SportFragment extends Fragment {
    //图表
    @Bind(R.id.lineChart)
    LineChart lineChart;
    //步数
    @Bind(R.id.tv_sport_steps)
    TextView tvSportSteps;
    //距离
    @Bind(R.id.tv_sport_distance)
    TextView tvSportDistance;
    //时间
    @Bind(R.id.tv_sport_time)
    TextView tvSportTime;
    //消耗
    @Bind(R.id.tv_sport_spend)
    TextView tvSportSpend;
    private View view;//fragment视图
    private PopupWindow popupWindow;//图表弹窗

    //当前日期
    private int dayIndex;
    //当前下标
    int currentIndex;
    //数据列表
    List<SportListBean> sportList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sport_fragment, container, false);
        ButterKnife.bind(this, view);
        initTime();//获取当前时间
        initChart();
        setChartListener();
        return view;
    }

    /**
     * 获取当前日期
     */
    private void initTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        dayIndex = c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 1.初始化LineChart
     * 2.添加数据x，y轴数据
     * 3.刷新图表
     */
    private void initChart() {
        //获取本地数据
        sportList=getLocationData();
        MpChartUtils.setLineChartSet(getActivity(),lineChart,MpChartUtils.getLineData(getActivity(), lineChart,
                        sportList),
                getResources().getColor(R.color.movement_line_a));
    }

    /**
     * 获取本期json数据
     */
    private List<SportListBean> getLocationData() {
        //获取assets文件目录下的String数据
        String sportJsonString = AssetsFileUtil.getJsonStr(getActivity(), "sport.txt");
        List<SportRecordBean.ParamBean.SportListBean> mList = new ArrayList<>();
        if (sportJsonString != null) {
            //解析json数据
            SportRecordBean sportRecordBean = GsonTools.getSportRecordBean(sportJsonString);
            mList = sportRecordBean.getParam().getSportList();
        }
        return mList;
    }


    /**
     * 监听折线图事件
     * */
    private void setChartListener() {

        //选中监听
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                setSelectData(e);//选中子选项后的操作处理
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    /**
     * 选中子选项后的操作处理
     * @param  e  选中数据
     * */
    private void setSelectData(Entry e) {
        int currentIndex=e.getXIndex();
        //如果当前选中下表大于3移动item到currentIndex - 3
        if (currentIndex >3) {
            lineChart.moveViewToX(currentIndex-3);
        }else {
            lineChart.moveViewToX(-1);
        }
        //每日运动对象
        SportListBean sportListBean = sportList.get(currentIndex);
        //设置步数
        tvSportSteps.setText(sportListBean.getSteps()+"步");
        //距离
        tvSportDistance.setText(sportListBean.getKilometre()+"公里");
        //活动时间
        tvSportTime.setText(sportListBean.getMinute()+"分钟");
        //活动消耗
        tvSportSpend.setText(sportListBean.getCalorie()+"大卡");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
