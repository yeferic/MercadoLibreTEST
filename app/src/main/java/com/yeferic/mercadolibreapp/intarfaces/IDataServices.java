package com.yeferic.mercadolibreapp.intarfaces;

import com.yeferic.mercadolibreapp.model.Item;

import retrofit2.Call;
import retrofit2.http.Query;

public interface IDataServices {
    
    Call<Item> getItems(@Query("q") String query, @Query("offset") Integer offset, @Query("limit") Integer limit);
}
