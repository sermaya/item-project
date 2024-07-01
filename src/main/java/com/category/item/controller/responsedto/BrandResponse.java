package com.category.item.controller.responsedto;

import com.category.item.domain.Brand;
import lombok.Data;

@Data
public class BrandResponse {
    private Long brandId;
    private String brandName;

    public static BrandResponse ofDomain(Brand brand) {
        BrandResponse brandResponse = new BrandResponse();
        brandResponse.brandId = brand.getId();
        brandResponse.brandName = brand.getName();

        return brandResponse;
    }
}
