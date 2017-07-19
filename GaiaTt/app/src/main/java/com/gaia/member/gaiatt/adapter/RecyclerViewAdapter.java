package com.gaia.member.gaiatt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.adapter.viewholder.TestViewHolder;

import java.util.List;

/**
 * Created by baiyuanwei on 16/4/5.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<TestViewHolder> {

    private Context context;

    private List<String> datas;

    private LayoutInflater layoutInflater;

    //记录是否滑动到底部
    private boolean isFooter = false;

    //记录是否滑动到顶部
    private boolean isHeader = false;

    public RecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 创建View和ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item,parent,false);
        TestViewHolder testViewHolder = new TestViewHolder(view);
        return testViewHolder;
    }

    /**
     * 给控件加载数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
         holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public boolean getIsHeader(){
        return isHeader;
    }

    public boolean getIsFooter(){
        return isFooter;
    }

    public void setIsHeader(boolean b){
        isHeader = b;
    }

    public void setIsFooter(boolean b){
        isFooter = b;
    }
}
