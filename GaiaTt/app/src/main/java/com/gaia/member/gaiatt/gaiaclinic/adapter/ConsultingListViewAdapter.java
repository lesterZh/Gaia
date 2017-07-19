/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: ConsultingListViewAdapter
 * @Package com.gaia.member.gaiatt.gaiaclinic.adapter
 * @Description: 咨询列表适配器
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
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.leancloud.ChatUtil;
import com.gaia.member.androidlib.net.bean.ConsultingBean;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @ClassName: ConsultingListViewAdapter
 *Description: 咨询列表适配器
 *@author android移动客户端-王浩韩
 * @date 2016/6/14 9:56
 */
public class ConsultingListViewAdapter extends BaseAdapter {

    private Context context;//上下文
    private ArrayList<ConsultingBean> datas;//数据源
    private LayoutInflater layoutInflater;//布局填充器
    private boolean isClick=true;//btn是否能点击

    public boolean isClick() {
        return isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }

    public ConsultingListViewAdapter(Context context, ArrayList<ConsultingBean> datas) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_consulting_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ConsultingBean consultingBean = (ConsultingBean) getItem(position);
        if (consultingBean != null) {
            //设置医师是推荐还是已诊状态
            if (consultingBean.getStatus() == 0) {
                viewHolder.imgItemStatus.setImageResource(R.drawable.icon_recommend);
                viewHolder.imgConsultingDemand.setImageResource(R.drawable.button_consultation);
            } else if (consultingBean.getStatus() == 1) {
                viewHolder.imgItemStatus.setImageResource(R.drawable.icon_haveclinical);
                viewHolder.imgConsultingDemand.setImageResource(R.drawable.button_visit);
            } else {
                viewHolder.imgConsultingDemand.setImageResource(R.drawable.button_consultation);
                viewHolder.imgItemStatus.setVisibility(View.GONE);
            }
            viewHolder.tvConsultingName.setText(consultingBean.getName());//姓名设置
            viewHolder.tvConsultingLevel.setText(consultingBean.getLevel());//级别设置
            viewHolder.tvConsultingSkilful.setText(consultingBean.getSkilful());//擅长设置
            viewHolder.tvConsultingBelongs.setText(consultingBean.getBelongs());//所属医院设置
        }
        viewHolder.imgConsultingDemand.setOnClickListener(mOnClickListener);
        return convertView;
    }

    View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isClick) {
                ChatUtil.gotoChatActivity(context, "1", "5");//LeanCloud入口
                isClick = false;
                handleBtnMultipleClick();
            }
        }
    };


    class ViewHolder {
        @Bind(R.id.img_item_headpic)
        ImageView imgItemHeadpic;
        @Bind(R.id.img_item_status)
        ImageView imgItemStatus;
        @Bind(R.id.tv_consulting_name)
        TextView tvConsultingName;
        @Bind(R.id.tv_consulting_level)
        TextView tvConsultingLevel;
        @Bind(R.id.tv_consulting_skilful)
        TextView tvConsultingSkilful;
        @Bind(R.id.tv_consulting_belongs)
        TextView tvConsultingBelongs;
        @Bind(R.id.img_consulting_demand)
        ImageView imgConsultingDemand;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    /**
     * 处理btn短期内多次点击事件
     * */
    private void handleBtnMultipleClick(){
        if (isClick == false) {
            Timer timer=new Timer();
            TimerTask timerTask=new TimerTask() {
                @Override
                public void run() {
                    isClick=true;
                }
            };
            timer.schedule(timerTask,5*1000);
        }
    }
}
