package com.example.cryptologger.ui;


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;


/**
 * Created by mayank on 2/23/18
 */

public class ChartPriceFormatter implements IValueFormatter, IAxisValueFormatter {


    private String mFormat;

    ChartPriceFormatter() {
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return null;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return null;
    }
}
