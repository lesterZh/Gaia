/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: SleepFragment
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 睡眠柱状图Fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gaia.member.androidlib.constant.NetConstant;
import com.gaia.member.androidlib.net.NetUtils;
import com.gaia.member.androidlib.net.bean.SleepRecordBean;
import com.gaia.member.androidlib.net.bean.SleepRecordBean.ParamBean.SleepListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.healthmanage.utils.LocalDataUtils;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.MpChartUtils;
import com.gaia.member.gaiatt.utils.PickerDialogUtils;
import com.gaia.member.gaiatt.utils.UserUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.squareup.okhttp.RequestBody;

import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author android移动客户端-王浩韩
 * @ClassName: SleepFragment
 * Description: 睡眠柱状图Fragment
 * @date 2016/6/5 0005 下午 11:30
 */
public class SleepFragment extends Fragment {
    //柱状图
    @Bind(R.id.barChart)
    BarChart barChart;
    //睡眠时长
    @Bind(R.id.tv_sleep_timelong)
    TextView tvSleepTimelong;
    //入睡时间
    @Bind(R.id.tv_sleep_timesleep)
    TextView tvSleepTimesleep;
    //醒来时间
    @Bind(R.id.tv_sleep_timewake)
    TextView tvSleepTimewake;
    //添加按钮
    @Bind(R.id.add_img)
    ImageView addImg;
    //ftagment视图
    private View view;
    //图表弹窗
    private PopupWindow popupWindow;
    //当前日期
    private int dayIndex;

    //当前下标
    int currentIndex;

