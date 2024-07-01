package com.category.item.repository;

import com.category.item.domain.Brand;
import com.category.item.repository.jpaentity.BrandJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    Brand mapToDomainEntity(BrandJpaEntity entity) {
        return new Brand(entity.getId(), entity.getName());
    }
}
