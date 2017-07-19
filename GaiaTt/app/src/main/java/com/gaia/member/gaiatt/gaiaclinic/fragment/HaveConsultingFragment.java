package com.gaia.member.gaiatt.gaiaclinic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.gaiaclinic.adapter.ConsultingListViewAdapter;
import com.gaia.member.gaiatt.ui.PullOrLoadListView;
import com.gaia.member.gaiatt.utils.AssetsFileUtil;
import com.gaia.member.androidlib.net.bean.ConsultingBean;
import com.gaia.member.androidlib.net.bean.ConsultingParamBean;
import com.gaia.member.gaiatt.utils.gsonutils.GsonTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: HaveConsultingFragment
 * @Package com.gaia.member.gaiatt.gaiaclinic.fragment
 * @Description: 健康咨询已咨询fragment
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class HaveConsultingFragment extends Fragment {
    @Bind(R.id.consulting__have_list_view)
    PullOrLoadListView consultingHaveListView;
    private ConsultingListViewAdapter consultingListViewAdapter;//列表适配器
    private List<ConsultingBean> datasList;//列表数据源
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.consulting_have_layout, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }


    private void initData() {
        datasList=getLocalconsultingData();
        consultingListViewAdapter=new ConsultingListViewAdapter(getActivity(), (ArrayList<ConsultingBean>) datasList);
        consultingHaveListView.setAdapter(consultingListViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 获取本地数据
     * */
    private List getLocalconsultingData() {
        String jsonString= AssetsFileUtil.getJsonStr(getActivity(), "consultinglist.txt");
        List<ConsultingBean> mList=new ArrayList<>();
        if (jsonString != null) {
            ConsultingParamBean consultingParamBean= GsonTools.getConsultingParamBean(jsonString);
            mList=consultingParamBean.getParam().getList();
        }
        return mList;
    }
}
