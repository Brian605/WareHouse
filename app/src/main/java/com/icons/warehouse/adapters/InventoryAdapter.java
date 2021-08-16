package com.icons.warehouse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.icons.warehouse.databinding.InventoryItemBinding;
import com.icons.warehouse.models.Inventory;

import java.util.List;
import java.util.Locale;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.Holder> {
    private Context context;
    private List<Inventory> inventoryList;
    private List<Inventory> filteredList;
    private ClickListener listener;

    public InventoryAdapter(Context context, List<Inventory> inventoryList, ClickListener listener) {
        this.context = context;
        this.inventoryList = inventoryList;
        this.filteredList = inventoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        InventoryItemBinding binding=InventoryItemBinding.inflate(LayoutInflater.from(context),parent,false);
        Holder holder=new Holder(binding);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v,filteredList.get(holder.getAdapterPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  InventoryAdapter.Holder holder, int position) {
        Inventory inventory=filteredList.get(position);
holder.quantityView.setText(String.format(Locale.getDefault(),"%d %s Available", inventory.getQuantity(), inventory.getUnits()));
   holder.nameView.setText(inventory.getProductName());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        MaterialTextView nameView,quantityView;
        public Holder(@NonNull InventoryItemBinding binding) {
            super(binding.getRoot());
            nameView=binding.itemName;
            quantityView=binding.itemQuantity;
        }
    }

   public interface ClickListener{
        default void onClick(View view,Inventory inventory){

        }
    }
}
