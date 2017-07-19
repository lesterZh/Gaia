package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;

import java.util.ArrayList;
import java.util.List;

public class CandleData extends BarLineScatterCandleBubbleData<ICandleDataSet> {

    public CandleData() {
        super();
    }

    public CandleData(List<String> xVals,List<String> xValsDate) {
        super(xVals,xValsDate);
    }
    public CandleData(String[] xVals,String[] xValsDate) {
        super(xVals,xValsDate);
    }
    public CandleData(List<String> xVals,List<String> xValsDate, List<ICandleDataSet> dataSets) {
        super(xVals, xValsDate,dataSets);
    }

    public CandleData(String[] xVals,String[] xValsDate, List<ICandleDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public CandleData(List<String> xVals,List<String> xValsDate, ICandleDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    public CandleData(String[] xVals,String[] xValsDate,  ICandleDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    private static List<ICandleDataSet> toList(ICandleDataSet dataSet) {
        List<ICandleDataSet> sets = new ArrayList<ICandleDataSet>();
        sets.add(dataSet);
        return sets;
    }
}
