package com.category.item.controller.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 카테고리별 최저가
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBrandPriceDto {
    private String categoryName;
    private String brandName;
    private String price;
}
