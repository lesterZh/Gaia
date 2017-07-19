package com.gaia.member.gaiatt.healthmanage.adapter;/**
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author android客户端-王浩韩
 * @date 2016/6/8 16:53
 * @version V1.0
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author android移动客户端-王浩韩
 * @ClassName: HorizontalListViewAdapter
 * Description: TODO
 * @date 2016/6/8 16:53
 */
public class HorizontalListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> datas;
    private LayoutInflater layoutInflater;

    public HorizontalListViewAdapter(Context context, ArrayList<String> datas) {
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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_diet, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvItemDiet.setText(getItem(position).toString());
        viewHolder.llItemDiet.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ls.onLongClick(v,position);
                return false;
            }
        });
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.tv_item_diet)
        TextView tvItemDiet;
        @Bind(R.id.ll_item_diet)
        LinearLayout llItemDiet;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setOnHorzontalLongClicklistener(OnHorzontalLongClicklistener ls){
        this.ls=ls;
    }
    private OnHorzontalLongClicklistener ls;
    public interface OnHorzontalLongClicklistener{
        void onLongClick(View view,int position);
    }
}
