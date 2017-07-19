/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: CustomViewPagerAdapter
 * @Package com.gaia.member.gaiatt.gaiaclinic.adapter
 * @Description: 身边盖亚搜寻列表适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.gaiaclinic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.AroundClinicParamBean.ParamBean.ClinicListBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @ClassName: CustomViewPagerAdapter
 *Description: 身边盖亚搜寻列表适配器
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class SearchListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ClinicListBean> datas;
    private LayoutInflater layoutInflater;

    public SearchListViewAdapter(Context context, ArrayList<ClinicListBean> datas) {
        this.context = context;
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
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
            convertView = layoutInflater.inflate(R.layout.item_search_aroundgaia, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ClinicListBean listBean = (ClinicListBean) getItem(position);
        viewHolder.itemTitle.setText(listBean.getClinicName());
        viewHolder.itemAds.setText(listBean.getAddress());
        viewHolder.itemPhone.setText(listBean.getContactPhone());
        viewHolder.itemDistance.setText(listBean.getDistance()+"m");
        viewHolder.itemTitle.setText(listBean.getClinicName());
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.item_icon)
        ImageView itemIcon;
        @Bind(R.id.item_title)
        TextView itemTitle;
        @Bind(R.id.item_ads)
        TextView itemAds;
        @Bind(R.id.item_phone)
        TextView itemPhone;
        @Bind(R.id.item_ll)
        LinearLayout itemLl;
        @Bind(R.id.item_distance)
        TextView itemDistance;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
