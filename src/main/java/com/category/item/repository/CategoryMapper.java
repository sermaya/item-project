package com.category.item.repository;

import com.category.item.domain.Category;
import com.category.item.repository.jpaentity.CategoryJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    Category mapToDomainEntity(CategoryJpaEntity entity) {
        return new Category(entity.getId(), entity.getName());
    }
}
