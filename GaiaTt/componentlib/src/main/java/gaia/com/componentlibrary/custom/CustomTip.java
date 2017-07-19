package gaia.com.componentlibrary.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gaia.com.componentlibrary.R;


/**
 * Created by kevin on 2016/4/14.
 */
public class CustomTip extends RelativeLayout{

    private int occlusionWidth;
    private int occlusionHeight;
    private int mTipCount;
    private boolean isFirst=true;
    private int mWidth=0;
    private int mHeight=0;


    View view1;
    TextView view2;


    public CustomTip(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.TRANSPARENT);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CustomTip);
        occlusionWidth= (int) array.getDimension(R.styleable.CustomTip_occlusionWidth,5);
        occlusionHeight= (int) array.getDimension(R.styleable.CustomTip_occlusionHeight,5);
        mTipCount=array.getInteger(R.styleable.CustomTip_tipsCount,0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view1=getChildAt(0);
        view2= (TextView) getChildAt(1);
        if(Integer.valueOf(view2.getText().toString())==0){
            view2.setVisibility(INVISIBLE);
        }else{
            view2.setVisibility(VISIBLE);
        }

        view2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Integer.valueOf(s.toString())==0){
                    view2.setVisibility(INVISIBLE);
                }else{
                    view2.setVisibility(VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            mWidth=view1.getMeasuredWidth()+view2.getMeasuredWidth()-occlusionWidth;
            mHeight= view1.getMeasuredHeight()+view2.getMeasuredHeight()-occlusionHeight;
            setMeasuredDimension(mWidth, mHeight);
            isFirst=false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        view1.layout(0,getMeasuredHeight()-view1.getMeasuredHeight(),view1.getMeasuredWidth(),getMeasuredHeight());
        view2.layout(view1.getRight() - occlusionWidth, 0,
                view1.getRight() - occlusionWidth + view2.getMeasuredWidth(), view2.getMeasuredHeight());
    }

//    public int getmTipCount() {
//        return mTipCount;
//    }

    public void setmTipCount(int mTipCount) {
        this.mTipCount = mTipCount;
        view2.setText(String.valueOf(mTipCount));
    }
}
