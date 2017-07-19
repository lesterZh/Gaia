package com.gaia.member.gaiatt.ui;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.gaia.member.gaiatt.R;

public class IndicatorView extends View{
	// 上下文
	private Context context;
	//普通圆的颜色
	private  int normalIndicatorColor =0xffffffff;
	// 滑动的圆的颜色
	private  int slidingIndictorColor =0xffff0000;
	// 圆直径
	private Integer circleWidth = 8;
	// 圆间距
	private Integer circleMargin = 8;
	// 上边距
	private Integer topMargin = 6;
	// 下边距
	private Integer bottomMargin = 6;
	// 圆的数量
	private int indicatorsCount;
	private Paint paint;
	
	public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		getAttrsValue(context, attrs, defStyleAttr);
	}

	public IndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getAttrsValue(context, attrs, 0);
	}

	public IndicatorView(Context context) {
		super(context);
		getAttrsValue(context, null, 0);
	}

	//获取圆的属性值
	private void getAttrsValue(Context context, AttributeSet attrs, int defStyleAttr) {
		this.context = context;
		// 获取属性集
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
		// 分别获取属性值
		normalIndicatorColor = typedArray.getColor(R.styleable.IndicatorView_normalCircleColor, 
				normalIndicatorColor);
		slidingIndictorColor = typedArray.getColor(R.styleable.IndicatorView_slideCircleColor, 
				slidingIndictorColor);
		topMargin = typedArray.getDimensionPixelOffset(R.styleable.IndicatorView_topMargin, 
				getPx(topMargin));
		bottomMargin =  typedArray.getDimensionPixelOffset(R.styleable.IndicatorView_bottomMargin,
				getPx(bottomMargin));
		circleWidth = typedArray.getDimensionPixelOffset(R.styleable.IndicatorView_circleWidth, 
				getPx(circleWidth));
		circleMargin = typedArray.getDimensionPixelOffset(R.styleable.IndicatorView_circleMargin,
				getPx(circleMargin));
		// 回收资源
		typedArray.recycle();
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	
	// 获取物理像素
	private int getPx(int dimension){
		float density = context.getResources().getDisplayMetrics().density;
		return (int)(dimension*density+0.5f);
	}
	
	/**
	 * �设置指示器的个数�
	 * @param indicatorsCount
	 */
	public void setIndicatorsCount(int indicatorsCount) {
		this.indicatorsCount = indicatorsCount;
		// 布局参数
		LayoutParams params = getLayoutParams();
		int layout_height = topMargin+bottomMargin+circleWidth;
		int layout_width = indicatorsCount*(circleWidth+circleMargin);
		if(params == null){
			params = new LayoutParams(layout_width, layout_height);
		}else{
			params.width = layout_width;
			params.height = layout_height;
		}
		// 设置布局参数
		setLayoutParams(params);
	}
	
	//滑动的圆的x起点坐标
	private float slidingCircleX;
	public void move(int scrollX,int pagerWidth){
		// 计算要滑动圆的起点X坐标
		// scrollX是Pager上当前滑动的起点位置通过Pager的位置变化来计算圆点位置的变化
		slidingCircleX = (scrollX*1f*(circleWidth+circleMargin)/pagerWidth)%getWidth();
		if(slidingCircleX < 0 ){
			slidingCircleX += getWidth();
		}
		//通知重绘
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(normalIndicatorColor);
		// 先循环绘制普通圆
		for (int i = 0; i < indicatorsCount; i++) {
			canvas.drawCircle(circleWidth/2+i*(circleMargin+circleWidth), topMargin+circleWidth/2, circleWidth/2, paint);
		}
		paint.setColor(slidingIndictorColor);
		// 绘制滑动圆
		canvas.drawCircle(slidingCircleX+circleWidth/2, circleWidth/2+topMargin, circleWidth/2, paint);
		if(slidingCircleX > getWidth() - circleWidth){
			canvas.drawCircle(slidingCircleX+circleWidth/2-getWidth(), circleWidth/2+topMargin, circleWidth/2, paint);
		}
	}
}
