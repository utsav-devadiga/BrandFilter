package com.brands.fliter.reponseClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BrandName {
    @SerializedName("brandName")
    @Expose
    public String brandName;
    @SerializedName("locationNameList")
    @Expose
    public List<LocationName> locationNameList = null;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<LocationName> getLocationNameList() {
        return locationNameList;
    }

    public void setLocationNameList(List<LocationName> locationNameList) {
        this.locationNameList = locationNameList;
    }

    @Override
    public String toString() {
        return "BrandName{" +
                "brandName='" + brandName + '\'' +
                ", locationNameList=" + locationNameList +
                '}';
    }
}
