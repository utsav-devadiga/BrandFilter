package com.brands.fliter.brandrepo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brands.fliter.reponseClass.BrandName;
import com.brands.fliter.reponseClass.BrandResponse;
import com.brands.fliter.reponseClass.Hierarchy;
import com.brands.fliter.reponseClass.LocationName;
import com.brands.fliter.utils.UtilMethods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This class is repository which has {@link BrandResponse} as response Data
 **/

public class BrandRepository {

    private final MutableLiveData<BrandResponse> brandResponseData;
    MutableLiveData<List<LocationName>> selectedLocations;
    MutableLiveData<List<BrandName>> selectedBrand;
    MutableLiveData<List<Hierarchy>> selectedAccountNumber;
    Application application;

    public BrandRepository(Application application) {
        this.brandResponseData = new MutableLiveData<>();
        this.application = application;
        this.selectedBrand = new MutableLiveData<>();
        this.selectedLocations = new MutableLiveData<>();
        this.selectedAccountNumber = new MutableLiveData<>();
    }

    //main data call (DATASOURCE)
    public LiveData<BrandResponse> getBrandResponse(String fileName) {

        //api call or get data from data base
        //in this case we get the data from JSON file
        String response = UtilMethods.getAssetJsonData(application, fileName);
        Type type = new TypeToken<BrandResponse>() {
        }.getType();
        BrandResponse brandResponse = new Gson().fromJson(response, type);

        Log.d("RESPONSE", "getBrandResponse(): " + brandResponse.toString());

        brandResponseData.setValue(brandResponse);

        return brandResponseData;
    }

    //to get the selected locations data
    public MutableLiveData<List<LocationName>> getSelectedLocations() {
        return selectedLocations;
    }

    //to set the selected locations data
    public void setSelectedLocations(List<LocationName> locations) {

        this.selectedLocations.setValue(locations);
    }

    //to get the selected Brands data
    public MutableLiveData<List<BrandName>> getSelectedBrand() {
        return selectedBrand;
    }

    //to set the selected Brands data
    public void setSelectedBrand(List<BrandName> selectedBrand) {
        this.selectedBrand.setValue(selectedBrand);
    }

    //to get the selected Account number data
    public MutableLiveData<List<Hierarchy>> getSelectedAccountNumber() {
        return selectedAccountNumber;
    }

    //to set the selected Account number data
    public void setSelectedAccountNumber(List<Hierarchy> selectedAccountNumber) {
        this.selectedAccountNumber.setValue(selectedAccountNumber);
    }


    //to get the response stored earlier if is null must call new ApiCall
    public LiveData<BrandResponse> getBrandResponseData() {
        return brandResponseData;
    }


    public void clearFilter() {
        this.selectedAccountNumber.setValue(null);
        this.selectedBrand.setValue(null);
        this.selectedLocations.setValue(null);

    }

}
