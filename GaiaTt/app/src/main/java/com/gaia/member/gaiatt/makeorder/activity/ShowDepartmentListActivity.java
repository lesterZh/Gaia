package com.gaia.member.gaiatt.makeorder.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: ShowDepartmentListActivity
 * @Package com.gaia.member.gaiatt.makeorder.activity
 * @Description:   查看该医院的科室
 *
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/14.
 */
public class ShowDepartmentListActivity extends BaseActivity {

    private static final int LIST_DATA_CHANGE = 1;
    @Bind(R.id.et_hospital_department_search)
    EditText etHospitalDepartmentSearch;
    @Bind(R.id.press_cancel)
    TextView pressCancel;
    @Bind(R.id.lv_hospital_department)
    ListView lvHospitalDepartment;


    private List<String> departmentsList = new ArrayList<>();
    private List<String> searchDepartmentsList = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LIST_DATA_CHANGE:
                    departmentAdapter = new MyHospitalDepartmentAdapter(mContext, 0, searchDepartmentsList);
                    lvHospitalDepartment.setAdapter(departmentAdapter);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_department);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        departmentAdapter = new MyHospitalDepartmentAdapter(mContext, 0, departmentsList);
        lvHospitalDepartment.setAdapter(departmentAdapter);

        lvHospitalDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到医生的列表界面 需要向下一个activity传递医院和科室的相关数据
                ActivityUtils.gotoIntent(mContext, ShowDoctorsListActivity.class);
            }
        });

        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                departmentsList.add("肠胃外科"+(i+1));
            } else if (i < 30) {
                departmentsList.add("儿外科"+(i+1-10));
            } else {
                departmentsList.add("精神科"+(i+1-30));
            }

        }
        departmentAdapter.notifyDataSetChanged();

        //搜索文本框监听事件
        etHospitalDepartmentSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                String searchKeyWord = etHospitalDepartmentSearch.getText().toString().trim();
//                LogUtil.i("change " + searchKeyWord);
                searchDepartmentsList.clear();
                for (int i=0; i<departmentsList.size(); i++) {
                    if (departmentsList.get(i).contains(searchKeyWord)) {
                        searchDepartmentsList.add(departmentsList.get(i));
                    }
                }
//                LogUtil.i(departmentsList.toString()+" new data");
                mHandler.sendEmptyMessage(LIST_DATA_CHANGE);
            }
        });




    }

    private MyHospitalDepartmentAdapter departmentAdapter;

    //清空搜索输入框
    @OnClick(R.id.press_cancel)
    public void pressCancel() {
        etHospitalDepartmentSearch.setText("");
        departmentAdapter = new MyHospitalDepartmentAdapter(mContext, 0, departmentsList);
        lvHospitalDepartment.setAdapter(departmentAdapter);
    }

    class MyHospitalDepartmentAdapter extends ArrayAdapter<String> {

        public MyHospitalDepartmentAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_list_make_order_hospital_department, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvHospitalDepartment.setText(getItem(position));
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.tv_hospital_department)
            TextView tvHospitalDepartment;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
