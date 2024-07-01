package com.category.item.controller.responsedto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryBrandMinPriceResponse {
    public List<CategoryBrandPriceDto> items;
    public String totalPrice;
}
