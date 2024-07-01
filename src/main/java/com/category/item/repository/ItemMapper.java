package com.category.item.repository;

import com.category.item.domain.Brand;
import com.category.item.domain.Item;
import com.category.item.repository.jpaentity.BrandJpaEntity;
import com.category.item.repository.jpaentity.ItemJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    Item mapToDomainEntity(ItemJpaEntity entity) {
        return new Item(entity.getId(), entity.getBrandId(), entity.getCategoryId(), entity.getPrice());
    }
}
