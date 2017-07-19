package com.gaia.member.gaiatt.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthPlanBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthQuestionaireAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 健康问卷列表适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthQuestionaireAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HealthPlanBean> datas;
    private LayoutInflater layoutInflater;

    public HealthQuestionaireAdapter(Context context, ArrayList<HealthPlanBean> datas) {
        this.context = context;
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return /*datas.size()*/15;
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
            convertView = layoutInflater.inflate(R.layout.item_health_questionaire_layout, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.item_divider)
        View itemDivider;
        @Bind(R.id.item_healthlist_title_tv)
        TextView itemHealthlistTitleTv;
        @Bind(R.id.item_info_tv)
        TextView itemInfoTv;
        @Bind(R.id.item_dateandon_ll)
        LinearLayout itemDateandonLl;
        @Bind(R.id.item_content_tv)
        TextView itemContentTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
