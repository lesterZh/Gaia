
package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;

import java.util.ArrayList;
import java.util.List;

public class BubbleData extends BarLineScatterCandleBubbleData<IBubbleDataSet> {

    public BubbleData() {
        super();
    }

    public BubbleData(List<String> xVals,List<String> xValsDate) {
        super(xVals,xValsDate);
    }

    public BubbleData(String[] xVals,String[] xValsDate) {
        super(xVals,xValsDate);
    }

    public BubbleData(List<String> xVals,List<String> xValsDate, List<IBubbleDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public BubbleData(String[] xVals, String[] xValsDate, List<IBubbleDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public BubbleData(List<String> xVals, List<String> xValsDate,IBubbleDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    public BubbleData(String[] xVals,String[] xValsDate, IBubbleDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    private static List<IBubbleDataSet> toList(IBubbleDataSet dataSet) {
        List<IBubbleDataSet> sets = new ArrayList<IBubbleDataSet>();
        sets.add(dataSet);
        return sets;
    }

    /**
     * Sets the width of the circle that surrounds the bubble when highlighted
     * for all DataSet objects this data object contains, in dp.
     * 
     * @param width
     */
    public void setHighlightCircleWidth(float width) {
        for (IBubbleDataSet set : mDataSets) {
            set.setHighlightCircleWidth(width);
        }
    }
}
