package com.gaia.member.gaiatt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import java.util.ArrayList;

/**
 * Created by baiyuanwei on 16/4/7.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> datas;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, ArrayList<String> datas){
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
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item,parent,false);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.item_text);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText((String)getItem(position));

        return convertView;
    }

    private class ViewHolder{
        private TextView textView;
    }
}
