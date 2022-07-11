package com.brands.fliter.reponseClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterData {
    @SerializedName("Cif")
    @Expose
    public String cif;
    @SerializedName("companyName")
    @Expose
    public String companyName;
    @SerializedName("hierarchy")
    @Expose
    public List<Hierarchy> hierarchy = null;

    public String getCif() {
        return cif;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Hierarchy> getHierarchy() {
        return hierarchy;
    }

    @Override
    public String toString() {
        return "FilterData{" +
                "cif='" + cif + '\'' +
                ", companyName='" + companyName + '\'' +
                ", hierarchy=" + hierarchy +
                '}';
    }
}
