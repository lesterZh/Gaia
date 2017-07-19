/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/17 13:04
 * @version V1.0
 */
package com.gaia.member.gaiatt.utils;

import android.content.Context;
import android.graphics.Color;

import com.gaia.member.androidlib.net.bean.SleepRecordBean;
import com.gaia.member.androidlib.net.bean.SportRecordBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.ui.ChartMarkView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName: MpChartUtils
 *Description: 图表工具
 *@author android移动客户端-王浩韩
 * @date 2016/6/17 13:04
 */
public class MpChartUtils {

    /**
     * 设置柱状图属性数据
     * @param  context
     * @param  barChart
     * @param  mBarData
     * */
    public static void setBarChartSet(Context context,BarChart barChart,BarData mBarData,int lineColor,String mUnit,int mColor){
        barChart.setDrawGridBackground(false);
        barChart.setDescription("");// 数据描述
        barChart.setNoDataTextDescription("no data to display"); // 如果没有数据，显示
        barChart.setDrawGridBackground(false); // 是否显示表格颜色
        barChart.setDrawMarkerViews(true);
        barChart.setDrawValueAboveBar(false);
        barChart.setTouchEnabled(true); // 设置是否可以触摸
        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(false);// 是否可以缩放
        barChart.setPinchZoom(false);//
        barChart.setDrawBarShadow(false);
        barChart.zoom(2.0f, 0f, 0f, 0f);
        barChart.setGridBackgroundColor(Color.argb(0, 255, 255, 255));// 表格的的颜色，在这里是是给颜色设置一个透明度
        // 设置数据
        barChart.setData(mBarData);
        Legend l =barChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(0f);

        // 刷新图表
        barChart.invalidate();
        // 折线图的点，点击的布局和数据
        ChartMarkView mv = new ChartMarkView(context,mUnit,mColor);
        mv.getYOffset(10f);
        barChart.setMarkerView(mv);
        // X轴设定
        XAxis xLabels = barChart.getXAxis();
        xLabels.setDrawGridLines(false);
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        xLabels.setTextColor(Color.WHITE);
        xLabels.setTextSize(15);
        xLabels.setAxisLineColor(lineColor);
        xLabels.setSpaceBetweenLabels(3);

        //Y轴设定
        YAxis leftAxis = barChart.getAxisLeft();
        YAxis rightAxis = barChart.getAxisRight();
        leftAxis.setEnabled(false);
        rightAxis.setEnabled(false);
        //barChart.animateY(3000);
        barChart.invalidate();
    }

