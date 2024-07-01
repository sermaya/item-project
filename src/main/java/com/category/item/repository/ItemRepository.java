package com.category.item.repository;

import com.category.item.repository.jpaentity.ItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemJpaEntity, Long> {

    Optional<ItemJpaEntity> findItemByBrandIdAndCategoryId(Long brandId, Long categoryId);

    List<ItemJpaEntity> findItemByBrandId(Long id);

    List<ItemJpaEntity> findItemByCategoryId(Long id);
}
