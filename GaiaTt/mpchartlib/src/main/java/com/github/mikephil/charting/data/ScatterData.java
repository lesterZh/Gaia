
package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;
import java.util.List;

public class ScatterData extends BarLineScatterCandleBubbleData<IScatterDataSet> {

    public ScatterData() {
        super();
    }
    
    public ScatterData(List<String> xVals,List<String> xValsDate) {
        super(xVals,xValsDate);
    }

    public ScatterData(String[] xVals,String[] xValsDate) {
        super(xVals,xValsDate);
    }

    public ScatterData(List<String> xVals,List<String> xValsDate, List<IScatterDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public ScatterData(String[] xVals,String[] xValsDate, List<IScatterDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public ScatterData(List<String> xVals,List<String> xValsDate, IScatterDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    public ScatterData(String[] xVals,String[] xValsDate,IScatterDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    private static List<IScatterDataSet> toList(IScatterDataSet dataSet) {
        List<IScatterDataSet> sets = new ArrayList<IScatterDataSet>();
        sets.add(dataSet);
        return sets;
    }

    /**
     * Returns the maximum shape-size across all DataSets.
     * 
     * @return
     */
    public float getGreatestShapeSize() {

        float max = 0f;

        for (IScatterDataSet set : mDataSets) {
            float size = set.getScatterShapeSize();

            if (size > max)
                max = size;
        }

        return max;
    }
}
