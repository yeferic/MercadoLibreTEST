package com.yeferic.mercadolibreapp.intarfaces;

import com.yeferic.mercadolibreapp.model.Item;
import com.yeferic.mercadolibreapp.model.QueryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDataServices {

    @GET("sites/MCO/search")
    Call<QueryResult> getItems(@Query("q") String query, @Query("offset") Integer offset, @Query("limit") Integer limit);
}
