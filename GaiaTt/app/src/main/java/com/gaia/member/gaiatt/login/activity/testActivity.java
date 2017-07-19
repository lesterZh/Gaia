package com.gaia.member.gaiatt.login.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.gaia.member.gaiatt.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class testActivity extends Activity {

    Activity mContext;
    @Bind(R.id.et_x)
    EditText etX;
    @Bind(R.id.et_y)
    EditText etY;
    @Bind(R.id.btn_scroll_to)
    Button btnScrollTo;
    @Bind(R.id.btn_scroll_by)
    Button btnScrollBy;
    @Bind(R.id.scroll_view)
    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);


        btnScrollTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(etX.getText().toString().trim());
                int y = Integer.parseInt(etY.getText().toString().trim());
                scrollView.scrollTo(x, y);

            }
        });
        btnScrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(etX.getText().toString().trim());
                int y = Integer.parseInt(etY.getText().toString().trim());
                scrollView.scrollBy(x, y);
            }
        });

    }
}
