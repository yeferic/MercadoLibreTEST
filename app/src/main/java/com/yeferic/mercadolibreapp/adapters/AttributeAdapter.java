package com.yeferic.mercadolibreapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yeferic.mercadolibreapp.databinding.ListAttributeBinding;
import com.yeferic.mercadolibreapp.databinding.ListItemBinding;
import com.yeferic.mercadolibreapp.intarfaces.ICustomClickListener;
import com.yeferic.mercadolibreapp.model.Attribute;
import com.yeferic.mercadolibreapp.model.Item;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AttributeAdapter extends RecyclerView.Adapter<AttributeAdapter.AttributeViewHolder> {

    private List<Attribute> attributes;

    public AttributeAdapter(List<Attribute> lsAttributes){
        this.attributes = lsAttributes == null ? new ArrayList<>() : lsAttributes;
    }

    @NonNull
    @NotNull
    @Override
    public AttributeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListAttributeBinding attributeBinding = ListAttributeBinding.inflate(layoutInflater, parent, false);
        return new AttributeViewHolder(attributeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AttributeViewHolder holder, int position) {
        Attribute attribute = attributes.get(position);
        holder.setItemToBinding(attribute);
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public class AttributeViewHolder extends RecyclerView.ViewHolder {

        private ListAttributeBinding binding;

        public AttributeViewHolder(ListAttributeBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setItemToBinding(Attribute item) {
            binding.setAttribute(item);
        }
    }
}
