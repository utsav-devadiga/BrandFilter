package com.brands.fliter.reponseClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationName {
    @SerializedName("locationName")
    @Expose
    public String locationName;
    @SerializedName("merchantNumber")
    @Expose
    public List<MerchantNumber> merchantNumber = null;

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setMerchantNumber(List<MerchantNumber> merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public String getLocationName() {
        return locationName;
    }

    public List<MerchantNumber> getMerchantNumber() {
        return merchantNumber;
    }

    @Override
    public String toString() {
        return "LocationName{" +
                "locationName='" + locationName + '\'' +
                ", merchantNumber=" + merchantNumber +
                '}';
    }
}
