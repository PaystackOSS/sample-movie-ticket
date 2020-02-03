package com.paystack.samplemovieticket.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.paystack.samplemovieticket.utils.Constants.BASE_URL;

public class NetworkConfig {

    private static Gson gson = new GsonBuilder().create();

    public static Retrofit configureNetwork = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
