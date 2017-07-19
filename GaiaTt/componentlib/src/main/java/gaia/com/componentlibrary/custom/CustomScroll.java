package gaia.com.componentlibrary.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by kevin on 2016/4/29.
 */
public class CustomScroll extends HorizontalScrollView{

    private onScrollListener listener;

    public CustomScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener!=null)
        listener.onScroll();
    }

    public interface onScrollListener{
        void onScroll();
    }

    public void setOnScrollListener(onScrollListener listener){
        this.listener=listener;
    }
}
