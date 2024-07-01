package com.category.item.controller.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrandSumPriceDto {
    private Long brandId;
    private int sumPrice;
}
