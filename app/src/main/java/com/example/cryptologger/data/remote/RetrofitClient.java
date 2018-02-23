package com.example.cryptologger.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mayank on 2/22/18
 */

public class RetrofitClient {

    // Retrofit instance
    private static Retrofit retrofit = null;

    // Client Method to get a Retrofit instance
    public static Retrofit getClient(String baseUrl) {

        if (retrofit != null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
