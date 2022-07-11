package com.brands.fliter.brandrepo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brands.fliter.reponseClass.BrandName;
import com.brands.fliter.reponseClass.BrandResponse;
import com.brands.fliter.reponseClass.Hierarchy;
import com.brands.fliter.reponseClass.LocationName;

import java.util.List;

public class BrandViewModel extends AndroidViewModel {

    BrandRepository brandRepository;

    public BrandViewModel(@NonNull Application application) {
        super(application);
        brandRepository = new BrandRepository(application);
    }


    public LiveData<BrandResponse> getBrandResponse(String fileName) {
        return brandRepository.getBrandResponse(fileName);
    }

    public LiveData<BrandResponse> getBrandResponseData() {
        return brandRepository.getBrandResponseData();
    }


    //to get the selected locations data
    public MutableLiveData<List<LocationName>> getSelectedLocations() {
        return brandRepository.getSelectedLocations();
    }

    //to set the selected locations data
    public void setSelectedLocations(List<LocationName> locations) {

        brandRepository.setSelectedLocations(locations);
    }

    //to get the selected Brands data
    public MutableLiveData<List<BrandName>> getSelectedBrand() {
        return brandRepository.getSelectedBrand();
    }

    //to set the selected Brands data
    public void setSelectedBrand(List<BrandName> selectedBrand) {
        brandRepository.setSelectedBrand(selectedBrand);
    }

    //to get the selected Account Number
    public MutableLiveData<List<Hierarchy>> getSelectedAccountNumber() {
        return brandRepository.getSelectedAccountNumber();
    }

    //to set the selected Account Number
    public void setSelectedAccountNumber(List<Hierarchy> accountNumber) {
        brandRepository.setSelectedAccountNumber(accountNumber);
    }

    public void clearFilter(){
        brandRepository.clearFilter();
    }

}
