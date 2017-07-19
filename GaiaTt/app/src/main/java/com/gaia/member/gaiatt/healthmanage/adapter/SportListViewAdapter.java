/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: SportListViewAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 运动干预详情适配器
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
package com.gaia.member.gaiatt.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.androidlib.net.bean.SportPlanBean.ParamBean;
import com.gaia.member.androidlib.net.bean.SportPlanBean.ParamBean.ListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.CommonConstants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @ClassName: SportListViewAdapter
 *Description: 运动干预详情适配器
 *@author android移动客户端-王浩韩
 * @date 2016/6/5 0005 下午 11:30
 */
public class SportListViewAdapter extends BaseAdapter  {
    //上下文
    private Context context;
    //列表数据源
    private ArrayList<ListBean> datas;
    //阶段数据源
    private ParamBean paramBean;
    //布局填充期
    private LayoutInflater layoutInflater;

    /**
     * 构造函数
     * @param  context 上下文
     * @param  datas   运动列表数据源
     * @param  paramBean  阶段描述
     * */
    public SportListViewAdapter(Context context, ArrayList<ListBean> datas,ParamBean paramBean) {
        this.context = context;
        this.datas = datas;
        this.paramBean = paramBean;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 获取当前个数
     * */
    @Override
    public int getCount() {
        return datas.size();
    }

    /**
     *获取当前子视图
     * */
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    /**
     *获取当前id
     * */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *获取列表子视图
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_sport_listview_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ListBean listBean= (ListBean) getItem(position);
        //设置标题
        viewHolder.itemHealthlistTitleTv.setText(listBean.getTitle());
        //设置内容
        viewHolder.itemContentTv.setText(listBean.getDetail());
        //设置强度
        int level=listBean.getLevel();
        String mStreng=null;
        switch (level){
            case 1:
                //轻松
                mStreng= CommonConstants.RELAXE;
                break;
            case 2:
                //适中
                mStreng=CommonConstants.MODERATE;
                break;
            case 3:
                //吃力
                mStreng=CommonConstants.SWEATY;
                break;
        }
        //设置强度
        viewHolder.itemStrengthTv.setText(mStreng);
        //设置阶段描述
        viewHolder.tvItemLeveldetail.setText(paramBean.getLevelDetail());
        //设置阶段时长
        viewHolder.tvItemTime.setText(paramBean.getStartDate()+" 至 "+paramBean.getEndDate());

        //控制显示一次阶段视图
        if (position != 0) {
            viewHolder.itemHealthlistBarLl.setVisibility(View.GONE);
        } else {
            viewHolder.itemHealthlistBarLl.setVisibility(View.VISIBLE);
        }
        return convertView;
    }


    class ViewHolder {
        //阶段视图组
        @Bind(R.id.item_healthlist_bar_ll)
        LinearLayout itemHealthlistBarLl;
        //标题
        @Bind(R.id.item_healthlist_title_tv)
        TextView itemHealthlistTitleTv;
        //内容
        @Bind(R.id.item_content_tv)
        TextView itemContentTv;
        //强度
        @Bind(R.id.item_strength_tv)
        TextView itemStrengthTv;
        //阶段描述
        @Bind(R.id.tv_item__leveldetail)
        TextView tvItemLeveldetail;
        //阶段时长
        @Bind(R.id.tv_item__time)
        TextView tvItemTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
