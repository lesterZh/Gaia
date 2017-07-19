package com.gaia.member.gaiatt.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.MealBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: MealGridViewAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 饮食选择器适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class MealGridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MealBean> datas;
    private LayoutInflater layoutInflater;

    private static List<RelativeLayout> rlList=new ArrayList<>();
    private static List<TextView> tvList=new ArrayList<>();
    public MealGridViewAdapter(Context context, ArrayList<MealBean> datas){
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
            convertView = layoutInflater.inflate(R.layout.item_mealgrid,parent,false);
            viewHolder.tvItemMeal = (TextView)convertView.findViewById(R.id.tv_item_meal);
            viewHolder.rlItemMeal = (RelativeLayout) convertView.findViewById(R.id.rl_item_meal);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        MealBean mealBean=(MealBean)getItem(position);
        viewHolder.tvItemMeal.setText(mealBean.getMealName());
        viewHolder.rlItemMeal.setOnClickListener(mOnClickListener);
        return convertView;
    }


    View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (rlList.size()!=0) {
                for (int i = 0; i <rlList.size() ; i++) {
                    rlList.get(i).setBackgroundColor(context.getResources().getColor(R.color.lightGrayline));
                    tvList.get(i).setTextColor(context.getResources().getColor(R.color.text_content));
                }
                rlList.clear();
                tvList.clear();
            }
            RelativeLayout rlItemMeal= (RelativeLayout) v.findViewById(R.id.rl_item_meal);
            TextView tvItemMeal= (TextView) v.findViewById(R.id.tv_item_meal);
            rlList.add(rlItemMeal);
            tvList.add(tvItemMeal);
            rlItemMeal.setBackgroundColor(context.getResources().getColor(R.color.blue));
            tvItemMeal.setTextColor(context.getResources().getColor(R.color.white));
            ls.getTextString(tvItemMeal.getText()+"");
        }
    };

    private class ViewHolder{
        private TextView tvItemMeal;
        private RelativeLayout rlItemMeal;
    }

    public interface ReGetClickListener{
        void getTextString(String result);
    }
    private ReGetClickListener ls;
    public void setReGetClickListener(ReGetClickListener ls){
        this.ls=ls;
    }
}
