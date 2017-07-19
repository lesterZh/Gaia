
package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;

import java.util.List;

/**
 * Baseclass for all Line, Bar, Scatter, Candle and Bubble data.
 * 
 * @author Philipp Jahoda
 */
public abstract class BarLineScatterCandleBubbleData<T extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>
        extends ChartData<T> {
    
    public BarLineScatterCandleBubbleData() {
        super();
    }
    
    public BarLineScatterCandleBubbleData(List<String> xVals,List<String> xValsDate) {
        super(xVals,xValsDate);
    }
    
    public BarLineScatterCandleBubbleData(String[] xVals,String[] xValsDate) {
        super(xVals,xValsDate);
    }

    public BarLineScatterCandleBubbleData(List<String> xVals,List<String> xValsDate, List<T> sets) {
        super(xVals,xValsDate, sets);
    }

    public BarLineScatterCandleBubbleData(String[] xVals,String[] xValsDate, List<T> sets) {
        super(xVals,xValsDate, sets);
    }
}
