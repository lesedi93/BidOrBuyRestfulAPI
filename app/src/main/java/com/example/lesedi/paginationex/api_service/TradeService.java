package com.example.lesedi.paginationex.api_service;

import com.example.lesedi.paginationex.models.Trade;
import com.example.lesedi.paginationex.models.TradeResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


public interface TradeService
{
    @GET("services/v3/tradesearch")

    Call<TradeResults> getTradeSearch(
            @Header("X-BOB-AUTHID") String auth_id,
            @Header("X-BOB-PLATFORM") int platform,
            @Header("X-BOB-CID") long c_id
    );

}
