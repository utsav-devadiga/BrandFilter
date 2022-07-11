package com.brands.fliter.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brands.fliter.databinding.ItemSelectionBinding;
import com.brands.fliter.reponseClass.BrandName;
import com.brands.fliter.reponseClass.LocationName;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    List<LocationName> locationNameList, selectedLocation;
    Context context;
    OnLocationSelectListener onLocationSelectListener;


    public LocationAdapter(List<LocationName> locationNameList, Context context, OnLocationSelectListener onLocationSelectListener) {
        this.locationNameList = locationNameList;
        this.context = context;
        this.onLocationSelectListener = onLocationSelectListener;
        this.selectedLocation = new ArrayList<>();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSelectionBinding binding = ItemSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LocationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        holder.binding.textView.setText(locationNameList.get(position).getLocationName());

        if (selectedLocation.contains(locationNameList.get(position))){
            holder.binding.checkBox.setChecked(true);
        }else{
            holder.binding.checkBox.setChecked(false);
        }

        holder.binding.checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                selectedLocation.add(locationNameList.get(position));
                onLocationSelectListener.onLocationSelectListener(locationNameList.get(position));
            } else {
                selectedLocation.remove(locationNameList.get(position));
                onLocationSelectListener.onLocationRemovedListener(locationNameList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (locationNameList == null ? 0 : locationNameList.size());
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        ItemSelectionBinding binding;

        public LocationViewHolder(@NonNull ItemSelectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
