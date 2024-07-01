package com.category.item.service;

import com.category.item.controller.requestdto.ItemRequest;
import com.category.item.controller.responsedto.*;
import com.category.item.domain.Brand;
import com.category.item.domain.Category;
import com.category.item.domain.Item;
import com.category.item.exception.ItemDuplicationException;
import com.category.item.repository.BrandJpaAdapter;
import com.category.item.repository.CategoryJpaAdapter;
import com.category.item.repository.ItemJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemJpaAdapter itemJpaAdapter;
    private final BrandJpaAdapter brandJpaAdapter;
    private final CategoryJpaAdapter categoryJpaAdapter;

    @Override
    public ItemResponse save(ItemRequest request) {

        Brand brand = brandJpaAdapter.findByName(request.getBrandName());
        Category category = categoryJpaAdapter.findByName(request.getCategoryName());

        Item findItem = itemJpaAdapter.findByBrandIdAndCategoryId(brand.getId(), category.getId());
        if (findItem != null) {
            throw new ItemDuplicationException();
        }

        Item result = itemJpaAdapter.save(new Item(brand.getId(), category.getId(), request.getPrice()));

        ItemResponse itemResponse = ItemResponse.ofDomain(result);
        itemResponse.setBrandName(brand.getName());
        itemResponse.setCategoryName(category.getName());

        return itemResponse;
    }

    @Override
    public ItemResponse update(Long id, ItemRequest request) {

        Item findItem = itemJpaAdapter.findById(id);
        
        Brand brand = brandJpaAdapter.findByName(request.getBrandName());
        Category category = categoryJpaAdapter.findByName(request.getCategoryName());
        
        // update
        findItem.setBrandId(brand.getId());
        findItem.setCategoryId(category.getId());
        findItem.setPrice(request.getPrice());
        Item result = itemJpaAdapter.update(id, findItem);

        ItemResponse itemResponse = ItemResponse.ofDomain(result);
        itemResponse.setBrandName(brand.getName());
        itemResponse.setCategoryName(category.getName());

        return itemResponse;
    }

    @Override
    public ItemResponse findById(Long id) {
        Item findItem = itemJpaAdapter.findById(id);

        Brand brand = brandJpaAdapter.findById(findItem.getBrandId());
        Category category = categoryJpaAdapter.findById(findItem.getCategoryId());

        ItemResponse itemResponse = ItemResponse.ofDomain(findItem);
        itemResponse.setBrandName(brand.getName());
        itemResponse.setCategoryName(category.getName());


        return itemResponse;
    }

    @Override
    public void delete(Long id) {
        itemJpaAdapter.delete(id);
    }
}
