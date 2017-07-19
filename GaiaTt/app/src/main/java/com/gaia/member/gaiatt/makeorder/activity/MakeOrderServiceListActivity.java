package com.gaia.member.gaiatt.makeorder.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.bean.MakeOrderServiceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Android客户端组-ZhangHaiTao
 * @version V1.0
 * @Title: MakeOrderServiceListActivity
 * @Package com.gaia.member.gaiatt.makeorder.activity
 * @Description: 显示一项服务的附近店面列表
 * 从.MakeOrderServiceFragment跳转进来
 * 需要接收上一个activity传递进来的数据
 * <p>
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/13.
 */

public class MakeOrderServiceListActivity extends BaseActivity {

    @Bind(R.id.et_service_search)
    EditText etServiceSearch;
    @Bind(R.id.press_cancel)
    TextView pressCancel;
    @Bind(R.id.lv_service_location)
    ListView lvServiceLocation;
    @Bind(R.id.iv_back_title_bar)
    ImageView mIvBackTitleBar;

    private MyOrderServiceListAdapter orderServiceListAdapter;
    private List<String> serviceLocationList = new ArrayList<>();

    MakeOrderServiceBean orderServiceBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order_service_list);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        //获取上一个activity传递过来的数据
        orderServiceBean = (MakeOrderServiceBean) getIntent().getSerializableExtra("serviceBean");

        for (int i = 0; i < 50; i++) {
            serviceLocationList.add("盖亚分店" + (i + 1));
        }
    }

    private void initView() {
        orderServiceListAdapter = new MyOrderServiceListAdapter(mContext, 0, serviceLocationList);
        lvServiceLocation.setAdapter(orderServiceListAdapter);
        lvServiceLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowServiceInfoActivity.actionStart(mContext, orderServiceBean);
            }
        });


    }

    public static void actionStart(Context context, MakeOrderServiceBean serviceBean) {
        Intent intent = new Intent(context, MakeOrderServiceListActivity.class);
        intent.putExtra("serviceBean", serviceBean);
        context.startActivity(intent);
    }

    @OnClick(R.id.press_cancel)
    public void pressCancel() {

    }

    @OnClick(R.id.iv_back_title_bar)
    public void onClick() {
        onBackPressed();
    }


    class MyOrderServiceListAdapter extends ArrayAdapter<String> {

        public MyOrderServiceListAdapter(Context context, int resource, List<String> objects) {
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

            viewHolder.tvServiceLocation.setText(getItem(position));
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.tv_hospital_department)
            TextView tvServiceLocation;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
