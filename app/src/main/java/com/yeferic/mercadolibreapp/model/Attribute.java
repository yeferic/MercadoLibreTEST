package com.yeferic.mercadolibreapp.model;

import com.google.gson.annotations.SerializedName;

public class Attribute {
    @SerializedName("name")
    private String name;

    @SerializedName("value_name")
    private String valueName;

    public Attribute(String name, String valueName) {
        this.name = name;
        this.valueName = valueName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
