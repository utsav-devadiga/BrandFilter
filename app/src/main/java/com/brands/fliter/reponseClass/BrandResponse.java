package com.brands.fliter.reponseClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BrandResponse {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("errorCode")
    @Expose
    public String errorCode;
    @SerializedName("filterData")
    @Expose
    public List<FilterData> filterData = null;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public List<FilterData> getFilterData() {
        return filterData;
    }

    @Override
    public String toString() {
        return "BrandResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", filterData=" + filterData +
                '}';
    }
}
