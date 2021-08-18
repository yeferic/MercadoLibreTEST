package com.yeferic.mercadolibreapp.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.annotations.SerializedName;
import com.yeferic.mercadolibreapp.R;

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

    @BindingAdapter({"loadImage"})
    public static void loadImageFromUrl(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.no_image)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .fitCenter()
                .into(imageView);
    }
}
