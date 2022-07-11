package com.brands.fliter.reponseClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hierarchy {

    @SerializedName("accountNumber")
    @Expose
    public String accountNumber;
    @SerializedName("brandNameList")
    @Expose
    public List<BrandName> brandNameList = null;


    public String getAccountNumber() {
        return accountNumber;
    }

    public List<BrandName> getBrandNameList() {
        return brandNameList;
    }

    @Override
    public String toString() {
        return "Hierarchy{" +
                "accountNumber='" + accountNumber + '\'' +
                ", brandNameList=" + brandNameList +
                '}';
    }
}