    /**
     * 运动数据
     * */
    public static LineData getLineData(Context context,LineChart lineChart,List<SportRecordBean.ParamBean.SportListBean> mSportList) {
        //x轴星期
        ArrayList<String> xVals = new ArrayList<String>();
        //x轴日期
        ArrayList<String> xValsDate = new ArrayList<String>();
        //折线图颜色
        ArrayList<Integer> colotList=new ArrayList<>();
        //折线图数据
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        //设置数据源
        for (int i = 0; i < mSportList.size(); i++) {
            xVals.add(getDayForWeek(mSportList.get(i).getDate()));
            xValsDate.add(getCurrentDate(mSportList.get(i).getDate()));
            yVals.add(new Entry(mSportList.get(i).getSteps(), i));
            colotList.add(Color.rgb(210, 105, 30));
        }

        LineDataSet set1 = new LineDataSet(yVals, "");
        set1.setDrawCubic(true);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(false);  //设置有圆点
        set1.setCircleColors(colotList);
        set1.setLineWidth(12f);    //设置线的宽度
        set1.setCircleSize(12f);   //设置小圆的大小
        //set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(context.getResources().getColor(R.color.movement_bar));    //设置曲线的颜色
        //设置初始位置
        if (yVals != null&&yVals.size()>5) {
            float index=yVals.size();
            lineChart.moveViewToX(index-5);
        }
        return new LineData(xVals,xValsDate, set1);
    }
    /**
     * 设置图表数据
     * @param  mSleepList 睡眠数据源
     * */
    public static BarData getBarData(BarChart barChart,List<SleepRecordBean.ParamBean.SleepListBean> mSleepList,int color) {
        //x轴星期
        ArrayList<String> xValues = new ArrayList<String>();
        //x轴日期
        ArrayList<String> xValuesDate = new ArrayList<String>();
        //柱状图颜色
        ArrayList<Integer> mColor = new ArrayList<Integer>();
        //柱状图数据
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        //设置数据源
        for (int i = 0; i < mSleepList.size(); i++) {
            xValues.add(getDayForWeek(mSleepList.get(i).getDate()));
            xValuesDate.add(getCurrentDate(mSleepList.get(i).getDate()));
            yValues.add(new BarEntry(mSleepList.get(i).getSleepTime(), i));
            mColor.add(color);
        }
        //设置数据源数据
        BarDataSet set1;
        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setYVals(yValues);
            barChart.getData().setXVals(xValues);
            //通知更新
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yValues, "");
            //柱状图距离
            set1.setBarSpacePercent(70f);
            set1.setColors(mColor);
            //数据源
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(xValues, xValuesDate, dataSets);
            data.setValueTextSize(10f);
            //设置初始位置
            if (yValues != null && yValues.size() > 5) {
                float index = yValues.size();
                barChart.moveViewToX(index - 5);
            }
            //data.setValueTypeface(mTf);
            return data;
        }
        return null;
    }

    /**
     * 设置折线图属性数据
     * @param  context
     * @param  lineChart
     * @param  mLineData
     * */
    public static void setLineChartSet(Context context,LineChart lineChart,LineData mLineData,int  lineColor){

        /**
         * ====================1.初始化-自由配置===========================
         */
        // 是否在折线图上添加边框
        lineChart.setDrawGridBackground(false);
        // 设置描述
        lineChart.setDescription("");
        //设置透明度
        lineChart.setAlpha(0.8f);
        //设置网格底下的那条线的颜色
        lineChart.setBorderColor(Color.rgb(213, 216, 214));
        //设置高亮显示
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        lineChart.setTouchEnabled(true);
        //设置是否可以拖拽
        lineChart.setDragEnabled(true);
        //设置是否可以缩放
        lineChart.setScaleEnabled(false);
        //设置是否能扩大扩小
        lineChart.setPinchZoom(false);
        lineChart.zoom(2.0f, 0f, 0f, 0f);
        /**
         * ====================2.布局点添加数据-自由布局===========================
         */
        // 折线图的点，点击的布局和数据
        ChartMarkView mv = new ChartMarkView(context,"大卡",context.getResources().getColor(R.color.movement_index));
        lineChart.setMarkerView(mv);
        lineChart.setSelected(true);
        XAxis  xAxis=lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setAxisLineColor(lineColor);
        xAxis.setTextSize(15);
        xAxis.setTextSize(15);
        xAxis.setSpaceBetweenLabels(3);
        // 加载数据
        lineChart.setData(mLineData);
        /**
         * ====================3.x，y动画效果和刷新图表等===========================
         /*    */
        //从X轴进入的动画
        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();
        leftAxis.setEnabled(false);
        rightAxis.setEnabled(false);
        //lineChart.animateX(4000);
        //lineChart.animateY(3000);   //从Y轴进入的动画
        //lineChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
        //设置最小的缩放
        lineChart.setScaleMinima(0.5f, 1f);
        Legend l = lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(0f);
        // 刷新图表
        lineChart.invalidate();
    }


    /**
     * 判断当前日期是星期几<br>
     * <br>
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static String getDayForWeek(String pTime)  {

        //计算日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        //每周的第一天为周日
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }

        //获取中文星期
        String dayForWeekStr=null;
        switch (dayForWeek){
            case 1:
                dayForWeekStr="一";
                break;
            case 2:
                dayForWeekStr="二";
                break;
            case 3:
                dayForWeekStr="三";
                break;
            case 4:
                dayForWeekStr="四";
                break;
            case 5:
                dayForWeekStr="五";
                break;
            case 6:
                dayForWeekStr="六";
                break;
            case 7:
                dayForWeekStr="日";
                break;
        }
        return dayForWeekStr;
    }

    /**
     * 获取日期列表的具体日期号
     * @param dateString 2016-06-22
     *                   return 22
     * */
    public static String getCurrentDate(String dateString){
        String[] dates=dateString.split("\\-");
        return dates[2];
    }
}
