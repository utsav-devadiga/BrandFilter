package com.brands.fliter.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brands.fliter.databinding.ItemSelectionBinding;
import com.brands.fliter.reponseClass.BrandName;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {

    List<BrandName> brandList;
    Context context;
    OnBrandSelectedListener brandSelectedListener;
    List<BrandName> selectedBrandList;

    public BrandAdapter(List<BrandName> brandList, Context context, OnBrandSelectedListener brandSelectedListener, List<BrandName> selectedBrandList) {
        this.brandList = brandList;
        this.context = context;
        this.selectedBrandList = selectedBrandList;
        this.brandSelectedListener = brandSelectedListener;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSelectionBinding binding = ItemSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BrandViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {


        if (selectedBrandList.contains(brandList.get(position))) {
            holder.binding.checkBox.setChecked(true);
        } else {
            holder.binding.checkBox.setChecked(false);
        }


        holder.binding.textView.setText(brandList.get(position).getBrandName());

        holder.binding.checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                brandSelectedListener.onBrandSelected(brandList.get(position));
            } else {
                brandSelectedListener.onBrandRemoved(brandList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return (brandList == null ? 0 : brandList.size());
    }

    public static class BrandViewHolder extends RecyclerView.ViewHolder {

        ItemSelectionBinding binding;

        public BrandViewHolder(@NonNull ItemSelectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
