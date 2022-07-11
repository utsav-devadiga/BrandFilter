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
import android.widget.Toast;

import com.brands.fliter.R;
import com.brands.fliter.brandrepo.BrandViewModel;
import com.brands.fliter.databinding.FragmentBrandBinding;
import com.brands.fliter.databinding.FragmentHomeBinding;
import com.brands.fliter.reponseClass.BrandName;
import com.brands.fliter.reponseClass.FilterData;
import com.brands.fliter.reponseClass.Hierarchy;
import com.brands.fliter.ui.BrandAdapter;
import com.brands.fliter.ui.OnBrandSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class BrandFragment extends Fragment implements OnBrandSelectedListener {

    FragmentBrandBinding binding;
    View view;
    BrandViewModel brandViewModel;
    BrandAdapter adapter;
    NavController navController;
    List<BrandName> brandList = new ArrayList<>();
    List<BrandName> selectedBrandList = new ArrayList<>();


    public BrandFragment() {
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
        binding = FragmentBrandBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        brandViewModel = new ViewModelProvider(requireActivity()).get(BrandViewModel.class);

        adapter = new BrandAdapter(new ArrayList<BrandName>(), requireContext(), this, brandList);
        binding.brandCycle.setAdapter(adapter);
        binding.brandCycle.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayout.VERTICAL));

        //to navigate
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        brandViewModel.getBrandResponseData().observe(getViewLifecycleOwner(), brandResponse -> {
            if (brandResponse != null) {

                ArrayList<BrandName> brandList = new ArrayList<>();

                //looping to get all the possible brands under various nodes
                for (FilterData filter : brandResponse.getFilterData()) {
                    for (Hierarchy hierarchy : filter.getHierarchy()) {
                        Log.d("ADDED", "BRANDS: " + hierarchy.getBrandNameList().toString());
                        brandList.addAll(hierarchy.getBrandNameList());
                    }
                }

                adapter = new BrandAdapter(brandList, requireContext(), this, this.brandList);
                // adapter.notifyItemRangeInserted(0, brandList.size());
                binding.brandCycle.setAdapter(adapter);
                Log.d("BRANDS", "onCreateView: " + brandList.toString());
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        binding.filterBtn.setOnClickListener(v -> {

            brandViewModel.setSelectedBrand(brandList);

            NavDirections actions = BrandFragmentDirections.actionBrandFragmentToLocationFragment();
            navController.navigate(actions);
        });

        return view;
    }

    @Override
    public void onBrandSelected(BrandName brand) {
        brandList.add(brand);
        adapter.notifyItemRangeChanged(0, brandList.size());
    }

    @Override
    public void onBrandRemoved(BrandName brand) {
        brandList.remove(brand);
        adapter.notifyItemRangeChanged(0, brandList.size());
    }
}