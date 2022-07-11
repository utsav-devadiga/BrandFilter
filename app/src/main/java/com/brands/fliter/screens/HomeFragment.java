package com.brands.fliter.screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brands.fliter.R;
import com.brands.fliter.brandrepo.BrandViewModel;
import com.brands.fliter.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    View view;
    BrandViewModel brandViewModel;
    NavController navController;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        //to navigate
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        binding.filterBtn.setOnClickListener(v -> {
            NavDirections actions = HomeFragmentDirections.actionHomeFragmentToAccountNumberFragment();
            navController.navigate(actions);
        });

        brandViewModel = new ViewModelProvider(requireActivity()).get(BrandViewModel.class);

        brandViewModel.getBrandResponse("brand.json").observe(getViewLifecycleOwner(), brandResponse -> {
            binding.companyNameText.setText(brandResponse.getFilterData().get(0).getCompanyName());
            binding.brandText.setText("Brands : 0");
            binding.brandCount.setText("0");

            binding.locationText.setText("Brands : 0");
            binding.locationCount.setText("0");

            binding.accountNoText.setText("Brands : 0");
            binding.accountCount.setText("0");
        });

        brandViewModel.getSelectedBrand().observe(getViewLifecycleOwner(), brandNames -> {
            if (brandNames != null) {
                binding.brandText.setText("Brands : " + String.valueOf(brandNames.size()));
                binding.brandCount.setText(String.valueOf(brandNames.size()));
            } else {
                binding.brandText.setText("Brands : 0");
                binding.brandCount.setText("0");
            }

        });
        brandViewModel.getSelectedLocations().observe(getViewLifecycleOwner(), locationNames -> {
            if (locationNames != null) {
                binding.locationText.setText("Locations : " + String.valueOf(locationNames.size()));
                binding.locationCount.setText(String.valueOf(locationNames.size()));
            } else {
                binding.locationText.setText("Brands : 0");
                binding.locationCount.setText("0");
            }
        });
        brandViewModel.getSelectedAccountNumber().observe(getViewLifecycleOwner(), accountNumber -> {
            if (accountNumber != null) {
                binding.accountNoText.setText("Acc No. : " + String.valueOf(accountNumber.size()));
                binding.accountCount.setText(String.valueOf(accountNumber.size()));
            } else {
                binding.accountNoText.setText("Brands : 0");
                binding.accountCount.setText("0");
            }
        });


        binding.clearBtn.setOnClickListener(v -> {
            brandViewModel.clearFilter();
        });

        return view;
    }
}