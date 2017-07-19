package gaia.com.componentlibrary.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import gaia.com.componentlibrary.R;

/**
 * Created by kevin on 2016/4/13.
 */
public class CustomCommentStar extends LinearLayout{

    private int lightStarId;
    private int unLightStarId;
    private ImageView mImageView;
    private LayoutParams params;
    private int mImageWidth;
    private int mImageHeight;
    private int mImagePadding;
    private boolean mClickable;
    private int mCount=0;


    public CustomCommentStar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CustomCommentStar);
        lightStarId=array.getResourceId(R.styleable.CustomCommentStar_lightStarSrc,0);
        unLightStarId= array.getResourceId(R.styleable.CustomCommentStar_unLightStarSrc,0);
        mImageHeight= (int) array.getDimension(R.styleable.CustomCommentStar_imageHeight,30);
        mImageWidth= (int) array.getDimension(R.styleable.CustomCommentStar_imageWidth,30);
        mImagePadding= (int) array.getDimension(R.styleable.CustomCommentStar_imagePadding,0);
        mClickable=array.getBoolean(R.styleable.CustomCommentStar_clickable,true);
        init(context);
    }

    void init(Context context){
        setOrientation(HORIZONTAL);
        for(int i=0;i<5;i++){
            final int position=i;
            mImageView=new ImageView(context);
            params=new LayoutParams(mImageWidth,mImageHeight);
            mImageView.setImageResource(unLightStarId);
            mImageView.setLayoutParams(params);
            mImageView.setPadding(mImagePadding, mImagePadding, mImagePadding, mImagePadding);
            if(mClickable)
            mImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setStarState(position);
                }
            });
            addView(mImageView);
        }
    }

    /**
     * 对外接口
     * @param count 需要点亮的数量
     */
    public void setStarcount(int count){
        setStarState(count-1);
    }

    /**
     * 获得点亮的数量
     * @return
     */
    public int getStarCount(){
        return mCount;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    private void setStarState(int position){

        mCount=position+1;

        for(int i=0;i<getChildCount();i++){
            ImageView imageView= (ImageView) getChildAt(i);
            if(i<=position){
                imageView.setImageResource(lightStarId);
            }else{
                imageView.setImageResource(unLightStarId);
            }

        }
    }
}
