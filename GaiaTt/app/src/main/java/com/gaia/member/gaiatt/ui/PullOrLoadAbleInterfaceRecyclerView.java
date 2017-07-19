package com.gaia.member.gaiatt.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.gaia.member.gaiatt.adapter.RecyclerViewAdapter;
import com.gaia.member.gaiatt.interfaces.PullOrLoadAbleInterface;

/**
 * Created by baiyuanwei on 16/4/6.
 */
public class PullOrLoadAbleInterfaceRecyclerView extends RecyclerView implements PullOrLoadAbleInterface {

    public PullOrLoadAbleInterfaceRecyclerView(Context context) {
        super(context);
    }

    public PullOrLoadAbleInterfaceRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullOrLoadAbleInterfaceRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canRefresh() {
        return ((RecyclerViewAdapter)getAdapter()).getIsHeader();
    }

    @Override
    public boolean canLoadMore() {


        return ((RecyclerViewAdapter)getAdapter()).getIsFooter();
     }
}
