package com.example.cryptologger.ui;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Created by mayank on 2/23/18
 */

public class ChartDateFormatter implements IValueFormatter, IAxisValueFormatter {


    String TAG = "ChartDateFormatter";
    private SimpleDateFormat dateFormat;


    public ChartDateFormatter() {

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        Log.d(TAG, "getFormattedValue: " + value);
        try {
            return dateFormat.parse("" + value).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

//        Log.d(TAG, "getFormattedValue: "+ (int) value);
        try {
            return dateFormat.parse("" + value).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
