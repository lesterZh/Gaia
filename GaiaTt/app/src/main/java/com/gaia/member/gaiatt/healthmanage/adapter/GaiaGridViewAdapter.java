/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: GaiaGridViewAdapter
 * @Package com.gaia.member.gaiatt.healthmanage.adapter
 * @Description: GridView适配器
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

import com.gaia.member.androidlib.net.bean.AroundGaiaDetailBean.ParamBean.GaiaDetailBean.CouponListBean;
import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.maputils.ToastUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * @ClassName: GaiaGridViewAdapter
 *Description: GridView适配器
 *@author android移动客户端-王浩韩
 * @date 2016/6/15 16:15
 */
public class GaiaGridViewAdapter extends BaseAdapter {

    //上下文
    private Context context;
    //数据源
    private ArrayList<CouponListBean> datas;
    //布局填充期
    private LayoutInflater layoutInflater;
    /***
     * 网格适配器构造函数
     * @param context  上下文
     * @param datas   数据源
     * */
    public GaiaGridViewAdapter(Context context, ArrayList<CouponListBean> datas) {
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
            convertView = layoutInflater.inflate(R.layout.item_gridview_img, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemLlClick.setOnClickListener(mOnClickListener);

        return convertView;
    }

    /**
     * 点击领取
     * */
    View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ToastUtil.show(context, context.getString(R.string.receive_success));
        }
    };

    class ViewHolder {
        //优惠券价值
        @Bind(R.id.item_num)
        TextView itemNum;
        //优惠券描述
        @Bind(R.id.item_dec)
        TextView itemDec;
        //优惠券内容
        @Bind(R.id.item_content)
        TextView itemContent;
        //点击领取
        @Bind(R.id.item_ll_click)
        LinearLayout itemLlClick;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
