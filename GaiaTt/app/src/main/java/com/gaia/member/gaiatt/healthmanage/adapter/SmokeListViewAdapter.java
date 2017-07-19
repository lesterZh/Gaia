package com.gaia.member.gaiatt.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.SportListBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: SmokeListViewAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 吸烟干预详情适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class SmokeListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SportListBean> datas;
    private LayoutInflater layoutInflater;

    public SmokeListViewAdapter(Context context, ArrayList<SportListBean> datas) {
        this.context = context;
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_smoke_listview_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position != 0) {
            viewHolder.itemHealthlistBarLl.setVisibility(View.GONE);
        } else {
            viewHolder.itemHealthlistBarLl.setVisibility(View.VISIBLE);
        }

        return convertView;
    }



    class ViewHolder {
        @Bind(R.id.item_healthlist_bar_ll)
        LinearLayout itemHealthlistBarLl;
        @Bind(R.id.item_healthlist_day_tv)
        TextView itemHealthlistDayTv;
        @Bind(R.id.item_date_tv)
        TextView itemDateTv;
        @Bind(R.id.item_istoday_tv)
        TextView itemIstodayTv;
        @Bind(R.id.item_breakfast_tv)
        TextView itemBreakfastTv;
        @Bind(R.id.item_breakfast_content_tv)
        TextView itemBreakfastContentTv;
        @Bind(R.id.item_lunch_tv)
        TextView itemLunchTv;
        @Bind(R.id.item_lunch_content_tv)
        TextView itemLunchContentTv;
        @Bind(R.id.item_dinner_tv)
        TextView itemDinnerTv;
        @Bind(R.id.item_dinner_content_tv)
        TextView itemDinnerContentTv;
        @Bind(R.id.item_divider)
        View itemDivider;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
