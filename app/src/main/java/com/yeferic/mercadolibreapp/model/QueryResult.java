package com.yeferic.mercadolibreapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QueryResult {
    @SerializedName("results")
    private List<Item> productos;

    @SerializedName("paging")
    private Paging paging;

    public List<Item> getProductos() {
        return productos;
    }

    public void setProductos(List<Item> productos) {
        this.productos = productos;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
