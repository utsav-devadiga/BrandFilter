package com.brands.fliter.screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.brands.fliter.R;
import com.brands.fliter.brandrepo.BrandViewModel;
import com.brands.fliter.databinding.FragmentHomeBinding;
import com.brands.fliter.databinding.FragmentLocationBinding;
import com.brands.fliter.reponseClass.BrandName;
import com.brands.fliter.reponseClass.FilterData;
import com.brands.fliter.reponseClass.Hierarchy;
import com.brands.fliter.reponseClass.LocationName;
import com.brands.fliter.ui.BrandAdapter;
import com.brands.fliter.ui.LocationAdapter;
import com.brands.fliter.ui.OnLocationSelectListener;

import java.util.ArrayList;
import java.util.List;


public class LocationFragment extends Fragment implements OnLocationSelectListener {
    FragmentLocationBinding binding;
    View view;
    BrandViewModel brandViewModel;
    LocationAdapter adapter;
    List<LocationName> locationNameList = new ArrayList<>();
    NavController navController;

    public LocationFragment() {
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
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        brandViewModel = new ViewModelProvider(requireActivity()).get(BrandViewModel.class);

        adapter = new LocationAdapter(new ArrayList<LocationName>(), requireContext(), this);
        binding.locationCycle.setAdapter(adapter);
        binding.locationCycle.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayout.VERTICAL));


        brandViewModel = new ViewModelProvider(requireActivity()).get(BrandViewModel.class);

        brandViewModel.getBrandResponseData().observe(getViewLifecycleOwner(), brandResponse -> {
            if (brandResponse != null) {
                ArrayList<LocationName> locationList = new ArrayList<>();
                for (FilterData filter : brandResponse.getFilterData()) {
                    for (Hierarchy hierarchy : filter.getHierarchy()) {

                        for (BrandName brandName : hierarchy.getBrandNameList()
                        ) {
                            locationList.addAll(brandName.getLocationNameList());
                        }
                    }
                }
                adapter = new LocationAdapter(locationList, requireContext(), this);
                // adapter.notifyItemRangeInserted(0, brandList.size());
                binding.locationCycle.setAdapter(adapter);
            }
        });

        //to navigate
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        binding.filterBtn.setOnClickListener(view -> {
            brandViewModel.setSelectedLocations(locationNameList);
            navController.navigateUp();
        });
        return view;
    }

    @Override
    public void onLocationSelectListener(LocationName locationName) {
        locationNameList.add(locationName);
    }

    @Override
    public void onLocationRemovedListener(LocationName locationName) {
        locationNameList.remove(locationName);
    }
}