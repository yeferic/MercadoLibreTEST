package com.yeferic.mercadolibreapp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.annotations.SerializedName;
import com.yeferic.mercadolibreapp.R;

public class Picture {
    @SerializedName("url")
    private String url;

    public Picture(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @BindingAdapter({"loadImage"})
    public static void loadImage(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.no_image)
                .into(imageView);
    }
}