    //数据列表
    List<SleepListBean> sleepList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sleep_fragment, container, false);
        ButterKnife.bind(this, view);
        initTime();//获取当前时间
        initBarChart();
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

    private void initBarChart() {
        //获取本地数据
        sleepList = getLocationData();
        //获取服务器睡眠数据
        //getServiceData();
        //设置柱状图数据与属性
        MpChartUtils.setBarChartSet(getActivity(), barChart,
                MpChartUtils.getBarData(barChart, sleepList,
                        getResources().getColor(R.color.sleep_bar)),
                getResources().getColor(R.color.sleep_line_a),
                "min", getActivity().getResources().getColor(R.color.sleep_index_r));
        //初始化下标
        setCurrentIndex();
    }

    /**
     * 获取当前日期下标
     * */
    private void setCurrentIndex() {
        if (sleepList != null) {
            for (int i = 0; i <sleepList.size() ; i++) {
                //获取服务器当前日期号
                String currentDate=MpChartUtils.getCurrentDate(sleepList.get(i).getDate());
                if (currentDate.equals(dayIndex+"") ) {
                    //当天日期下标
                    currentIndex=i;
                }
            }
        }
    }

    /**
     * 获取服务器睡眠数据
     */
    private void getServiceData() {
        try {
            //构造请求体
            JSONStringer jsonStringer = new JSONStringer().object()
                    .key("userId").value(UserUtils.getUserId())//当前页
                    .endObject();
            String jsonStr = jsonStringer.toString();
            RequestBody body = RequestBody.create(NetConstant.JSON, jsonStr);
            //发起请求
            NetUtils.getNetUtilsInstance().getNetApiServiceInterface().postSleepRecordn(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<SleepRecordBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(SleepRecordBean sleepRecordBean) {
                            if (sleepRecordBean.isSuccess()) {
                                //设置数据源
                                sleepList = sleepRecordBean.getParam().getSleepList();
                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
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
     * 获取本期json数据
     */
    private List<SleepListBean> getLocationData() {
        //获取assets文件目录下的String数据
        String sleepJsonString = AssetsFileUtil.getJsonStr(getActivity(), "sleep.txt");
        List<SleepListBean> mList = new ArrayList<>();
        if (sleepJsonString != null) {
            //解析json数据
            SleepRecordBean sleepRecordBean = GsonTools.getSleepRecordBean(sleepJsonString);
            mList = sleepRecordBean.getParam().getSleepList();
        }
        return mList;
    }

    /**
     * 监听柱状图事件
     */
    private void setChartListener() {
        //选中监听
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
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
        int currentIndex = e.getXIndex();
        //如果当前选中下表大于3移动item到currentIndex - 3
        if (currentIndex > 3) {
            barChart.moveViewToX(currentIndex - 3);
        } else {
            //不移动
            barChart.moveViewToX(-1);
        }
        SleepListBean sleepListBean = sleepList.get(currentIndex);
        //转换分钟为小时
        int  min=sleepListBean.getSleepTime();
        String timeLong = getStringFromMin(min);
        //时长
        //设置睡眠时长
        tvSleepTimelong.setText(timeLong);
        //设置入睡时间
        tvSleepTimesleep.setText(sleepListBean.getFallAsleep());
        //设置睡醒时间
        tvSleepTimewake.setText(sleepListBean.getWake());
        //如果选择当天按钮可点击
        if (MpChartUtils.getCurrentDate(sleepListBean.getDate()).equals(dayIndex+"")) {
            addImg.setEnabled(true);
            addImg.setImageResource(R.drawable.btn_add_purple_normal);
            //获取当前下标
            this.currentIndex=currentIndex;
        } else {
            //按钮不能点击
            addImg.setEnabled(false);
            addImg.setImageResource(R.drawable.btn_add_purple_selected);
        }
    }

    /**
     * 分钟转化为小时
     * @param  min
     * */
    @NonNull
    private String getStringFromMin(int min) {
        int house=min/60;//小时
        int  currrentMin=min%60;//余数分钟
        String timeLong=null;
        //处理时长显示
        if (currrentMin == 0) {
            timeLong=house+"h";
        }else if(currrentMin> 0&&currrentMin<10){
            timeLong=house+"h0"+currrentMin+"m";
        }else {
            timeLong=house+"h"+currrentMin+"m";
        }
        return timeLong;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 添加按钮
     */
    @OnClick(R.id.add_img)
    public void onClick() {
        //睡眠时间设置弹窗
        PickerDialogUtils pickerDialogUtils = new PickerDialogUtils();
        pickerDialogUtils.setDoublePickerDialog(getActivity(), GetResourcesUtils.getString(getActivity(), R
                        .string.sleep_dialog_title), GetResourcesUtils.getString(getActivity(), R.string
                        .sleep_dialog_content1),
                GetResourcesUtils.getString(getActivity(), R.string.sleep_dialog_content2), LocalDataUtils
                        .getTimeArrayList(), LocalDataUtils.getTimeArrayList(), 20,
                new PickerDialogUtils.OnDialogClickListener() {
                    @Override
                    public void okDialogClick(String text1, String text2) {
                        //设置数据并计算时长
                        //设置入睡时间
                        tvSleepTimesleep.setText(text1);
                        //设置醒来时间
                        tvSleepTimewake.setText(text2);
                        //获取时长
                        int timeLong = getTimelong(text1, text2);
                        tvSleepTimelong.setText(getStringFromMin(timeLong));
                        //刷新图表

                        //添加睡眠时间
                        SleepListBean sleepListBean=sleepList.get(currentIndex);
                        sleepListBean .setSleepTime(timeLong);//时长
                        sleepListBean.setFallAsleep(text1);//入睡时间
                        sleepListBean.setWake(text2);//醒来时间
                        //设置数据源
                         BarData mBarData = MpChartUtils.getBarData(barChart, sleepList,
                         getResources().getColor(R.color.sleep_bar));
                        barChart.setData(mBarData);
                        barChart.invalidate();
                    }
                });
    }

    /**
     * 獲取計算時長
     * */
    private int getTimelong(String begin , String end){
        //入睡时间
        String[] begins=begin.split("\\:");
        //醒来时间
        String[] ends=end.split("\\:");
        //入睡小时
        int beginsH=Integer.parseInt(begins[0]);
        //入睡分钟
        int beginsM=Integer.parseInt(begins[1]);
        //醒来小时
        int endsH=Integer.parseInt(ends[0]);
        //醒来分钟
        int endsM=Integer.parseInt(ends[1]);
        //时长
        int timeLong=0;
        //如果入睡时间在从0-12
        if (beginsH>=0&&beginsH<=12) {
            //时长为负
            if ((endsH-beginsH) <0) {
                timeLong=0;
            }else {
                timeLong=(endsH-beginsH)*60;
                //入睡分钟小于醒来分钟点
                if (beginsM <= endsM) {
                    timeLong+=(endsM-beginsM);
                }else{
                    timeLong=(endsH-beginsH-1)*60
                            +(60+endsM-beginsM);
                }
            }
            //入随时间12-24
        }else {
            timeLong=(endsH+(24-beginsH))*60;
            if (beginsM <= endsM) {
                timeLong+=(endsM-beginsM);
            }else{
                timeLong=(endsH-beginsH-1)*60
                        +(60+endsM-beginsM);
            }
        }
        return timeLong;
    }
}
