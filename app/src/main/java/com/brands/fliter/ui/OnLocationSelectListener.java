package com.brands.fliter.ui;

import com.brands.fliter.reponseClass.LocationName;

public interface OnLocationSelectListener {
    void onLocationSelectListener(LocationName locationName);

    void onLocationRemovedListener(LocationName locationName);
}
