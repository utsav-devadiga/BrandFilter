package com.brands.fliter.ui;

import com.brands.fliter.reponseClass.BrandName;

public interface OnBrandSelectedListener {
    void onBrandSelected(BrandName brand);

    void onBrandRemoved(BrandName brand);
}
