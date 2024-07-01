package com.category.item.repository;

import com.category.item.domain.Brand;
import com.category.item.domain.Category;

import java.util.List;

public interface CategoryJpaAdapter {

    Category save(Category category);

    Category findById(Long id);

    Category findByName(String name);

    List<Category> findAll();
}
