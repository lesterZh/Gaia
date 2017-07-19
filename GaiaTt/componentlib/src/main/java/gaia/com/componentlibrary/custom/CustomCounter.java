package gaia.com.componentlibrary.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gaia.com.componentlibrary.R;


/**
 * Created by kevin on 2016/4/15.
 */
public class CustomCounter extends LinearLayout{

    private int mCounterWidth;
    private int mCounterHeight;
    private int mButtonBackgroundId;
    private int mCounterBackgroundId;
    private float mCounterTextSize;
    private int mButtonTextColorId;
    private int mCounterTextColorId;
    private TextView mReduceButton;
    private TextView mAddButton;
    private TextView mCounter;
    private LayoutParams buttonParams;
    private LayoutParams counterParams;
    private int mCount=0;
    private OnCountChangeListener mListener;

    private Paint paint;
    private Rect rect;


    public CustomCounter(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.TRANSPARENT);
        setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CustomCounter);

        mCounterWidth= (int) array.getDimension(R.styleable.CustomCounter_counterWidth,160);
        mCounterHeight= (int) array.getDimension(R.styleable.CustomCounter_counterHeight,40);
        mCounterTextSize=array.getDimension(R.styleable.CustomCounter_counterTextSize, 10);
        mButtonBackgroundId=array.getResourceId(R.styleable.CustomCounter_buttonBackground,
                android.R.color.darker_gray);
        mCounterBackgroundId=array.getResourceId(R.styleable.CustomCounter_counterBackground,
                android.R.color.white);
        mButtonTextColorId=array.getColor(R.styleable.CustomCounter_buttonTextColor,
                getResources().getColor(android.R.color.white));
        mCounterTextColorId=array.getColor(R.styleable.CustomCounter_buttonTextColor,
                getResources().getColor(android.R.color.holo_blue_dark));
        array.recycle();

        if(mCounterWidth<mCounterHeight*3){
            mCounterWidth=mCounterHeight*3;
        }

        mReduceButton=new TextView(context);
        mAddButton=new TextView(context);
        mCounter =new TextView(context);

        buttonParams=new LayoutParams(mCounterHeight,mCounterHeight);
        counterParams =new LayoutParams(mCounterWidth-mCounterHeight*2,mCounterHeight);

        mReduceButton.setLayoutParams(buttonParams);
        mAddButton.setLayoutParams(buttonParams);
        mCounter.setLayoutParams(counterParams);

        mReduceButton.setText("-");
        mAddButton.setText("+");
        mCounter.setText("0");

        mReduceButton.setBackgroundResource(mButtonBackgroundId);
        mAddButton.setBackgroundResource(mButtonBackgroundId);
        mCounter.setBackgroundResource(mCounterBackgroundId);

        mReduceButton.setTextColor(mButtonTextColorId);
        mAddButton.setTextColor(mButtonTextColorId);
        mCounter.setTextColor(mCounterTextColorId);

        mReduceButton.setTextSize(mCounterTextSize);
        mAddButton.setTextSize(mCounterTextSize);
        mCounter.setTextSize(mCounterTextSize);

        mCounter.setGravity(Gravity.CENTER);
        mReduceButton.setGravity(Gravity.CENTER);
        mAddButton.setGravity(Gravity.CENTER);

        addView(mReduceButton, 0);
        addView(mCounter, 1);
        addView(mAddButton, 2);

        mReduceButton.setClickable(true);
        mAddButton.setClickable(true);
        mReduceButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCount > 0) {
                    mCount--;
                    if(mListener!=null){
                        mListener.onCounterChange(mCount);
                    }
                }
                mCounter.setText(String.valueOf(mCount));
            }
        });
        mAddButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount++;
                mCounter.setText(String.valueOf(mCount));
                if(mListener!=null){
                    mListener.onCounterChange(mCount);
                }
            }
        });

        rect=new Rect();
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    public int getmCount() {
//        mCount=Integer.valueOf(mCounter.getText().toString());
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public void setOnCountChangeListener(OnCountChangeListener listener){
        this.mListener=listener;
    }

    public interface OnCountChangeListener{
        void onCounterChange(int count);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect.set(mReduceButton.getLeft(), mReduceButton.getTop(), mAddButton.getRight(), mAddButton.getBottom());
        canvas.drawRect(rect, paint);
    }
}
