package com.gaia.member.gaiatt.makeorder.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangHaiTao on 2016/5/16.
 */
public class CustomTimePicker {
    Context mContext;
    @Bind(R.id.tv_selected1)
    TextView tvHour;
    @Bind(R.id.tv_minute)
    TextView tvMinute;
    @Bind(R.id.wheel_hour)
    WheelView wheelHour;
    @Bind(R.id.wheel_minute)
    WheelView wheelMinute;
    @Bind(R.id.btn_ok)
    Button btnOk;
    @Bind(R.id.btn_cancel)
    Button btnCancel;

    public View rootView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private String selectedHour = "--";
    private String selectedMinute = "--";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tvHour.setText(selectedHour);
            tvMinute.setText(selectedMinute);
        }
    };

    public CustomTimePicker(Context context) {
        mContext = context;
        rootView = View.inflate(context, R.layout.layout_custom_time_picker, null);
        ButterKnife.bind(this, rootView);

        tvTitle.setText("设置时间");
        wheelHour.setData(getHourList());
        wheelHour.setDefault(1);
        wheelMinute.setData(getMinuteList());
        wheelMinute.setDefault(1);

        wheelHour.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedHour = text;
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        wheelMinute.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedMinute = text;
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

    }

    private AlertDialog mDialog;
    public void show() {
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setView(rootView);
        mDialog.show();
    }

    public void dismiss() {
        if (mDialog != null) {
            //这里一定要先移除rootView否则二次调用会报错的
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            viewGroup.removeView(rootView);
            mDialog.dismiss();
        }
    }

    @OnClick({R.id.btn_ok, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
//                Log.i("ZHT", "ok");
                mOkButtonSelectListener.okClick(selectedHour, selectedMinute);
                dismiss();
                break;
            case R.id.btn_cancel:
//                Log.i("ZHT", "cancel");
                dismiss();
                break;
        }
    }

    OnOkButtonSelectListener mOkButtonSelectListener;
    public void setOkButtonSelectListener(OnOkButtonSelectListener selectListener) {
        mOkButtonSelectListener = selectListener;
    }

    public interface OnOkButtonSelectListener {
        void okClick(String hour, String minute);
    }
    private ArrayList<String> getHourList() {
        ArrayList<String> hours = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            hours.add("0" + i);
        }
        for (int i = 10; i < 25; i++) {
            hours.add("" + i);
        }
        return hours;
    }

    private ArrayList<String> getMinuteList() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add("0" + i);
        }
        for (int i = 10; i < 61; i++) {
            list.add("" + i);
        }
        return list;
    }

}
