package com.example.cryptologger.data.models;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;


/**
 * Created by mayank on 2/22/18
 */

public class CoinDashJsonResponse {


    public String jsonResponse;
    public List<Entry> entries = new ArrayList<>();

    public String TAG = "CounDashJsonResponse";

    public CoinDashJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public void logResponse() {
        Log.d(TAG, "Response" + jsonResponse);
    }

    public List<Entry> getKeyValueData() {

        try {
            JSONObject object = new JSONObject(jsonResponse).getJSONObject("bpi");

            String[] list = object.toString().replace("{", "").replace("}", "").split(",");

            int i = 0;
            while (i < list.length) {
                splitNadd(list[i]);
                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entries;
    }

    public void splitNadd(String key_value) {

        String[] key = key_value.split(":");
        Log.d(TAG, "key: " + key[0] + ", value: " + key[1]);
        key[0] = (String) key[0].subSequence(1, 11);

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(key[0]);


//            dataPairs.put(date,parseDouble(key[1]));
//            Log.d(TAG, "Date: " + date.getTime());


            float value = (float) parseDouble(key[1]);
            entries.add(new Entry(date.getTime(), value));

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getCurrentRate() {

        String rate = "";
        try {
            JSONObject object = new JSONObject(jsonResponse).getJSONObject("bpi").getJSONObject("INR");
            rate = "BitCoin Current Rate: ₹" + object.getString("rate");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rate;
    }
}
