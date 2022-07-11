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
import com.brands.fliter.databinding.FragmentAccountNumberBinding;
import com.brands.fliter.reponseClass.BrandName;
import com.brands.fliter.reponseClass.FilterData;
import com.brands.fliter.reponseClass.Hierarchy;
import com.brands.fliter.ui.AccountNumberAdapter;
import com.brands.fliter.ui.BrandAdapter;
import com.brands.fliter.ui.OnAccountNumberSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class AccountNumberFragment extends Fragment implements OnAccountNumberSelectedListener {


    FragmentAccountNumberBinding binding;
    BrandViewModel brandViewModel;
    View view;
    AccountNumberAdapter adapter;
    List<Hierarchy> AccountNumberList = new ArrayList<>();
    NavController navController;

    public AccountNumberFragment() {
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
        binding = FragmentAccountNumberBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        brandViewModel = new ViewModelProvider(requireActivity()).get(BrandViewModel.class);



        binding.accountNumberCycle.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayout.VERTICAL));

        //to navigate
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        brandViewModel.getBrandResponseData().observe(getViewLifecycleOwner(), brandResponse -> {
            if (brandResponse != null) {

                ArrayList<Hierarchy> accountList = new ArrayList<>();

                //looping to get all the possible brands under various nodes
                for (FilterData filter : brandResponse.getFilterData()) {
                    for (Hierarchy hierarchy : filter.getHierarchy()) {
                        Log.d("ADDED ", "ACCOUNT: " + hierarchy.getAccountNumber().toString());
                        accountList.add(hierarchy);
                    }
                }

                adapter = new AccountNumberAdapter(accountList, requireContext(), this, AccountNumberList);
                // adapter.notifyItemRangeInserted(0, brandList.size());
                binding.accountNumberCycle.setAdapter(adapter);
                Log.d("ACCOUNT ", "onCreateView: " + accountList.toString());
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        binding.filterBtn.setOnClickListener(v -> {

            brandViewModel.setSelectedAccountNumber(AccountNumberList);
            NavDirections actions = AccountNumberFragmentDirections.actionAccountNumberFragmentToBrandFragment();
            navController.navigate(actions);
        });

        return view;
    }

    @Override
    public void onAccountSelected(Hierarchy hierarchy) {
        AccountNumberList.add(hierarchy);
        adapter.notifyItemRangeChanged(0, AccountNumberList.size());
    }

    @Override
    public void onAccountRemoved(Hierarchy hierarchy) {
        AccountNumberList.remove(hierarchy);
        adapter.notifyItemRangeChanged(0, AccountNumberList.size());

    }
}