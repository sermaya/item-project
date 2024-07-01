package com.category.item.controller.responsedto;

import lombok.Data;

import java.util.List;

@Data
public class BrandMinPriceResponse {
    public String brandName;
    public List<CategoryPriceDto> items;
    public String totalPrice;
}
