package com.example.lesedi.paginationex.api_service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TradeAPI
{
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://demo.bidorbuy.co.za/")
                    .build();
        }
        return retrofit;
    }

}
