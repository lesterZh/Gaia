package com.gaia.member.gaiatt.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.androidlib.net.bean.MedicinePlanBean.ParamBean.ListBean;
import com.gaia.member.gaiatt.R;

import java.util.ArrayList;


/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: MedicineListViewAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 中医干预详情适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class MedicineListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ListBean> datas;
    private LayoutInflater layoutInflater;

    public MedicineListViewAdapter(Context context, ArrayList<ListBean> datas){
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
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_medicine_listview_layout,parent,false);
            //viewHolder.textView = (TextView)convertView.findViewById(R.id.item_search_iv);
            viewHolder.healthlistBarLl= (LinearLayout) convertView.findViewById(R.id.item_healthlist_bar_ll);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if (position!=0){
            viewHolder.healthlistBarLl.setVisibility(View.GONE);
        }else{
            viewHolder.healthlistBarLl.setVisibility(View.VISIBLE);
        }
        //viewHolder.textView.setText((String)getItem(position));

        return convertView;
    }

    private class ViewHolder{
        private TextView titile;
        private TextView content;
        private ImageView edit;
        private TextView strength;
        private LinearLayout healthlistBarLl;
    }
}
