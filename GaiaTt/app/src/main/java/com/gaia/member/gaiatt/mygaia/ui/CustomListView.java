package com.gaia.member.gaiatt.mygaia.ui;
/**
 * @Title: CustomListView
 * @Package com.gaia.member.gaiatt.mygaia.ui
 * @Description: TODO
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/19 16:11
 * @version V1.0
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *
 *@ClassName: CustomListView
 * Description:解决ListView和ScrollView的嵌套冲突问题
 * @author Android客户端开发组-zhangHaiTao
 * @date 2016/5/19 16:11
 *
 */
public class CustomListView  extends ListView{

    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //该自定义控件只是重写了onMeasure方法，使其不会出现滚动条
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
