package com.gaia.member.gaiatt.mygaia.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 改控价在使用时的layout_height应该使用wrap_content
 * 可以通过titleTopBottomPadding、titleLeftRightPadding设置整个title的上下padding 和 左右padding
 * Created by kevin on 2016/4/29.
 */
public class CustomIndicator extends LinearLayout{


    private Paint mPaint; // 画指示符的paint
    private int mTop; // 指示符的top
    private int mLeft; // 指示符的left
    private int mWidth; // 指示符的width
    private int mHeight = 5; //指示器的高度
    private int mColor; // 指示符的颜色
    private int mChildCount; // 子item的个数，用于计算指示符的宽度
    private int leftMargin=5;//当指示器宽度不足一个item宽度时，计算指示器离父边界的距离
    private int itemWidth; //一个item的宽度
    private ViewPager mViewPager;//绑定的ViewPager
    private int mTopMargin;//指示器离上边界的距离
    private int mSelectTextColorId; //被选中字体颜色
    private int mUnselectTextColorId; //未被选中字体颜色
    private float mTitleTextSize; //标题字体大小
    private int mTitleSpacing;  //标题间距

    private int mTitleTopBottomPadding;
    private int mTitleLeftRightPadding;

    private int currentPosition=0;
    private int lastPosition=0;

    private CustomScroll scrollView;
    private LinearLayout innerView;
    private int mScrollX=0;

    private Context mContext;
    private AttributeSet attributeSet;

    Rect rect =new Rect(); //矩形指示器
    Map<Integer,TextView> map=new HashMap<>();

    public CustomIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        this.attributeSet=attrs;

        setBackgroundColor(Color.TRANSPARENT);  // 必须设置背景，否则onDraw不执行
        setOrientation(HORIZONTAL);
        // 获取自定义属性 指示器的颜色等
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomIndicator, 0, 0);
        mColor = ta.getColor(R.styleable.CustomIndicator_indicatorColor,
                getResources().getColor(android.R.color.holo_blue_dark));

        mWidth= (int) ta.getDimension(R.styleable.CustomIndicator_indicatorWidth,20);
        mHeight= (int) ta.getDimension(R.styleable.CustomIndicator_indicatorHeight,5);
        mTopMargin= (int) ta.getDimension(R.styleable.CustomIndicator_indicatorMarginTop, 2);
        mSelectTextColorId=ta.getColor(R.styleable.CustomIndicator_selectTextColor,
                getResources().getColor(android.R.color.holo_blue_dark));
        mUnselectTextColorId=ta.getColor(R.styleable.CustomIndicator_unSelectTextColor,
                getResources().getColor(android.R.color.black));
        mTitleTextSize = ta.getDimension(R.styleable.CustomIndicator_titleTextSize, 10);
        mTitleSpacing= (int) ta.getDimension(R.styleable.CustomIndicator_titleSpacing,10);
        mTitleTopBottomPadding= (int) ta.getDimension(R.styleable.CustomIndicator_titleTopBottomPadding,10);
        mTitleLeftRightPadding= (int) ta.getDimension(R.styleable.CustomIndicator_titleLeftRightPadding,10);

        ta.recycle();


        scrollView =new CustomScroll(context,attrs);
        scrollView.setFillViewport(true);
        //不保存状态
        scrollView.setSaveEnabled(false);
        scrollView.setSaveFromParentEnabled(false);
        scrollView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setOnScrollListener(new CustomScroll.onScrollListener() {
            @Override
            public void onScroll() {
                scroll(mViewPager.getCurrentItem(), 0);
            }
        });

        innerView=new LinearLayout(context,attrs);
        innerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        innerView.setOrientation(HORIZONTAL);
        scrollView.addView(innerView);
        mScrollX= scrollView.getScrollX();
        addView(scrollView);



        // 初始化paint
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);

        if(isInEditMode()){
            inEditMode();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTop = getMeasuredHeight()+mTopMargin;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight()+ mHeight+mTopMargin;

        View view=innerView.getChildAt(0);
        if(view!=null){
            itemWidth=view.getMeasuredWidth();
        }else{
            itemWidth=0;
        }
//        itemWidth = width / mChildCount;
        if(mWidth>= itemWidth){
            mWidth= itemWidth;
        }else{
            leftMargin=(itemWidth -mWidth)/2;
        }
        setMeasuredDimension(width, height);
    }

    /**
     * 指示符滚动
     * @param position 现在的位置
     * @param offset  偏移量 0 ~ 1
     */
    private void scroll(int position, float offset) {
        mScrollX= scrollView.getScrollX();
        mLeft = (int) ((position + offset) * itemWidth)+leftMargin-mScrollX;
        invalidate(); //这会触发OnDraw()
    }

    /**
     * 绑定ViewPager
     * @param viewPager
     */
    public void bindViewPager( ViewPager viewPager){
        if (viewPager == null) return;

        this.mViewPager =viewPager;
        lastPosition=currentPosition=mViewPager.getCurrentItem();
        setTextColor(lastPosition,currentPosition);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                scrollTitle(currentPosition);
                setTextColor(lastPosition, currentPosition);
                lastPosition = currentPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     *
     * @param lastPosition 上个指示条的位置
     * @param currentPosition 当前指示条的位置
     */
    private void setTextColor(int lastPosition,int currentPosition){

             TextView textView;

            if(currentPosition==lastPosition){
                textView= map.get(currentPosition);
                textView.setTextColor(mSelectTextColorId);
            }else{
                textView= map.get(currentPosition);
                textView.setTextColor(mSelectTextColorId);
                textView= map.get(lastPosition);
                textView.setTextColor(mUnselectTextColorId);
            }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 圈出一个矩形
        rect.set(mLeft, mTop, mLeft + mWidth, mTop + mHeight);
        canvas.drawRect(rect, mPaint); // 绘制该矩形
        super.onDraw(canvas);
    }

    private void scrollTitle(int position){
        TextView textView=map.get(position);
        int textLeft=textView.getLeft();
        int textRight=textView.getRight();
        if(textLeft-mScrollX<0){
            scrollView.smoothScrollBy(textLeft - mScrollX, 0);
        }else if(textRight-mScrollX> scrollView.getMeasuredWidth()){
            scrollView.smoothScrollBy(textRight-mScrollX- scrollView.getMeasuredWidth(),0);
        }
    }

    public void setTitles(final List<String> titles){
        mChildCount=titles.size();
        for(int i=0;i<mChildCount;i++){
            final TextView text=new TextView(mContext,attributeSet);
            final int position=i;
            text.setLayoutParams(new LayoutParams(0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,(float)1));
//            text.setPadding(mTitleSpacing, mTitleSpacing, mTitleSpacing, mTitleSpacing);
            text.setPadding(mTitleLeftRightPadding, mTitleTopBottomPadding, mTitleLeftRightPadding, mTitleTopBottomPadding);

            text.setText(titles.get(i));
            text.setTextSize(px2sp(mContext, mTitleTextSize));
            text.setTextColor(mUnselectTextColorId);
            text.setGravity(Gravity.CENTER);
            map.put(i, text);
            text.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mViewPager != null)
                        mViewPager.setCurrentItem(position, true);
                }
            });
            innerView.addView(text, i);
        }
    }

    private void inEditMode(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<8;i++){
            list.add("哈哈");
        }
        setTitles(list);
    }

    public int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}
