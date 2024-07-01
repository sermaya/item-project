package com.category.item.service;

import com.category.item.controller.requestdto.CategoryRequest;
import com.category.item.controller.responsedto.BrandResponse;
import com.category.item.controller.responsedto.CategoryResponse;
import com.category.item.domain.Brand;
import com.category.item.domain.Category;
import com.category.item.exception.BrandDuplicationException;
import com.category.item.exception.CategoryDuplicationException;
import com.category.item.repository.CategoryJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryJpaAdapter categoryJpaAdapter;

    @Override
    public CategoryResponse save(CategoryRequest request) {
        Category findBrand = categoryJpaAdapter.findByName(request.getName());
        if (findBrand != null) {
            throw new CategoryDuplicationException();
        }

        Category result = categoryJpaAdapter.save(new Category(request.getName()));

        return CategoryResponse.ofDomain(result);
    }

    @Override
    public CategoryResponse findCategory(String name) {
        return CategoryResponse.ofDomain(categoryJpaAdapter.findByName(name));
    }
}

