package gaia.com.componentlibrary.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by kevin on 2016/4/19.
 */
public class CustomToolBar extends RelativeLayout{

    private Context mContext;
    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        getChildAt(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activity= (Activity) mContext;
                activity.finish();

                if(mContext.getClass()==Activity.class){

                }
            }
        });
    }
}
