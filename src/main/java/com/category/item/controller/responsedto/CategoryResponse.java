package com.category.item.controller.responsedto;

import com.category.item.domain.Brand;
import com.category.item.domain.Category;
import lombok.Data;

@Data
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;

    public static CategoryResponse ofDomain(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.categoryId = category.getId();
        categoryResponse.categoryName = category.getName();

        return categoryResponse;
    }
}
