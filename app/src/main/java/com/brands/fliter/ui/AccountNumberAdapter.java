package com.brands.fliter.ui;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.brands.fliter.R;
import com.brands.fliter.databinding.ItemSelectionBinding;
import com.brands.fliter.reponseClass.Hierarchy;

import java.util.ArrayList;
import java.util.List;

public class AccountNumberAdapter extends RecyclerView.Adapter<AccountNumberAdapter.AccountNumberViewHolder> {

    List<Hierarchy> hierarchies, selectedHierarchy;
    Context context;
    OnAccountNumberSelectedListener onAccountNumberSelectedListener;


    public AccountNumberAdapter(List<Hierarchy> hierarchies, Context context, OnAccountNumberSelectedListener onAccountNumberSelectedListener,List<Hierarchy> selectedHierarchy) {
        this.hierarchies = hierarchies;
        this.context = context;
        this.onAccountNumberSelectedListener = onAccountNumberSelectedListener;
        this.selectedHierarchy = selectedHierarchy;
    }

    @NonNull
    @Override
    public AccountNumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSelectionBinding binding = ItemSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AccountNumberViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountNumberViewHolder holder, int position) {



        holder.binding.textView.setText(hierarchies.get(position).getAccountNumber());

        if (selectedHierarchy.contains(hierarchies.get(position))) {
            holder.binding.checkBox.setChecked(true);
        } else {
            holder.binding.checkBox.setChecked(false);
        }

        holder.binding.checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                selectedHierarchy.add(hierarchies.get(position));
                onAccountNumberSelectedListener.onAccountSelected(hierarchies.get(position));
            } else {
                selectedHierarchy.remove(hierarchies.get(position));
                onAccountNumberSelectedListener.onAccountRemoved(hierarchies.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (hierarchies == null ? 0 : hierarchies.size());
    }


    public static class AccountNumberViewHolder extends RecyclerView.ViewHolder {
        ItemSelectionBinding binding;

        public AccountNumberViewHolder(@NonNull ItemSelectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
