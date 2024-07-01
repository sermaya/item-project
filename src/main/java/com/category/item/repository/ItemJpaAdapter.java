package com.category.item.repository;

import com.category.item.domain.Category;
import com.category.item.domain.Item;

import java.util.List;

public interface ItemJpaAdapter {

    Item save(Item item);

    Item update(Long id, Item item);

    Item findById(Long itemId);

    void delete(Long itemId);

    Item findByBrandIdAndCategoryId(Long brandId, Long categoryId);

    List<Item> findByBrandId(Long brandId);

    List<Item> findByCategoryId(Long categoryId);
}
