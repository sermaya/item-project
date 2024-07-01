package com.category.item.repository;


import com.category.item.domain.Brand;
import com.category.item.repository.jpaentity.BrandJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandJpaEntity, Long> {

    Optional<BrandJpaEntity> findBrandByName(String name);
}
