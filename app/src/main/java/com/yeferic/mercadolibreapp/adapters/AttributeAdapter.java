package com.yeferic.mercadolibreapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yeferic.mercadolibreapp.databinding.ListItemBinding;
import com.yeferic.mercadolibreapp.intarfaces.ICustomClickListener;
import com.yeferic.mercadolibreapp.model.Item;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> implements ICustomClickListener {

    private ICustomClickListener fragmentListener;
    private List<Item> items;

    public ItemsAdapter(ICustomClickListener fragment, List<Item> lsItems){
        this.fragmentListener = fragment;
        this.items = lsItems == null ? new ArrayList<>() : lsItems;
    }

    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemBinding itemBinding = ListItemBinding.inflate(layoutInflater, parent, false);
        return new ItemViewHolder(itemBinding, this);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemViewHolder holder, int position) {
        Item product = items.get(position);
        holder.setItemToBinding(product);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(int p) {
        fragmentListener.onItemClick(p);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ListItemBinding binding;
        private ICustomClickListener listener;

        public ItemViewHolder(ListItemBinding binding, ICustomClickListener clickListener){
            super(binding.getRoot());
            this.binding = binding;
            this.listener = clickListener;
            itemView.setOnClickListener(this);
        }

        public void setItemToBinding(Item item) {
            binding.setListItem(item);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            this.listener.onItemClick(getAdapterPosition());
        }
    }

}
