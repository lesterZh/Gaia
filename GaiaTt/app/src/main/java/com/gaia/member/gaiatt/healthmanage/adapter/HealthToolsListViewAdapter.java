/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HealthToolsListViewAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: 健康工具列表适配器
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
import android.widget.TextView;

import com.gaia.member.androidlib.net.bean.HealthToolsListBean.ParamBean.ListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.ui.CircleImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author android移动客户端-王浩韩
 * @ClassName: HealthToolsListViewAdapter
 * Description: 健康工具列表适配器
 * @date 2016/6/5 0005 下午 11:30
 */
public class HealthToolsListViewAdapter extends BaseAdapter {
    //上下文
    private Context context;
    //列表数据源
    private ArrayList<ListBean> datas;
    //布局填充器
    private LayoutInflater layoutInflater;
    //本地化工具图标
    private int[] toolsHeadPic = {R.drawable.selector_icon_hesthorytools_3, R.drawable.selector_icon_hesthorytools_1,
            R.drawable.selector_icon_hesthorytools_2, R.drawable.selector_icon_hesthorytools_4};

    /**
     * 适配器构造函数
     *
     * @param context 上下文
     * @param datas   数据源
     */
    public HealthToolsListViewAdapter(Context context, ArrayList<ListBean> datas) {
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
        //列表优化
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_healthtools_listview_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置工具名
        viewHolder.tvToolsName.setText(((ListBean) getItem(position)).getTitle());
        //设置工具内容
        viewHolder.tvToolsIntro.setText(((ListBean) getItem(position)).getDetail());
        //设置工具图标
        viewHolder.imgToolsIcon.setImageResource(toolsHeadPic[position]);
        //设置网络图片
        //ImageLoaderUtils.loadImageView(viewHolder.imgToolsIcon,((ListBean) getItem(position)).getIcon());
        return convertView;
    }


    class ViewHolder {
        //工具图标
        @Bind(R.id.img_tools_icon)
        CircleImageView imgToolsIcon;
        //健康工具名
        @Bind(R.id.tv_tools_name)
        TextView tvToolsName;
        //工具描述
        @Bind(R.id.tv_tools_intro)
        TextView tvToolsIntro;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
