package gaia.com.componentlibrary.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import gaia.com.componentlibrary.R;


/**
 * Created by 正杰 on 2015/10/16.
 */
public class CustomListView extends LinearLayout{

    private ArrayAdapter adapter;

    private onItemClickListener listener;

    private View footerView=null;

    private int resourceId =0;



    private ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
            , ViewGroup.LayoutParams.WRAP_CONTENT);

    public CustomListView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    public void setAdapter(ArrayAdapter adapter){
        this.adapter=adapter;
        notifyDateChange();
    }

    public ArrayAdapter getAdapter(){
        return adapter;
    }

    public void notifyDateChange(){

        int count;

        if(footerView!=null){
            removeView(footerView);
        }

        count=getChildCount();

        for(int i=count;i<adapter.getCount();i++){
           addItem(i);
        }

        if(footerView!=null){
            addView(footerView,getChildCount());
            footerView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        listener.onItemClick(CustomListView.this,footerView,getChildCount()-1,adapter.getItem(0));
                    }
                }
            });
        }

    }

    public void setOnItemClickListener(onItemClickListener listener){
       this.listener=listener;
    }

    public interface onItemClickListener{
        /**
         *
         * @param parent 简而言之，就是此listview
         * @param view 点击的当前的item
         * @param position 当前点击的item位置
         * @param o 当前点击的view绑定的数据
         */
        void onItemClick(ViewGroup parent, View view, int position,
                         Object o);
    }

    public void addFooterView(View view){
        this.footerView=view;
    }

    public void setDivideBackground(int resourceId){
        this.resourceId =resourceId;
    }

    private void addItem(int i){
        final int index=i;
        final LinearLayout layout=new LinearLayout(getContext());
        layout.setLayoutParams(params);
        layout.setOrientation(VERTICAL);
        View view=adapter.getView(i,null,null);
        layout.addView(view, 0);

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onItemClick(CustomListView.this,layout,index,adapter.getItem(index));
                }
            }
        });

        ImageView imageView=new ImageView(getContext());
        imageView.setLayoutParams(params);

        if(resourceId !=0)
            imageView.setBackgroundResource(resourceId);
        else
            imageView.setBackgroundResource(R.drawable.custom_line);
        layout.addView(imageView, 1);
        addView(layout, i);
    }

    public void insert(Object o,int positon){


        removeAllViews();

        adapter.insert(o, positon);
        notifyDateChange();

    }

//    public Observable getBottomMargin(){
//        return getFooterBottomMargin;
//    }

}
