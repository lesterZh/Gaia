
package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Data container for the RadarChart.
 *
 * @author Philipp Jahoda
 */
public class RadarData extends ChartData<IRadarDataSet> {

    public RadarData() {
        super();
    }

    public RadarData(List<String> xVals,List<String> xValsDate) {
        super(xVals,xValsDate);
    }

    public RadarData(String[] xVals,String[] xValsDate) {
        super(xVals,xValsDate);
    }

    public RadarData(List<String> xVals,List<String> xValsDate, List<IRadarDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public RadarData(String[] xVals,String[] xValsDate, List<IRadarDataSet> dataSets) {
        super(xVals,xValsDate, dataSets);
    }

    public RadarData(List<String> xVals,List<String> xValsDate,IRadarDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    public RadarData(String[] xVals,String[] xValsDate, IRadarDataSet dataSet) {
        super(xVals,xValsDate, toList(dataSet));
    }

    private static List<IRadarDataSet> toList(IRadarDataSet dataSet) {
        List<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(dataSet);
        return sets;
    }
}
