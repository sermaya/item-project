package com.category.item.service;

import com.category.item.controller.responsedto.BrandItemByCategoryNameResponse;
import com.category.item.controller.responsedto.BrandMinPriceResponse;
import com.category.item.controller.responsedto.CategoryBrandMinPriceResponse;

public interface CompositeService {

    CategoryBrandMinPriceResponse findCategoryMinPrice();

    BrandMinPriceResponse findBrandMinPrice();

    BrandItemByCategoryNameResponse findBrandItemByCategoryName(String categoryName);
}
