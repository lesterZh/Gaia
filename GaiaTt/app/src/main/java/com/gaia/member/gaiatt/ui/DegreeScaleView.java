package com.gaia.member.gaiatt.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.gaia.member.gaiatt.R;

/**
 * Created by baiyuanwei on 16/4/8.
 *
 *  此刻度表是有三种线的
 * 1. 此刻度表最小单位是0.1,为了方便,在画刻度表的时候就乘以10,转换为整数,最后获取刻度值时再除以10
 * 2. 自定义的属性: 请看attrs.xml文件
 * 3. 此view提供了对外通知刻度值的接口OnValueChangeListener
 *
 *
 */
public class DegreeScaleView extends View {

    //默认普通刻度线的颜色
    private final int DEFAULT_DEGREE_LINE_COLOR = Color.BLACK;

    //默认选中线的颜色
    private final int DEFAULT_SELECT_LINE_COLOR = Color.RED;

    //默认刻度值的字体颜色
    private final int DEFAULT_DEGREE_VALUE_TEXT_COLOR = Color.BLUE;

    //默认最大值
    private final int DEFAULT_MAX_VALUE = 100;

    //默认的当前值
    private final int DEFAULT_CURRENT_VALUE = 60;

    //默认刻度值的字体大小
    private final int DEFAULT_TEXT_SIZE = 18;

    //默认刻度最小间隔的宽度
    private final int DEFAULT_DIVIDER_WIDTH = 15;

    //默认三种线的高度
    private final int DEFAULT_LOW_LINE_HEIGHT = 12;
    private final int DEFAULT_MIDDLE_LINE_HEIGHT = 18;
    private final int DEFAULT_HIGH_LINE_HEIGHT = 24;

    //默认选中线的高度
    private final int DEFAULT_SELECT_LINE_HEIGHT = 24;

    //默认刻度线的宽度
    private final int DEFAULT_DEGREE_LINE_WIDTH = 2;

    //默认选中线的宽度
    private final int DEFAULT_SELECT_LINE_WIDTH = 5;


    //最大刻度值,此值是正常最大值的10倍
    private int maxValue;

    //当前的刻度值,此值是正常最大值的10倍
    private int currentValue;

    //画字的笔
    private TextPaint textPaint;

    //画普通刻度线的笔
    private Paint linePaint;

    //画选中刻度的那条线
    private Paint selectLinePaint;

    //字体的大小
    private int textSize;

    //屏幕的密度
    private float density;

    //此view的宽度
    private int width;

    //最小刻度的宽
    private int dividerWidth;

    //三种线的长度
    private int lowLineHeight;
    private int middleLineHeight;
    private int highLineHeight;

    //选中线的长度
    private int selectLineHeight;

    //普通刻度线的宽度
    private int degreeLineWidth;

    //选中刻度线的宽度
    private int selectLineWidth;

    //普通刻度线的颜色
    private int degreeLineColor;

    //选中刻度线的颜色
    private int selectLineColor;

    //刻度值字体的颜色
    private int degreeValueColor;

    //记录手指水平滑动的距离
    private int horizontalMoveX = 0;

    //记录手指滑动开始的位置
    private int lastX;


    //画线时,起点坐标y
    private int lineStartY = 0;

    //滑动器
    private Scroller scroller;

    //速度跟踪器,在onTouchEvent中初始化
    private VelocityTracker velocityTracker;

    //最小运动的速度
    private int minVelocity;

    //向外部通知当前值的接口
    private OnValueChangeListener onValueChangeListener;

