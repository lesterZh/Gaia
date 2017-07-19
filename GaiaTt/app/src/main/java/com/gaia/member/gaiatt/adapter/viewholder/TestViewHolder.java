package com.gaia.member.gaiatt.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

/**
 * Created by baiyuanwei on 16/4/5.
 */
public class TestViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public TestViewHolder(View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.item_text);
    }
}
