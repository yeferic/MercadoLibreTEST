package com.yeferic.mercadolibreapp.intarfaces;

import com.yeferic.mercadolibreapp.model.Item;
import com.yeferic.mercadolibreapp.model.ProductDetail;
import com.yeferic.mercadolibreapp.model.QueryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDataServices {

    /**
     *
     * @param query Text to search items
     * @param offset Use to paging the result
     * @param limit Limit of items on service's response
     * @return A QueryResult object
     * @see com.yeferic.mercadolibreapp.model.QueryResult
     */
    @GET("sites/MCO/search")
    Call<QueryResult> getItems(@Query("q") String query, @Query("offset") Integer offset, @Query("limit") Integer limit);

    /**
     *
     * @param id ID of the selected item
     * @return A ProductDetaile object
     * @see com.yeferic.mercadolibreapp.model.ProductDetail
     */
    @GET("items/{id}")
    Call<ProductDetail> getProductDetail(@Path("id") String id);
}
