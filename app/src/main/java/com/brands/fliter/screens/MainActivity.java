package com.brands.fliter.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.brands.fliter.R;
import com.brands.fliter.brandrepo.BrandViewModel;
import com.brands.fliter.databinding.ActivityMainBinding;
import com.brands.fliter.utils.AppServerConstants;
import com.brands.fliter.utils.UtilMethods;

public class MainActivity extends AppCompatActivity {

    BrandViewModel brandViewModel;
    NavController navController;
    ActivityMainBinding binding;
    View view;

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this,navController);


        brandViewModel = new ViewModelProvider(this).get(BrandViewModel.class);

        brandViewModel.getBrandResponse("brand.json").observe(this, brandResponse -> {
            //we get the response here!
            if (brandResponse.getStatus().equals(AppServerConstants.SUCCESS)) {

            } else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}