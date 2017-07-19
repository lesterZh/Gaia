package com.gaia.member.gaiatt.ui;

import android.content.Context;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.utils.DisplayUtil;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;

/**
 * Created by WangHaohan on 2016/4/20.
 */
public class ChartMarkView extends MarkerView {

    private TextView tvMarkText;
    private String mUnit;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */

    public ChartMarkView(Context context,String mUnit,int mColor) {
        super(context, R.layout.item_popuwindow);
        tvMarkText = (TextView) findViewById(R.id.item_popu_tv);
        tvMarkText.setTextColor(mColor);
        this.mUnit=mUnit;
    }


    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            tvMarkText.setText("" + Utils.formatNumber(ce.getHigh(), 0, true)+mUnit);
        } else {
            tvMarkText.setText("" + (int)e.getVal()+mUnit);
        }
    }

    @Override
    public int getXOffset(float xpos) {
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset(float ypos) {
        return -getHeight() - DisplayUtil.dip2px(getContext(), 2);
    }
}
