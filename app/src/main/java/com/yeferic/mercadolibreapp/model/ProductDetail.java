package com.yeferic.mercadolibreapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetail {
    @SerializedName("title")
    private String title;

    @SerializedName("price")
    private int price;

    @SerializedName("sold_quantity")
    private int soldQuantity;

    @SerializedName("condition")
    private String condition;

    @SerializedName("pictures")
    private List<Picture> pictures;

    @SerializedName("attributes")
    private List<Attribute> attributes;

    @SerializedName("warranty")
    private String warranty;

    public ProductDetail(String title, int price, int soldQuantity, String condition, List<Picture> pictures, List<Attribute> attributes, String warranty) {
        this.title = title;
        this.price = price;
        this.soldQuantity = soldQuantity;
        this.condition = condition;
        this.pictures = pictures;
        this.attributes = attributes;
        this.warranty = warranty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}
