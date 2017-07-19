
package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Data object that encapsulates all data associated with a LineChart.
 * 
 * @author Philipp Jahoda
 */
public class LineData extends BarLineScatterCandleBubbleData<ILineDataSet> {

    public LineData() {
        super();
    }

    public LineData(List<String> xVals,List<String> xValsDate) {
        super(xVals,xValsDate);
    }

    public LineData(String[] xVals,String[] xValsDate) {
        super(xVals,xValsDate);
    }

    public LineData(List<String> xVals, List<String> xValsDate,List<ILineDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public LineData(String[] xVals,String[] xValsDate, List<ILineDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public LineData(List<String> xVals,List<String> xValsDate, ILineDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    public LineData(String[] xVals, String[] xValsDate,ILineDataSet dataSet) {
        super(xVals, xValsDate,toList(dataSet));
    }

    private static List<ILineDataSet> toList(ILineDataSet dataSet) {
        List<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(dataSet);
        return sets;
    }
}
