package com.gaia.member.gaiatt.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.utils.ImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baiyuanwei on 16/3/19.
 */
public class TestActivity extends AppBaseActivity {

    @Bind(R.id.blur_image_view)
    ImageView imageView;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        Bitmap bitmap = ImageUtils.resIdToBitmap(this,R.drawable.test3);
        imageView.setImageBitmap(bitmap);
    }
}
