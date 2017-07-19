package com.gaia.member.gaiatt.message.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.message.bean.MessageBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by zhangHaiTao on 2016/5/3.
 */
public class MessageAdapter extends BaseAdapter {
    private Context mContext;

    List<MessageBean> messages;

    public MessageAdapter(Context context, List<MessageBean> msgs) {
        mContext = context;
        messages = msgs;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_list_message, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessageBean messageBean = (MessageBean) getItem(position);
        viewHolder.tvMessageTitle.setText(messageBean.getTitle());
        viewHolder.tvMessageContet.setText(messageBean.getContent());
        viewHolder.tvMessageDate.setText("2016-4-"+(position+1));
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_message_title)
        TextView tvMessageTitle;
        @Bind(R.id.tv_message_contet)
        TextView tvMessageContet;
        @Bind(R.id.tv_message_date)
        TextView tvMessageDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
