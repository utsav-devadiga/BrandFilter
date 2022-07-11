package com.brands.fliter.reponseClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MerchantNumber {
    @SerializedName("mid")
    @Expose
    public String mid;
    @SerializedName("outletNumber")
    @Expose
    public List<String> outletNumber = null;


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public List<String> getOutletNumber() {
        return outletNumber;
    }

    public void setOutletNumber(List<String> outletNumber) {
        this.outletNumber = outletNumber;
    }

    @Override
    public String toString() {
        return "MerchantNumber{" +
                "mid='" + mid + '\'' +
                ", outletNumber=" + outletNumber +
                '}';
    }
}
