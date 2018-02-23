package com.example.cryptologger.data.remote;


import android.util.Log;

/**
 * Created by mayank on 2/22/18
 */

public class ApiUtils {

    //A base url static string
    public static String baseUrl = "https://api.coindesk.com/v1/bpi/";


    public static RestService getRestService() {

        return RetrofitClient.getClient(baseUrl).create(RestService.class);
    }

    public String getDataBetween(String startDate, String endDate) {

        Log.d("getDataBetween: ", baseUrl + "historical/close.json&start=" + startDate + "&end=" + endDate + "&currency=INR");
        return baseUrl + "historical/close.json?start=" + startDate + "&end=" + endDate + "&currency=INR";

    }

    public String getWeeklyData() {
        return baseUrl + "historical/close.json?start=2018-02-16" + "&end=" + "2018-02-23" + "&currency=INR";
    }

    public String getMonthlyData() {

        String data_30_in_INR = baseUrl + "historical/close.json?currency=INR";

        return data_30_in_INR;
    }

    public String getCurrentPrice() {
        String data_current_in_INR = baseUrl + "currentprice/INR.json";

        return data_current_in_INR;
    }

    public String getYearlyData() {
        String yearly = baseUrl + "historical/close.json?start=2017-02-23" + "&end=" + "2018-02-23" + "&currency=INR";
        return yearly;
    }

}
