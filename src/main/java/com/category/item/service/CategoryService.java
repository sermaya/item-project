package com.category.item.service;

import com.category.item.controller.requestdto.BrandRequest;
import com.category.item.controller.requestdto.CategoryRequest;
import com.category.item.controller.responsedto.BrandResponse;
import com.category.item.controller.responsedto.CategoryResponse;

public interface CategoryService {
    CategoryResponse save(CategoryRequest request);

    CategoryResponse findCategory(String name);

}
