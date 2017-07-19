package com.gaia.member.gaiatt.mygaia.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.gaia.member.gaiatt.ui.PullOrLoadGridView;

/**
 * Created by zhangHaiTao on 2016/5/6.
 * 自定义gridView 解决和scrollview的嵌套冲突
 */
public class CustomGridView extends GridView{
    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    //该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
