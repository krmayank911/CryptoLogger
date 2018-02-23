package com.example.cryptologger.data.remote;

import com.example.cryptologger.data.models.CoinDashJsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mayank on 2/22/18
 */

public interface RestService {

    @GET("")
    Call<CoinDashJsonResponse> getBitcoinData();

}
