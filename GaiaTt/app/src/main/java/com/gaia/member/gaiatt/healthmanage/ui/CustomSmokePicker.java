package com.gaia.member.gaiatt.healthmanage.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: CustomSmokePicker
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 自定义选择器
使用范例：
CustomPicker customPicker = new CustomPicker(mContext);
customPicker.setTitle("自定义");
customPicker.setSubTitles("小标题1", "小标题2");
customPicker.setWheelDataIndex(1, customPicker.getHourList(), 1);
customPicker.setWheelDataIndex(2, customPicker.getHourList(), 0);
customPicker.setSaveButtonClickListener(new CustomPicker.OnSaveButtonClickListener() {……});
customPicker.show(true);

如果需要改变布局样式，则可将布局文件layout_custom_picker复制一份，在新的布局文件中修改为你希望的样式
里面的控件id不要改动即可
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class CustomSmokePicker {
    Context mContext;
    @Bind(R.id.tv_selected1)
    TextView tvSelected1;
    @Bind(R.id.tv_selected2)
    TextView tvSelected2;
    @Bind(R.id.wheel_1)
    WheelView wheel1;
    @Bind(R.id.wheel_2)
    WheelView wheel2;
    @Bind(R.id.btn_save)
    Button btnSave;

    public View rootView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_subtitle1)
    TextView tvSubtitle1;
    @Bind(R.id.tv_subtitle2)
    TextView tvSubtitle2;

    private String selectedText1 = "--";
    private String selectedText2 = "--";

    private AlertDialog mDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tvSelected1.setText(selectedText1);
            tvSelected2.setText(selectedText2);
        }
    };


    /**
     * 设置单个选择器的数据和默认选中位置
     * @param wheel 1：设置第一个选择器   2：设置第二个
     * @param list 数据列表
     * @param defaultIndex 默认选中位置
     */
    public void setWheelDataIndex(int wheel, ArrayList<String> list, int defaultIndex) {
        if (wheel == 1) {
            wheel1.setData(list);
            wheel1.setDefault(defaultIndex);
            selectedText1 = list.get(defaultIndex);
            tvSelected1.setText(selectedText1);
        } else if (wheel == 2) {
            wheel2.setData(list);
            wheel2.setDefault(defaultIndex);
            selectedText2 = list.get(defaultIndex);
            tvSelected2.setText(selectedText2);
        }
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置2个子标题
     * @param subTitle1
     * @param subTitle2
     */
    public void setSubTitles(String subTitle1, String subTitle2) {
        tvSubtitle1.setText(subTitle1);
        tvSubtitle2.setText(subTitle2);
    }

    /**
     * 显示弹窗
     * @param cancleable 设置是否可支持返回键消除弹窗 true：支持  false：不支持
     */
    public void show(boolean cancleable) {
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setView(rootView);
        mDialog.setCancelable(cancleable);
        mDialog.show();
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //这里一定要先移除rootView否则二次调用会报错的
                ViewGroup viewGroup = (ViewGroup) rootView.getParent();
                viewGroup.removeView(rootView);
            }
        });
    }

    /**
     * 关闭弹窗
     */
    public void dismiss() {
        if (mDialog != null) {
            //这里一定要先移除rootView否则二次调用会报错的
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            viewGroup.removeView(rootView);
            mDialog.dismiss();
        }
    }

    public CustomSmokePicker(Context context) {
        mContext = context;
        rootView = View.inflate(context, R.layout.layout_custom_smoke_picker, null);
        ButterKnife.bind(this, rootView);

        wheel1.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedText1 = text;
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        wheel2.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedText2 = text;
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

    }

    /**
     * 可以自定义布局文件，但布局文件中的控件ID要一致
     * @param context
     * @param layout
     */
    public CustomSmokePicker(Context context, int layout) {
        mContext = context;
        rootView = View.inflate(context, layout, null);
        ButterKnife.bind(this, rootView);

        wheel1.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedText1 = text;
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        wheel2.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                selectedText2 = text;
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

    }

    @OnClick({R.id.btn_save})
    public void btnSave(View view) {
        mSaveButtonClickListener.okClick(selectedText1, selectedText2);
        dismiss();
    }

    OnSaveButtonClickListener mSaveButtonClickListener;

    public void setSaveButtonClickListener(OnSaveButtonClickListener selectListener) {
        mSaveButtonClickListener = selectListener;
    }

    public interface OnSaveButtonClickListener {
        void okClick(String text1, String text2);
    }


    public ArrayList<String> getHourList() {
        ArrayList<String> hours = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            hours.add("0" + i);
        }
        for (int i = 10; i < 25; i++) {
            hours.add("" + i);
        }
        return hours;
    }

    public ArrayList<String> getMinuteList() {
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
