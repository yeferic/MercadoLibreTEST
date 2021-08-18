package com.yeferic.mercadolibreapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yeferic.mercadolibreapp.databinding.ListAttributeBinding;
import com.yeferic.mercadolibreapp.databinding.ListPictureBinding;
import com.yeferic.mercadolibreapp.model.Attribute;
import com.yeferic.mercadolibreapp.model.Picture;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    private List<Picture> pictures;

    public PictureAdapter(List<Picture> lsPictures){
        this.pictures = lsPictures == null ? new ArrayList<>() : lsPictures;
    }

    @NonNull
    @NotNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListPictureBinding pictureBinding = ListPictureBinding.inflate(layoutInflater, parent, false);
        return new PictureViewHolder(pictureBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PictureViewHolder holder, int position) {
        Picture picture = pictures.get(position);
        holder.setItemToBinding(picture);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder {

        private ListPictureBinding binding;

        public PictureViewHolder(ListPictureBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            this.binding.executePendingBindings();
        }

        public void setItemToBinding(Picture item) {
            binding.setPicture(item);
        }
    }
}