    /**
     * 对外提供接口,将数据传出去
     */
    public interface OnValueChangeListener {
        void onValueChange(float value);
    }

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        this.onValueChangeListener = onValueChangeListener;
    }

    public DegreeScaleView(Context context) {
        this(context, null);
    }

    public DegreeScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DegreeScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        //获取屏幕的密度
        density = getContext().getResources().getDisplayMetrics().density;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DegreeScaleView,defStyleAttr,0);

        //此值是正常最大值的10倍
        maxValue = ta.getInteger(R.styleable.DegreeScaleView_max_value,DEFAULT_MAX_VALUE)*10;
        currentValue = ta.getInteger(R.styleable.DegreeScaleView_current_value,DEFAULT_CURRENT_VALUE) * 10;

        //这是一个整型,计算真正字体大小时,需要乘以屏幕的密度
        textSize = ta.getDimensionPixelSize(R.styleable.DegreeScaleView_degree_value_size, (int)(DEFAULT_TEXT_SIZE*density));
        dividerWidth = ta.getDimensionPixelSize(R.styleable.DegreeScaleView_min_degree_width,(int)(DEFAULT_DIVIDER_WIDTH*density));

        lowLineHeight = DEFAULT_LOW_LINE_HEIGHT;
        middleLineHeight = DEFAULT_MIDDLE_LINE_HEIGHT;
        highLineHeight = DEFAULT_HIGH_LINE_HEIGHT;
        selectLineHeight = DEFAULT_SELECT_LINE_HEIGHT;
        degreeLineWidth = DEFAULT_DEGREE_LINE_WIDTH;
        selectLineWidth = DEFAULT_SELECT_LINE_WIDTH;

        degreeLineColor = ta.getColor(R.styleable.DegreeScaleView_degree_line_color,DEFAULT_DEGREE_LINE_COLOR);
        selectLineColor = ta.getColor(R.styleable.DegreeScaleView_select_degree_line_color,DEFAULT_SELECT_LINE_COLOR);
        degreeValueColor = ta.getColor(R.styleable.DegreeScaleView_degree_value_color,DEFAULT_DEGREE_VALUE_TEXT_COLOR);

        ta.recycle();

        init(context);
    }

    private void init(Context context) {

        //初始化滑动器
        scroller = new Scroller(context);

        //获得最小运动速度
        minVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();


        //初始化线的画笔
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(degreeLineColor);
        linePaint.setStrokeWidth(degreeLineWidth);

        //初始化字的画笔
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(degreeValueColor);
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(textSize);

        //此方法的意思是设置横坐标时,以字的中心为标准
        textPaint.setTextAlign(Paint.Align.CENTER);

        //初始化选中线的画笔
        selectLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectLinePaint.setColor(selectLineColor);
        selectLinePaint.setStrokeWidth(selectLineWidth);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        width = getWidth();
        lineStartY = getPaddingTop();

        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawNormalLine(canvas);

        drawSelectLine(canvas);

    }

    /**
     * 画刻度线
     *
     * @param canvas
     */
    private void drawNormalLine(Canvas canvas) {
        canvas.save();

        //最小刻度
        int minDegree = dividerWidth;

        int lowLineH = (int) (lowLineHeight * density);
        int middleLineH = (int) (middleLineHeight * density);
        int highLineH = (int) (highLineHeight * density);

        float positionX = 0;
        int drawCount = 0;
        int startX = width / 2;
        int layoutWidth = width;
        for (int i = 0; drawCount < layoutWidth; i++) {

            //向右画刻度
            positionX = startX - horizontalMoveX + i * dividerWidth;
            if (positionX < width) {

                int lineHeight = 0;

                //当前刻度的值 乘以 10,转换为整数
                int value = currentValue + i;

                //计算是不是最大单元
                int residueDegree = value % 10;

                //计算是不是最大单元的一半
                int halfResidueDegree = value % 5;

                //是最大单元
                if (residueDegree == 0) {
                    lineHeight = highLineH;

                    if (value <= maxValue) {
                        canvas.drawText(String.valueOf(value / 10), positionX, lineHeight + lineStartY + textSize, textPaint);
                    }

                }
                //是最大单元的一半
                else if (halfResidueDegree == 0) {
                    lineHeight = middleLineH;
                } else {
                    lineHeight = lowLineH;
                }

                if (value <= maxValue) {
                    canvas.drawLine(positionX, lineStartY, positionX, lineStartY + lineHeight, linePaint);
                }
            }

            //向左画刻度线
            positionX = startX - horizontalMoveX - i * dividerWidth;
            if (positionX > 0) {

                int lineHeight = 0;

                //当前刻度的值
                int value = currentValue - i;

                //计算是不是最大单元
                int residueDegree = value % 10;

                //计算是不是最大单元的一半
                int halfResidueDegree = value % 5;

                //是最大单元
                if (residueDegree == 0) {
                    lineHeight = highLineH;

                    if (value >= 0) {
                        canvas.drawText(String.valueOf(value / 10), positionX, lineHeight + lineStartY + textSize, textPaint);
                    }
                }
                //是最大单元的一半
                else if (halfResidueDegree == 0) {
                    lineHeight = middleLineH;
                }
                //普通刻度线
                else {
                    lineHeight = lowLineH;
                }

                if (value >= 0) {
                    canvas.drawLine(positionX, lineStartY, positionX, lineStartY + lineHeight, linePaint);
                }
            }

            drawCount += 2 * minDegree;

        }


        canvas.restore();
    }

    /**
     * 画选中的那条线
     *
     * @param canvas
     */
    private void drawSelectLine(Canvas canvas) {
        canvas.save();

        int selectX = width / 2;

        int selectLineH = (int) (selectLineHeight * density);

        canvas.drawLine(selectX, lineStartY, selectX, lineStartY + selectLineH, selectLinePaint);

        canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //初始化速度跟踪器
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scroller.forceFinished(true);
                lastX = (int) event.getX();
                horizontalMoveX = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                horizontalMoveX += (lastX - moveX);

                changeValueAndMove();

                //重置起始位置
                lastX = moveX;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                countValueEnd();
                countVelocityTracker();
                return false;
            default:
                break;

        }
        return true;
    }


    /**
     * 滑动过程中,改变当前值,并通知外部
     */
    private void changeValueAndMove() {

        //计算滑过几个刻度
        int numDegree =  horizontalMoveX / dividerWidth;

        if (Math.abs(numDegree) > 0) {

            //每个刻度代表1
            currentValue += numDegree;

            //水平滑动距离减去已经计算过的距离
            horizontalMoveX -= numDegree * dividerWidth ;

            judgeValueOverBoundary();

            notifyValueChanged();
        }

        postInvalidate();
    }

    /**
     * 最后当手指抬起后,计算当前值
     * <p/>
     * 此方法中必须掉用postInvalidate()方法重绘刻度表
     */
    private void countValueEnd() {

        //将值舍入到最接近的整数
        int numDegree = Math.round(horizontalMoveX / dividerWidth );
//
        currentValue += numDegree;

        judgeValueOverBoundary();

        //重置
        lastX = 0;
        horizontalMoveX = 0;

        notifyValueChanged();
        postInvalidate();
    }

    /**
     * 根据速度滑动
     */
    private void countVelocityTracker() {

        //意思是一秒中运动了多少像素
        velocityTracker.computeCurrentVelocity(1000);

        //得到水平滑动的速度
        float xVelocity = velocityTracker.getXVelocity();

        if (Math.abs(xVelocity) > minVelocity) {
            scroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        }
    }

    /**
     * 判断当前值是否越界
     */
    private void judgeValueOverBoundary() {
        if (currentValue < 0 || currentValue > maxValue) {
            currentValue = currentValue < 0 ? 0 : maxValue;

            //重置水平滑动距离
            horizontalMoveX = 0;

            //强制停止滑动
            scroller.forceFinished(true);
        }
    }

    /**
     * 通知外部当前的刻度值
     */
    private void notifyValueChanged() {
        float value = currentValue / 10f;
        onValueChangeListener.onValueChange(value);
    }

    /**
     * 使用scroller必须要实现的方法
     * 父方法是空的,即 super.computeScroll() 是个空方法
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            if (scroller.getCurrX() == scroller.getFinalX()) {
                countValueEnd();
            } else {
                int xPosition = scroller.getCurrX();
                horizontalMoveX += (lastX - xPosition);
                changeValueAndMove();
                lastX = xPosition;
            }
        }
    }
}
