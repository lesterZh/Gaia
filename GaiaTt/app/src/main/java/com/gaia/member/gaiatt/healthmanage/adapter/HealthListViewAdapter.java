package com.gaia.member.gaiatt.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanBean;

import java.util.ArrayList;

/**
 * Created by WangHaohan on 2016/5/18.
 *
 * @author android客户端-WangHaohan
 * @version V1.0
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/5/18 18:21
 */
public class HealthListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HealthPlanBean> datas;
    private LayoutInflater layoutInflater;

    public HealthListViewAdapter(Context context, ArrayList<HealthPlanBean> datas){
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
        HealthPlanBean healthPlanBean= (HealthPlanBean) getItem(position);
        viewHolder.titile.setText(healthPlanBean.getTitle());
        viewHolder.date.setText(healthPlanBean.getDate());
        viewHolder.content.setText(healthPlanBean.getDetail());
        String sateString="";
        switch (healthPlanBean.getStatus()){
            case CommonConstants.NOTSTART:
                sateString= GetResourcesUtils.getString(context,R.string.not_start);
                break;
            case CommonConstants.ONGOING:
                sateString=GetResourcesUtils.getString(context,R.string.ongoing);
                break;
            case CommonConstants.FINISHED:
                sateString=GetResourcesUtils.getString(context,R.string.finished);
                break;
            default:
                break;
        }
        viewHolder.state.setText(sateString);
        return convertView;
    }

    private class ViewHolder{
        private TextView titile;
        private TextView date;
        private TextView content;
        private TextView state;
    }
}
