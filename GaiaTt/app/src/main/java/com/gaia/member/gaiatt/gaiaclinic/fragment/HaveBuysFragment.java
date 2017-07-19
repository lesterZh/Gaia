package com.gaia.member.gaiatt.gaiaclinic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthServiceBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonBean.HealthServiceParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HaveBuysFragment
 * @Package com.gaia.member.gaiatt.gaiaclinic.fragment
 * @Description: 健康服务已购买界面fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class HaveBuysFragment extends Fragment {

    @Bind(R.id.gv_healthservice_havebuy)
    GridView gvHealthserviceHavebuy;
    private View view;
    private List<HealthServiceBean> serviceBeanList;
    private HealthServiceAdapter healthServiceAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.grid_view_havebuys_layout, container, false);
        ButterKnife.bind(this, view);
        initData();//初始化数据
        initView();//初始化视图
        return view;
    }


    private void initData() {
        serviceBeanList=getLocalServiceData();
    }

    private void initView() {

        healthServiceAdapter=new HealthServiceAdapter(getActivity(),serviceBeanList);
        gvHealthserviceHavebuy.setAdapter(healthServiceAdapter);
    }


    /**
     * 获取本地数据
     */
    private List getLocalServiceData() {
        String jsonString = AssetsFileUtil.getJsonStr(getActivity(), "healthservicehavelist.txt");
        List<HealthServiceBean> mList = new ArrayList<>();
        if (jsonString != null) {
            HealthServiceParamBean healthServiceParamBean = GsonTools.getHealthServiceParamBean(jsonString);
            mList = healthServiceParamBean.getParam().getList();
        }
        return mList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * gridView适配器
     */
    class HealthServiceAdapter extends BaseAdapter {
        Context context;
        List<HealthServiceBean> datas;

        public HealthServiceAdapter(Context context, List<HealthServiceBean> datas) {
            this.context = context;
            this.datas = datas;
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
                convertView = View.inflate(context, R.layout.item_gird_healthservice, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            initItemView(position, viewHolder);//初始化网格视图
            return convertView;
        }

        private void initItemView(int position, ViewHolder viewHolder) {
            HealthServiceBean healthServiceBean = (HealthServiceBean) getItem(position);
            viewHolder.tvNameHealthserviceBuys.setText(healthServiceBean.getServiceName());
            viewHolder.tvPriceHealthserviceBuys.setText("¥" + healthServiceBean.getPrice());
            //VIP 级别设置
            viewHolder.tvLevelHealthserviceBuys.setText(healthServiceBean.getLevel());

            //是否可购买
            if (healthServiceBean.getBuyStatus() == CommonConstants.BUYSTATUS) {
                viewHolder.tvDescHealthserviceBuys.setText("不可购");
            } else {
                viewHolder.tvDescHealthserviceBuys.setText("可购");
            }
            viewHolder.tvHealthservices.setText(healthServiceBean.getDescription());
        }

        class ViewHolder {
            @Bind(R.id.iv_healthservice_buys)
            ImageView ivHealthserviceBuys;
            @Bind(R.id.tv_name_healthservice_buys)
            TextView tvNameHealthserviceBuys;
            @Bind(R.id.tv_price_healthservice_buys)
            TextView tvPriceHealthserviceBuys;
            @Bind(R.id.tv_level_healthservice_buys)
            TextView tvLevelHealthserviceBuys;
            @Bind(R.id.tv_desc_healthservice_buys)
            TextView tvDescHealthserviceBuys;
            @Bind(R.id.btn_add_shopping_cart)
            Button btnAddShoppingCart;
            @Bind(R.id.tv_healthservices)
            TextView tvHealthservices;
            @Bind(R.id.rl_healthservice_buys)
            RelativeLayout rlHealthserviceBuys;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
