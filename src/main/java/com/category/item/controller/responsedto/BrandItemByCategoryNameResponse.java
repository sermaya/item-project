package com.category.item.controller.responsedto;

import lombok.Data;

import java.util.List;

@Data
public class BrandItemByCategoryNameResponse {
    private String categoryName;
    private List<BrandItemDto> minPriceItems;
    private List<BrandItemDto> maxPriceItems;
}
