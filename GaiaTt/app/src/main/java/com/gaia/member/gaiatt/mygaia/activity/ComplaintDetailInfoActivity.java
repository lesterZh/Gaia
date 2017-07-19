package com.gaia.member.gaiatt.mygaia.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.makeorder.activity.BaseActivity;
import com.gaia.member.gaiatt.mygaia.bean.CompaintProgressBean;
import com.gaia.member.gaiatt.mygaia.ui.CustomListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * @Title: ComplaintDetailInfoActivity
 * @Package com.gaia.member.gaiatt.mygaia.activity
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/19 12:03
 * @version V1.0
 */

/**
 * @author Android客户端开发组-zhangHaiTao
 * @ClassName: ComplaintDetailInfoActivity
 * Description: 投诉详情信息
 * @date 2016/5/19 12:03
 */
public class ComplaintDetailInfoActivity extends BaseActivity {

    @Bind(R.id.tv_title_title_bar)
    TextView tvTitleTitleBar;
    @Bind(R.id.lv_show_progress)
    CustomListView lvShowProgress;

    private ProgressAdapter progressAdapter;
    List<CompaintProgressBean> compaintProgressBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_detail_info);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        for (int i=0; i<5; i++) {
            compaintProgressBeanList.add(new CompaintProgressBean());
        }
    }

    private void initView() {
        tvTitleTitleBar.setText("投诉详情");
        progressAdapter = new ProgressAdapter(mContext, 0, compaintProgressBeanList);
        lvShowProgress.setAdapter(progressAdapter);
        lvShowProgress.setFocusable(false);
    }


    class ProgressAdapter extends ArrayAdapter<CompaintProgressBean> {

        public ProgressAdapter(Context context, int resource, List<CompaintProgressBean> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_list_show_progress_complain, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //最上面的条目 不显示上一半的指示条
            if (position == 0) {
                viewHolder.viewTop.setVisibility(View.INVISIBLE);
                viewHolder.viewBottom.setVisibility(View.VISIBLE);
            } else if (position == (compaintProgressBeanList.size() - 1)) {//最后一个条目 不显示下一半的指示条
                viewHolder.viewTop.setVisibility(View.VISIBLE);
                viewHolder.viewBottom.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.viewTop.setVisibility(View.VISIBLE);
                viewHolder.viewBottom.setVisibility(View.VISIBLE);
            }

            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.view_top)
            View viewTop;
            @Bind(R.id.view_node)
            View viewNode;
            @Bind(R.id.view_bottom)
            View viewBottom;
            @Bind(R.id.tv_type)
            TextView tvType;
            @Bind(R.id.tv_name)
            TextView tvName;
            @Bind(R.id.tv_state)
            TextView tvState;
            @Bind(R.id.tv_time)
            TextView tvTime;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
