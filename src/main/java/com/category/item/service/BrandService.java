package com.category.item.service;

import com.category.item.controller.requestdto.BrandRequest;
import com.category.item.controller.responsedto.BrandResponse;

public interface BrandService {

    BrandResponse save(BrandRequest request);

    BrandResponse findBrand(String name);

    BrandResponse updateBrand(Long id, BrandRequest request);

    void deleteBrand(Long id);

}
