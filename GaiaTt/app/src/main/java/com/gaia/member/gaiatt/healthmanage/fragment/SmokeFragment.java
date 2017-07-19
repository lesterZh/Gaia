package com.gaia.member.gaiatt.healthmanage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.utils.LocalDataUtils;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.MpChartUtils;
import com.gaia.member.gaiatt.utils.PickerDialogUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: SmokeFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 吸烟柱状图Fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class SmokeFragment extends Fragment {
    @Bind(R.id.barChart)
    BarChart barChart;
    @Bind(R.id.add_img)
    ImageView addImg;
    @Bind(R.id.tv_smoke_number)
    TextView tvSmokeNumber;
    private View view;

    private PopupWindow popupWindow;
    private int weekIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.smoke_fragment, container, false);
        ButterKnife.bind(this, view);
        initTime();
        initBarChart();
        setChartListener();
        return view;
    }


    /**
     * 获取当前时间
     * **/
    private void initTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        weekIndex = c.get(Calendar.DAY_OF_WEEK) - 2;//西方从周日到周六

    }

    /**
     * 初始化图表数据
     * */
    private void initBarChart() {
        //设置柱状图数据与属性
        MpChartUtils.setBarChartSet(getActivity(),barChart
                ,getBarData(stringBar.length, yy.length)
                ,getResources().getColor(R.color.smoke_line_a),
               "支",getActivity().getResources().getColor(R.color.smoke_index_r) );
        //添加睡眠时间
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* int count = 1;
                yy[weekIndex] = yy[weekIndex] + count;
                if (yy[weekIndex] <= 90) {
                    BarData mBarData = getBarData(7);
                    barChart.upDayOfWeek(mBarData);
                }*/
                PickerDialogUtils pickerDialogUtils=new PickerDialogUtils();
                pickerDialogUtils.setSmokePickerDialog(getActivity(), GetResourcesUtils.getString(getActivity(),R.string.smoke_dialog_title), LocalDataUtils.getSmokeNumArrayList(), 5, new
                        PickerDialogUtils.OnDialogClickListener() {

                            @Override
                            public void okDialogClick(String text1, String text2) {
                                tvSmokeNumber.setText(text1);
                            }
                        });
            }
        });

    }

    int[] yy = {60, 40, 80, 50, 70, 10, 50, 60, 40, 80, 50, 70, 10};
    String[] stringBar = {"一", "二", "三", "四", "五", "六", "日", "一", "二", "三", "四", "五", "六", "日"};
    String[] stringBarDate = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};

    private BarData getBarData(int count, int dataCount) {

        ArrayList<String> xValues = new ArrayList<String>();
        ArrayList<String> xValuesDate = new ArrayList<String>();
        ArrayList<Integer> mColor = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            xValues.add(stringBar[i]);
            xValuesDate.add(stringBarDate[i]);
        }


        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        for (int i = 0; i < dataCount; i++) {
            yValues.add(new BarEntry(yy[i], i));
            mColor.add(getResources().getColor(R.color.smoke_bar));
        }

        BarDataSet set1;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)barChart.getData().getDataSetByIndex(0);
            set1.setYVals(yValues);
            barChart.getData().setXVals(xValues);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yValues, "");
            set1.setBarSpacePercent(70f);
            set1.setColors(mColor);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(xValues,xValuesDate, dataSets);
            data.setValueTextSize(10f);
            //data.setValueTypeface(mTf);
            //设置初始位置
            if (yValues != null&&yValues.size()>5) {
                float index=yValues.size();
                barChart.moveViewToX(index-5);
            }
            return data;
        }
        return null;
    }

    /**
     * 监听柱状图事件
     * */
    private void setChartListener() {

        //选中监听
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                int currentIndex=e.getXIndex();
                if (currentIndex >3) {
                    barChart.moveViewToX(currentIndex-3);
                }else {
                    barChart.moveViewToX(-1);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
