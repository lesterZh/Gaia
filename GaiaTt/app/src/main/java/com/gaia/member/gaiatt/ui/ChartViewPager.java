package com.gaia.member.gaiatt.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by WangHaohan on 2016/5/5.
 */
public class ChartViewPager extends ViewPager {


    private boolean noScroll = true;
    public ChartViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChartViewPager(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {

    }


    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    private int currentY;
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        switch (arg0.getAction()){
            case MotionEvent.ACTION_DOWN:
                currentY= (int) arg0.getY();
                if (currentY<height){
                    if (noScroll)
                        return false;
                    else
                        return super.onTouchEvent(arg0);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(arg0);
    }

    private int height=900;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {


        switch (arg0.getAction()){
            case MotionEvent.ACTION_DOWN:
                currentY= (int) arg0.getY();
                if (currentY<height){
                    if (noScroll)
                        return false;
                    else
                        return super.onInterceptTouchEvent(arg0);
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }


}
