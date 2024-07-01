package com.category.item.repository;

import com.category.item.controller.responsedto.BrandItemDto;
import com.category.item.domain.Brand;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BrandJpaAdapter {

    Brand save(Brand brand);

    Brand findById(Long id);

    Brand findByName(String name);

    Brand update(Long id, String name);

    void delete(Long id);

    List<Brand> findAll();

    Map<Long, String> findAllWithMap();
}
