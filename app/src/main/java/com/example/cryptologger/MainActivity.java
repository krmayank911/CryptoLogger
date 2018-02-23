package com.example.cryptologger;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cryptologger.data.models.CoinDashJsonResponse;
import com.example.cryptologger.data.remote.ApiUtils;
import com.example.cryptologger.ui.ChartDateFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    LineChart chart;
    List<Entry> entries;
    ApiUtils apiUtils;

    TextView currentRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        apiUtils = new ApiUtils();
        new GetRate().execute(apiUtils.getCurrentPrice());
        new GetData().execute(apiUtils.getMonthlyData());

        setupViews();

    }

    public void setupViews() {
        chart = findViewById(R.id.chart);

        currentRate = findViewById(R.id.currentRate);

        final Button yearly, monthly, weekly, show;

        yearly = findViewById(R.id.yearly);
        yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entries.clear();
                new GetData().execute(apiUtils.getYearlyData());
            }
        });

        monthly = findViewById(R.id.monthly);
        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entries.clear();
                new GetData().execute(apiUtils.getMonthlyData());

            }
        });
        weekly = findViewById(R.id.weekly);
        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entries.clear();
                new GetData().execute(apiUtils.getWeeklyData());
            }
        });

        final EditText from, to;
        from = findViewById(R.id.startDate);
        to = findViewById(R.id.endDate);
        show = findViewById(R.id.show);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entries.clear();
                new GetData().execute(apiUtils.getDataBetween(from.getText().toString(), to.getText().toString()));
            }
        });
    }

    public void createPlot() {

        LineDataSet dataSet = new LineDataSet(entries, "BitCoin Rate Index");
        dataSet.setColor(getResources().getColor(R.color.colorAccent));
        dataSet.setValueTextColor(getResources().getColor(R.color.colorPrimaryDark));

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();

        XAxis xAxis = chart.getXAxis();
        ;
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
//        xAxis.setValueFormatter(new ChartDateFormatter());

        YAxis yAxis_left = chart.getAxisLeft();
        yAxis_left.setTextSize(0f);
        YAxis yAxis = chart.getAxisRight();
        yAxis.setTextColor(Color.BLACK);
        yAxis.setValueFormatter(new LargeValueFormatter("₹"));
    }


    public class GetData extends AsyncTask<String, Void, String> {

        CoinDashJsonResponse coinDashJsonResponse;

        @Override
        protected String doInBackground(String... strings) {

            String url = strings[0];
            coinDashJsonResponse = new CoinDashJsonResponse(retrieveJson(url));
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            coinDashJsonResponse.logResponse();
            entries = coinDashJsonResponse.getKeyValueData();
            createPlot();

        }
    }

    public class GetRate extends AsyncTask<String, Void, String> {

        CoinDashJsonResponse coinDashJsonResponse;

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            coinDashJsonResponse = new CoinDashJsonResponse(retrieveJson(url));
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            currentRate.setText(coinDashJsonResponse.getCurrentRate());
        }
    }

    public String retrieveJson(String src) {

        try {
            URL url = new URL(src);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (httpURLConnection.getResponseCode() != 200) {

                Log.d(TAG, "Connection Response:" + httpURLConnection.getResponseMessage());

            } else {

                Log.d(TAG, "Connection Response:" + httpURLConnection.getResponseMessage());

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                bufferedReader.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "null";
    }

}
