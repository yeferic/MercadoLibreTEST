package com.yeferic.mercadolibreapp.model;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("price")
    public String price;

    @SerializedName("thumbnail")
    public String imageUrl;

    @SerializedName("shipping")
    public Shipping shipping;

    public Item(String id, String title, String price, String imageUrl, Shipping shipping) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
        this.shipping = shipping;
    }
}
