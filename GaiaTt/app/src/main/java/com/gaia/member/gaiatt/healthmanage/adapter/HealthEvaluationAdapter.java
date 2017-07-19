package com.gaia.member.gaiatt.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.ProcessEvaluationBean.ParamBean.ListBean;

import java.util.ArrayList;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthEvaluationAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 健康评价列表适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthEvaluationAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ListBean> datas;
    private LayoutInflater layoutInflater;

    public HealthEvaluationAdapter(Context context, ArrayList<ListBean> datas){
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
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_health_listview_layout,parent,false);
            viewHolder.titile = (TextView)convertView.findViewById(R.id.item_healthlist_title_tv);
            viewHolder.date = (TextView)convertView.findViewById(R.id.item_date_tv);
            viewHolder.content = (TextView)convertView.findViewById(R.id.item_content_tv);
            viewHolder.state = (TextView)convertView.findViewById(R.id.item_state_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        ListBean listBean= (ListBean) getItem(position);
        viewHolder.titile.setText(listBean.getTitle());
        viewHolder.date.setText(listBean.getDate());
        viewHolder.content.setText(listBean.getDetail());
        viewHolder.state.setText(GetResourcesUtils.getString(context,R.string.degree)+listBean.getDegree()+GetResourcesUtils.getString(context, R.string.percent_sign));
        return convertView;
    }
    private class ViewHolder{
        private TextView titile;
        private TextView date;
        private TextView content;
        private TextView state;
    }
}
